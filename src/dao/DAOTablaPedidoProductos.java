package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Pedido;
import vos.PedidoProducto;


public class DAOTablaPedidoProductos {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOPedidoPlato
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaPedidoProductos() {
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

	public ArrayList<PedidoProducto> darPedidosPlato() throws SQLException, Exception {
		ArrayList<PedidoProducto> pedidosProductos = new ArrayList<PedidoProducto>();

		String sql = "SELECT * FROM PEDIDOPRODUCTO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long numPedido = rs.getLong("NUMPEDIDO");
			Long idPlato = rs.getLong("IDPRODUCTO");
			pedidosProductos.add(new PedidoProducto(numPedido, idPlato));
		}
		return pedidosProductos;
	}
	
	public ArrayList<PedidoProducto> bucarPedidoProductoPorIdPedido(Long id) throws SQLException, Exception 
	{
		ArrayList<PedidoProducto>  pedidosProductos = new ArrayList<>();

		String sql = "SELECT * FROM PEDIDOPRODUCTO WHERE NUMPEDIDO =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long numPedido = rs.getLong("NUMPEDIDO");
			Long idPlato = rs.getLong("IDPRODUCTO");
			pedidosProductos.add(new PedidoProducto(numPedido, idPlato));
		}
		return pedidosProductos;
	}
	
	public void addPedidoProducto(PedidoProducto pedidoPlato) throws SQLException, Exception {

		String sql = "INSERT INTO PEDIDOPRODUCTO VALUES (";
		sql += pedidoPlato.getNumPedido() + ",";
		sql += pedidoPlato.getIdProducto() + ")";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	public void upDatePedidoProducto(PedidoProducto pedido)throws SQLException, Exception{
		String sql = "UPDATE PEDIDOPRODUCTO SET ";
		sql += "IDPRODUCTO=" + pedido.getIdProducto();
		sql += " WHERE NUMPEDIDO = " + pedido.getNumPedido();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void deletePedidoProducto(PedidoProducto pedido)throws SQLException, Exception{
		String sql = "DELETE FROM PEDIDOPRODUCTO";
		sql += " WHERE IDPRODUCTO = " + pedido.getIdProducto();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void removerPedidos(Pedido pedido) throws SQLException, Exception {
		String sql = "DELETE FROM PEDIDOPRODUCTO WHERE NUMPEDIDO =" + pedido.getNumPedido();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
