package ru.effectivemobile.link_shortener.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.effectivemobile.link_shortener.dto.FullLink;
import ru.effectivemobile.link_shortener.dto.ExistsShortLink;
import ru.effectivemobile.link_shortener.dto.ShortLink;
import ru.effectivemobile.link_shortener.dto.UpdateLink;
import ru.effectivemobile.link_shortener.model.Link;
import ru.effectivemobile.link_shortener.repository.LinkRepository;
import ru.effectivemobile.link_shortener.util.exception.exceptions.BadRequestException;
import ru.effectivemobile.link_shortener.util.exception.exceptions.ObjectNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    @Override
    public FullLink getFullLink(ExistsShortLink shortLink) {

        return null;
    }

    @Override
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

    @Override
    public ShortLink updateShortLink(ExistsShortLink shortLink, UpdateLink fullLink) {

        return null;
    }

    private String convertByteArrayToHexString(byte[] arrayBytes) {

        var stringBuffer = new StringBuilder();

        for (byte arrayByte : arrayBytes) {
            stringBuffer.append(Integer.toString((arrayByte & 0xff) + 0x100, 16)
                    .substring(1));
        }

        return stringBuffer.toString();
    }
}
