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
import vos.Ingrediente;
import vos.Producto;

@Path("productos")
public class ProductosServices {

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
	public Response getProductos() {
		RotAndesTM tm = new RotAndesTM(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	
	@POST
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
	
	@POST
	@Path("{id : \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addIngrediente(@PathParam("id") Long idProd,Ingrediente ingrediente) {
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.addIngrediente(idProd, ingrediente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ingrediente).build();
	}
	@GET
	@Path("{quieroEsto/nombre : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPorNameCost(@PathParam("nombre")String nombre) {
		RotAndesTM tm = new RotAndesTM(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductosOrdenadosPorCOsto(nombre);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	@GET
	@Path("ordenarPrecio")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductosOrdenadosPorNombre() {
		RotAndesTM tm = new RotAndesTM(getPath());
		List<Producto> productos;
		try {
			productos = tm.ordenarPorNombre();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	
}
