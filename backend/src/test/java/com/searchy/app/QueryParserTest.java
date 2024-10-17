package com.searchy.app;

import com.searchy.app.services.search.QueryParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QueryParserTest {
    @Test
    void contextLoads() {
    }

    @Test
    void should_split_into_separate_strings_on_whitespaces() {
        var input = "Hello There";
        var result = new QueryParser().parse(input);

        assertEquals(2, result.size());
        assertEquals("Hello", result.getFirst());
        assertEquals("There", result.get(1));
    }

    @Test
    void should_remove_excess_whitespace() {
        var input = "    Hello                                There ";
        var result = new QueryParser().parse(input);

        assertEquals(2, result.size());
        assertEquals("Hello", result.getFirst());
        assertEquals("There", result.get(1));
    }
}
