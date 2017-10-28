package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Ingrediente;



public class DAOTablaIngredientes {
	
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;
	
	public DAOTablaIngredientes(){
		recursos = new ArrayList<>();
	}
	/**
	 * Metodo que cierra todos los recursos que estan enel arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}


	/**
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexion que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	/**
	 * Metodo que, usando la conexion a la base de datos, saca todas las Zonas de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM VIDEOS;
	 * @return Arraylist con los videos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Ingrediente> darIngredientes() throws SQLException, Exception {
		ArrayList<Ingrediente> ingredi = new ArrayList<>();

		String sql = "SELECT * FROM INGREDIENTES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String descrip = rs.getString("DESCRIPCION");
			String name = rs.getString("NAME");
			ingredi.add(new Ingrediente(id, name, descrip));
		}
		return ingredi;
	}
	public Ingrediente darIngredientes(Long id)throws SQLException, Exception{
		Ingrediente ingrediente = null;

		String sql = "SELECT * FROM INGREDIENTES WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String name = rs.getString("NAME");
			Long id2 = rs.getLong("ID");
			String descrip = rs.getString("DESCRIPCION");
			ingrediente = new Ingrediente(id2, name, descrip);
		}

		return ingrediente;
	}
	public void addIngrediente(Ingrediente ingrediente)throws SQLException, Exception{
		String sql = "INSERT INTO INGREDIENTES VALUES (";
		sql += ingrediente.getId() + ",'";
		sql += ingrediente.getName()+"','";
		sql += ingrediente.getDescrip()+"')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	public void upDateIngre(Ingrediente ingrediente)throws SQLException, Exception{
		String sql = "UPDATE INGREDIENTES SET ";
		sql += "NAME='"+ ingrediente.getName()+"',";
		sql += "DESCRIPCION='"+ ingrediente.getDescrip()+"'";
		sql += "WHERE ID ="+ ingrediente.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void deleteIngrediente(Ingrediente ingrediente)throws SQLException, Exception{
		String sql = "DELETE FROM INGREDIENTES";
		sql += " WHERE ID = " + ingrediente.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
