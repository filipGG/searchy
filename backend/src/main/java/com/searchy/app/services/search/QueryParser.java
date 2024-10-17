package com.searchy.app.services.search;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QueryParser {
    public List<String> parse(String input) {
        var splitOnWhitespace = input.split(" ");
        return Arrays.stream(splitOnWhitespace)
            .filter(chunk -> !chunk.isEmpty())
            .toList();
    }
}
