package se.leet.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;

class JsoupUtilTest {

    private static final String DOMAIN = "http://books.toscrape.com/";

    @ParameterizedTest
    @ValueSource(strings = {"index.html", "catalogue/a-light-in-the-attic_1000/index.html"})
    void verifyReferencesAreFound(String path) {
        // given
        var page = JsoupUtil.getPage(DOMAIN + path);
        // when
        var resources = JsoupUtil.getResources(page);
        var links = JsoupUtil.getLinks(page);
        // then
        assertFalse(resources.isEmpty());
        assertFalse(links.isEmpty());
    }
}