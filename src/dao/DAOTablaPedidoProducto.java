package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.PedidoProducto;

public class DAOTablaPedidoProducto {

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
	public DAOTablaPedidoProducto() {
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


	/**
	 * Metodo que, usando la conexión a la base de datos, saca todos los pedidosPlato de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM PEDIDO_PLATO;
	 * @return Arraylist con los pedidosPlato de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<PedidoProducto> darPedidosPlato() throws SQLException, Exception {
		ArrayList<PedidoProducto> pedidosProductos = new ArrayList<PedidoProducto>();

		String sql = "SELECT * FROM PEDIDOPRODCTO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long numPedido = rs.getLong("NUMPEDIDO");
			Long idPlato = rs.getLong("IDPLATO");
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
			Long idPlato = rs.getLong("IDPLATO");
			pedidosProductos.add(new PedidoProducto(numPedido, idPlato));
		}
		return pedidosProductos;
	}
	
	public void addPedidoProducto(PedidoProducto pedidoPlato) throws SQLException, Exception {

		String sql = "INSERT INTO PEDIDOPRODUCTO VALUES (";
		sql += pedidoPlato.getNumPedido() + ",";
		sql += pedidoPlato.getIdPlato() + ")";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

}
