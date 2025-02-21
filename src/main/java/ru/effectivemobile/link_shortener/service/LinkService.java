package ru.effectivemobile.link_shortener.service;

import ru.effectivemobile.link_shortener.dto.FullLink;
import ru.effectivemobile.link_shortener.dto.ExistsShortLink;
import ru.effectivemobile.link_shortener.dto.ShortLink;
import ru.effectivemobile.link_shortener.dto.UpdateLink;

public interface LinkService {

    FullLink getFullLink(ExistsShortLink shortLink);

    ShortLink createShortLink(FullLink fullLink);

    ShortLink updateShortLink(ExistsShortLink shortLink, UpdateLink fullLink);
}
