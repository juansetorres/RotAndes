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
				String produ1 = rs.getString("PRODU1");
				String produ2 = rs.getString("PRODU2");
				String tipo = rs.getString("TIPO");
				equivalentes.add(new EquivalentesP(tipo, produ1,produ2));
			}
			return equivalentes;
		}
		public ArrayList<EquivalentesP> buscarEquivalentesPorProducto1(String produ1,String produ2) throws SQLException, Exception {
			ArrayList<EquivalentesP> equivalentes = new ArrayList<EquivalentesP>();

			String sql = "SELECT * FROM EQUIVALENTESP WHERE PRODU1 ='" + produ1 + "'and PRODU2 ='"+produ2+"'";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				String product1 = rs.getString("PRODU1");
				String product2 = rs.getString("PRODU2");
				String tipo = rs.getString("TIPO");
				equivalentes.add(new EquivalentesP(tipo, product1,product2));
			}

			return equivalentes;
		}
		public void addEquivalente(EquivalentesP equivalente) throws SQLException, Exception {

			String sql = "INSERT INTO EQUIVALENTESP VALUES ('";
			sql += equivalente.getTipo() + "','";
			sql += equivalente.getProdu1() + "',";
			sql += "'"+equivalente.getProdu2()+"')"; 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}

}
