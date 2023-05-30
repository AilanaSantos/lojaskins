package br.unitins.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;


import br.unitins.model.Skins;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class SkinsRepository implements PanacheRepository<Skins> {
    
    public List<Skins> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }

    
}