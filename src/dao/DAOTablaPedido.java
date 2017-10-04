package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Pedido;

public class DAOTablaPedido {
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;
	
	public DAOTablaPedido(){
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
	public ArrayList<Pedido> darPedidos()throws SQLException, Exception{
		ArrayList<Pedido> pedidos = new ArrayList<>();
		String sql = "SELECT * FROM PEDIDOS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) {
			Long id = rs.getLong("ID");
			Integer fecha = rs.getInt("FECHA");
			Integer cantPerso = rs.getInt("CPERSONAS");
			Integer estado = rs.getInt("ESTADO");
			pedidos.add(new Pedido(id, fecha, cantPerso, estado));
		}
		
		return pedidos;
	}
	public Pedido darPedido(Long id)throws SQLException, Exception{
		Pedido pedido = null;
		String sql = "SELECT * FROM PEDIDOS WHERE ID ="+id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) {
			Long id2 = rs.getLong("ID");
			Integer fecha = rs.getInt("FECHA");
			Integer cantPerso = rs.getInt("CPERSONAS");
			Integer estado = rs.getInt("ESTADO");
			pedido = new Pedido(id2, fecha, cantPerso, estado);
		}
		
		return pedido;
	}
	public void addPedido(Pedido pedido)throws SQLException, Exception{
		String sql = "INSERT INTO PEDIDOS VALUES (";
		sql += "FECHA="+pedido.getFecha()+",";
		sql += "CPERSONAS="+pedido.getCantPersonas()+",";
		sql += "ESTADO" + pedido.getEstado()+")";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void upDatePedido(Pedido pedido)throws SQLException, Exception{
		String sql = "UPDATE PEDIDOS SET ";
		sql += "FECHA="+pedido.getFecha()+",";
		sql += "CPERSONAS="+pedido.getCantPersonas()+",";
		sql += "ESTADO" + pedido.getEstado();
		sql += "WHERE ID = "+pedido.getId();
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void deletePedido(Pedido pedido)throws SQLException, Exception{
		String sql = "DELETE FROM PEDIDOS SET WHERE ID ="+pedido.getId();
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	

}
