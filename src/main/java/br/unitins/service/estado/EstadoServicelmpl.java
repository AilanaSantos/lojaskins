package br.unitins.service.estado;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;
import br.unitins.dto.estado.EstadoDTO;
import br.unitins.dto.estado.EstadoResponseDTO;
import br.unitins.model.Estado;
import br.unitins.repository.CidadeRepository;
import br.unitins.repository.EstadoRepository;

@ApplicationScoped
public class EstadoServicelmpl implements EstadoService {

    @Inject
    EstadoRepository estadoRepository;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    Validator validator;

    @Override
    public List<EstadoResponseDTO> getAll() {

        List<Estado> list = estadoRepository.listAll();
        return list.stream().map(EstadoResponseDTO::new).collect(Collectors.toList());

    }

    @Override
    public EstadoResponseDTO findById(Long id) {

        Estado estado = estadoRepository.findById(id);
        if (estado == null)
            return null;
        return new EstadoResponseDTO(estado);
    }

    @Override
    @Transactional
    public EstadoResponseDTO create(EstadoDTO estadoDTO) throws ConstraintViolationException {

        validar(estadoDTO);

        Estado entity = new Estado();
        entity.setSigla(estadoDTO.sigla());
        entity.setNome(estadoDTO.nome());

        estadoRepository.persist(entity);

        return new EstadoResponseDTO(entity);

    }

    private void validar(EstadoDTO estadoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<EstadoDTO>> violations = validator.validate(estadoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    public EstadoResponseDTO update(Long id, EstadoDTO estadoDTO) throws ConstraintViolationException{
        validar(estadoDTO);
        Estado estadoBanco = estadoRepository.findById(id);
        if (estadoBanco == null) {
            throw new NotFoundException("Estado não encontrado pelo id");
        }

        estadoBanco.setSigla(estadoDTO.sigla());
        estadoBanco.setNome(estadoDTO.nome());

        estadoRepository.persist(estadoBanco);

        return new EstadoResponseDTO(estadoBanco);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public List<EstadoResponseDTO> findByNome(String nome) {

        List<Estado> list = estadoRepository.findByNome(nome);
        return list.stream().map(EstadoResponseDTO::new).collect(Collectors.toList());

    }

    @Override
    public long count() {
        return cidadeRepository.count();
    }

}
