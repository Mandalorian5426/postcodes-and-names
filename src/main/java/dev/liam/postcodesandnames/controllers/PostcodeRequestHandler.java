package dev.liam.postcodesandnames.controllers;

import dev.liam.postcodesandnames.models.Postcode__Name;
import dev.liam.postcodesandnames.services.Postcode__NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostcodeRequestHandler {
    @Autowired
    private Postcode__NameService databaseService;

    @PostMapping("/postcode")
    public ResponseEntity<Postcode__Name[]> post(@RequestBody Postcode__Name[] tuples) {
        databaseService.insert(tuples);
        return ResponseEntity.status(HttpStatus.CREATED).body(tuples);
    }
}
