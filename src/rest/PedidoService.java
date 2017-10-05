package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.RotAndesTM;
import vos.Pedido;

@Path("pedidos")
public class PedidoService {
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
	@PUT
	@Path("{idPedido : \\d+/productos/idProducto : \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	private Response ponerPedido(@PathParam("idPedido") Long idPedido,@PathParam("idProducto")Long idProducto){
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.addProducaPedido(idPedido, idProducto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(idPedido).build();
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	private Response addPedido(Pedido pedidos){
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.addPedido(pedidos);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pedidos).build();
	}
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	private Response  finalizarPedido(Pedido pedidos){
		RotAndesTM tm = new RotAndesTM(getPath());
		try {
			tm.finalizaPedido(pedidos);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pedidos).build();
		
	}
	

}
