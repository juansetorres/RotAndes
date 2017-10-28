package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ProductoIngrediente;

public class DAOTablaProductoIngrediente {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOIngredientesPlato
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaProductoIngrediente() {
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


	public ArrayList<ProductoIngrediente> darIngredientes() throws SQLException, Exception {
		ArrayList<ProductoIngrediente> ingredientesPlato = new ArrayList<>();

		String sql = "SELECT * FROM PRODUCTO_ING";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idPlato = rs.getLong("ID_PRODUCTO");
			Long idIngrediente = rs.getLong("ID_ING");
			ingredientesPlato.add(new ProductoIngrediente(idPlato, idIngrediente));
		}
		return ingredientesPlato;
	}

	public ArrayList<ProductoIngrediente> buscarIngredientesPlatoPorIngrediente(Long idIngr) throws SQLException, Exception {
		ArrayList<ProductoIngrediente> ingredientesPlato = new ArrayList<>();

		String sql = "SELECT * FROM PRODUCTO_ING WHERE ID_ING ='" + idIngr + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idPlato = rs.getLong("ID_PRODUCTO");
			Long idIngrediente = rs.getLong("ID_ING");
			ingredientesPlato.add(new ProductoIngrediente(idPlato, idIngrediente));
		}

		return ingredientesPlato;
	}
	
	public ArrayList<ProductoIngrediente> buscarIngredientesPlatoPorId(Long idPlato) throws SQLException, Exception {
		ArrayList<ProductoIngrediente> ingredientesPlato = new ArrayList<ProductoIngrediente>();

		String sql = "SELECT * FROM PRODUCTO_ING WHERE ID_PRODUCTO =" + idPlato;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idPlato2 = rs.getLong("ID_PRODUCTO");
			Long idIngrediente = rs.getLong("ID_ING");
			ingredientesPlato.add(new ProductoIngrediente(idPlato2, idIngrediente));
		}
		return ingredientesPlato;
	}
	
	public ArrayList<ProductoIngrediente> buscarIngredientePlatoPorIdAndId(Long idPlato, Long idIngr) throws SQLException, Exception {
		ArrayList<ProductoIngrediente> ingredientesPlato = new ArrayList<>();

		String sql = "SELECT * FROM PRODUCTO_ING WHERE ID_PRODUCTO =" + idPlato
				+ " AND ID_ING = '" + idIngr + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idPlato2 = rs.getLong("ID_PRODUCTO");
			Long idIngrediente = rs.getLong("ID_ING");
			ingredientesPlato.add(new ProductoIngrediente(idPlato2, idIngrediente));
		}
		return ingredientesPlato;
	}
	

	/**
	 * Metodo que agrega el ingredientesPlato que entra como parametro a la base de datos.
	 * @param ingredientesPlato - el ingredientesPlato a agregar. video !=  null
	 * <b> post: </b> se ha agregado el ingredientesPlato a la base de datos en la transaction actual. pendiente que el ingredientesPlato master
	 * haga commit para que el ingredientesPlato baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el ingredientesPlato a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addIngredientesPlato(ProductoIngrediente ingredientePlato) throws SQLException, Exception {

		String sql = "INSERT INTO PRODUCTO_ING VALUES (";
		sql += ingredientePlato.getIdProducto() + ",'";
		sql += ingredientePlato.getIdIng() + "')";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	/**
	 * Metodo que elimina el ingredientesPlato que entra como parametro en la base de datos.
	 * @param ingredientesPlato - el ingredientesPlato a borrar. ingredientesPlato !=  null
	 * <b> post: </b> se ha borrado el ingredientesPlato en la base de datos en la transaction actual. pendiente que el ingredientesPlato master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el ingredientesPlato.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteIngredientesPlato(ProductoIngrediente ingredientePlato) throws SQLException, Exception {

		String sql = "DELETE FROM PRODUCTO_ING_PLATO";
		sql += " WHERE ID_PRODUCTO = " + ingredientePlato.getIdProducto();
		sql += " AND ID_ING = '" + ingredientePlato.getIdIng()+ "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
