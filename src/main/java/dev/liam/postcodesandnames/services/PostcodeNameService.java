package dev.liam.postcodesandnames.services;

import dev.liam.postcodesandnames.models.PostcodeName;
import dev.liam.postcodesandnames.repositories.PostcodeNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class PostcodeNameService {
    @Autowired
    private PostcodeNameRepository repository;

    public List<PostcodeName> getAll(){
        return repository.findAll();
    }

    /**
     * Return all Names (string) that have a postcode between startPostcode and endPostcode.
     *
     * @param startPostcode the smallest postcode
     * @param endPostcode the largest postcode
     * @return list of unique names sorted alphabetically
     */
    public Supplier<Stream<String>> getAllNamesWithinPostcodeRange(int startPostcode, int endPostcode) {
        return () -> this.getAll().stream()
            .filter(postcode__name -> postcode__name.withinPostCodeRange(startPostcode, endPostcode))
            .map(PostcodeName::getName)
            .distinct()
            .sorted();
    }

    public List<PostcodeName> insert(PostcodeName[] entities) {
        return repository.saveAll(Arrays.asList(entities));
    }
}
