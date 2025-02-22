package ru.effectivemobile.link_shortener.service;

import ru.effectivemobile.link_shortener.dto.FullLink;
import ru.effectivemobile.link_shortener.dto.ExistsShortLink;
import ru.effectivemobile.link_shortener.dto.ShortLink;

public interface LinkService {

    String getFullLink(ExistsShortLink shortLink);

    ShortLink createShortLink(FullLink fullLink);
}
