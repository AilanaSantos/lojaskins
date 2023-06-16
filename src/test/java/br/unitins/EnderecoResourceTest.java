package br.unitins;

import br.unitins.dto.AuthUsuarioDTO;
import br.unitins.dto.endereco.EnderecoDTO;
import br.unitins.dto.endereco.EnderecoResponseDTO;
import br.unitins.service.cidade.CidadeService;
import br.unitins.service.endereco.EnderecoService;
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
public class EnderecoResourceTest {

        @Inject
        EnderecoService enderecoService;

        @Inject
        CidadeService cidadeservice;

        @Inject
        EstadoService estadoservice;

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
                                .header("Authorization", "Bearer " + token)
                                .when().get("/enderecos")
                                .then()
                                .statusCode(200);

        }

        @Test
        public void testInsert() {
                EnderecoDTO endereco = new EnderecoDTO(
                                "Rua Brasil",
                                "77660000",
                                "Rua Brasil",
                                "Setor Aero",
                                "1786",
                                "Casa",
                                "Rua Brasil, Setor Aero");

                EnderecoResponseDTO enderecobanco = enderecoService.create(endereco);

                given()
                                .header("Authorization", "Bearer " + token)
                                .contentType(ContentType.JSON)
                                .body(enderecobanco)
                                .when().post("/enderecos")
                                .then()
                                .statusCode(201)
                                .body("id", notNullValue(),
                                                "principal", is("Rua Brasil"),
                                                "cep", is("77660000"),
                                                "rua", is("Rua Brasil"),
                                                "bairro", is("Setor Aero"),
                                                "numero", is("1786"),
                                                "complemento", is("Casa"),
                                                "logradouro", is("Rua Brasil, Setor Aero"));

        }

        @Test
        public void testUpdate() {
                EnderecoDTO endereco = new EnderecoDTO(
                                "Rua Brasil",
                                "77660000",
                                "Rua Brasil",
                                "Setor Aero",
                                "1786",
                                "Casa",
                                "Rua Brasil, Setor Aero");

                Long id = enderecoService.create(endereco).id();

                // Criando outra pessoa para atuailzacao
                EnderecoDTO enderecoUpdate = new EnderecoDTO(
                                "Rua Brasil",
                                "77660000",
                                "Rua Brasil",
                                "Setor Aero",
                                "1786",
                                "Casa",
                                "Rua Brasil, Setor Aero");

                EnderecoResponseDTO enderecoAtualizado = enderecoService.update(id, enderecoUpdate);

                given()
                                .header("Authorization", "Bearer " + token)
                                .contentType(ContentType.JSON)
                                .body(enderecoAtualizado)
                                .when().put("/enderecos/" + id)
                                .then()
                                .statusCode(200);

                // Verificando se os dados foram atualizados no banco de dados
                EnderecoResponseDTO enderecoResponse = enderecoService.findById(id);
                assertThat(enderecoResponse.principal(), is("Rua Salomao"));
                assertThat(enderecoResponse.cep(), is("75670000"));
                assertThat(enderecoResponse.rua(), is("Rua Salomao"));
                assertThat(enderecoResponse.bairro(), is("Setor Juventus"));
                assertThat(enderecoResponse.numero(), is("1786"));
                assertThat(enderecoResponse.complemento(), is("Casa"));
                assertThat(enderecoResponse.logradouro(), is("Rua Salomao, Setor Juventus"));
                
        }

        @Test
        public void testDelete() {
                EnderecoDTO endereco = new EnderecoDTO(
                                "Rua Brasil",
                                "77660000",
                                "Rua Brasil",
                                "Setor Aero",
                                "1786",
                                "Casa",
                                "Rua Brasil, Setor Aero");
                Long id = enderecoService.create(endereco).id();

                given()
                                .header("Authorization", "Bearer " + token)
                                .when().delete("/enderecos/" + id)
                                .then()
                                .statusCode(204);

                // verificando se a pessoa fisica foi excluida
                EnderecoResponseDTO enderecoResponse = null;
                try {
                        enderecoResponse = enderecoService.findById(id);
                } catch (Exception e) {
                        assert true;
                } finally {
                        assertNull(enderecoResponse);
                }
        }
}
