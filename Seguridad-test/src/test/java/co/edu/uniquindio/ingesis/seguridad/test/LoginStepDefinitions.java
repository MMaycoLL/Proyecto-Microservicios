package co.edu.uniquindio.ingesis.seguridad.test;

import co.edu.uniquindio.ingesis.seguridad.dto.Usuario;
import co.edu.uniquindio.ingesis.seguridad.model.RequestLogin;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class LoginStepDefinitions {

    private static final String RUTA_URL = "http://localhost:8080/login";
    private final Faker faker = new Faker();
    private Usuario usuario;
    private Response response;
    private RequestLogin requestLogin;

    @Given("un usuario existente")
    public void unUsuarioExistente() {
        requestLogin = new RequestLogin("1", "1");
    }

    @When("se envia una solicitud de login con datos validos")
    public void seEnviaUnaSolicitudDeLoginConDatosValidos() {
        baseURI = "http://localhost:8080/login";
        response = given().contentType(ContentType.JSON)
                .body(requestLogin).when().post();
    }

    @Then("el usuario se loguea correctamente")
    public void elUsuarioSeLogueaCorrectamente() {
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Given("usuario no registrado")
    public void usuarioNoRegistrado() {
        requestLogin = new RequestLogin("0", "1");
    }

    @When("se envia una solicitud con datos no registrados")
    public void seEnviaUnaSolicitudConDatosNoRegistrados() {
        baseURI = "http://localhost:8080/login";
        response = given().contentType(ContentType.JSON)
                .body(requestLogin).when().post();
    }

    @Then("el usuario no se loguea")
    public void elUsuarioNoSeLoguea() {
        response.then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
