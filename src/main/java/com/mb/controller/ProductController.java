package com.mb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.entity.Product;
import com.mb.model.ProductModel;
import com.mb.service.ProductService;

@RestController
@RequestMapping("api/v1/product")
public class ProductController
{

	@Autowired
	private ProductService productService;

	@PostMapping("/save-product")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Product saveProduct(@RequestBody ProductModel productModel)
	{
		return productService.saveProduct(productModel);
	}

	@GetMapping("/get-all-product")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Product> getProductDetails(ProductModel productModel)
	{
		return productService.getProductDetails();
	}

	@DeleteMapping("/delete-product/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deleteProduct(@PathVariable("id") long id)
	{
		productService.deleteProduct(id);
		return "Deleted Successfully";
	}

	@PutMapping("/update-product/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateProduct(@PathVariable long id,
			@RequestBody ProductModel productModel)
	{
		Product product = productService.updateProduct(id, productModel);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	//
	// @GetMapping("/search/{cnb}")
	// public List<Product> searchProduct(@PathVariable(name = "cnb") String cnb)
	// {
	// System.out.println(cnb);
	// List<Product> listProducts = productService.searchProduct(cnb);
	//
	// return listProducts;
	// }

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/search/{cnb}")
	public List<Product> searchProduct(@PathVariable(name = "cnb") String cnb)
	{
		System.out.println(cnb);
		List<Product> listProducts = productService.searchProduct(cnb);
		return listProducts;
	}

	// filter products by price range,
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/filter-product/{min}/{max}")
	public List<Product> filterByPrice(@PathVariable int min, @PathVariable int max)
	{
		return productService.filterByPrice(min, max);
	}

}
