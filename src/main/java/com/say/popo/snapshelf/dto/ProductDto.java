package com.say.popo.snapshelf.dto;

import com.say.popo.snapshelf.entity.AIDescription;
import com.say.popo.snapshelf.entity.Product;

public class ProductDto {
	private Long id;
	private String product_name;
	private int price;
	private int stock;
	private String imageUrl;
	private String description;
	
	public ProductDto(Product product) {
		this.id = product.getId();
		this.product_name = product.getProduct_name();
		this.price = product.getPrice();
		this.stock = product.getStock();
		this.imageUrl = product.getImage_url();
		AIDescription desc = product.getAiDescription();
		this.description = desc.getEdited_description();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
