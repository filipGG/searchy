package com.searchy.app;

import com.searchy.app.services.search.search_providers.bing.BingResponseParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BingResponseParserTests {
    @Test
    void contextLoads() {
    }

    @Test
    void should_parse_no_of_results_to_long() {
        var input = "<span class=\"sb_count\">About 30,700 results</span>";
        var result = new BingResponseParser().parse(input);

        assertEquals(30700L, result);
    }

    @Test
    void should_return_0_when_no_results_found() {
        var input = "<li class=\"b_no\" data-bm=\"4\"><h1>There are no results for <strong data-bm=\"23\">\"&gt;ASdaskldkhj\"</strong></h1><ul>";
        var result = new BingResponseParser().parse(input);

        assertEquals(0L, result);
    }

    @Test
    void should_thrown_when_we_cant_parse() {
        var input = "ajdfslköajlökdfsjlöakdfsjölkadfsjölkadfs";
        boolean exceptionThrown = false;
        try {
            new BingResponseParser().parse(input);
        }
        catch (Exception ex) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);
    }

    @Test
    void should_handle_K_indicator_for_thousands() {
        var input = "<span class=\"sb_count\">About 33K results</span>";
        var result = new BingResponseParser().parse(input);

        assertEquals(33000, result);
    }

    @Test
    void should_handle_M_indicator_for_millions() {
        var input = "<span class=\"sb_count\">About 96M results</span>";
        var result = new BingResponseParser().parse(input);

        assertEquals(96000000, result);
    }

    @Test
    void should_handle_K_indicator_with_dot_for_thousands() {
        var input = "<span class=\"sb_count\">About 33.4K results</span>";
        var result = new BingResponseParser().parse(input);

        assertEquals(33400, result);
    }

    @Test
    void should_handle_M_indicator_with_dot_for_millions() {
        var input = "<span class=\"sb_count\">About 1.2M results</span>";
        var result = new BingResponseParser().parse(input);

        assertEquals(1200000, result);
    }
}
