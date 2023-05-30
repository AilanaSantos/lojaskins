package br.unitins.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.MediaType;
import br.unitins.dto.UsuarioResponseDTO;
import br.unitins.service.UsuarioService;


@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    
    @Inject
    UsuarioService usuarioService;

    @GET
    public List<UsuarioResponseDTO> getAll() {
        return usuarioService.getAll();
    }

    @GET
    @Path("/{id}")
    public UsuarioResponseDTO findById(@PathParam("id") Long id) {
        return usuarioService.findById(id);
    }

//    @POST
//    public Response insert(UsuarioDTO dto) {
//        try {
//            UsuarioResponseDTO usuariofisica = usuarioService.create(dto);
//            return Response.status(Status.CREATED).entity(usuariofisica).build();
//        } catch(ConstraintViolationException e) {
//            Result result = new Result(e.getConstraintViolations());
//            return Response.status(Status.NOT_FOUND).entity(result).build();
//        }
//    }
//
//    @PUT
//    @Path("/{id}")
//    public Response update(@PathParam("id") Long id, UsuarioDTO dto) {
//        try {
//            UsuarioResponseDTO usuariofisica = usuarioService.update(id, dto);
//            return Response.ok(usuariofisica).build();
//        } catch(ConstraintViolationException e) {
//            Result result = new Result(e.getConstraintViolations());
//            return Response.status(Status.NOT_FOUND).entity(result).build();
//        }
//    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        usuarioService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }


    @GET
    @Path("/count")
    public long count(){
        return usuarioService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<UsuarioResponseDTO> search(@PathParam("nome") String nome){
        return usuarioService.findByNome(nome);
        
    }
}

