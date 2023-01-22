import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusCodeTest {
    @Test
    public void StatusCodeTest() {
        Response response = RestAssured.get("https://restcountries.com/v2/alpha/rus");
        int statusCode = response.getStatusCode();
        double time = response.getTime();
        System.out.println("The response time is: " + time);
        Assert.assertEquals(statusCode, 200);
    }
}