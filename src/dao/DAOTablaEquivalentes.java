package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Equivalentes;

public class DAOTablaEquivalentes {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOEquivalentes
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaEquivalentes() {
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
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public ArrayList<Equivalentes> darEquivalentes() throws SQLException, Exception {
		ArrayList<Equivalentes> equivalentes = new ArrayList<Equivalentes>();

		String sql = "SELECT * FROM EQUIVALENTES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long ingrediente = rs.getLong("INGREDIENTE");
			Long equivalente = rs.getLong("EQUIVALENTE");
			Long idRest = rs.getLong("IDRESTAURANTE");
			equivalentes.add(new Equivalentes(ingrediente, equivalente,idRest));
		}
		return equivalentes;
	}
	
	public ArrayList<Equivalentes> buscarEquivalentesPorNombre(Long ingred) throws SQLException, Exception {
		ArrayList<Equivalentes> equivalentes = new ArrayList<Equivalentes>();

		String sql = "SELECT * FROM EQUIVALENTES WHERE INGREDIENTE =" + ingred + "";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long ingrediente = rs.getLong("INGREDIENTE");
			Long equivalente = rs.getLong("EQUIVALENTE");
			Long idRest = rs.getLong("IDRESTAURANTE");
			equivalentes.add(new Equivalentes(ingrediente, equivalente,idRest));
		}

		return equivalentes;
	}
	
	public void addEquivalente(Equivalentes equivalente) throws SQLException, Exception {

		String sql = "INSERT INTO EQUIVALENTES VALUES (";
		sql += equivalente.getIngrediente() + ",'";
		sql += equivalente.getEquivalente() + ",";
		sql += equivalente.getIdRestaurante() + ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
}
