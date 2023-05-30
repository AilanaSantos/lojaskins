package br.unitins;

import br.unitins.dto.EnderecoDTO;
import br.unitins.dto.EnderecoResponseDTO;
import br.unitins.service.EnderecoService;
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
public class EnderecoResourceTest {

    @Inject
    EnderecoService enderecoService;

    @Test
    public void getAllTest() {
        given()
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

        EnderecoResponseDTO produtoCriado = enderecoService.create(endereco);

        given()
                .contentType(ContentType.JSON)
                .body(produtoCriado)
                .when().post("/enderecos")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "principal", is("Skin"),
                        "cep", is("Dragao"),
                        "rua", is(10),
                        "bairro", is(40.00F));

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
        EnderecoDTO enderecoRequisicao = new EnderecoDTO(
                "Rua Salomao",
                "75670000",
                "Rua Salomao",
                "Setor Juventus",
                "1786",
                "Casa",
                "Rua Salomao, Setor Juventus");

        EnderecoResponseDTO enderecoAtualizado = enderecoService.update(id, enderecoRequisicao);

        given()
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
