package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.RotAndesTM;
import vos.Menu;
import vos.Producto;
import vos.Restaurante;

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
	public Response addProducto(Producto prod) {
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.addProducto(prod);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(prod).build();
	}

	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
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
	
	
}
