package dev.poporo.course.ch04.datasource.problemz.repository;

import dev.poporo.course.ch04.datasource.problemz.entity.UserzToken;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserzTokenRepository extends CrudRepository<UserzToken, UUID> {

}
