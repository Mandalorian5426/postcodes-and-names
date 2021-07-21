package dev.liam.postcodesandnames.controllers;


import dev.liam.postcodesandnames.models.Postcode__Name;
import dev.liam.postcodesandnames.services.Postcode__NameService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

@RestController
public class NameRequestHandler {
    @Autowired
    private  Postcode__NameService databaseService;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Name_Get_Response {
        String[] names;
        int totalChars;
    }

    @GetMapping("/name")
    @ResponseBody
    public ResponseEntity<Name_Get_Response> get(
            @RequestParam(value = "start") String startPostcode,
            @RequestParam(value = "end") String endPostcode) {
        // Validate Request Params
        int parsedStartPostcode = Integer.parseInt(startPostcode);
        int parsedEndPostcode = Integer.parseInt(endPostcode);

        if (parsedStartPostcode > parsedEndPostcode || parsedStartPostcode < 0 || parsedEndPostcode < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<Postcode__Name> allNames = databaseService.getAll();

        // Extract response out of data in db
        Supplier<Stream<String>> namesInPostcodeRange = () -> allNames.stream()
                .filter(postcode__name -> postcode__name.withinPostCodeRange(parsedStartPostcode, parsedEndPostcode))
                .map(Postcode__Name::getName)
                .distinct()
                .sorted();

        String[] namesArr = namesInPostcodeRange.get().toArray(String[]::new);
        int numChars = namesInPostcodeRange.get()
                .mapToInt(String::length)
                .reduce(0, Integer::sum);

        // Return the response
        return ResponseEntity.ok(new Name_Get_Response(namesArr, numChars));
    }
}
