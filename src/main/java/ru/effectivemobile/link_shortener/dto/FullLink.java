package ru.effectivemobile.link_shortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record FullLink(@NotBlank String originalLink,
                       @NotNull LocalDateTime createdAt,
                       LocalDateTime expiredAt) {
}
