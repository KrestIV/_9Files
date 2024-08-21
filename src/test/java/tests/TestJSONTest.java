package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Organization;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class TestJSONTest {

    private final ClassLoader classLoader = TestJSONTest.class.getClassLoader();

    @Test
    public void jsonFileShouldContainDataTest() throws Exception {
        try (InputStream js = classLoader.getResourceAsStream("organization.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Organization org = objectMapper.readValue(js, Organization.class);
            assertThat(org.getOrganizationId()).isEqualTo("fb652eff-bbb6-4354-9c2d-cd44748af438");
            assertThat(org.getStatus()).isEqualTo("ACTIVATED");
            assertThat(org.getTimestamp()).isEqualTo(1723063416);
            assertThat(org.isContractSigned()).isEqualTo(false);
            assertThat(org.getBranchOffice().length).isEqualTo(3);
            assertThat(org.getBranchOffice()[0].getScope()).isEqualTo("main");
        }
    }
}
