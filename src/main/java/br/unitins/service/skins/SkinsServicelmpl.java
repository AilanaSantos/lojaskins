package br.unitins.service.skins;

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
import br.unitins.dto.skins.SkinsDTO;
import br.unitins.dto.skins.SkinsResponseDTO;
import br.unitins.model.Skins;
import br.unitins.repository.SkinsRepository;

@ApplicationScoped
public class SkinsServicelmpl implements SkinsService {

    @Inject
    SkinsRepository skinsRepository;

  

    @Inject
    Validator validator;

    @Override
    public List<SkinsResponseDTO> getAll() {
        List<Skins> list = skinsRepository.listAll();
        return list.stream().map(SkinsResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public SkinsResponseDTO findById(Long id) {

        Skins skins = skinsRepository.findById(id);
        if (skins == null)
            throw new NotFoundException("Skins não encontradas.");
        return new SkinsResponseDTO(skins);

    }

    private void validar(SkinsDTO skinsDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<SkinsDTO>> violations = validator.validate(skinsDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }


    @Override
    @Transactional
    public SkinsResponseDTO create(SkinsDTO skinsDTO) throws ConstraintViolationException{

        validar(skinsDTO);
       
        
            Skins entity = new Skins();
            entity.setNome(skinsDTO.nome());
            entity.setTipo(skinsDTO.tipo());
            skinsRepository.persist(entity);

            return new SkinsResponseDTO(entity);
    }


    
    @Override
    @Transactional
    public SkinsResponseDTO update(Long id, SkinsDTO skinsDTO) throws ConstraintViolationException {

        validar(skinsDTO);
        Skins skinsBanco = skinsRepository.findById(id);
        if (skinsBanco == null) {
            throw new NotFoundException("Skin não encontrada pelo id");
        }


            skinsBanco.setNome(skinsDTO.nome());
            skinsBanco.setTipo(skinsDTO.tipo());

            skinsRepository.persist(skinsBanco);

            return new SkinsResponseDTO(skinsBanco);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        skinsRepository.deleteById(id);
    }

    @Override
    public List<SkinsResponseDTO> findByNome(String nome) {
        List<Skins> list = skinsRepository.findByNome(nome);
            return list.stream().map(SkinsResponseDTO::new).collect(Collectors.toList());
    }

    @Override
        public long count(){
            return skinsRepository.count();
        }
    }


