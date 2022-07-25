package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mb.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>
{
	// boolean existByProductId(long id);
	Cart getCartById(long id);
}
