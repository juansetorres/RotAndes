package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Restaurante;
import vos.Zona;

public class DAOTablaZona {

	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;

	public DAOTablaZona(){
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
	public ArrayList<Zona> darZonas() throws SQLException, Exception {
		ArrayList<Zona> zonas = new ArrayList<Zona>();

		String sql = "SELECT * FROM ZONAS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			Integer capacidad = rs.getInt("CAPACIDAD");
			String name = rs.getString("NAME");
			zonas.add(new Zona(id, capacidad, name));
		}
		return zonas;
	}

	public Zona darZona(Long id)throws SQLException, Exception{
		Zona zona = null;

		String sql = "SELECT * FROM ZONAS WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String name = rs.getString("NAME");
			Long id2 = rs.getLong("ID");
			Integer capacidad = rs.getInt("CAPACIDAD");
			zona = new Zona(id2, capacidad, name);
		}

		return zona;
	}

	public void addZona(Zona zona)throws SQLException, Exception{
		String sql = "INSERT INTO ZONAS VALUES (";
		sql += zona.getId() + ",'";
		sql += zona.getName() + ",";
		sql += zona.getCap() + ",";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	public void updateZona(Zona zona)throws SQLException, Exception{
		String sql = "UPDATE ZONAS SET ";
		sql += "CAP='" + zona.getCap()+ "',";
		sql += "NAME=" + zona.getName();
		sql += " WHERE ID = " + zona.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void deleteZona(Zona zona)throws SQLException, Exception{
		String sql = "DELETE FROM ZONAS";
		sql += " WHERE ID = " + zona.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}