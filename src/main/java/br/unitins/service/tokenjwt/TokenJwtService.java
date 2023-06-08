package br.unitins.service.tokenjwt;

import br.unitins.model.Usuario;
public interface TokenJwtService {
    public String generateJwt(Usuario usuario);
}
