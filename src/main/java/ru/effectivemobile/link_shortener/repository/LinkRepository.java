package ru.effectivemobile.link_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.effectivemobile.link_shortener.model.Link;
import java.time.LocalDateTime;
import java.util.Optional;
import jakarta.persistence.LockModeType;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    boolean existsByOriginalLink(String originalLink);

    @Lock(LockModeType.OPTIMISTIC)
    @Query(name = """
            SELECT *
            FROM links
            WHERE original_link = : originalLink
            """, nativeQuery = true)
    Optional<Link> getByOriginalLink(@Param("originalLink") String originalLink);

    @Lock(LockModeType.OPTIMISTIC)
    @Query(value = """
        SELECT *
        FROM links
        WHERE short_link = :shortLink
                   """, nativeQuery = true)
    Optional<Link> getByShortLink(@Param("shortLink") String shortLink);

    @Modifying
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = """
        DELETE
        FROM links
        WHERE expired_at <= :itsTimeToDie
                   """, nativeQuery = true)
    void deleteOnSchedule(@Param("itsTimeToDie")LocalDateTime itsTimeToDie);
}
