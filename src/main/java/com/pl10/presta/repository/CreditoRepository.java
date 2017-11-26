package com.pl10.presta.repository;

import com.pl10.presta.entity.Cliente;
import com.pl10.presta.entity.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("creditoRepository")
public interface CreditoRepository extends JpaRepository<Credito, Serializable>{

    public abstract Credito findById(String id);

    public abstract List<Credito> findAllByCliente(Cliente cliente);
}
