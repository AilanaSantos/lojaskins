//package br.unitins;
//
//
//import br.unitins.dto.EnderecoDTO;
//import br.unitins.dto.TelefoneDTO;
//import br.unitins.dto.UsuarioDTO;
//import br.unitins.dto.UsuarioResponseDTO;
//import br.unitins.service.UsuarioService;
//import io.quarkus.test.junit.QuarkusTest;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.Test;
//
//import jakarta.inject.Inject;
//
//import java.util.ArrayList;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//@QuarkusTest
//public class UsuarioResourceTest {
//
//    @Inject
//    UsuarioService usuarioService;
//
//    @Test
//    public void getAllTest() {
//        given()
//                .when().get("/usuario")
//                .then()
//                .statusCode(200);
//    }
//
//    @Test
//    public void testInsert() {
//
//        var telefones = new ArrayList<TelefoneDTO>();
//        telefones.add(new TelefoneDTO("63", "9985438645"));
//
//        var enderecos = new ArrayList<EnderecoDTO>();
//        enderecos.add(new EnderecoDTO("Rua Brasil,Setor Aero", "7665000", "Rua Brasil", "Setor Aero", "2867", "casa", "Rua Brasil, Setor Aero, numero: 2867"));
//
//        UsuarioDTO usuario = new UsuarioDTO(
//                telefones,
//                enderecos,
//                "08734567867",
//                "joyce@gmail.com",
//                "095684848",
//                "Joyce Aguiar",
//                1);
//
//        UsuarioResponseDTO usuarioCriado = usuarioService.create(usuario);
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(usuarioCriado)
//                .when().post("/usuario")
//                .then()
//                .statusCode(201)
//                .body("id", notNullValue(),
//                        "nome", is("Joyce Aguiar"),
//                        "email", is("joyce@gmail.com"),
//                        "senha", is("095684848"),
//                        "cpf", is("08734567867"));
//
//    }
//
//    @Test
//    public void testUpdate() {
//
//
//        var telefones = new ArrayList<TelefoneDTO>();
//        telefones.add(new TelefoneDTO("63", "9985438645"));
//
//        var enderecos = new ArrayList<EnderecoDTO>();
//        enderecos.add(new EnderecoDTO("Rua Brasil,Setor Aero", "7665000", "Rua Brasil", "Setor Aero", "2867", "casa", "Rua Brasil, Setor Aero, numero: 2867"));
//
//
//        UsuarioDTO usuario = new UsuarioDTO(
//                telefones,
//                enderecos,
//                "023-304-456-87",
//                "joyce@gmail.com",
//                "8765432",
//                "Joyce Aguiar",
//                1);
//
//        Long id = usuarioService.create(usuario).id();
//
//
//
//        UsuarioDTO usuarioRequisicao = new UsuarioDTO(
//                telefones,
//                enderecos,
//                "023-304-456-87",
//                "joyce@gmail.com",
//                "8765432",
//                "Joyce Araujo",
//                1);
//
//        UsuarioResponseDTO usuarioAtualizado = usuarioService.update(id, usuarioRequisicao);
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(usuarioAtualizado)
//                .when().put("/usuario/" + id)
//                .then()
//                .statusCode(200);
//
//        // Verificando se os dados foram atualizados no banco de dados
//        UsuarioResponseDTO usuarioResponse = usuarioService.findById(id);
//        assertThat(usuarioResponse.telefones(), notNullValue());
//        assertThat(usuarioResponse.enderecos(), notNullValue());
//        assertThat(usuarioResponse.cpf(), is("023-304-456-87"));
//        assertThat(usuarioResponse.email(), is("joyce@gmail.com"));
//        assertThat(usuarioResponse.senha(), is("8765432"));
//        assertThat(usuarioResponse.nome(), is("Joyce Araujo"));
//        assertThat(usuarioResponse.sexo(), is(2));
//
//    }
//
//    @Test
//    public void testDelete() {
//
//        var telefones = new ArrayList<TelefoneDTO>();
//        telefones.add(new TelefoneDTO("63", "9985438645"));
//
//        var enderecos = new ArrayList<EnderecoDTO>();
//        enderecos.add(new EnderecoDTO("Rua Brasil,Setor Aero", "7665000", "Rua Brasil", "Setor Aero", "2867", "casa", "Rua Brasil, Setor Aero, numero: 2867"));
//
//        UsuarioDTO usuario = new UsuarioDTO(
//                telefones,
//                enderecos,
//                "023-304-456-87",
//                "joyce@gmail.com",
//                "8765432",
//                "Joyce Araujo",
//                1);
//
//        Long id = usuarioService.create(usuario).id();
//
//        given()
//                .when().delete("/usuario/" + id)
//                .then()
//                .statusCode(204);
//
//        // verificando se a pessoa fisica foi excluida
//        UsuarioResponseDTO usuarioResponse = null;
//        try {
//            usuarioResponse = usuarioService.findById(id);
//        } catch (Exception e) {
//            assert true;
//        } finally {
//            assertNull(usuarioResponse);
//        }
//    }
//}
