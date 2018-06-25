package com.example.nabivach;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
public class DocumentReader {
    private static String CHARSET_NAME = "utf8";

    public Optional<Document> readFromFilePath(String filePath) {
        File file = new File(filePath);
        try {
            Document doc = Jsoup.parse(
                    file,
                    CHARSET_NAME,
                    file.getAbsolutePath());

            return Optional.of(doc);

        } catch (IOException e) {
            log.error("Error reading [{}] file", file.getAbsolutePath(), e);
            return Optional.empty();
        }

    }
}
