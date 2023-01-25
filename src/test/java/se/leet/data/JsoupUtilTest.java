package se.leet.data;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class JsoupUtilTest {

    private static final String DOMAIN = "http://books.toscrape.com/";

    @ParameterizedTest
    @ValueSource(strings = {"index.html", "catalogue/a-light-in-the-attic_1000/index.html"})
    void verifyReferencesAreFound(String path) {
        // given
        var page = JsoupUtil.getPage(DOMAIN, path);
        // when
        var resources = JsoupUtil.getResources(page);
        var links = JsoupUtil.getLinks(page);
        // then
        assertFalse(resources.isEmpty());
        assertFalse(links.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("nextPageArguments")
    void verifyNextPageIsFound(String path, String expected) {
        // given
        var page = JsoupUtil.getPage(DOMAIN, path);
        // when
        var result = JsoupUtil.getNextPage(page);
        // then
        assertEquals(expected, result.orElse(null));
    }

    static Stream<Arguments> nextPageArguments() {
        return Stream.of(
                Arguments.arguments("index.html", "catalogue/page-2.html"),
                Arguments.arguments("catalogue/a-light-in-the-attic_1000/index.html", null)
        );
    }
}