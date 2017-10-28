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
		ArrayList<String> productos = new ArrayList<>();
		
		DAOTablaProductos daoProd = new DAOTablaProductos();
		daoProd.setConn(conn);
		
		String sql = "SELECT * FROM MENUS";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			Integer costo = rs.getInt("COSTO");
			Integer precio = rs.getInt("PRECIO");
			String sqlAux = "SELECT * FROM MENU_PROD WHERE ID_MENU ="+id;
			PreparedStatement prepStmtAux = conn.prepareStatement(sqlAux);
			recursos.add(prepStmtAux);
			ResultSet rsAux = prepStmtAux.executeQuery();
			while (rsAux.next())
			{
				Long idProd = rsAux.getLong("ID_PRODUCTO");
				productos.add(daoProd.darProducto(idProd).getName());
			}
			menus.add(new Menu(id, costo, precio, productos));
		}
		return menus;
	}
	
	public Menu darMenu(Long id)throws SQLException, Exception{
		Menu menu = null;
		ArrayList<String> productos = new ArrayList<>();
		String sql = "SELECT * FROM MENUS WHERE ID="+id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		DAOTablaProductos daoProd = new DAOTablaProductos();
		daoProd.setConn(conn);

		while (rs.next()) {
			Long id2 = rs.getLong("ID");
			Integer costo = rs.getInt("COSTO");
			Integer precio = rs.getInt("PRECIO");
			
			String sqlAux = "SELECT * FROM MENU_PROD WHERE ID_MENU ="+id2;
			PreparedStatement prepStmtAux = conn.prepareStatement(sqlAux);
			recursos.add(prepStmtAux);
			ResultSet rsAux = prepStmtAux.executeQuery();
			while (rsAux.next())
			{
				Long idProd = rsAux.getLong("ID_PRODUCTO");
				
				productos.add(daoProd.darProducto(idProd).getName());
			} 
			menu = (new Menu(id2, costo, precio, productos));
		}
		return menu;
	}
	public void addMenu(Menu menu)throws SQLException, Exception{
		String sql = "INSERT INTO MENUS VALUES (";
		sql += menu.getIdMenu()+",";
		sql += menu.getCosto()+",";
		sql += menu.getPrecio()+")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	public void upDateMenu(Menu menu)throws SQLException, Exception{
		String sql = "UPDATE MENUS SET ";
		sql += "COSTO="+menu.getCosto()+",";
		sql += "PRECIO=" + menu.getPrecio();
		sql += "WHERE ID ="+ menu.getIdMenu();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	public void deleteMenu(Menu menu)throws SQLException, Exception{
		String sql = "DELETE FROM MENUS SET ";
		sql += "WHERE ID ="+ menu.getIdMenu();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}



}
