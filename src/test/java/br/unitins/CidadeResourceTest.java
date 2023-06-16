package br.unitins;

import br.unitins.dto.AuthUsuarioDTO;
import br.unitins.dto.cidade.CidadeDTO;
import br.unitins.dto.cidade.CidadeResponseDTO;
import br.unitins.dto.estado.EstadoDTO;
import br.unitins.model.Estado;
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
                var auth = new AuthUsuarioDTO("Ailana", "123");
                Response response = (Response) given()
                                .contentType("application/json")
                                .body(auth).when()
                                .post("/auth")
                                .then().statusCode(200).extract().response();
                token = response.header("Authorization");
        }

        @Test
        public void testGetAll() {
                given()
                                .header("Authorization", "Bearer " + token)
                                .when().get("/cidades")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {

                Long id = estadoService.create(new EstadoDTO("Sao Paulo", "SP")).id();
                CidadeDTO cidade = new CidadeDTO("Santos",id);

                given()
                                .header("Authorization", "Bearer " + token)
                                .contentType(ContentType.JSON)
                                .body(cidade)
                                .when().post("/cidades")
                                .then()
                                .statusCode(201)
                                .body("id", notNullValue(),
                                                "nome", is("Santos"),
                                                "estado", notNullValue(Estado.class));
        }

        @Test
        public void testUpdate() {
                Long id = estadoService.create(new EstadoDTO("Sao Paulo", "SP")).id();
                CidadeDTO cidade = new CidadeDTO("Santos",id);
                Long idCidade = cidadeService.create(cidade).id();

                // Criando outra cidade para atuailzacao
                CidadeDTO cidadeAtualizada = new CidadeDTO("Barrolandia",id);

                given()
                                .header("Authorization", "Bearer " + token)
                                .contentType(ContentType.JSON)
                                .body(cidadeAtualizada)
                                .when().put("/cidades/" + idCidade)
                                .then()
                                .statusCode(204);

                // Verificando se os dados foram atualizados no banco de dados
                CidadeResponseDTO cidadeResponse = cidadeService.findById(id);
                assertThat(cidadeResponse.nome(), is("Barrolandia"));
                assertThat(cidadeResponse.estado(), notNullValue());
        }

        @Test
        public void testDelete() {
                 Long id = estadoService.create(new EstadoDTO("Tocantins", "TO")).id();
                CidadeDTO cidade = new CidadeDTO("Santos",id);
                Long idCidade = cidadeService.create(cidade).id();

                given()
                                .header("Authorization", "Bearer " + token)
                                .when().delete("/cidades/" + idCidade)
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
