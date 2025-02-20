package ru.effectivemobile.link_shortener.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.effectivemobile.link_shortener.dto.FullLink;
import ru.effectivemobile.link_shortener.dto.NewShortLink;
import ru.effectivemobile.link_shortener.dto.ShortLink;
import ru.effectivemobile.link_shortener.dto.UpdateLink;
import ru.effectivemobile.link_shortener.repository.LinkRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    @Override
    public FullLink getFullLink(NewShortLink shortLink) {

        return null;
    }

    @Override
    public ShortLink createShortLink(FullLink fullLink) {

        return null;
    }

    @Override
    public ShortLink updateShortLink(NewShortLink shortLink, UpdateLink fullLink) {

        return null;
    }


}
