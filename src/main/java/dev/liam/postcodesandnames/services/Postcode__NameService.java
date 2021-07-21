package dev.liam.postcodesandnames.services;

import dev.liam.postcodesandnames.models.Postcode__Name;
import dev.liam.postcodesandnames.repositories.Postcode__NameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class Postcode__NameService {
    @Autowired
    private Postcode__NameRepository repository;

    public List<Postcode__Name> getAll(){
        return repository.findAll();
    }

    public List<Postcode__Name> insert(Postcode__Name[] entities) {
        return repository.saveAll(Arrays.asList(entities));
    }
}
