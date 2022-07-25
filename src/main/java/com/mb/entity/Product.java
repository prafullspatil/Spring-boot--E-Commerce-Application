package com.mb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.mb.model.Auditable;

@Entity
@Table(name = "product")
public class Product extends Auditable
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "category")
	private String category;

	@Column(name = "price")
	private long price;

	@Column(name = "brand_name")
	private String brandName;

	@Column(name = "description")
	private String description;

	@Column(name = "qty")
	private int qty;

	@Column(name = "model_no", unique = true)
	private String modelNo;

	public Product(long id, String productName, String category, long price, String brandName, String description, int qty, String modelNo)
	{
		super();
		this.id = id;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.brandName = brandName;
		this.description = description;
		this.qty = qty;
		this.modelNo = modelNo;
	}

	public Product()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId()
	{
		return id;
	}

	public String getProductName()
	{
		return productName;
	}

	public String getCategory()
	{
		return category;
	}

	public long getPrice()
	{
		return price;
	}

	public String getBrandName()
	{
		return brandName;
	}

	public String getDescription()
	{
		return description;
	}

	public int getQty()
	{
		return qty;
	}

	public String getModelNo()
	{
		return modelNo;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public void setPrice(long price)
	{
		this.price = price;
	}

	public void setBrandName(String brandName)
	{
		this.brandName = brandName;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setQty(int qty)
	{
		this.qty = qty;
	}

	public void setModelNo(String modelNo)
	{
		this.modelNo = modelNo;
	}

}
