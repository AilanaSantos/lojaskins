package br.unitins.service;

import br.unitins.model.Usuario;
public interface TokenJwtService {
    public String generateJwt(Usuario usuario);
}
