package br.unitins;

import br.unitins.dto.estado.EstadoDTO;
import br.unitins.dto.estado.EstadoResponseDTO;
import br.unitins.service.estado.EstadoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;




import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class EstadoResourceTest {

    @Inject
    EstadoService estadoService;

    @Test
    public void getAllTest() {
        given()
                .when().get("/estados")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        EstadoDTO estado = new EstadoDTO(
                "Porto de Galinhas",
                "PE");


        EstadoResponseDTO estadoCriado = estadoService.create(estado);

        given()
                .contentType(ContentType.JSON)
                .body(estadoCriado)
                .when().post("/estados")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("Porto de Galinhas"),
                        "sigla", is("PE"));

    }

    @Test
    public void testUpdate() {
        EstadoDTO estado = new EstadoDTO(
                "Porto de Galinhas",
                "PE");

        Long id = estadoService.create(estado).id();

        // Criando outra cidade para atuailzacao
        EstadoDTO estadoRequisicao = new EstadoDTO(
                "Maranhao",
                "MA");

        EstadoResponseDTO estadoAtualizado = estadoService.update(id, estadoRequisicao);

        given()
                .contentType(ContentType.JSON)
                .body(estadoAtualizado)
                .when().put("/estados/" + id)
                .then()
                .statusCode(200);

        // Verificando se os dados foram atualizados no banco de dados
        EstadoResponseDTO estadoResponse = estadoService.findById(id);
        assertThat(estadoResponse.nome(), is("Maranhao"));
        assertThat(estadoResponse.sigla(), is("MA"));

    }

    @Test
    public void testDelete() {
        EstadoDTO estado = new EstadoDTO(
                "Maranhao",
                "Ma");

        Long id = estadoService.create(estado).id();


        given()
                .contentType(ContentType.JSON)
                .when().get("/estados/" + id)
                .then()
                .statusCode(200);

        // Deleta Estado
        estadoService.delete(id);

        //verificando se o Estado foi excluido

        EstadoResponseDTO estadoResponse = estadoService.findById(id);
        assertNull(estadoResponse);
    }



}



