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
import vos.Prefieren;
import vos.Usuario;


@Path("usuarios")
public class UsuariosServices {

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
	public Response getUsuarios() {
		RotAndesTM tm = new RotAndesTM(getPath());
		List<Usuario> ususarios;
		try {
			ususarios = tm.darUsuarios();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ususarios).build();
	}
	
	@POST
	@Path( "{id: \\d+}" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCliente(@PathParam( "id" ) Long id ,Usuario usu) {
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.addClient(usu,id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usu).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAdmin(Usuario usu) {
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.addUsuario(usu);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usu).build();
	}
	@POST
	@Path( "{id: \\d+/prefieren}" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPrefe(@PathParam( "id" ) Long id ,Prefieren prefieren) {
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.addPreferencia(id, prefieren);;
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(prefieren).build();
	}
	@PUT
	@Path( "{id: \\d+/prefieren}" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response upDatePrefieren(@PathParam( "id" ) Long id ,Prefieren prefieren) {
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.upDatePrefieren(id, prefieren);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(prefieren).build();
	}
	
	
}
