package com.mt.model.dao;

import com.mt.model.dto.ClienteDto;
import com.mt.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente,Integer> {
}
