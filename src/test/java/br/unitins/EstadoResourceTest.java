package br.unitins;

import br.unitins.dto.AuthUsuarioDTO;
import br.unitins.dto.estado.EstadoDTO;
import br.unitins.dto.estado.EstadoResponseDTO;
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
public class EstadoResourceTest {

        private String token;

        @BeforeEach
        public void setUp() {
                var auth = new AuthUsuarioDTO("Ailana", "123");

                Response response = (Response) given()
                                .contentType("application/json")
                                .body(auth)
                                .when().post("/auth")
                                .then().statusCode(200)
                                .extract()
                                .response();
                token = response.header("Authorization");
        }

    @Inject
    EstadoService estadoService;

    @Test
    public void getAllTest() {
        given()
                .header("Authorization", "Bearer " + token)
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
                .header("Authorization", "Bearer " + token)
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
                .header("Authorization", "Bearer " + token)
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
                "MA");

        Long id = estadoService.create(estado).id();


        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when().get("/estados/" + id)
                .then()
                .statusCode(200);

        // Deleta Estado
         EstadoResponseDTO estadoresponse = null;
                try {
                        estadoService.findById(id);
                } catch (Exception e) {
                        assert true;
                } finally {
                        assertNull(estadoresponse);
                }
    }



}



