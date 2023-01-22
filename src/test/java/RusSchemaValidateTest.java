import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;

public class RusSchemaValidateTest {
    @Test
    public void SchemaValidateTest() {
        String path = "https://restcountries.com/v2/alpha/rus";
        given().
                when().
                get(path).
                then().
                assertThat().
                contentType(ContentType.JSON).
                body(matchesJsonSchemaInClasspath("schemaForRus.json"));
    }
}