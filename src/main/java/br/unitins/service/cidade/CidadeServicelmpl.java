package br.unitins.service.cidade;

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
import br.unitins.dto.cidade.CidadeDTO;
import br.unitins.dto.cidade.CidadeResponseDTO;
import br.unitins.model.Cidade;
import br.unitins.model.Estado;
import br.unitins.repository.CidadeRepository;
import br.unitins.repository.EstadoRepository;

@ApplicationScoped
public class CidadeServicelmpl implements CidadeService {

    @Inject
    CidadeRepository cidadeRepository;

    

    @Inject
    EstadoRepository estadoRepository;

    @Inject
    Validator validator;

    @Override
    public List<CidadeResponseDTO> getAll() {

        List<Cidade> list = cidadeRepository.listAll();
        return list.stream().map(CidadeResponseDTO::new).collect(Collectors.toList());

    }

    @Override
    public CidadeResponseDTO findById(Long id) {

        Cidade cidade = cidadeRepository.findById(id);
        if (cidade == null)
            return null;
        return new CidadeResponseDTO(cidade);

    }

    @Override
    @Transactional
    public CidadeResponseDTO create(CidadeDTO cidadeDTO) throws ConstraintViolationException {

        validar(cidadeDTO);

        Cidade entity = new Cidade();
        entity.setEstado(new Estado());
        entity.getEstado().setId(cidadeDTO.idEstado());
        entity.setNome(cidadeDTO.nome());

        cidadeRepository.persist(entity);
        return new CidadeResponseDTO(entity);

    }

    private void validar(CidadeDTO cidadeDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<CidadeDTO>> violations = validator.validate(cidadeDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public CidadeResponseDTO update(Long id, CidadeDTO cidadeDTO) throws ConstraintViolationException {

        validar(cidadeDTO);
        Cidade cidadeBanco = cidadeRepository.findById(id);
        if (cidadeBanco == null) {
            throw new NotFoundException("Cidade não encontrada pelo id");
        }
        cidadeBanco.setEstado(new Estado());
        cidadeBanco.getEstado().setId(cidadeDTO.idEstado());
        cidadeBanco.setNome(cidadeDTO.nome());

        cidadeRepository.persist(cidadeBanco);

        return new CidadeResponseDTO(cidadeBanco);

    }

    @Override
    public void delete(Long id) {
        cidadeRepository.deleteById(id);
    }

    @Override
    public List<CidadeResponseDTO> findByNome(String nome) {

        List<Cidade> list = cidadeRepository.findByNome(nome);
        return list.stream().map(CidadeResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return cidadeRepository.count();
    }

}
