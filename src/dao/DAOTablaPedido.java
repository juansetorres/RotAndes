package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Pedido;
import vos.Zona;

public class DAOTablaPedido {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOPedido
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaPedido() {
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


	public ArrayList<Pedido> darPedidos() throws SQLException, Exception {
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

		String sql = "SELECT * FROM PEDIDO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long numPedido = rs.getLong("NUMPEDIDO");
			double precio = rs.getDouble("PRECIO");
			String fecha = rs.getString("FECHA");
			String emailUser = rs.getString("EMAILUSER");
			int pagado = rs.getInt("PAGADO");
			int entregado = rs.getInt("ENTREGADO");
			String hora = rs.getString("HORA");
			String cambio = rs.getString("CAMBIO");
			pedidos.add(new Pedido(numPedido, precio, fecha, emailUser, pagado, entregado, hora,cambio));
		}
		return pedidos;
	}
	
	/**
	 * Metodo que, usando la conexión a la base de datos, saca todos los pedidos de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM PEDIDOS;
	 * @return Arraylist con los pedidos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Pedido> darPedidosCliente(String emailCliente) throws SQLException, Exception {
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

		String sql = "SELECT * FROM PEDIDO WHERE EMAILUSER ='" + emailCliente + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long numPedido = rs.getLong("NUMPEDIDO");
			double precio = rs.getDouble("PRECIO");
			String fecha = rs.getString("FECHA");
			String emailUser = rs.getString("EMAILUSER");
			int pagado = rs.getInt("PAGADO");
			int entregado = rs.getInt("ENTREGADO");
			String hora = rs.getString("HORA");
			String cambio = rs.getString("CAMBIO");
			pedidos.add(new Pedido(numPedido, precio, fecha, emailUser, pagado, entregado, hora, cambio));
		}
		return pedidos;
	}
	public Pedido darPedidoId(Long idPedido) throws SQLException, Exception {
		Pedido pedidos =null;

		String sql = "SELECT * FROM PEDIDO WHERE NUMPEDIDO ='" + idPedido + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			Long numPedido = rs.getLong("NUMPEDIDO");
			double precio = rs.getDouble("PRECIO");
			String fecha = rs.getString("FECHA");
			String emailUser = rs.getString("EMAILUSER");
			int pagado = rs.getInt("PAGADO");
			int entregado = rs.getInt("ENTREGADO");
			String hora = rs.getString("HORA");
			String cambio = rs.getString("CAMBIO");
			pedidos =new Pedido(numPedido, precio, fecha, emailUser, pagado, entregado, hora, cambio);
		}
		return pedidos;
	}
	
	public void addPedido(Pedido pedido) throws SQLException, Exception {

		String sql = "INSERT INTO PEDIDO VALUES (";
		sql += pedido.getNumPedido() + ",";
		sql += pedido.getPrecio() + ",";
		sql += "TO_DATE('" + pedido.getFecha() + "', 'DD/MM/YYYY')"  + ",'";
		sql += pedido.getEmailUser() + "',";
		sql += pedido.getPagado() + ",";
		sql += pedido.getEntregado() + ",'";
		sql += pedido.getHora() + "','";
		sql += pedido.getCambios() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	public void upDatePedido(Pedido pedido)throws SQLException, Exception{
		String sql = "UPDATE PEDIDOS SET ";
		sql += "PAGADO ="+pedido.getPagado();
		sql += "ENTREGADO="+pedido.getEntregado();
		sql += "WHERE NUMPEDIDO ="+pedido.getNumPedido();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
