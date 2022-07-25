package com.mb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.entity.Cart;
import com.mb.entity.Product;
import com.mb.entity.User;
import com.mb.exception.CustomException;
import com.mb.model.CartModel;
import com.mb.repository.CartRepository;
import com.mb.repository.ProductRepository;
import com.mb.repository.UserRepository;
import com.mb.service.CartService;

@Service
public class CartServiceImpl implements CartService
{

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Cart addTocart(CartModel cartModel)
	{
		System.out.println(cartModel.getProductId());
		Optional<Product> optionalProduct = productRepository.findById(cartModel.getProductId());
		Optional<User> optionalUser = userRepository.findById(cartModel.getUserId());
		System.out.println(optionalProduct);
		if (!optionalUser.isPresent())
		{
			throw new CustomException("User not exists");
		}
		if (!optionalProduct.isPresent())
		{
			throw new CustomException("Product not found");
		}
		Product product = optionalProduct.get();
		System.out.println(optionalProduct.get().getId());
		User user = optionalUser.get();
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setProduct(product);
		cart.setQuantity(cartModel.getQuantity());
		return cartRepository.save(cart);

	}

	@Override
	public List<Product> getAllCartItems()
	{
		List<Product> cartItemsList = new ArrayList<>();
		List<Cart> cartList = cartRepository.findAll();
		for (int idx = 0; idx < cartList.size(); idx++)
		{
			Product product = cartList.get(idx).getProduct();
			cartItemsList.add(product);
		}
		return cartItemsList;

	}

	@Override
	public void deleteCart(long id)
	{
		Cart cart = cartRepository.getCartById(id);
		cartRepository.delete(cart);

	}

}
