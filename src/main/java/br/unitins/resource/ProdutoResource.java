package br.unitins.resource;

import br.unitins.application.Result;
import br.unitins.dto.produto.ProdutoDTO;
import br.unitins.dto.produto.ProdutoResponseDTO;
import br.unitins.dto.usuario.UsuarioResponseDTO;
import br.unitins.form.ImageForm;
import br.unitins.service.file.FileService;
import br.unitins.service.produto.ProdutoService;
import br.unitins.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.util.List;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    FileService fileService;

    @Inject
    JsonWebToken jwt;



    private static final Logger LOG = Logger.getLogger(ProdutoResource.class);

    @GET
    @RolesAllowed({ "Admin" })
    public List<ProdutoResponseDTO> getAll() {
        LOG.info("Buscando todos os produtos.");
        LOG.debug("ERRO DE DEBUG.");
        return produtoService.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public ProdutoResponseDTO findById(@PathParam("id") Long id) {
        return produtoService.findById(id);
    }


    @POST
    @RolesAllowed({ "Admin" })
    public Response insert(ProdutoDTO dto) {
         // LOG.info("Inserindo um produto: " + dto.nome());
         LOG.infof("Inserindo um produto: %s", dto.nome());
         Result result = null;
         try {
             ProdutoResponseDTO produto = produtoService.create(dto);
             LOG.infof("Produto (%d) criado com sucesso.", produto.id());
             return Response.status(Status.CREATED).entity(produto).build();
         } catch (ConstraintViolationException e) {
             LOG.error("Erro ao incluir um produto.");
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
    public Response update(@PathParam("id") Long id, ProdutoDTO dto) {
        try {
            ProdutoResponseDTO produto = produtoService.update(id, dto);
            return Response.ok(produto).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("id") Long id) {
        produtoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    @RolesAllowed({ "Admin" })
    public long count() {
        return produtoService.count();
    }

    @GET
    @Path("/search/{nome}")
    @RolesAllowed({ "Admin" })
    public List<ProdutoResponseDTO> search(@PathParam("nome") String nome) {
        return produtoService.findByNome(nome);
    }

    @GET
    @RolesAllowed({ "Admin", "User" })
    public Response getUsuario() {

        // obtendo o login a partir do token
        String login = jwt.getSubject();
        UsuarioResponseDTO usuario = usuarioService.findByLogin(login);

        return Response.ok(usuario).build();
    }

    @PATCH
    @Path("/novaimagem")
    @RolesAllowed({ "Admin", "User" })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ImageForm form) {
        String nomeImagem = "";

        try {
            nomeImagem = fileService.salvarImagemUsuario(form.getImagem(), form.getNomeImagem());
        } catch (IOException e) {
            Result result = new Result(e.getMessage());
            return Response.status(Status.CONFLICT).entity(result).build();
        }

        // obtendo o login a partir do token
        String login = jwt.getSubject();
        UsuarioResponseDTO usuario = usuarioService.findByLogin(login);

        usuario = usuarioService.update(usuario.id(), nomeImagem);

        return Response.ok(usuario).build();

    }

    @GET
    @Path("/download/{nomeImagem}")
    @RolesAllowed({ "Admin", "User" })
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }

}