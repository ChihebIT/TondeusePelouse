package com.socgen.tondeusepelousemvc.tondeusepelouse.service;

import com.socgen.tondeusepelousemvc.tondeusepelouse.TondeusepelouseApplication;
import com.socgen.tondeusepelousemvc.tondeusepelouse.model.MaxDimensionsPelouse;
import com.socgen.tondeusepelousemvc.tondeusepelouse.serivce.TondeuseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = TondeusepelouseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TondeuseServiceTU {

    @Autowired
    private TondeuseService tondeuseService;

    @Test
    void should_return_max_dimensions_pelouse() throws IOException {
        ClassPathResource resource = new ClassPathResource(("/inputTest.txt"));
        Path path = resource.getFile().toPath();
        BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
        MaxDimensionsPelouse expected = MaxDimensionsPelouse.builder().maxX(5).maxY(5).build();
        MaxDimensionsPelouse result = tondeuseService.getMaxDimensionsPelouse(reader);
        assertEquals(result, expected);
    }

    @Test
    void should_return_final_positions_tondeuses() throws IOException {
        ClassPathResource resource = new ClassPathResource(("/inputTest.txt"));
        Path path = resource.getFile().toPath();
        BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
        String expectedOutput = "1 3 N\n5 1 E\n";
        String result = tondeuseService.execution(reader);
        assertEquals(expectedOutput, result);
    }

}
