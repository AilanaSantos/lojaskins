package br.unitins.resource;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.MediaType;
import br.unitins.application.Result;
import br.unitins.dto.usuario.UsuarioDTO;
import br.unitins.dto.usuario.UsuarioResponseDTO;
import br.unitins.service.usuario.UsuarioService;
import org.jboss.logging.Logger;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    private static final Logger LOG = Logger.getLogger(UsuarioResource.class);

    @GET
    @RolesAllowed({ "Admin" })
    public List<UsuarioResponseDTO> getAll() {
        LOG.info("Buscando todos os usuarios.");
        LOG.debug("ERRO DE DEBUG.");
        return usuarioService.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public UsuarioResponseDTO findById(@PathParam("id") Long id) {
        return usuarioService.findById(id);
    }

    // @POST
    // public Response insert(UsuarioDTO dto) {
    // LOG.info("Inserindo um usuario: " + dto.login());
    // LOG.infof("Inserindo um usuario: %s", dto.login());
    // Result result = null;
    // try {
    // UsuarioResponseDTO usuario = usuarioService.create(dto);
    // LOG.infof("Usuario (%d) criado com sucesso.", usuario.id());
    // return Response.status(Status.CREATED).entity(usuario).build();
    // } catch(ConstraintViolationException e) {
    // LOG.error("Erro ao incluir um usuario.");
    // LOG.debug(e.getMessage());
    // result = new Result(e.getConstraintViolations());
    // } catch (Exception e) {
    // LOG.fatal("Erro sem identificacao: " + e.getMessage());
    // result = new Result(e.getMessage(), false);
    // }
    // return Response.status(Status.NOT_FOUND).entity(result).build();

    // }

    
    // @PUT
    // @Path("/{id}")
    // public Response update(@PathParam("id") Long id, UsuarioDTO dto) {
    // try {
    // UsuarioResponseDTO usuario = usuarioService.update(id,dto);
    // return Response.ok(usuario).build();
    // } catch(ConstraintViolationException e) {
    // Result result = new Result(e.getConstraintViolations());
    // return Response.status(Status.NOT_FOUND).entity(result).build();
    // }
    // }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("id") Long id) {
        usuarioService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    @RolesAllowed({ "Admin" })
    public long count() {
        return usuarioService.count();
    }

    @GET
    @Path("/search/{nome}")
    @RolesAllowed({ "Admin" })
    public List<UsuarioResponseDTO> search(@PathParam("nome") String nome) {
        return usuarioService.findByNome(nome);

    }


}
