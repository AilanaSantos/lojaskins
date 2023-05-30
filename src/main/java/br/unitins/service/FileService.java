package br.unitins.service;

import java.io.File;
import java.io.IOException;

public interface FileService {

    String salvarImagemUsuario(byte[] imagem, String nomeImagem) throws IOException;
    
    File download (String nomeArquivo);
}
