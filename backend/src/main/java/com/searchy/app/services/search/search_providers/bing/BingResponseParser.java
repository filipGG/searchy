package com.searchy.app.services.search.search_providers.bing;

import org.springframework.stereotype.Service;

@Service
public class BingResponseParser {
    public long parse(String response) {
        if (isNoResultsFoundPage(response)) {
            return 0;
        }

        var result = response
            .split("<span class=\"sb_count\">About ")[1]
            .split(" results</span>")[0]
            .replace(",", "");

        result = replaceK(result);
        result = replaceM(result);

        result = result.replace(".", "");

        return Long.parseLong(result);
    }

    private boolean isNoResultsFoundPage(String response) {
        return response.contains("class=\"b_no\"");
    }

    private String replaceK(String input) {
        if (!input.contains("K")) {
            return input;
        }

        if (input.contains(".")) {
            return input.replace("K", "00");
        }

        return input.replace("K", "000");
    }

    private String replaceM(String input) {
        if (!input.contains("M")) {
            return input;
        }

        if (input.contains(".")) {
            return input.replace("M", "00000");
        }

        return input.replace("M", "000000");
    }
}
