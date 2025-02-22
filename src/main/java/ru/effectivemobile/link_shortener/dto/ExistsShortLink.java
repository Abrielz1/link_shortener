package ru.effectivemobile.link_shortener.dto;

import jakarta.validation.constraints.NotBlank;

public record ExistsShortLink(@NotBlank String shortLink) {
}
