package vos;
import org.codehaus.jackson.annotate.*;

public class Prefieren {
	
	@JsonProperty("idUsuario")
	private Long idUsuario;
	@JsonProperty("idRestaurante")
	private Long idRestaurante;
	
	public Prefieren(@JsonProperty("idUsuario")Long pIdUsu,@JsonProperty("idRestaurante")Long pIdRest){
		idUsuario=pIdUsu;
		idRestaurante=pIdRest;
	}
	public Long getIdusu(){
		return idUsuario;
	}
	public Long getIdRest(){
		return idRestaurante;
	}
	public void setIdUsu(Long pIdUsu){
		idUsuario = pIdUsu;
	}
	public void setIdRest(Long pIdRest){
		idRestaurante = pIdRest;
	}
	

}
