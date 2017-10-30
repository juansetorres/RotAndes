package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tm.RotAndesTM;
import vos.Equivalentes;
import vos.EquivalentesP;
import vos.Ingrediente;
import vos.Menu;
import vos.MenuProducto;
import vos.Producto;
import vos.Restaurante;
import vos.Ventas;

@Path("restaurantes")
public class RestaurantesService {

	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la conexion actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRestaurante() {
		RotAndesTM tm = new RotAndesTM(getPath());
		List<Restaurante> rest;
		try {
			rest = tm.darRestaurantes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rest).build();
	}
	
	@POST
	@Path( "{id: \\d+}/menus" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMenu(@PathParam( "id" ) Long id , Menu menu){
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.addMenu(menu, id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(menu).build();
	}
	
	@POST
	@Path( "{id: \\d+}/productos" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProducto(@PathParam( "id" ) Long id,Producto prod) {
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.addProducto(id,prod);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(prod).build();
	}

	@GET
	@Path( "{id: \\d+}" )
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRestaurante( @PathParam( "id" ) Long id ){
		RotAndesTM tm = new RotAndesTM( getPath( ) );
		try{
			Restaurante r = tm.buscarRestaurante(id);
			return Response.status( 200 ).entity( r ).build( );			
		}
		catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{id: \\d+}/productos/{idProd: \\d+}" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducto(@PathParam( "id" ) Long idRest, @PathParam( "idProd" ) Long idProd ) {
		RotAndesTM tm = new RotAndesTM( getPath( ) );
		try{
			Producto r = tm.buscarProductoPorId(idRest, idProd);
			return Response.status( 200 ).entity( r ).build( );			
		}
		catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{id: \\d+}/productos" )
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts(@PathParam( "id" ) Long idRest) {
		RotAndesTM tm = new RotAndesTM( getPath( ) );
		List<Producto> r;
		try{
			r = tm.darProductosPorRestaurante(idRest);
		}
		catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( r ).build( );	
	}
	
	@POST
	@Path("{id : \\d+}/ingredientes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addIngrediente(@PathParam("id") Long idRest,Ingrediente ingrediente) {
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.addIngrediente(idRest, ingrediente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ingrediente).build();
	}
	
	@PUT
	@Path( "{id: \\d+}/menuProducto" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProductoMenu( @PathParam( "id" ) Long idRest, MenuProducto prod){
		RotAndesTM tm = new RotAndesTM(getPath());
		Menu menu;
		try {
			tm.addProductoMenu(prod.getIdMenu(), prod.getIdProducto());
			menu = tm.buscarMenu(prod.getIdMenu());
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(menu).build();
	}
	
	@POST
	@Path( "{id: \\d+}/equivalentes" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEquivalenteIngrediente( Equivalentes equiv ) {
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.addEquivalenciaIngrediente(equiv);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(equiv).build();
	}
	
	@PUT
	@Path( "{id: \\d+}/surtir" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response surtirProductos(@PathParam( "id" ) Long idRest) {
		RotAndesTM tm = new RotAndesTM(getPath());
		List<Producto> rta;
		try {
			tm.surtirProductos(idRest);
			rta = tm.darProductosPorRestaurante(idRest);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rta).build();
	}
	@POST
	@Path( "{idRest: \\d+}/equivalenciaProducto" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEquivalentProduct(Long idRest,EquivalentesP equiv){
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.addEquivalenciaProducto(idRest,equiv);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(equiv).build();
	}
	@GET
	@Path( "{id: \\d+}/ventas" )
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVentas(@PathParam( "id" ) Long idRest) {
		RotAndesTM tm = new RotAndesTM( getPath( ) );
		List<Ventas> r;
		try{
			r = tm.darVentas();
		}
		catch( Exception e ){
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		return Response.status( 200 ).entity( r ).build( );	
	}
	@Path("{idRestaurante: \\d+}/pedido")
	public Class<PedidoService> realizarUnPedido(@PathParam( "idUsuario" ) Long id){
		return PedidoService.class;
	}
}
