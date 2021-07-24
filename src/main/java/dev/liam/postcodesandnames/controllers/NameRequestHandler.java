package dev.liam.postcodesandnames.controllers;

import dev.liam.postcodesandnames.models.dataTransferObjects.NameGetResponse;
import dev.liam.postcodesandnames.services.PostcodeNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;
import java.util.stream.Stream;

@RestController
public class NameRequestHandler {
    @Autowired
    private PostcodeNameService postcodeNameService;

    @GetMapping("/name")
    @ResponseBody
    public ResponseEntity<NameGetResponse> get(
            @RequestParam(value = "start") int startPostcode,
            @RequestParam(value = "end") int endPostcode) {

        if (startPostcode > endPostcode || startPostcode < 0 || endPostcode < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Supplier<Stream<String>> namesInPostcodeRange = postcodeNameService
                .getAllNamesWithinPostcodeRange(startPostcode, endPostcode);

        return ResponseEntity.ok(new NameGetResponse(namesInPostcodeRange));
    }
}
