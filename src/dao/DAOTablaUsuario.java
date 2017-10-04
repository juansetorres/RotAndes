package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Usuario;


public class DAOTablaUsuario {
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;
	
	
	public DAOTablaUsuario(){
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
	public ArrayList<Usuario> darUsuarios()throws SQLException, Exception{
		ArrayList<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM USUARIOS";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NAME");
			Long id = rs.getLong("ID");
			String correo = rs.getString("CORREO");
			Integer rol = rs.getInt("ROL");
			
			usuarios.add(new Usuario(id,correo,name,rol));
		}
		return usuarios;
	}
	public Usuario darUsuario(Long id)throws SQLException, Exception{
		Usuario usuario = null;

		String sql = "SELECT * FROM USUARIOS WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String name = rs.getString("NAME");
			Long id2 = rs.getLong("ID");
			String correo = rs.getString("CORREO");
			Integer rol = rs.getInt("ROL");
			usuario = new Usuario(id2,correo,name,rol);
		}

		return usuario;
	}
	
	public void addUsuario(Usuario Rusuario)throws SQLException, Exception{
		String sql = "INSERT INTO USUARIOS VALUES (";
		sql += Rusuario.getId() + ",'";
		sql += Rusuario.getCorreo()+",";
		sql += Rusuario.getName()+",";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	public void upDateUsu(Usuario usuario)throws SQLException, Exception{
		String sql = "UPDATE USUARIOS SET ";
		sql += "NAME='" + usuario.getName() + "',";
		sql += "CORREO=" + usuario.getCorreo()+"',";
		sql += "ROL="+ usuario.getRol();
		sql += " WHERE ID = " + usuario.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void deleteRest(Usuario usuario)throws SQLException, Exception{
		String sql = "DELETE FROM Usuarios";
		sql += " WHERE ID = " + usuario.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	


}
