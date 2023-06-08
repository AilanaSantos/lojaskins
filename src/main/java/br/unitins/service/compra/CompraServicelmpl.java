package br.unitins.service.compra;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.dto.compra.CompraDTO;
import br.unitins.dto.compra.CompraResponseDTO;
import br.unitins.model.Compra;
import br.unitins.repository.CompraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CompraServicelmpl implements CompraService {

    @Inject
    CompraRepository compraRepository;

    @Inject
    Validator validador;

    @Override
    public List<CompraResponseDTO> getAll() {
        List<Compra> list = compraRepository.listAll();
        return list.stream().map(CompraResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public CompraResponseDTO findById(Long id) {
        Compra compra = compraRepository.findById(id);
        if (compra == null)
            throw new NotFoundException("Compra não encontrada.");
        return new CompraResponseDTO(compra);
    }

    @Override
    @Transactional
    public CompraResponseDTO create(CompraDTO compraDTO) throws ConstraintViolationException {
        validar(compraDTO);

        Compra entity = new Compra();
        entity.setDate(compraDTO.date());
        entity.setTotalcompra(compraDTO.totalcompra());

        compraRepository.persist(entity);

        return new CompraResponseDTO(entity);
    }

    private void validar(CompraDTO compraDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<CompraDTO>> violations = validador.validate(compraDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public CompraResponseDTO update(Long id, CompraDTO compraDTO) throws ConstraintViolationException {
        validar(compraDTO);
        Compra compraBanco = compraRepository.findById(id);
        if (compraBanco == null) {
            throw new NotFoundException("Compra não encontrado pelo id");
        }

        
        compraBanco.setTotalcompra(compraDTO.totalcompra());
        compraBanco.setDate(compraDTO.date());

        compraRepository.persist(compraBanco);

        return new CompraResponseDTO(compraBanco);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        compraRepository.deleteById(id);
    }

    @Override
    public List<CompraResponseDTO> findByNome(String nome) {
        List<Compra> list = compraRepository.findByNome(nome);
        return list.stream().map(CompraResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return compraRepository.count();
    }

}
