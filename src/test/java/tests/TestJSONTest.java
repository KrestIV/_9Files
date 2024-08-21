package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Organization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class TestJSONTest {

    private final ClassLoader classLoader = TestJSONTest.class.getClassLoader();

    @Test
    public void jsonFileShouldContainDataTest() throws Exception {
        try (InputStream js = classLoader.getResourceAsStream("organization.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Organization org = objectMapper.readValue(js, Organization.class);
            Assertions.assertEquals("fb652eff-bbb6-4354-9c2d-cd44748af438", org.getOrganizationId());
            Assertions.assertEquals("ACTIVATED", org.getStatus());
            Assertions.assertEquals(1723063416, org.getTimestamp());
            Assertions.assertEquals(false, org.isContractSigned());
            Assertions.assertTrue(org.getBranchOffice().length == 3);
            Assertions.assertEquals("main",org.getBranchOffice()[0].getScope());
        }
    }
}
