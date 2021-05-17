package org.security.directory;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DirectoryControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DirectoryController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testProviderSearch() {
        assertThat(
                    this
                    .restTemplate
                    .getForObject("http://localhost:" + port + "/", String.class))
                .contains("Directory Search");

        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("zipCode", "21230");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        String response = this.restTemplate.postForObject("http://localhost:" + port + "/", request, String.class);

        //These two are public
        assertThat(response).contains("Helen");
        assertThat(response).contains("James");

        //This one is not
        assertThat(response).doesNotContain("Henry");
    }
}
