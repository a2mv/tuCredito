package com.pl10.presta.repository.query;

import com.pl10.presta.entity.Cliente;
import com.pl10.presta.entity.QCliente;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository("clienteDslRepository")
public class ClienteDslRepository {

    private QCliente qCliente = QCliente.cliente;

    @PersistenceContext
    private EntityManager em;

    public List<Cliente> searchAllDataCliente(String str, int page){
        JPAQuery<Cliente> query = new JPAQuery<Cliente>(em);
        page = (page*10)-10;
        List<Cliente> clientes = query.select(qCliente).from(qCliente)
                .where(qCliente.ndocumento.contains(str)
                .or(qCliente.fullname.contains(str))
                .or(qCliente.observacion.contains(str))
                ).offset(page).limit(10).fetch();
        return clientes;
    }

    public Long countAllDataCliente(String str){
        JPAQuery<Cliente> query = new JPAQuery<Cliente>(em);
        long count = query.select(qCliente).from(qCliente)
                .where(qCliente.ndocumento.contains(str)
                        .or(qCliente.fullname.contains(str))
                        .or(qCliente.observacion.contains(str))
                ).fetchCount();
        return count;
    }


}
