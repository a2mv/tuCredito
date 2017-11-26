package com.pl10.presta.repository.query;

import com.pl10.presta.entity.Cliente;
import com.pl10.presta.entity.QCredito;
import com.pl10.presta.enums.CreditoStatus;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("creditoDslRepository")
public class CreditoDslRepository {

    private QCredito qCredito = QCredito.credito;

    @PersistenceContext
    private EntityManager em;

    public List<Cliente> searchAllClienteCredit(String str, int page, CreditoStatus creditoStatus){
        JPAQuery<Cliente> query = new JPAQuery<Cliente>(em);
        page = (page*10)-10;
        List<Cliente> clientes = query.select(qCredito.cliente).from(qCredito)
                .where(qCredito.cliente.ndocumento.contains(str)
                        .or(qCredito.cliente.fullname.contains(str))
                        .or(qCredito.cliente.observacion.contains(str))
                        .and(qCredito.creditoStatus.eq(creditoStatus))
                ).groupBy(qCredito.cliente).offset(page).limit(10).fetch();
        return clientes;
    }

    public Long countAllClienteCredit(String str, CreditoStatus creditoStatus){
        JPAQuery<Cliente> query = new JPAQuery<Cliente>(em);
        long count = query.select(qCredito.cliente).from(qCredito)
                .where(qCredito.cliente.ndocumento.contains(str)
                        .or(qCredito.cliente.fullname.contains(str))
                        .or(qCredito.cliente.observacion.contains(str))
                        .and(qCredito.creditoStatus.eq(creditoStatus))
                ).groupBy(qCredito.cliente).fetchCount();
        return count;
    }
}
