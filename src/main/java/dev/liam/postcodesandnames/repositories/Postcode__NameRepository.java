package dev.liam.postcodesandnames.repositories;

import dev.liam.postcodesandnames.models.Postcode__Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Postcode__NameRepository extends JpaRepository<Postcode__Name, Long> {
    // intentionally left blank
}
