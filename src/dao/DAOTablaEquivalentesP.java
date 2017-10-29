package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Equivalentes;
import vos.EquivalentesP;

public class DAOTablaEquivalentesP {
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
	 */public DAOTablaEquivalentesP(){
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
		public ArrayList<EquivalentesP> darProductosEquivalentes() throws SQLException{
			ArrayList<EquivalentesP> equivalentes = new ArrayList<>();

			String sql = "SELECT * FROM EQUIVALENTESP";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Long product1 = rs.getLong("PRODU1");
				Long idRest = rs.getLong("IDRESTAURANTE");
				Long product2 = rs.getLong("PRODU2");
				String tipo = rs.getString("TIPO");
				equivalentes.add(new EquivalentesP(idRest,tipo, product1,product2));
			}
			return equivalentes;
		}
		public ArrayList<EquivalentesP> buscarEquivalentesPorProducto1(Long produ1) throws SQLException, Exception {
			ArrayList<EquivalentesP> equivalentes = new ArrayList<EquivalentesP>();

			String sql = "SELECT * FROM EQUIVALENTESP WHERE PRODU1 ='" + produ1 +"'";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Long product1 = rs.getLong("PRODU1");
				Long idRest = rs.getLong("IDRESTAURANTE");
				Long product2 = rs.getLong("PRODU2");
				String tipo = rs.getString("TIPO");
				equivalentes.add(new EquivalentesP(idRest,tipo, product1,product2));
			}

			return equivalentes;
		}
		public void addEquivalente(EquivalentesP equivalente) throws SQLException, Exception {

			String sql = "INSERT INTO EQUIVALENTESP VALUES (";
			sql += equivalente.getIdRest() + ",'";
			sql += equivalente.getTipo() + "','";
			sql += equivalente.getProdu1() + "',";
			sql += "'"+equivalente.getProdu2()+"')"; 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}

}
