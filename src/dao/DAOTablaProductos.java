package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Producto;



public class DAOTablaProductos {
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;
	
	
	public DAOTablaProductos(){
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
	public ArrayList<Producto> darProductos()throws SQLException, Exception{
		ArrayList<Producto> productos = new ArrayList<>();
		String sql = "SELECT * FROM PRODUCTOS";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NAME");
			Long id = rs.getLong("ID");
			String descrip = rs.getString("DESCRIPCION");
			Integer costo = rs.getInt("COSTO");
			Integer precio = rs.getInt("PRECIO");
			
			productos.add(new Producto(id,name,descrip,costo,precio));
		}
		return productos;
	}
	public Producto darProducto(Long id)throws SQLException, Exception{
		Producto producto = null;

		String sql = "SELECT * FROM PRODUCTOS WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String name = rs.getString("NAME");
			Long id2 = rs.getLong("ID");
			String descrip = rs.getString("DESCRIPCION");
			Integer costo = rs.getInt("COSTO");
			Integer precio = rs.getInt("PRECIO");
			producto = new Producto(id2,name,descrip,costo,precio);
		}

		return producto;
	}
	
	public void addProducto(Producto Rproducto)throws SQLException, Exception{
		String sql = "INSERT INTO PRODUCTOS VALUES (";
		sql += Rproducto.getId() + ",'";
		sql += Rproducto.getName()+"','";
		sql += Rproducto.getDescrip()+"',";
		sql += Rproducto.getCosto()+",";
		sql += Rproducto.getPrecio()+")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	public void upDateProdu(Producto producto)throws SQLException, Exception{
		String sql = "UPDATE PRODUCTOS SET ";
		sql += "NAME='" + producto.getName() + "',";
		sql += "DESCRIPCION='" + producto.getDescrip()+ "',";
		sql += "COSTO=" + producto.getCosto()+ ",";
		sql += "PRECIO=" + producto.getPrecio();
		sql += " WHERE ID = " + producto.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void deleteProdu(Producto producto)throws SQLException, Exception{
		String sql = "DELETE FROM PRODUCTOS";
		sql += " WHERE ID = " + producto.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
