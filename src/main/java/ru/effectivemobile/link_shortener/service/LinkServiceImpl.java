package ru.effectivemobile.link_shortener.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.effectivemobile.link_shortener.dto.FullLink;
import ru.effectivemobile.link_shortener.dto.ExistsShortLink;
import ru.effectivemobile.link_shortener.dto.ShortLink;
import ru.effectivemobile.link_shortener.model.Link;
import ru.effectivemobile.link_shortener.repository.LinkRepository;
import ru.effectivemobile.link_shortener.util.exception.exceptions.BadRequestException;
import ru.effectivemobile.link_shortener.util.exception.exceptions.ObjectNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@CacheConfig(cacheManager = "redisCacheManager")
@Transactional
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    @Override
    @Cacheable("databaseEntities")
    public String getFullLink(ExistsShortLink shortLink) {

        return this.linkFinder(shortLink.shortLink());
    }

    @Override
    @CacheEvict(value = "databaseEntities")
    @Transactional
    public ShortLink createShortLink(FullLink fullLink) {

        if (fullLink == null || fullLink.originalLink() == null) {
            throw  new ObjectNotFoundException("No link to shred!");
        }

        Link link = new Link();
        link.setOriginalLink(fullLink.originalLink());
        link.setCreatedAt(fullLink.createdAt());

        if (fullLink.expiredAt() != null) {
            link.setExpiredAt(fullLink.expiredAt());
        }

        String shortLink;
        try {
            shortLink =  convertByteArrayToHexString(MessageDigest.getInstance("SHA-1")
                    .digest(fullLink.originalLink().getBytes(StandardCharsets.UTF_8)));

        } catch (NoSuchAlgorithmException e) {
            throw new BadRequestException(e.getMessage());
        }

        link.setShortLink(shortLink);
        linkRepository.saveAndFlush(link);
        return new ShortLink(shortLink);
    }

    private String convertByteArrayToHexString(byte[] arrayBytes) {

        var stringBuffer = new StringBuilder();
        for (byte arrayByte : arrayBytes) {
            stringBuffer.append(Integer.toString((arrayByte & 0xff) + 0x100, 16)
                    .substring(1));
        }

        return stringBuffer.toString();
    }

    private String linkFinder(String link) {

        Link link1 = linkRepository.getByShortLink(link)
                .orElseThrow(() -> {
                    log.warn("No link!!");
                    return new ObjectNotFoundException("No link!!!");
                });

        if (link1.getExpiredAt().isBefore(LocalDateTime.now()) && link1.getExpiredAt() != null ) {
            log.warn("No link!");
            throw new ObjectNotFoundException("No link!");
        }

        return link1.getOriginalLink();
    }

    @Scheduled(fixedRate = 60, timeUnit = TimeUnit.MINUTES)
    private void cleaner() {

        log.info("DeadLinks will be wiped now!");
        linkRepository.deleteOnSchedule(LocalDateTime.now());
        log.info("DeadLinks are wiped now!");
    }
}


