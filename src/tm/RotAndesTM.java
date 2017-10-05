package tm;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

import vos.*;
import dao.*;
import oracle.jdbc.dcn.DatabaseChangeRegistration;

public class RotAndesTM {
	/**
	 * Atributo estatico que contiene el path relativo del archivo que tiene los datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;
	
	/**
	 * conexion a la base de datos
	 */
	private Connection conn;
	
	
	public RotAndesTM(String contextPathP){
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que  retorna la conexion a la base de datos
	 * @return Connection - la conexion a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}
	////////////TRANSACCTIONS///////////
	
	public List<Usuario> darUsuarios() throws Exception{
		List<Usuario> usuarios;
		DAOTablaUsuario daoUsuarios = new DAOTablaUsuario();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			usuarios = daoUsuarios.darUsuarios();
		}
		catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return usuarios;
		
	}
	
	public Usuario buscarAdmin(Long id) throws Exception {
		Usuario usu;
		DAOTablaUsuario daoUsu = new DAOTablaUsuario();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsu.setConn(conn);
			usu = daoUsu.darUsuario(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsu.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return usu;
	}
	
	public List<Zona> darZonas() throws Exception{
		List<Zona> zonas;
		DAOTablaZona daoZonas = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.darZonas();
		}
		catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoZonas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return zonas;
		
	}
	public List<Producto> darProductos()throws Exception{
		List<Producto> productos;
		DAOTablaProductos daoProductos = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			productos = daoProductos.darProductos();
		}
		catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}
	
	public List<Restaurante> darRestaurantes()throws Exception{
		List<Restaurante> restaurantes;
		DAOTablaRestaurantes daoRestaurante = new DAOTablaRestaurantes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoRestaurante.setConn(conn);
			restaurantes = daoRestaurante.darRestaurantes();
		}
		catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoRestaurante.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return restaurantes;
	}
	
	public List<Menu> darMenus()throws Exception{
		List<Menu> menus;
		DAOTablaMenu daoMenu = new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenu.setConn(conn);
			menus = daoMenu.darMenus();
		}
		catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoMenu.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return menus;
	}
	
	public Restaurante buscarRestaurante(Long id) throws Exception {
		Restaurante rest;
		DAOTablaRestaurantes daoRest = new DAOTablaRestaurantes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoRest.setConn(conn);
			rest = daoRest.darRestaurante(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoRest.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return rest;
	}
	
	public void addRestaurante(Restaurante rest)throws Exception{
		DAOTablaRestaurantes daoRestaurante = new DAOTablaRestaurantes();
		if(darRestaurantes().contains(rest)){
			throw new Exception("ya existe el restaurante");
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoRestaurante.setConn(conn);
			daoRestaurante.addRestaurante(rest);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoRestaurante.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void addClient(Usuario cliente,Long id)throws Exception{
		DAOTablaUsuario daoUsuario = new DAOTablaUsuario();
		System.err.println(buscarAdmin(id).getRol());
		if(buscarAdmin(id).getRol() != Usuario.ADMIN) {
			throw new Exception("No es administrador");
		}
		if(cliente.getRol()!=Usuario.CLIENTE){
			throw new Exception("No esta añadiendo un cliente");
		}
		if(darUsuarios().contains(cliente)){
			throw new Exception("ya existe el cliente");
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			daoUsuario.addUsuario(cliente);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuario.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	public void addUsuario(Usuario pUsuario)throws Exception{
		DAOTablaUsuario daoUsuario = new DAOTablaUsuario();
		if(pUsuario.getRol()!=Usuario.ADMIN){
			throw new Exception("No esta añadiendo un usuario");
		}
		if(darUsuarios().contains(pUsuario)){
			throw new Exception("ya existe el usuario");
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			daoUsuario.addUsuario(pUsuario);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuario.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	public void addZona(Zona zona)throws Exception{
		DAOTablaZona daoZona = new DAOTablaZona();
		if(darZonas().contains(zona)){
			throw new Exception("ya existe la zona");
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZona.setConn(conn);
			daoZona.addZona(zona);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoZona.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void addProducto(Producto prod)throws Exception{
		DAOTablaProductos daoProd = new DAOTablaProductos();
		if(darProductos().contains(prod)){
			throw new Exception("ya existe el producto");
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProd.setConn(conn);
			daoProd.addProducto(prod);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProd.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void addIngrediente(Long id ,Ingrediente ingrediente)throws Exception{
		DAOTablaIngredientes daoIngrediente = new DAOTablaIngredientes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngrediente.setConn(conn);
			daoIngrediente.addIngrediente(ingrediente);
			conn.commit();
			buscarProductoPorId(id).darIngredientes().add(ingrediente);
			

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngrediente.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public Producto buscarProductoPorId(Long id) throws Exception {
		Producto producto;
		DAOTablaProductos daoProducto = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			producto = daoProducto.darProducto(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProducto.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return producto;
	}
	public List<Producto> ordenarPorNombre()throws Exception{
		List<Producto> productos;
		DAOTablaProductos daoProductos = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			productos = daoProductos.ordenerProductosPorNombre();
		}
		catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}
	public List<Producto> darProductosOrdenadosPorCOsto(String name)throws Exception{
		List<Producto> productos;
		DAOTablaProductos daoProductos = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			productos = daoProductos.darProductosPorNombreOrdenadosPorPrecio(name);
		}
		catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}
	
	public void addMenu(Menu menu, Long id)throws Exception{
		DAOTablaMenu daoMenu = new DAOTablaMenu();
		Restaurante rest = buscarRestaurante(id);
		if(darMenus().contains(menu)){
			throw new Exception("ya existe el Menu");
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenu.setConn(conn);
			rest.setMenu(menu);
			daoMenu.addMenu(menu);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoMenu.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void addProducaPedido(Long id,Long id2) throws Exception {
		Pedido pedido;
		DAOTablaPedido daoPedido = new DAOTablaPedido();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedido.setConn(conn);
			pedido = daoPedido.darPedido(id);
			pedido.getProd().add(buscarSirven(id2));

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPedido.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	public void addPedido(Pedido pedidos) throws Exception {
		DAOTablaPedido daoPedido = new DAOTablaPedido();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedido.setConn(conn);
			daoPedido.addPedido(pedidos);
			conn.commit();
			

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPedido.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	public Pedido buscarPedido(Long id) throws Exception {
		Pedido pedido;
		DAOTablaPedido daoPedido = new DAOTablaPedido();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedido.setConn(conn);
			pedido = daoPedido.darPedido(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPedido.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return pedido;
		
	}
	public void finalizaPedido(Pedido pedidos) throws Exception{
		DAOTablaPedido daoPedido = new DAOTablaPedido();
		DAOTablaSirven daosirven = new DAOTablaSirven();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedido.setConn(conn);
			daoPedido.upDatePedido(pedidos);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPedido.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daosirven.setConn(conn);
			for(Sirven sirven : pedidos.getProd()){
				sirven.setCant(sirven.darCantidad()-1);
				daosirven.upDateMenu(sirven);
				
			}
			
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPedido.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public Sirven buscarSirven(Long idPro)throws Exception{
		Sirven sirven;
		DAOTablaSirven daoSirven = new DAOTablaSirven();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoSirven.setConn(conn);
			sirven = daoSirven.darSirvenUni(idPro);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoSirven.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return sirven;
	}
	public ArrayList<Producto> darMasServido()throws Exception{
		ArrayList<Producto> producto = new ArrayList<>();
		ArrayList<Sirven> sirven = new ArrayList<>();
		DAOTablaSirven daoSirven = new DAOTablaSirven();
		DAOTablaProductos daoProducto = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoSirven.setConn(conn);
			sirven = daoSirven.darMasServidos();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoSirven.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			for(Sirven x : sirven){
				producto.add(buscarProductoPorId(x.getIdProducto()));
			}

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProducto.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return producto;
		
	}
	public void addPreferencia(Long id,Prefieren prefe)throws Exception{
		DAOTablaPrefieren daoPrefiren = new DAOTablaPrefieren();
		if(prefe.getIdusu()!=id){
			throw new Exception("no puedes postear la preferencia de otro cliente");
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPrefiren.setConn(conn);
			daoPrefiren.addPrefieren(prefe);
			conn.commit();
			

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPrefiren.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void upDatePrefieren(Long id,Prefieren prefieren) throws Exception {
		DAOTablaPrefieren daoPrefieren = new DAOTablaPrefieren();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPrefieren.setConn(conn);
			daoPrefieren.upDatePedido(prefieren);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPrefieren.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	

}
