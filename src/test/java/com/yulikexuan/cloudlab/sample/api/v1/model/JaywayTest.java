//: com.yulikexuan.cloudlab.sample.api.v1.model.JaywayTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JaywayTest {

    static final String STORE_JSON =
            "{ \"store\": { \"book\": [ { " +
                    "\"category\": \"reference\", \"author\": \"Nigel Rees\", " +
                    "\"title\": \"Sayings of the Century\", \"price\": 8.95 }, {" +
                    " \"category\": \"fiction\", \"author\": \"Evelyn Waugh\", " +
                    "\"title\": \"Sword of Honour\", \"price\": 12.99 }, { " +
                    "\"category\": \"fiction\", \"author\": \"Herman Melville\", " +
                    "\"title\": \"Moby Dick\", \"isbn\": \"0-553-21311-3\", " +
                    "\"price\": 8.99 }, { " +
                    "\"category\": \"fiction\", \"author\": \"J. R. R. Tolkien\", " +
                    "\"title\": \"The Lord of the Rings\", " +
                    "\"isbn\": \"0-395-19395-8\", \"price\": 22.99 } ], " +
                    "\"bicycle\": { \"color\": \"red\", \"price\": 19.95 } }, " +
                    "\"expensive\": 10 }";

    static Configuration jsonPathConfig;
    static ReadContext jsonReadContext;

    @BeforeAll
    static void setup() {
        jsonPathConfig = Configuration.builder()
                .mappingProvider(new JacksonMappingProvider()).build();
        jsonReadContext = JsonPath.using(jsonPathConfig).parse(STORE_JSON);
    }

    @DisplayName("Should Avoid JsonPath.read(...)")
    @Test
    void testReadJsonOnce() {

        // Given
        String allAuthor = "$.store.book[*].author";
        String[] expectedAuthors = {"Nigel Rees","Evelyn Waugh", "Herman Melville",
                "J. R. R. Tolkien"};

        // When
        List<String> actualAuthors = JsonPath.read(STORE_JSON, allAuthor);

        // Then
        assertThat(actualAuthors).as("Should list all authors")
                .containsExactly(expectedAuthors);
    }

    @Test
    void testWorkWithJackson() {

        // Given
        String bookPath = "$.store.book[0]";

        // When
//        Book book = jsonReadContext.read(bookPath);
    }

}///:~