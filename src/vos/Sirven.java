package vos;
import org.codehaus.jackson.annotate.JsonProperty;
public class Sirven {
	
	@JsonProperty("idProducto")
	private Long idProdu;
	@JsonProperty("idRestaurante")
	private Long idRest;
	
	public Sirven(@JsonProperty("idProducto") Long pId,@JsonProperty("idRestaurante") Long rId){
		super();
		idProdu = pId;
		idRest = rId;
	}

}
