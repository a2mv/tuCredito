package com.pl10.presta.repository;

import com.pl10.presta.entity.Credito;
import com.pl10.presta.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("pagoRepository")
public interface PagoRepository extends JpaRepository<Pago, Serializable> {

    public abstract Pago findById(String id);

    public abstract List<Pago> findAllByCredito(Credito credito);

    @Query(value="SELECT SUM(pagos.cuotas) FROM pagos WHERE pagos.credito = ?1", nativeQuery = true)
    public abstract Long sumCuotasByCredito(Credito credito);
}
