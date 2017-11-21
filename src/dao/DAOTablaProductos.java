package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vos.*;

public class DAOTablaProductos {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;


	public DAOTablaProductos() {
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
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}

	public List<Producto> darProductosPorRestaurante(Long idRestaurante) throws SQLException, Exception {
		ArrayList<Producto> productos = new ArrayList<>();

		String sql = "SELECT * FROM PRODUCTOS WHERE IDRESTAURANTE = '" +idRestaurante +"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String name = rs.getString("NAME");
			String descrip = rs.getString("DESCRIPCION");
			double precio = rs.getDouble("PRECIO");
			double costo = rs.getDouble("COSTO");
			int disponibilidad = rs.getInt("DISPONIBILIDAD");
			Integer cantidadMaxima = rs.getInt("CANTIDADMAXIMA");
			Long rest = rs.getLong("IDRESTAURANTE");
			productos.add(new Producto(id, name, descrip, costo, precio, disponibilidad, rest, cantidadMaxima));
		}
		return productos;
	}

	public List<Producto> darProductos() throws SQLException, Exception {
		ArrayList<Producto> productos = new ArrayList<>();

		String sql = "SELECT * FROM PRODUCTOS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String name = rs.getString("NAME");
			String descrip = rs.getString("DESCRIPCION");
			double precio = rs.getDouble("PRECIO");
			double costo = rs.getDouble("COSTO");
			int disponibilidad = rs.getInt("DISPONIBILIDAD");
			Integer cantidadMaxima = rs.getInt("CANTIDADMAXIMA");
			Long rest = rs.getLong("IDRESTAURANTE");
			productos.add(new Producto(id, name, descrip, costo, precio, disponibilidad, rest, cantidadMaxima));
		}
		return productos;
	}

	public Producto darProductoId(Long idRest, Long idProd) throws SQLException, Exception {
		Producto rta = null;



		String sql = "SELECT * FROM PRODUCTOS WHERE ID = " + idProd + " AND IDRESTAURANTE LIKE " + idRest + "";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while(rs.next()) {
			Long id2 = rs.getLong("ID");
			String name = rs.getString("NAME");
			String descrip = rs.getString("DESCRIPCION");
			double precio = rs.getDouble("PRECIO");
			double costo = rs.getDouble("COSTO");
			int disponibilidad = rs.getInt("DISPONIBILIDAD");

			Integer cantidadMaxima = rs.getInt("CANTIDADMAXIMA");
			Long rest = rs.getLong("IDRESTAURANTE");
			rta = new Producto(id2, name, descrip, costo, precio, disponibilidad, rest, cantidadMaxima);
		}
		return rta;
	}

	public Producto darProducto(Long idProd) throws SQLException, Exception {
		Producto rta = null;


		String sql = "SELECT * FROM PRODUCTOS WHERE ID = " + idProd;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while(rs.next()) {
			Long id2 = rs.getLong("ID");
			String name = rs.getString("NAME");
			String descrip = rs.getString("DESCRIPCION");
			double precio = rs.getDouble("PRECIO");
			double costo = rs.getDouble("COSTO");
			int disponibilidad = rs.getInt("DISPONIBILIDAD");
			Integer cantidadMaxima = rs.getInt("CANTIDADMAXIMA");
			Long rest = rs.getLong("IDRESTAURANTE");

			rta = new Producto(id2, name, descrip, costo, precio, disponibilidad, rest, cantidadMaxima);
		}
		return rta;
	}


	public void addProductoRestaurante(Long idRest,Producto prodRest) throws SQLException, Exception {


		String sql = "INSERT INTO PRODUCTOS VALUES (";
		sql += prodRest.getId() + ", ";
		sql += "'"+prodRest.getName() + "',";
		sql += "'"+prodRest.getDescripcion() + "',";
		sql += prodRest.getCosto() + ", ";
		sql += prodRest.getPrecio() +", ";
		sql += prodRest.getDisponibilidad() + ", ";
		sql += "" + idRest + ", ";
		sql += prodRest.getCantidadMaxima() + ")";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void updateProductoRestaurante(Long idRest ,Producto prodRest) throws SQLException, Exception {

		String sql = "UPDATE PRODUCTOS SET ";
		sql += "NAME ='" + prodRest.getName()+"'";
		sql += "DESCRIPCION ='" + prodRest.getDescripcion() + "'";
		sql += "PRECIO = " + prodRest.getPrecio();
		sql += ", COSTO = "+ prodRest.getCosto();
		sql += ", DISPONIBILIDAD = " + prodRest.getDisponibilidad();
		sql += ", CANTIDADMAXIMA = " + prodRest.getCantidadMaxima();

		sql += " WHERE ID= " + prodRest.getId()+ " AND IDRESTAURANTE LIKE " + 
				idRest+"";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();	
	}

	public void deleteProductoRestaurante(Long idRest ,Producto prodRest) throws SQLException, Exception {


		String sql = "DELETE FROM PRODUCTOS";
		sql += " WHERE ID = " + prodRest.getId(); 
		sql += " AND IDRESTAURANTE LIKE " + idRest + "";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void surtirProductos(Long idRest) throws SQLException, Exception {


		String sql = "UPDATE PRODUCTOS SET DISPONIBILIDAD = CANTIDADMAXIMA  WHERE IDRESTAURANTE LIKE " + idRest + "";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();	
	}

	public List<Producto> darPlatosCliente(Usuario cliente) throws SQLException, Exception {
		List<Producto> platos = new ArrayList<>();
		String sql = "SELECT * FROM PRODUCTOS WHERE ID "
				+ "IN (SELECT IDPRODUCTO FROM (PEDIDOPRODUCTO NATURAL JOIN PEDIDOS) "
				+ "WHERE CORREO = '" + cliente.getId() +"') OR ID IN "
				+ "(SELECT IDPRODUCTO FROM MENUPROD WHERE IDMENU IN "
				+ "(SELECT IDMENU FROM PEDIDOMENU NATURAL JOIN PEDIDOS "
				+ "WHERE IDUSUARIO = '" + cliente.getId() +"' ))";
		System.out.println("BUSQUEDA C7: \n" + sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String nombre2 = rs.getString("NAME");
			String descripcion = rs.getString("DESCRIPCION");
			double costo = rs.getFloat("COSTO");
			double precio= rs.getFloat("PRECIO");
			int disponibilidad = rs.getInt("DISPONIBILIDAD");
			Long rest = rs.getLong("IDRESTAURANTE");
			int cantidadMaxima = rs.getInt("CANTIDADMAXIMA");
			platos.add(new Producto(id, nombre2, descripcion, costo, precio, disponibilidad, rest, cantidadMaxima));
		}
		return platos;
	}
}
