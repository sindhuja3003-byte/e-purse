package com.payment.epurse.repository;

import com.payment.epurse.entity.Product;
import org.springframework.data.repository.CrudRepository;

/*author - aniket das*/
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
