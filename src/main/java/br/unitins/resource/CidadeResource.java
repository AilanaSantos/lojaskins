package br.unitins.resource;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


import br.unitins.application.Result;
import br.unitins.dto.cidade.CidadeDTO;
import br.unitins.dto.cidade.CidadeResponseDTO;
import br.unitins.service.cidade.CidadeService;

import org.jboss.logging.Logger;

@Path("/cidades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CidadeResource {

    @Inject
    CidadeService cidadeService;


    private static final Logger LOG = Logger.getLogger(CidadeResource.class);

    @GET
    
    public List<CidadeResponseDTO> getAll() {
        LOG.info("Buscando todas as cidades.");
        LOG.debug("ERRO DE DEBUG.");
        return cidadeService.getAll();
    }
    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public CidadeResponseDTO findById(@PathParam("id") Long id) {
        return cidadeService.findById(id);
    }


    @POST
    @RolesAllowed({ "Admin" })
    public Response insert(CidadeDTO dto) {
        // LOG.info("Inserindo um estado: " + dto.nome());
        LOG.infof("Inserindo uma cidade: %s", dto.nome());
        Result result = null;
        try {
            CidadeResponseDTO cidade = cidadeService.create(dto);
            LOG.infof("Cidade (%d) criada com sucesso.", cidade.id());
            return Response.status(Status.CREATED).entity(cidade).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao incluir uma cidade.");
            LOG.debug(e.getMessage());
            result = new Result(e.getConstraintViolations());
        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), false);
        }
        return Response.status(Status.NOT_FOUND).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response update(@PathParam("id") Long id, CidadeDTO dto) {
        try {
            CidadeResponseDTO cidade = cidadeService.update(id, dto);
            return Response.ok(cidade).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("id") Long id) {
        cidadeService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    @RolesAllowed({ "Admin" })
    public long count() {
        return cidadeService.count();
    }

    @GET
    @Path("/search/{nome}")
    @RolesAllowed({ "Admin" })
    public List<CidadeResponseDTO> search(@PathParam("nome") String nome) {
        return cidadeService.findByNome(nome);

    }
}