package lab4.Multi;

import lab4.Mono.MonoCrawler;

/**
 * Runs a multi-threaded crawler.
 */
public class MultiCrawlerTest {
    public static void main(String[] args) {
        MultiCrawler crawler = new MultiCrawler();
        crawler.run("http://cs.lth.se/eda095/");
    }
}
