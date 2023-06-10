package br.unitins.service.pagamento;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;

import br.unitins.dto.pagamento.PagamentoDTO;
import br.unitins.dto.pagamento.PagamentoResponseDTO;
import br.unitins.model.Pagamento;
import br.unitins.repository.PagamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ApplicationScoped
public class PagamentoServicelmpl implements PagamentoService {

    @Inject
    PagamentoRepository pagamentoRepository;

    @Inject
    Validator validador;

    @Override
    public List<PagamentoResponseDTO> getAll() {
        List<Pagamento> list = pagamentoRepository.listAll();
        return list.stream().map(PagamentoResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public PagamentoResponseDTO findById(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id);
        if (pagamento == null)
            throw new NotFoundException("Pagamento não encontrado.");
        return new PagamentoResponseDTO(pagamento);
    }

    @Override
    @Transactional
    public PagamentoResponseDTO create(PagamentoDTO pagamentoDTO) throws ConstraintViolationException {
        validar(pagamentoDTO);

        Pagamento entity = new Pagamento();
        entity.setValor(pagamentoDTO.valor());
        entity.setCompra(pagamentoDTO.compra());

        pagamentoRepository.persist(entity);

        return new PagamentoResponseDTO(entity);

    }

    private void validar(PagamentoDTO pagamentoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<PagamentoDTO>> violations = validador.validate(pagamentoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public PagamentoResponseDTO update(Long id, PagamentoDTO pagamentoDTO) {

        validar(pagamentoDTO);
        Pagamento pagamento = pagamentoRepository.findById(id);
        if (pagamento == null) {
            throw new NotFoundException("Pagamento não encontrado pelo id");
        }

        pagamento.setValor(pagamentoDTO.valor());
        pagamento.setCompra(pagamentoDTO.compra());

        pagamentoRepository.persist(pagamento);

        return new PagamentoResponseDTO(pagamento);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        pagamentoRepository.deleteById(id);
    }

    @Override
    public List<PagamentoResponseDTO> findByNome(String nome) {
        List<Pagamento> list = pagamentoRepository.findByNome(nome);
        return list.stream().map(PagamentoResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return pagamentoRepository.count();
    }

}
