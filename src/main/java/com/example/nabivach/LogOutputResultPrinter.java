package com.example.nabivach;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class LogOutputResultPrinter implements ResultPrinter {
    private static final String DELIMITER = " > ";

    @Override
    public void print(Element element) {
        List<Element> path = new ArrayList<>(element.parents());
        Collections.reverse(path);

        String result = path
                .stream()
                .map(Element::tag)
                .map(Tag::getName)
                .collect(Collectors.joining(DELIMITER))
                .concat(DELIMITER + element.tag());

        log.info("RESULT: {}", result);
    }

}
