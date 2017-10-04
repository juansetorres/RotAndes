package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vos.Restaurante;


public class DAOTablaRestaurantes {
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	
	public DAOTablaRestaurantes(){
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
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}
	
	public ArrayList<Restaurante> darRestaurantes()throws SQLException, Exception{
		ArrayList<Restaurante> restaurantes = new ArrayList<>();
		
		String sql = "SELECT * FROM RESTAURANTES";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String paginaWeb = rs.getString("PAGINAWEB");
			Long id = rs.getLong("ID");
			String tipoComida = rs.getString("TIPOCOMIDA");
			
			restaurantes.add(new Restaurante(id, tipoComida, paginaWeb));
		}
		return restaurantes;
	}
	public Restaurante darRestaurante(Long id)throws SQLException, Exception{
		Restaurante video = null;

		String sql = "SELECT * FROM RESTAURANTES WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String tipoComida = rs.getString("TIPOCOMIDA");
			Long id2 = rs.getLong("ID");
			String web = rs.getString("PAGINAWEB");
			video = new Restaurante(id2,tipoComida,web);
		}

		return video;
	}
	
	public void addRestaurante(Restaurante restaurante)throws SQLException, Exception{
		String sql = "INSERT INTO RESTAURANTES VALUES (";
		sql += restaurante.getId() + ",'";
		sql += restaurante.getTipoComida()+"','";
		sql += restaurante.getPaginaWeb()+"')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	public void upDateRest(Restaurante restaurante)throws SQLException, Exception{
		String sql = "UPDATE RESTAURANTES SET ";
		sql += "TIPOCOMIDA='" + restaurante.getTipoComida() + "',";
		sql += "PAGINAWEB='" + restaurante.getPaginaWeb()+"'";
		sql += " WHERE ID = " + restaurante.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void deleteRest(Restaurante restaurante)throws SQLException, Exception{
		String sql = "DELETE FROM RESTAURANTES";
		sql += " WHERE ID = " + restaurante.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}


}
