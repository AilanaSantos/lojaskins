package br.unitins.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


import br.unitins.application.Result;

import br.unitins.dto.TelefoneDTO;
import br.unitins.dto.TelefoneResponseDTO;
import br.unitins.service.TelefoneService;

@Path("/telefone")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TelefoneResource {

    @Inject
    TelefoneService telefoneService;

    @GET
    public List<TelefoneResponseDTO> getAll() {
        return telefoneService.getAll();
    }

    @GET
    @Path("/{id}")
    public TelefoneResponseDTO findById(@PathParam("id") Long id) {
        return telefoneService.findById(id);
    }

    @POST
    public Response insert(TelefoneDTO dto) {
        try {
            TelefoneResponseDTO telefone = telefoneService.create(dto);
            return Response.status(Status.CREATED).entity(telefone).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, TelefoneDTO dto) {
        try {
            TelefoneResponseDTO telefone = telefoneService.update(id,dto);
            return Response.status(Status.NO_CONTENT).entity(telefone).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        telefoneService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return telefoneService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<TelefoneResponseDTO> search(@PathParam("nome") String nome) {
        return telefoneService.findByNome(nome);

    }
}
