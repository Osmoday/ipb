package com.ipb.projekt.repos;

import com.ipb.projekt.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends CrudRepository<ClientEntity, Integer> {
}
