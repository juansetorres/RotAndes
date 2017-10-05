package vos;
import org.codehaus.jackson.annotate.*;

public class Prefieren {
	@JsonProperty("idUsuario")
	private Long idUsu;
	@JsonProperty("idRestaurante")
	private Long idRest;
	
	public Prefieren(@JsonProperty("idUsuario")Long pIdUsu,@JsonProperty("idRestaurante")Long pIdRest){
		idUsu=pIdUsu;
		idRest=pIdRest;
	}
	public Long getIdusu(){
		return idUsu;
	}
	public Long getIdRest(){
		return idRest;
	}
	public void setIdUsu(Long pIdUsu){
		idUsu = pIdUsu;
	}
	public void setIdRest(Long pIdRest){
		idRest = pIdRest;
	}
	

}
