package com.example.nabivach;


import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Optional;


/**
 * Created by anastasiia_911 on 6/26/18.
 */
public interface SearchEngine {

    Optional<Element> findBestMatch(Attributes attributes, Document document);
}
