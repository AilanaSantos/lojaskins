package br.unitins.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


import br.unitins.application.Result;
import br.unitins.dto.SkinsDTO;
import br.unitins.dto.SkinsResponseDTO;
import br.unitins.service.SkinsService;


@Path("/skins")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SkinsResource {
    
    @Inject
    SkinsService skinsService;


    @GET
    public List<SkinsResponseDTO> getAll() {
        return skinsService.getAll();
    }

    @GET
    @Path("/{id}")
    public SkinsResponseDTO findById(@PathParam("id") Long id) {
        return skinsService.findById(id);
    }

    @POST
    public Response insert(SkinsDTO dto) {
        try {
            SkinsResponseDTO skins = skinsService.create(dto);
            return Response.status(Status.CREATED).entity(skins).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
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
    public Response delete(@PathParam("id") Long id) {
        skinsService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }


    @GET
    @Path("/count")
    public long count(){
        return skinsService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<SkinsResponseDTO> search(@PathParam("nome") String nome){
        return skinsService.findByNome(nome);
        
    }
}