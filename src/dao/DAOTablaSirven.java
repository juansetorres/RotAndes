package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Sirven;

public class DAOTablaSirven {
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;
	
	public DAOTablaSirven(){
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
	public ArrayList<Sirven> darSirven()throws SQLException, Exception{
		ArrayList<Sirven> sirven = new ArrayList<>();
		String sql = "SELECT * FROM SIRVEN";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) {
			Long idR = rs.getLong("IDRESTAURANTE");
			Long idP = rs.getLong("IDPRODUCTO");
			Long id = rs.getLong("ID");
			Long idM = rs.getLong("IDMENU");
			Integer cant = rs.getInt("CANTIDAD");
			sirven.add(new Sirven(idR, idP, cant,id,idM));
			
			
		}
		return sirven;
	}
	public Sirven darSirvenUni(Long idPro)throws SQLException, Exception{
		Sirven sirven = null;
		String sql = "SELECT * FROM SIRVEN WHERE IDPRODUCTO = "+idPro;
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		if (rs.next()) {
			Long idR = rs.getLong("IDRESTAURANTE");
			Long idP = rs.getLong("IDPRODUCTO");
			Long id = rs.getLong("ID");
			Long idM = rs.getLong("IDMENU");
			Integer cant = rs.getInt("CANTIDAD");
			sirven = new Sirven(idR, idP, cant,id,idM);
			
			
		}
		return sirven;
	}
	public void addSirven(Sirven sirven)throws SQLException, Exception{
		String sql = "INSERT INTO SIRVEN VALUES (";
		sql += sirven.getIdRestaurante();
		sql += sirven.getIdProducto();
		sql += sirven.darCantidad()+")";
		
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	public void upDateMenu(Sirven sirven)throws SQLException, Exception{
		String sql = "UPDATE SIRVEN SET ";
		sql += "CANTIDAD="+sirven.darCantidad();
		sql += "WHERE IDRESTAURANTE ="+ sirven.getIdRestaurante()+"and"+"WHERE IDPRODUCTO ="+ sirven.getIdProducto();
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	public void delete(Long idRest,Long idPro)throws SQLException, Exception{
		String sql = "DELETE FROM SIRVEN SET ";
		sql += "WHERE IDRESTAURANTE ="+ idRest+"and"+"WHERE IDPRODUCTO ="+ idPro;
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	public ArrayList<Sirven> darMasServidos()throws SQLException, Exception{
		ArrayList<Sirven> sirven = new ArrayList<>();
		String sql = "SELECT IDPRODUCTO,MAX(SELECT count(IDMENU) FROM SIRVEN group by IDPRODUCTO) as CANTMAX FROM SIRVEN group by IDPRODUCTO ";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) {
			Long idR = rs.getLong("IDRESTAURANTE");
			Long idP = rs.getLong("IDPRODUCTO");
			Long id = rs.getLong("ID");
			Long idM = rs.getLong("IDMENU");
			Integer cant = rs.getInt("CANTIDAD");
			sirven .add( new Sirven(idR, idP, cant,id,idM));
			
			
		}
		
		return sirven;
		
	}
	
	

}
