package com.socgen.tondeusepelousemvc.tondeusepelouse.controller;

import com.socgen.tondeusepelousemvc.tondeusepelouse.serivce.TondeuseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tondeuse")
public class TondeuseController {

    Logger logger = Logger.getLogger(TondeuseController.class.getName());
    private final TondeuseService tondeuseService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String result = tondeuseService.execution(reader);
        logger.info("Positions finales des tondeuses : " + result);
        return ResponseEntity.ok(result);
    }
}
