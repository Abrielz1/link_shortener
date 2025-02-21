package ru.effectivemobile.link_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.effectivemobile.link_shortener.model.Link;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query(value = """
        SELECT *
        FROM links
        WHERE short_link = :shortLink
""", nativeQuery = true)
    Optional<Link> getByShortLink(@Param("shortLink") String shortLink);
}
