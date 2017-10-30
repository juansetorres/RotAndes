package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Pedido;
import vos.PedidoMenu;


public class DAOTablaPedidoMenu {
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;
	public DAOTablaPedidoMenu(){
		recursos =new ArrayList<>();

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
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexi�n que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}
	public ArrayList<PedidoMenu> darPedidosMenu() throws SQLException, Exception {
		ArrayList<PedidoMenu> pedidosProductos = new ArrayList<PedidoMenu>();

		String sql = "SELECT * FROM PEDIDOMENU";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long numPedido = rs.getLong("NUMPEDIDO");
			Long idMenu = rs.getLong("IDMENU");
			pedidosProductos.add(new PedidoMenu(numPedido, idMenu));
		}
		return pedidosProductos;
	}

	public ArrayList<PedidoMenu> buscarPedidoMenuPorId(Long id) throws SQLException, Exception 
	{
		ArrayList<PedidoMenu>  pedidosProductos = new ArrayList<>();

		String sql = "SELECT * FROM PEDIDOMENU WHERE NUMPEDIDO =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long numPedido = rs.getLong("NUMPEDIDO");
			Long idMenu = rs.getLong("IDMENU");
			pedidosProductos.add(new PedidoMenu(numPedido, idMenu));
		}
		return pedidosProductos;
	}
	
	public void addPedidoMenu(PedidoMenu pedidoMenu) throws SQLException, Exception {

		String sql = "INSERT INTO PEDIDOMENU VALUES (";
		sql += pedidoMenu.getNumPedido() + ",";
		sql += pedidoMenu.getIdMenu() + ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	public void removerPedidos(Pedido pedido) throws SQLException, Exception {
		String sql = "DELETE FROM PEDIDOMENU WHERE NUMPEDIDO =" + pedido.getNumPedido();

		System.out.println("REMOVER PEDIDOS: " + sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
