package com.mb.service;

import java.util.List;
import com.mb.entity.Product;
import com.mb.model.ProductModel;

public interface ProductService
{

	Product saveProduct(ProductModel productModel);

	List<Product> getProductDetails();

	void deleteProduct(long id);

	// @Valid left
	Product updateProduct(long id, ProductModel productModel);

	public List<Product> searchProduct(String cnb);

	public List<Product> filterByPrice(int min, int max);
}
