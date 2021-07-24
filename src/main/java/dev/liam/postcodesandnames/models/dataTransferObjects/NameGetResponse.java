package dev.liam.postcodesandnames.models.dataTransferObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;
import java.util.stream.Stream;

@Getter
@Setter
public class NameGetResponse {
    String[] names;
    int totalChars;

    public NameGetResponse (Supplier<Stream<String>> postcodeNames) {
        this.names = postcodeNames.get().toArray(String[]::new);
        this.totalChars = postcodeNames.get()
                .mapToInt(String::length)
                .reduce(0, Integer::sum);
    }
}

