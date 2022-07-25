package com.mb.service.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import com.mb.entity.Product;
import com.mb.model.ProductModel;
import com.mb.repository.ProductRepository;
import com.mb.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Product saveProduct(ProductModel productModel)
	{
		// TODO Auto-generated method stub
		Product product = modelMapper.map(productModel, Product.class);
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}

	@Override
	public List<Product> getProductDetails()
	{

		return productRepository.findAll();
	}

	@Override
	public void deleteProduct(long id)
	{
		Product product = productRepository.getProductById(id);
		productRepository.delete(product);

	}

	@Override
	public Product updateProduct(long id, ProductModel productModel)
	{
		Product oldProduct = productRepository.getProductById(id);

		if (!oldProduct.getModelNo().equals(productModel.getModelNo())
				&& productRepository.existsByModelNo(productModel.getModelNo()))
		{
			throw new ResourceAccessException("Product Already Exits with This Model No" + productModel.getModelNo());
		}

		Product updatedProduct = modelMapper.map(productModel, Product.class);
		updatedProduct.setId(oldProduct.getId());
		// updatedUser.setUser(employeeUuid);

		return productRepository.save(updatedProduct);
	}

	@Override
	public List<Product> searchProduct(String cnb)
	{
		if (cnb != null)
		{
			return productRepository.searchProduct(cnb);
		}
		return productRepository.findAll();
	}

	@Override
	public List<Product> filterByPrice(int min, int max)
	{
		return productRepository.filterByPriceRange(min, max);
	}

	// @Override
	// public List<Product> searchProduct(String productName, String category)
	// {
	// List<Product> product = productRepository.searchProduct(productName,category)
	// if (cnb != null)
	// {
	// return productRepository.searchProduct(cnb);
	// }
	// return productRepository.findAll();
	// }

}
