package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class EquivalentesP {
	
	@JsonProperty(value="tipo")
	private String tipo;

	@JsonProperty(value="producto1")
	private Long producto1;
	
	@JsonProperty(value="producto2")
	private Long producto2;
	
	@JsonProperty(value="idRestaurante")
	private Long idRestaurante;
	
	public EquivalentesP (@JsonProperty(value="idRestaurante")Long pIdRest,@JsonProperty(value="tipo") String pTipo,@JsonProperty(value="producto1") Long pProdu1,@JsonProperty(value="producto2") Long pProdu2){
		super();
		this.idRestaurante = pIdRest;
		this.tipo = pTipo;
		this.producto1 = pProdu1;
		this.producto2 = pProdu2;
	}
	public Long getProdu1() {
		return producto1;
	}

	public void setProdu1(Long pProdu1) {
		this.producto1 = pProdu1;
	}

	public Long getProdu2() {
		return producto2;
	}
	public void setIdRest(Long pIdRest) {
		this.idRestaurante = pIdRest;
	}

	public Long getIdRest() {
		return idRestaurante;
	}

	public void setProdu2(Long pProdu2) {
		this.producto2 = pProdu2;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String pTipo) {
		this.tipo = pTipo;
	}


}
