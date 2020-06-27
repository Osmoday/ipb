package com.ipb.projekt.repos;

import com.ipb.projekt.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<ProductEntity, Integer> {
}
