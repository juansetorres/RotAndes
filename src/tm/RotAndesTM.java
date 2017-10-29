package tm;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import vos.*;
import dao.*;

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
		DAOTablaUsuarios daoUsuarios = new DAOTablaUsuarios();
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
		DAOTablaUsuarios daoUsu = new DAOTablaUsuarios();
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
	
	public List<Producto> darProductosPorRestaurante(Long id)throws Exception{
		List<Producto> productos;
		DAOTablaProductos daoProductos = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			productos = daoProductos.darProductosPorRestaurante(buscarRestaurante(id).getName());
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
	
	public Menu buscarMenu(Long id) throws Exception {
		Menu menu;
		DAOTablaMenu daoMenu = new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenu.setConn(conn);
			menu = daoMenu.darMenu(id);

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
		return menu;
	}
	
	public Producto buscarProductoPorId(Long idRest,Long idProd) throws Exception {
		Producto producto;
		DAOTablaProductos daoProducto = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			producto = daoProducto.darProductoId(idRest, idProd);
	
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
	
	public Ingrediente buscarIngrdientePorNombre(String name) throws Exception {
		Ingrediente ingrediente;
		DAOTablaIngredientes daoIng = new DAOTablaIngredientes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIng.setConn(conn);
			ingrediente = daoIng.darIngredienteNombre(name);
	
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
				daoIng.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ingrediente;
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
		DAOTablaUsuarios daoUsuario = new DAOTablaUsuarios();
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
		DAOTablaUsuarios daoUsuario = new DAOTablaUsuarios();
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
	
	public void addProducto(Long idRestauratne,Producto prod )throws Exception{
		DAOTablaProductos daoProd = new DAOTablaProductos();
		if(darProductosPorRestaurante(idRestauratne).contains(prod)){
			throw new Exception("Ya existe el producto.");
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProd.setConn(conn);
			daoProd.addProductoRestaurante(idRestauratne,prod);
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
	public void addIngrediente(Long idProd ,Ingrediente ingrediente)throws Exception{
		DAOTablaIngredientes daoIngrediente = new DAOTablaIngredientes();
		if(daoIngrediente.darIngredientes().contains(ingrediente)) {
			throw new Exception("Ya existe el ingrediente.");
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngrediente.setConn(conn);
			daoIngrediente.addIngrediente(ingrediente);
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
	public void addMenu(Menu menu, Long id)throws Exception{
		DAOTablaMenu daoMenu = new DAOTablaMenu();
		if(darMenus().contains(menu)){
			throw new Exception("ya existe el Menu");
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenu.setConn(conn);
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
	public void addProductoMenu(Long idMenu, Long idPlato) throws Exception {
		DAOTablaMenuProductos daoMenuPlato = new DAOTablaMenuProductos();
		if(darMenus().contains(buscarMenu(idMenu))) {
			throw new Exception("No existe el menu");
		}
		try 
		{
			this.conn = darConexion();
			daoMenuPlato.setConn(conn);
			MenuProducto menuPlato = new MenuProducto(idMenu, idPlato);
			daoMenuPlato.addMenuPlato(menuPlato);
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
				daoMenuPlato.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	
	public void addPedidoProducto(Pedido pedido, Long idProd) throws Exception {
		DAOTablaPedido daoPedido = new DAOTablaPedido();
		DAOTablaPedidoProductos daoPedidoPlato = new DAOTablaPedidoProductos();
		DAOTablaProductos daoPlato = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedido.setConn(conn);
			daoPedidoPlato.setConn(conn);
			daoPlato.setConn(conn);
			if (daoPlato.darProducto(idProd).getDisponibilidad() < 1)
				throw new Exception("No hay disponibilidad del plato " + daoPlato.darProducto(idProd).getName());
			daoPedido.addPedido(pedido);
			PedidoProducto pedidoPlato = new PedidoProducto(pedido.getNumPedido(), idProd);
			daoPedidoPlato.addPedidoProducto(pedidoPlato);
			
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
				daoPedidoPlato.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void addPreferencia(Long id,Prefieren prefe)throws Exception{
		DAOTablaPrefieren daoPrefiren = new DAOTablaPrefieren();
		System.out.println(id + "            "+ prefe.getIdusu() );
		if( !prefe.getIdusu().equals(id) ){
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
	
	public void addEquivalenciaIngrediente(Equivalentes equiv) throws Exception {
		DAOTablaEquivalentes daoEquiv = new DAOTablaEquivalentes();
		if(buscarIngrdientePorNombre(equiv.getNomIngrediente()) ==null) {
			throw new Exception("No exsite el ingrediente 1");
		}
		if(buscarIngrdientePorNombre(equiv.getEquivalente()) == null) {
			throw new Exception("No exsite el equivalente");
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEquiv.setConn(conn);
			daoEquiv.addEquivalente(equiv);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoEquiv.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void surtirProductos(Long idRest) throws Exception {
		DAOTablaProductos daoProd = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProd.setConn(conn);
			daoProd.surtirProductos(idRest);
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
	
	public Producto darProducto(Long idProdu)throws Exception{
		
		Producto producto;
		DAOTablaProductos daoProducto = new DAOTablaProductos();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			producto = daoProducto.darProducto(idProdu);
	
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
	public void addEquivalenciaProducto(Long idRest,EquivalentesP equiv)throws Exception{
		DAOTablaEquivalentesP daoEquiv = new DAOTablaEquivalentesP();
		if(darProducto(equiv.getProdu1()) ==null) {
			throw new Exception("No exsite el producto a comparar 1");
		}
		if(darProducto(equiv.getProdu2()) == null) {
			throw new Exception("No exsite el producto");
		}
		if(equiv.getIdRest()==null){
			equiv.setIdRest(idRest);
		}
		if(equiv.getIdRest()!=idRest){
			throw new Exception("No tienes el permiso de hacer esta operacion");
		}
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEquiv.setConn(conn);
			daoEquiv.addEquivalente(equiv);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoEquiv.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void registrarPedidoEquiv(PedidoProducto pedido)throws Exception{
		DAOTablaEquivalentesP daoEquiv = new DAOTablaEquivalentesP();
		DAOTablaPedidoProductos daoPedidoPro=new DAOTablaPedidoProductos();
		try{
			this.conn = darConexion();
			daoPedidoPro.setConn(conn);
			ArrayList<PedidoProducto> aCambiar = daoPedidoPro.bucarPedidoProductoPorIdPedido(pedido.getNumPedido());
			daoEquiv.setConn(conn);
			for(PedidoProducto p : aCambiar){
				ArrayList<EquivalentesP> aCambiar2 = daoEquiv.buscarEquivalentesPorProducto1(p.getIdProducto());
				for (EquivalentesP equivalentesP : aCambiar2) {
					if(equivalentesP.getProdu2()==pedido.getIdProducto()){
						daoPedidoPro.upDatePedidoProducto(pedido);
					}
					
					
				}
			}
			conn.commit();
			if(daoPedidoPro.bucarPedidoProductoPorIdPedido(pedido.getIdProducto())==null){
				throw new Exception("No existe el producto por el que se pueda cambiar el productoEquivaletne");
			}
				
		}
		 catch (SQLException e) {
			 conn.rollback();
			 System.err.println("SQLException:" + e.getMessage());
			 e.printStackTrace();
			 throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally{
			try {
				daoPedidoPro.cerrarRecursos();
				daoEquiv.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
			
			
		}
	}
	public List<Pedido> darPedidos()throws Exception{
		List<Pedido> pedidos;
		DAOTablaPedido daoPedido = new DAOTablaPedido();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedido.setConn(conn);
			pedidos = daoPedido.darPedidos();
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
				daoPedido.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return pedidos;
	}
	public void pagarPedido(PedidoProducto pedido)throws Exception{
		DAOTablaPedido daoPedido = new DAOTablaPedido();
		DAOTablaPedidoProductos daoPedidoPro=new DAOTablaPedidoProductos();
		DAOTablaProductos daoProducto = new DAOTablaProductos();
		try{
			this.conn = darConexion();
			daoPedido .setConn(conn);
			Pedido a = daoPedido.darPedidoId(pedido.getNumPedido());
			daoProducto.setConn(conn);
			Producto b = daoProducto.darProducto(pedido.getIdProducto());
			a.setPagado(a.getPagado()+(int)(b.getPrecio()/1));
			if(a.getPrecio()==a.getPagado()){
				a.setEntregado(1);
			}
			daoPedido.upDatePedido(a);
			daoPedidoPro.setConn(conn);
			daoPedidoPro.daeletePedidoProducto(pedido);
			
			conn.commit();
				
		}
		 catch (SQLException e) {
			 conn.rollback();
			 System.err.println("SQLException:" + e.getMessage());
			 e.printStackTrace();
			 throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally{
			try {
				daoPedidoPro.cerrarRecursos();
				daoPedido.cerrarRecursos();
				daoProducto.cerrarRecursos();
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
