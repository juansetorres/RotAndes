package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class EquivalentesP {
	
	@JsonProperty(value="tipo")
	private String tipo;

	@JsonProperty(value="producto1")
	private Long produ1;
	
	@JsonProperty(value="producto2")
	private Long produ2;
	
	@JsonProperty(value="idRestaurante")
	private Long idRest;
	
	public EquivalentesP (@JsonProperty(value="idRestaurante")Long pIdRest,@JsonProperty(value="tipo") String pTipo,@JsonProperty(value="producto1") Long pProdu1,@JsonProperty(value="producto2") Long pProdu2){
		super();
		this.idRest = pIdRest;
		this.tipo = pTipo;
		this.produ1 = pProdu1;
		this.produ2 = pProdu2;
	}
	public Long getProdu1() {
		return produ1;
	}

	public void setProdu1(Long pProdu1) {
		this.produ1 = pProdu1;
	}

	public Long getProdu2() {
		return produ2;
	}
	public void setIdRest(Long pIdRest) {
		this.idRest = pIdRest;
	}

	public Long getIdRest() {
		return idRest;
	}

	public void setProdu2(Long pProdu2) {
		this.produ2 = pProdu2;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String pTipo) {
		this.tipo = pTipo;
	}


}
