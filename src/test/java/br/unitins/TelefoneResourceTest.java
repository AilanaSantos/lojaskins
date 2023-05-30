package br.unitins;


import br.unitins.dto.TelefoneDTO;
import br.unitins.dto.TelefoneResponseDTO;
import br.unitins.service.TelefoneService;
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
public class TelefoneResourceTest {

    @Inject
    TelefoneService telefoneService;

    @Test
    public void getAllTest() {
        given()
                .when().get("/telefone")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        TelefoneDTO telefone = new TelefoneDTO(
                "64",
                "984756362");


        TelefoneResponseDTO telefoneCriado = telefoneService.create(telefone);

        given()
                .contentType(ContentType.JSON)
                .body(telefoneCriado)
                .when().post("/telefone")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "codigoArea", is("64"),
                        "numero", is("984756362"));
    }

    @Test
    public void testUpdate() {
        TelefoneDTO telefone = new TelefoneDTO(
                "64",
                "984756362");

        Long id = telefoneService.create(telefone).id();

        // Criando outra pessoa para atuailzacao
        TelefoneDTO telefoneRequisicao = new TelefoneDTO(
                "88",
                "9844559988");

        TelefoneResponseDTO telefoneAtualizado = telefoneService.update(id, telefoneRequisicao);

        given()
                .contentType(ContentType.JSON)
                .body(telefoneAtualizado)
                .when().put("/telefone/" + id)
                .then()
                .statusCode(204);

        // Verificando se os dados foram atualizados no banco de dados
        TelefoneResponseDTO telefoneResponse = telefoneService.findById(id);
        assertThat(telefoneResponse.codigoarea(), is("88"));
        assertThat(telefoneResponse.numero(), is("9844559988"));

    }

    @Test
    public void testDelete() {
        TelefoneDTO telefone = new TelefoneDTO(
                "88",
                "9844559988");
        Long id = telefoneService.create(telefone).id();

        given()
                .when().delete("/telefone/" + id)
                .then()
                .statusCode(204);

        // verificando se a pessoa fisica foi excluida
        TelefoneResponseDTO telefoneResponse = null;
        try {
            telefoneResponse = telefoneService.findById(id);
        } catch (Exception e) {
            assert true;
        } finally {
            assertNull(telefoneResponse);
        }
    }
}
