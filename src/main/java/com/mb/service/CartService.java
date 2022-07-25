package com.mb.service;

import java.util.List;
import com.mb.entity.Cart;
import com.mb.entity.Product;
import com.mb.model.CartModel;

public interface CartService
{

	public Cart addTocart(CartModel cartModel);

	List<Product> getAllCartItems();

	void deleteCart(long id);
}
