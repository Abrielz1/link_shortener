package ru.effectivemobile.link_shortener.util.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "app.cache")
public class AppCacheProperties {

    private final List<String> cacheNames = new ArrayList<>();

    private final Map<String, CacheProperties> cachePropertiesMap = new HashMap<>();

    @Data
    public static class CacheProperties {
        private Duration expiry = Duration.ofHours(1);
    }

    public interface CacheNames {
        String DATABASE_ENTITIES = "databaseEntities";

        String DATABASE_ENTITY_BY_NAME = "databaseEntityByName";
    }
}
