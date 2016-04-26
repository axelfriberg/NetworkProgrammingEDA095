package lab4.Multi;

import lab4.Mono.Leg;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by axel on 2016-04-26.
 */
public class MultiCrawler {
    private static final int MAX_PAGES_TO_SEARCH = 3;
    private Set<String> pagesVisited = new HashSet<>();
    private Set<String> links = new HashSet<>();
    private List<String> pagesToVisit = new LinkedList<>();
    private Set<String> mailAddresses = new HashSet<>();

    public void run(String startUrl) {

        pagesToVisit.add(startUrl);
        for (int i = 0; i < 10; i++) {
            new Processor(pagesVisited, links, pagesToVisit, mailAddresses).start();
        }

        System.out.printf("%n Total links %d%n",links.size());
        for(String l : links) System.out.println(l);
        System.out.printf("Total mail addresses %d%n", mailAddresses.size());
        for(String ma : mailAddresses) System.out.println(ma);
    }
}
