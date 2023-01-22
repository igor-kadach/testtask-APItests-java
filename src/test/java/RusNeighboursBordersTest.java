import java.util.ArrayList;
import java.util.Iterator;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;

public class RusNeighboursBordersTest {
    @Test
    public void GetBordersList() {
        ArrayList<String> listNeighboursOfRus = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        try {
            Response responseRus = RestAssured.get("https://restcountries.com/v2/alpha/rus");
            String bodyRus = responseRus.getBody().asString();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(bodyRus);
            JSONArray arrayNeighboursRus = (JSONArray) jsonObject.get("borders");
                for (int i = 0; i < arrayNeighboursRus.size(); i++) {
                    listNeighboursOfRus.add(arrayNeighboursRus.get(i).toString());
            }

            Iterator<String> iterator = listNeighboursOfRus.iterator();
            while (iterator.hasNext()) {
                String path = "https://restcountries.com/v2/alpha/" + iterator.next();

                given().
                        when().
                        get(path).
                        then().
                        assertThat().
                        contentType(ContentType.JSON).
                        body("borders", hasItems("RUS"));

                System.out.println(path + " has RUS as a neighbour");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}