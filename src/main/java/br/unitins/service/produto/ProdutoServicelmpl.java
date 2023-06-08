package br.unitins.service.produto;

import br.unitins.dto.produto.ProdutoDTO;
import br.unitins.dto.produto.ProdutoResponseDTO;
import br.unitins.model.Produto;
import br.unitins.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProdutoServicelmpl implements ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    Validator validator;

    @Override
    public List<ProdutoResponseDTO> getAll() {
        List<Produto> list = produtoRepository.listAll();
        return list.stream().map(ProdutoResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public ProdutoResponseDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id);
        if (produto == null)
            throw new NotFoundException("Produto não encontrado.");
        return new ProdutoResponseDTO(produto);
    }

    @Override
    @Transactional
    public ProdutoResponseDTO create(ProdutoDTO produtoDTO) throws ConstraintViolationException {
        validar(produtoDTO);

        Produto entity = new Produto();
        entity.setNome(produtoDTO.nome());
        entity.setDescricao(produtoDTO.descricao());
        entity.setEstoque(produtoDTO.estoque());
        entity.setPreco(produtoDTO.preco());

        produtoRepository.persist(entity);

        return new ProdutoResponseDTO(entity);

    }

    private void validar(ProdutoDTO produtoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produtoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public ProdutoResponseDTO update(Long id, ProdutoDTO produtoDTO) throws ConstraintViolationException {
        validar(produtoDTO);
        Produto produtoBanco = produtoRepository.findById(id);
        if (produtoBanco == null) {
            throw new NotFoundException("Produto não encontrado pelo id");
        }

        produtoBanco.setNome(produtoDTO.nome());
        produtoBanco.setDescricao(produtoDTO.descricao());
        produtoBanco.setEstoque(produtoDTO.estoque());
        produtoBanco.setPreco(produtoDTO.preco());

        produtoRepository.persist(produtoBanco);

        return new ProdutoResponseDTO(produtoBanco);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public List<ProdutoResponseDTO> findByNome(String nome) {
        List<Produto> list = produtoRepository.findByNome(nome);
        return list.stream().map(ProdutoResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return produtoRepository.count();
    }

    
}
