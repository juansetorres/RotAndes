package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Equivalentes {

	@JsonProperty(value="ingrediente")
	private Long ingrediente;

	@JsonProperty(value="equivalente")
	private Long equivalente;
	
	@JsonProperty(value="idRestaurante")
	private Long idRestaurante;

	public Equivalentes(@JsonProperty(value="ingrediente")Long ingrediente, @JsonProperty(value="equivalente")Long equivalente, @JsonProperty(value = "idRestaurante")Long idRestaurante) {
		super();
		this.ingrediente = ingrediente;
		this.equivalente = equivalente;
		this.idRestaurante = idRestaurante;
	}

	public Long getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Long ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Long getEquivalente() {
		return equivalente;
	}

	public void setEquivalente(Long equivalente) {
		this.equivalente = equivalente;
	}

	public Long getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}


}
