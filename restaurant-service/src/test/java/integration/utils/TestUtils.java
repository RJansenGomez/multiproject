package integration.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtils {
    public static String createURLWithPort(int port, String uri) {
        return "http://localhost:" + port + uri;
    }

    public static void validateListOK(List<?> expectedList, ResponseEntity<?> responseToValidate) {
        assertEquals(HttpStatus.OK, responseToValidate.getStatusCode());
        List<?> bodyList = (List<?>) responseToValidate.getBody();
        assertNotNull(bodyList);
        assertFalse(bodyList.isEmpty());
        if (expectedList.getClass().isInstance(bodyList.getClass())) {
            expectedList.forEach(expectedValueSingle -> {
                if (!bodyList.contains(expectedValueSingle)) {
                    fail("Value expected not found :" + expectedValueSingle);
                }
            });
        } else {
            fail("Can't validate, not same instance");
        }
    }
}
