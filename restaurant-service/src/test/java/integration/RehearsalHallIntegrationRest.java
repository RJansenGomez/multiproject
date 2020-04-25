package integration;

import com.rjansen.multiproject.restaurant.RestaurantApplication;
import com.rjansen.multiproject.restaurant.entrypoints.rest.v1.json.RehearsalHallJsonResponse;
import integration.utils.TestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = RestaurantApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(
        locations = "classpath:application-integrationtest.yml")
public class RehearsalHallIntegrationRest {

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    private final int port;

    @Autowired
    RehearsalHallIntegrationRest(@LocalServerPort int port) {
        this.port = port;
    }

    private static final String BASE_REHEARSAL_URI_V1 = "/musche/rehearsalhall/v1";

    @BeforeEach
    void setup() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Nested
    @DisplayName("GET Requests V1")
    class GetRequests {

        @Test
        @DisplayName("Get a rehearsal hall by name")
        void get_rehearsal_hall_by_name() {
            String name = "rehearsal";
            ResponseEntity<List<RehearsalHallJsonResponse>> response = restTemplate.exchange(
                    TestUtils.createURLWithPort(port, BASE_REHEARSAL_URI_V1 + "?name=" + name),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<RehearsalHallJsonResponse>>() {
                    }
            );
            List<RehearsalHallJsonResponse> expectedValue = generateExpectedList(name);
            TestUtils.validateListOK(expectedValue,response);

        }

        private List<RehearsalHallJsonResponse> generateExpectedList(String name) {
            return Collections.emptyList();
        }
    }
}
