package ru.effectivemobile.link_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effectivemobile.link_shortener.model.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

}
