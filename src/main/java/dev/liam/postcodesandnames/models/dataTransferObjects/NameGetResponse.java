package dev.liam.postcodesandnames.models.dataTransferObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;
import java.util.stream.Stream;

@Getter
@Setter
public class NameGetResponse {
    private String[] names;
    private int totalChars;

    /**
     * Constructs the response for the GET /name endpoint. The endpoint
     * returns a list of unique names sorted alphabetically and the total
     * number of chars within the list of names.
     *
     * @param postcodeNames a Supplier of names to send in the response
     */
    public NameGetResponse (Supplier<Stream<String>> postcodeNames) {
        this.names = postcodeNames.get()
                .distinct()
                .sorted()
                .toArray(String[]::new);
        this.totalChars = postcodeNames.get()
                .mapToInt(String::length)
                .reduce(0, Integer::sum);
    }
}

