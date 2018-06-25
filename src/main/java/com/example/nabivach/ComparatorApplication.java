package com.example.nabivach;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class ComparatorApplication implements CommandLineRunner {

    private final DocumentReader documentReader;
    private final SearchEngine searchEngine;
    private final ResultPrinter resultPrinter;
    @Value("${elementid}")
    private String targetElementId;

    public static void main(String[] args) {
        SpringApplication.run(ComparatorApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        String originalFilePath = strings[0];
        String comparableFilePath = strings[1];
        log.info("Starting application with parameters: {} {}", originalFilePath, comparableFilePath);

        Optional<Element> targetElement = documentReader.readFromFilePath(originalFilePath)
                .map(document -> document.getElementById(targetElementId));

        if (!targetElement.isPresent()) {
            log.info("No target element found in original file");
            return;
        }

        Optional<Document> document = documentReader.readFromFilePath(comparableFilePath);
        if (!document.isPresent()) {
            log.info("No file to compare - exiting application ... ");
            return;
        }
        searchEngine.findBestMatch(targetElement.get().attributes(), document.get())
                .ifPresent(resultPrinter::print);
    }

}
