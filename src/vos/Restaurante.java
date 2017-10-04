package vos;

import org.codehaus.jackson.annotate.*;

public class Restaurante {
	//Atributos
		@JsonProperty(value="id")
		private Long idRest;
		@JsonProperty(value="tipocomida")
		private String tipoComida;
		@JsonProperty(value="paginaweb")
		private String paginaWeb;
	
	
	public Restaurante(@JsonProperty(value="id")Long idR,@JsonProperty(value="tipocomida") String tipoComidaR,@JsonProperty(value="paginaweb")String paginaWebR){
		super();
		idRest = idR;
		tipoComida = tipoComidaR;
		paginaWeb = paginaWebR;
	}
	
	public String getTipoComida(){
		return tipoComida;
	}
	public String getPaginaWeb(){
		return paginaWeb;
	}
	public Long getId(){
		return idRest;
	}
	
	public void setTipoComida(String pTtip){
		tipoComida = pTtip;
	}
	public void setPaginaWeb(String pWeb){
		paginaWeb = pWeb;
	}
	public void setId(Long pId){
		idRest = pId;
	}
	
	

}
