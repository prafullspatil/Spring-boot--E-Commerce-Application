package com.mb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mb.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
	Product getProductById(long id);

	boolean existsByModelNo(String modelNo);

	@Query("SELECT p FROM Product p WHERE CONCAT(p.productName, p.category, p.brandName) LIKE %?1%")
	public List<Product> search(String keyword);

	@Query(value = "SELECT * FROM product  WHERE price BETWEEN(?1) AND (?2)", nativeQuery = true)
	public List<Product> filterByPriceRange(int max, int min);
}
