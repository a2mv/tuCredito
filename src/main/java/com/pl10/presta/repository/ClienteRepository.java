package com.pl10.presta.repository;

import com.pl10.presta.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("clienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, Serializable>{

    public abstract Cliente findById(String id);

}
