package io.das.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OM_PRODUCT")
public class Product {
   
	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE,generator="product_sq")
	private Long id;
	private String sku;
	private int category;
	private String name;
	private String description;
	private double unitPrice;
	private String imageURL;

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

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Product(Long id, String sku, int category, String name, String description, double unitPrice) {
		super();
		this.id = id;
		this.sku = sku;
		this.category = category;
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
	}
	

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", name=" + name + ", description=" + description + "]";
	}

}
