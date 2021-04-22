package br.com.ubs.carga.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ubs.carga.modelo.Carga;
import br.com.ubs.carga.modelo.Lojista;


public class CargaDto {
	
	private Long id;
	private String product;
	private String quantity;
	private String price;
	private String tipo;
	private String industry;
	private String origin;
	
	public CargaDto (Carga carga) {
		this.id = carga.getId();
		this.product = carga.getProduct();
		this.quantity = carga.getQuantity();
		this.price = carga.getPrice();
		this.tipo = carga.getType();
		this.industry = carga.getIndustry();
		this.origin = carga.getOrigin();
	}
	
	public Long getId() {
		return id;
	}
	public String getProduct() {
		return product;
	}
	public String getQuantity() {
		return quantity;
	}
	public String getPrice() {
		return price;
	}
	public String getTipo() {
		return tipo;
	}
	public String getIndustry() {
		return industry;
	}
	public String getOrigin() {
		return origin;
	}

	public static List<CargaDto> converter(List<Carga> cargas) {
		return cargas.stream().map(CargaDto::new).collect(Collectors.toList());
	}

	public static List<LojistaDto> converterLojista(List<Lojista> lojistas) {
		return lojistas.stream().map(LojistaDto::new).collect(Collectors.toList());
	}

}
