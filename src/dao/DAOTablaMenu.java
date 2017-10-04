package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import vos.Menu;

public class DAOTablaMenu {
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;
	
	public DAOTablaMenu(){
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
	public ArrayList<Menu> darMenus()throws SQLException, Exception{
		ArrayList<Menu> menus = new ArrayList<>();
		String sql = "SELECT * FROM MENUS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) {
			Long id = rs.getLong("ID");
			Integer costo = rs.getInt("COSTO");
			Integer precio = rs.getInt("PRECIO");
			menus.add(new Menu(id, costo, precio));
		}
		return menus;
	}
	public Menu darMenu(Long id)throws SQLException, Exception{
		 Menu menu = null;
		String sql = "SELECT * FROM MENUS WHERE ID="+id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) {
			Long id2 = rs.getLong("ID");
			Integer costo = rs.getInt("COSTO");
			Integer precio = rs.getInt("PRECIO");
			menu = new Menu(id2, costo, precio);
		}
		return menu;
	}
	public void addMenu(Menu menu)throws SQLException, Exception{
		String sql = "INSERT INTO MENUS VALUES (";
		sql += "COSTO="+menu.getCosto()+",";
		sql += "PRECIO" + menu.getPrecio()+")";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	public void upDateMenu(Menu menu)throws SQLException, Exception{
		String sql = "UPDATE MENUS SET ";
		sql += "COSTO="+menu.getCosto()+",";
		sql += "PRECIO" + menu.getPrecio();
		sql += "WHERE ID ="+ menu.getId();
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	public void deleteMenu(Menu menu)throws SQLException, Exception{
		String sql = "DELETE FROM MENUS SET ";
		sql += "WHERE ID ="+ menu.getId();
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	
	

}
