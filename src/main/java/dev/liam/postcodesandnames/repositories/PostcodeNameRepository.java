package dev.liam.postcodesandnames.repositories;

import dev.liam.postcodesandnames.models.PostcodeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostcodeNameRepository extends JpaRepository<PostcodeName, Long> {
}
