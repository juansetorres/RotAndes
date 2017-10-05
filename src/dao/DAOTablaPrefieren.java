package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Pedido;
import vos.Prefieren;

public class DAOTablaPrefieren {
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;
	public DAOTablaPrefieren(){
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
	public ArrayList<Prefieren> darPedidos()throws SQLException, Exception{
		ArrayList<Prefieren> prefieren = new ArrayList<>();
		String sql = "SELECT * FROM PREFIEREN";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) {
			Long idU = rs.getLong("IDUSUARIO");
			Long idR = rs.getLong("IDRESTAURANTE");
			
			prefieren.add(new Prefieren(idU,idR));
		}
		
		return prefieren;
	}
	public ArrayList<Prefieren> darPedidos(Long id)throws SQLException, Exception{
		ArrayList<Prefieren> prefieren = new ArrayList<>();
		String sql = "SELECT * FROM PREFIEREN WHERE IDUSUARIO = "+id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		if (rs.next()) {
			Long idU = rs.getLong("IDUSUARIO");
			Long idR = rs.getLong("IDRESTAURANTE");
			
			prefieren.add(new Prefieren(idU,idR));
		}
		
		return prefieren;
	}
	public void addPrefieren(Prefieren prefieren)throws SQLException, Exception{
		String sql = "INSERT INTO PREFIEREN VALUES (";
		sql += "IDUSUARIO ="+prefieren.getIdusu()+",";
		sql += "IDRESTAURANTE ="+prefieren.getIdRest()+")"; 
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void upDatePedido(Prefieren prefieren)throws SQLException, Exception{
		String sql = "UPDATE PREFIEREN SET ";
		sql += "IDRESTAURANTE="+prefieren.getIdRest();
		sql += "WHERE IDUSUARIO ="+prefieren.getIdusu();
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	

}
