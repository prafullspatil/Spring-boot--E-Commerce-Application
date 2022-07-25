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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.entity.Cart;
import com.mb.entity.Product;
import com.mb.model.CartModel;
import com.mb.service.CartService;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController
{

	@Autowired
	private CartService cartService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/add-to-cart")
	public Cart addTocart(@RequestBody CartModel cartModel)
	{
		return cartService.addTocart(cartModel);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/items")
	public ResponseEntity<List<Product>> getAllCartItems()
	{
		List<Product> responseBody = cartService.getAllCartItems();
		return new ResponseEntity<>(responseBody, HttpStatus.OK);
	}

	@DeleteMapping("/delete-cart-product/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String deleteCart(@PathVariable("id") long id)
	{
		cartService.deleteCart(id);
		return "Deleted Successfully";
	}

}
