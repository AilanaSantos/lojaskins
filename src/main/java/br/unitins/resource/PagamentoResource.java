package br.unitins.resource;

import java.util.List;
import org.jboss.logging.Logger;
import br.unitins.application.Result;
import br.unitins.dto.pagamento.PagamentoDTO;
import br.unitins.dto.pagamento.PagamentoResponseDTO;
import br.unitins.service.pagamento.PagamentoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


@Path("/pagamentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PagamentoResource {
    
    @Inject
    PagamentoService pagamentoService;

    @Inject
    Validator validator;

    private static final Logger LOG = Logger.getLogger(PagamentoResource.class);

    @GET
    @RolesAllowed({ "Admin" })
    public List<PagamentoResponseDTO> getAll() {
        LOG.info("Buscando todas os Pagamentos.");
        LOG.debug("ERRO DE DEBUG.");
        return pagamentoService.getAll();
    }
    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public PagamentoResponseDTO findById(@PathParam("id") Long id) {
        return pagamentoService.findById(id);
    }

    @POST
    @RolesAllowed({ "Admin" })
    public Response insert(PagamentoDTO dto) {
        try {
            PagamentoResponseDTO pagamento = pagamentoService.create(dto);
            return Response.status(Status.CREATED).entity(pagamento).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response update(@PathParam("id") Long id, PagamentoDTO dto) {
        try {
            PagamentoResponseDTO pagamento = pagamentoService.update(id, dto);
            return Response.ok(pagamento).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("id") Long id) {
        pagamentoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    @RolesAllowed({ "Admin" })
    public long count() {
        return pagamentoService.count();
    }

    @GET
    @Path("/search/{nome}")
    @RolesAllowed({ "Admin" })
    public List<PagamentoResponseDTO> search(@PathParam("nome") String nome) {
        return pagamentoService.findByNome(nome);
    }
}
