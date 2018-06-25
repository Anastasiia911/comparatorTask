package com.example.nabivach;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.jsoup.nodes.Attributes;

@Component
public class MatchesCountSearchEngine implements SearchEngine {

    @Override
    public Optional<Element> findBestMatch(Attributes attributes, Document document) {
        Map<Element, Integer> matchingElements = new HashMap<>();
        attributes.forEach(
                attribute ->
                        document
                                .getElementsByAttributeValue(attribute.getKey(), attribute.getValue())
                                .forEach(e -> matchingElements
                                        .compute(e, (key, value) -> value == null ? 1 : value + 1))
        );

        return matchingElements.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst();
    }
}
