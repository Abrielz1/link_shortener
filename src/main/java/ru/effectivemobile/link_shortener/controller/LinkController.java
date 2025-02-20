package ru.effectivemobile.link_shortener.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.effectivemobile.link_shortener.dto.FullLink;
import ru.effectivemobile.link_shortener.dto.NewShortLink;
import ru.effectivemobile.link_shortener.dto.ShortLink;
import ru.effectivemobile.link_shortener.dto.UpdateLink;
import ru.effectivemobile.link_shortener.service.LinkService;
import ru.effectivemobile.util.Create;
import ru.effectivemobile.util.Update;

@Slf4j
@Validated
@RestController
@RequestMapping("/link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FullLink getFullLink(@NotBlank @PathVariable(name = "id") NewShortLink shortLink) {

       return linkService.getFullLink(shortLink);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShortLink createShortLink(@Validated(Create.class)@RequestBody FullLink fullLink) {

        return linkService.createShortLink(fullLink);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShortLink updateShortLink(@NotBlank @PathVariable(name = "id") NewShortLink shortLink,
                               @Validated(Update.class)@RequestBody UpdateLink fullLink) {

        return linkService.updateShortLink(shortLink, fullLink);
    }
}
