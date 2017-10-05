package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.*;

public class Restaurante {
	//Atributos
	@JsonProperty(value="id")
	private Long idRest;
	@JsonProperty(value="name")
	private String name;


	@JsonProperty(value="tipoComida")
	private String tipoComida;
	@JsonProperty(value="paginaWeb")
	private String paginaWeb;

	private Menu menu;

	public Restaurante(@JsonProperty(value="id")Long idR, @JsonProperty(value="name") String pName, @JsonProperty(value="tipoComida") String tipoComidaR,@JsonProperty(value="paginaWeb")String paginaWebR){
		super();
		idRest = idR;
		tipoComida = tipoComidaR;
		paginaWeb = paginaWebR;
		name = pName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}



}
