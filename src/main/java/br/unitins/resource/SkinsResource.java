package br.unitins.resource;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;
import br.unitins.application.Result;
import br.unitins.dto.skins.SkinsDTO;
import br.unitins.dto.skins.SkinsResponseDTO;
import br.unitins.service.skins.SkinsService;



@Path("/skins")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SkinsResource {
    
    @Inject
    SkinsService skinsService;

    private static final Logger LOG = Logger.getLogger(SkinsResource.class);

    @GET
    
    public List<SkinsResponseDTO> getAll() {
        LOG.info("Buscando todas as skins.");
        LOG.debug("ERRO DE DEBUG.");
        return skinsService.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public SkinsResponseDTO findById(@PathParam("id") Long id) {
        return skinsService.findById(id);
    }

    @POST
    @RolesAllowed({ "Admin" })
    public Response insert(SkinsDTO dto) {
        // LOG.info("Inserindo uma skin: " + dto.nome());
        LOG.infof("Inserindo uma skin: %s", dto.nome());
        Result result = null;
        try {
            SkinsResponseDTO skins = skinsService.create(dto);
            LOG.infof("Skins (%d) criadas com sucesso.", skins.nome());
            return Response.status(Status.CREATED).entity(skins).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao incluir um estado.");
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
    public Response update(@PathParam("id") Long id, SkinsDTO dto) {
        try {
            SkinsResponseDTO skins = skinsService.update(id, dto);
            return Response.ok(skins).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }      
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("id") Long id) {
        skinsService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }


    @GET
    @Path("/count")
    @RolesAllowed({ "Admin" })
    public long count(){
        return skinsService.count();
    }

    @GET
    @Path("/search/{nome}")
    @RolesAllowed({ "Admin" })
    public List<SkinsResponseDTO> search(@PathParam("nome") String nome){
        return skinsService.findByNome(nome);
        
    }
}