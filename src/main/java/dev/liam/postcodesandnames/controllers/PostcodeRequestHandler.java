package dev.liam.postcodesandnames.controllers;

import dev.liam.postcodesandnames.models.PostcodeName;
import dev.liam.postcodesandnames.services.PostcodeNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostcodeRequestHandler {
    @Autowired
    private PostcodeNameService databaseService;

    @PostMapping("/postcode")
    public ResponseEntity<PostcodeName[]> post(@RequestBody PostcodeName[] tuples) {
        databaseService.insert(tuples);
        return ResponseEntity.status(HttpStatus.CREATED).body(tuples);
    }
}
