package io.das.product.domain;

public class Product {

	private Long id;
	private String sku;
	private int category;
	private String name;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

/*	public Product(Long id, String sku, String name, String description) {
		super();
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.description = description;
	} */

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Product(Long id, String sku, int category, String name, String description) {
		super();
		this.id = id;
		this.sku = sku;
		this.category = category;
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", name=" + name + ", description=" + description + "]";
	}

}
