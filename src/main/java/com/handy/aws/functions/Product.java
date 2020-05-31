package com.handy.aws.functions;

public class Product {

	int id;
	String toolType;
	String brand;
	String name;
	int count;
	
	public Product() {
		
	}
	
	public Product(int id, String toolType, String brand, String name, int count) {
		super();
		this.id = id;
		this.toolType = toolType;
		this.brand = brand;
		this.name = name;
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToolType() {
		return toolType;
	}
	public void setToolType(String toolType) {
		this.toolType = toolType;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", toolType=" + toolType + ", brand=" + brand + ", name=" + name + ", count="
				+ count + "]";
	}
	
}
