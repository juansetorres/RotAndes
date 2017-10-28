package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.MenuProducto;


public class DAOTablaMenuProductos {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOMenuPlato
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaMenuProductos() {
		recursos = new ArrayList<Object>();
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


	public ArrayList<MenuProducto> darMenuProducto() throws SQLException, Exception {
		ArrayList<MenuProducto> menusP = new ArrayList<MenuProducto>();

		String sql = "SELECT * FROM MENU_PROD";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idMenu = rs.getLong("ID_MENU");
			Long idPlato = rs.getLong("ID_PRODUCTO");
			menusP.add(new MenuProducto(idMenu, idPlato));
		}
		return menusP;
	}

	public MenuProducto buscarMenuPlatoPorIds(Long idMenu, Long idPlato) throws SQLException, Exception 
	{
		MenuProducto menuProd = null;

		String sql = "SELECT * FROM MENU_PROD WHERE ID_MENU =" + idMenu
				+ " AND ID_PLATO =" + idPlato;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long idMenu2 = rs.getLong("ID_MENU");
			Long idPlato2 = rs.getLong("ID_PRODUCTO");
			menuProd = new MenuProducto(idMenu2, idPlato2);
		}

		return menuProd;
	}
	
	public ArrayList<MenuProducto> buscarMenuPlatoPorIdPlato(int idPlato) throws SQLException, Exception 
	{
		ArrayList<MenuProducto> menusProd = new ArrayList<MenuProducto>();

		String sql = "SELECT * FROM MENU_PROD WHERE ID_PRODUCTO =" + idPlato;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idMenu = rs.getLong("ID_MENU");
			Long idPlato2 = rs.getLong("ID_PRODUCTO");
			menusProd.add(new MenuProducto(idMenu, idPlato2));
		}
		return menusProd;
	}
	
	public ArrayList<MenuProducto> buscarMenuPlatoPorIdMenu(Long idMenu) throws SQLException, Exception 
	{
		ArrayList<MenuProducto> menusProd = new ArrayList<MenuProducto>();

		String sql = "SELECT * FROM MENU_PROD WHERE ID_MENU =" + idMenu;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idMenu2 = rs.getLong("ID_MENU");
			Long idPlato2 = rs.getLong("ID_PRODUCTO");
			menusProd.add(new MenuProducto(idMenu2, idPlato2));
		}
		return menusProd;
	}

	public void addMenuPlato(MenuProducto menuProd) throws SQLException, Exception {

		String sql = "INSERT INTO MENU_PROD VALUES (";
		sql += menuProd.getIdMenu() + ",";
		sql += menuProd.getIdProducto()+ ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	

}