package com.socgen.tondeusepelousemvc.tondeusepelouse.controller;

import com.socgen.tondeusepelousemvc.tondeusepelouse.TondeusepelouseApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = TondeusepelouseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TondeuseControllerIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void should_return_200_when_instructions_executed() throws Exception{
        MockMultipartFile file = new MockMultipartFile("file", new FileInputStream(new File("src/test/resources/inputTest.txt")));
        String expected = "1 3 N\n5 1 E\n";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.multipart("/tondeuse/upload")
                .file(file);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        int responseStatusCode = mvcResult.getResponse().getStatus();
        String responseContent = mvcResult.getResponse().getContentAsString();
        assertEquals(responseContent, expected);
        assertEquals(200, responseStatusCode);
    }
}
