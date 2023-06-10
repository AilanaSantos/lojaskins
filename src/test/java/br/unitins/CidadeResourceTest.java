package br.unitins;

import br.unitins.dto.AuthUsuarioDTO;
import br.unitins.dto.cidade.CidadeDTO;
import br.unitins.dto.cidade.CidadeResponseDTO;
import br.unitins.dto.estado.EstadoDTO;
import br.unitins.service.cidade.CidadeService;
import br.unitins.service.estado.EstadoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;

@QuarkusTest
public class CidadeResourceTest {

    @Inject
    CidadeService cidadeService;

    @Inject
    EstadoService estadoService;

    private String token;

    @BeforeEach
    public void setUp() {
        var auth = new AuthUsuarioDTO("Ailana", "coxinha");
        Response response = (Response) given()
                .contentType("application/json")
                .body(auth).when()
                .post("/auth")
                .then().statusCode(200).extract().response();
        token = response.header("Authorization");
    }

    @Test
    public void getAllTest() {
        given()
                .header("Autorization", "Bearer " + token)
                .when().get("/cidades")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {

        EstadoDTO estado = new EstadoDTO("Sao Paulo", "SP");

        CidadeDTO cidade = new CidadeDTO(
                null,
                1L,
                "Santos");

        given()
                .header("Autorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(cidade)
                .when().post("/cidades")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "id", is(null),
                        "idEstado", is(1L),
                        "nome", is("Santos"));

    }

    @Test
    public void testUpdate() {
        Long id = estadoService.create(new EstadoDTO("Sao Paulo", "SP")).id();
        CidadeDTO cidade = new CidadeDTO(
                null,
                1L,
                "Santos");

        // Criando outra cidade para atuailzacao
        CidadeDTO cidadeRequisicao = new CidadeDTO(
                null,
                1L,
                "Barrolandia");

        CidadeResponseDTO produtoAtualizado = cidadeService.update(id, cidadeRequisicao);

        given()
                .header("Autorization", "Bearer " + token)
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
                .header("Autorization", "Bearer " + token)
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
