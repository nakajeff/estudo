package br.com.ubs.carga.controller.dto;

import br.com.ubs.carga.modelo.Carga;
import br.com.ubs.carga.modelo.Lojista;

public class LojistaDto {
	
	private Long id;
	private String product;
	private String quantity;
	private String price;
	private String volume;
	private String qtdTotal;
	private String financeiro;
	private String precoMedio;
	
	public LojistaDto (Lojista lojista) {
		this.id = lojista.getId();
		this.product = lojista.getProduct();
		this.quantity = lojista.getQuantity();
		this.price = lojista.getPrice();
		this.volume = lojista.getVolume();
		this.qtdTotal = lojista.getQtdTotal();
		this.financeiro = lojista.getFinanceiro();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getQtdTotal() {
		return qtdTotal;
	}

	public void setQtdTotal(String qtdTotal) {
		this.qtdTotal = qtdTotal;
	}

	public String getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(String financeiro) {
		this.financeiro = financeiro;
	}

	public String getPrecoMedio() {
		return precoMedio;
	}

	public void setPrecoMedio(String precoMedio) {
		this.precoMedio = precoMedio;
	}



}
