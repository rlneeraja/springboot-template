package gov.cdc.ncezid.eip.services.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by marcelo on 10/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@Profile("test")
//@SpringBootTest(classes = TestContextConfiguration.class)
public class InfoServiceControllerTest {

    private String rootAPIIURL;

    //TODO::Unable to read this from application.yml - Need solution
    @Value("${server.port:10000}")
    private String port ;

    @Before
    public void setUp() throws Exception {
        this.rootAPIIURL = "http://localhost:" + port + "/serviceContext/api/v1/";

    }

    @Test
    public void about() throws Exception {
        System.out.println("rootAPIIURL = " + rootAPIIURL);
        when().
                get(this.rootAPIIURL + "info/about").
        then().
                statusCode(200).
                body("summary", containsString("Spring REST services"));
    }

    @Test
    public void ping() throws Exception {
        when()
                .get(this.rootAPIIURL + "info/ping")
                .then()
                .statusCode(200)
                .body(containsString ("Hello There! I'm alive."));
    }
}

