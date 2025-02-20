package ru.effectivemobile.link_shortener.service;

import ru.effectivemobile.link_shortener.dto.FullLink;
import ru.effectivemobile.link_shortener.dto.NewShortLink;
import ru.effectivemobile.link_shortener.dto.ShortLink;
import ru.effectivemobile.link_shortener.dto.UpdateLink;

public interface LinkService {

    FullLink getFullLink(NewShortLink shortLink);

    ShortLink createShortLink(FullLink fullLink);

    ShortLink updateShortLink(NewShortLink shortLink, UpdateLink fullLink);


}
