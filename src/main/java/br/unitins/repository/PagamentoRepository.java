package br.unitins.repository;

import java.util.List;

import br.unitins.model.Pagamento;
import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {

    public List<Pagamento> findByNome(String valor) {
        if (valor == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + valor.toUpperCase() + "%").list();
    }
}
