package br.unitins;

import br.unitins.dto.CidadeResponseDTO;
import br.unitins.dto.EstadoDTO;
import br.unitins.service.EstadoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import br.unitins.dto.CidadeDTO;
import br.unitins.service.CidadeService;

import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class CidadeResourceTest {

    @Inject
    CidadeService cidadeService;

    @Inject
    EstadoService estadoService;

    @Test
    public void getAllTest() {
        given()
                .when().get("/cidades")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {

        EstadoDTO estado = new EstadoDTO("Sao Paulo","SP");
        Long id = estadoService.create(estado).id();

        CidadeDTO cidade = new CidadeDTO(
                null,
                1L,
                "Santos");


        given()
                .contentType(ContentType.JSON)
                .body(cidade)
                .when().post("/cidades")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "id",is(null),
                        "idEstado", is(1L),
                        "nome", is("Santos"));

    }

    @Test
    public void testUpdate() {
        Long id = estadoService.create(new EstadoDTO("Sao Paulo","SP")).id();
        CidadeDTO cidade = new CidadeDTO(null,1L,"Santos");
        Long idCidade = cidadeService.create(cidade).id();


        // Criando outra cidade para atuailzacao
        CidadeDTO cidadeRequisicao = new CidadeDTO(
                null,
                1L,
                "Barrolandia");

        CidadeResponseDTO produtoAtualizado = cidadeService.update(id, cidadeRequisicao);

        given()
                .contentType(ContentType.JSON)
                .body(produtoAtualizado)
                .when().put("/cidades/" + id)
                .then()
                .statusCode(200);

        // Verificando se os dados foram atualizados no banco de dados
        CidadeResponseDTO produtoResponse = cidadeService.findById(id);
        assertThat(produtoResponse.id(), is(1L));
        assertThat(produtoResponse.nome(), is("Barrolandia"));

    }

    @Test
    public void testDelete() {
        CidadeDTO cidade = new CidadeDTO(
                null,
                1L,
                "Santos");

        Long id = cidadeService.create(cidade).id();

        given()
                .when().delete("/cidades/" + id)
                .then()
                .statusCode(204);

        // verificando se a cidade foi excluida
        CidadeResponseDTO cidadeResponse = null;
        try {
            cidadeResponse = cidadeService.findById(id);
        } catch (Exception e) {
            assert true;
        } finally {
            assertNull(cidadeResponse);
        }
    }



    }



