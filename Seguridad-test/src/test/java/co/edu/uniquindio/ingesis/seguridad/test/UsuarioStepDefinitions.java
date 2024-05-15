package co.edu.uniquindio.ingesis.seguridad.test;

import co.edu.uniquindio.ingesis.seguridad.dto.Usuario;
import co.edu.uniquindio.ingesis.seguridad.model.RequestLogin;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class UsuarioStepDefinitions {

    private static final String RUTA_URL = "http://localhost:8080/usuarios";
    private final Faker faker = new Faker();
    private Usuario usuario;
    private Response response;
    private final String cedulaFaker = "0000";
    private RequestLogin requestLogin;

    public String LoginRolAdmin(){
        RequestLogin requestLogin = new RequestLogin("1", "1");
        baseURI = "http://localhost:8080/login";
        response = given().contentType(ContentType.JSON)
                .body(requestLogin).when().post();
        return  response.getBody().asString();
    }

    public String LoginRolBasico(){
        RequestLogin requestLogin = new RequestLogin("2222@gmail.com", "2222");
        baseURI = "http://localhost:8080/login";
        response = given().contentType(ContentType.JSON)
                .body(requestLogin).when().post();
        return  response.getBody().asString();
    }

    @Given("un usuario no registrado")
    public void unUsuarioNoRegistrado() {
        usuario = new Usuario(
                cedulaFaker,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                "BASICO"
        );
    }

    @When("se envia una solicitud de registro con datos validos")
    public void seEnviaunaSolicitudDeRegistroConDatosValidos() {
        String token = LoginRolAdmin();

        baseURI = RUTA_URL + "/crear";
        response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(usuario).when().put();
    }

    @Then("el usuario se registra correctamente")
    public void elUsuarioSeRegistraCorrectamente() {
        response.then().statusCode(HttpStatus.SC_CREATED);

    }

    @And("se valida la estructura de la respuesta json")
    public void seValidaLaRespuestaJson() throws ProcessingException {
        String ruta = "usuario.json";
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(ruta));
    }



    @Given("usuario existente")
    public void usuarioExistente() {
        usuario = new Usuario(
                cedulaFaker,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                "BASICO"
        );
    }

    @When("se envia una solicitud con los datos actualizados")
    public void seEnviaUnaSolicitudConLosDatosActualizados() {
        String token = LoginRolAdmin();

        baseURI = RUTA_URL + "/actualizar";
        response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(usuario).when().patch();
    }

    @Then("el usuario se actualiza correctamente")
    public void elUsuarioSeActualizaCorrectamente() {
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @When("se envia una solicitud para eliminar el usuario")
    public void seEnviaUnaSolicitudParaEliminarElUsuario() {
        String token = LoginRolAdmin();

        baseURI = RUTA_URL + "/eliminar";
        response = given().contentType(ContentType.JSON)
                .queryParam("cedula", cedulaFaker)
                .header("Authorization", token)
                .when().delete();
    }

    @Then("el usuario es eliminado correctamente")
    public void elUsuarioEsEliminadoCorrectamente() {
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Given("usuario existente define nuevo password")
    public void unUsuarioExistente() {
        String passwordFaker = faker.internet().password();
        requestLogin = new RequestLogin("2222@gmail.com", passwordFaker);
    }

    @When("se envia una solicitud con password actualizado")
    public void seEnviaUnaSolicitudConPasswordActualizado() {
        String token = LoginRolAdmin();

        baseURI = RUTA_URL + "/actualizarPassword";
        response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(requestLogin)
                .when().patch();
    }

    @Then("el password se actualiza correctamente")
    public void elPasswordSeActualizaCorrectamente() {
        response.then().statusCode(HttpStatus.SC_OK);
    }


}
