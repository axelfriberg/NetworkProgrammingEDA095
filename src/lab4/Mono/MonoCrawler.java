package lab4.Mono;

import java.util.*;

public class MonoCrawler {
    private static final int MAX_PAGES_TO_SEARCH = 3;
    private Set<String> pagesVisited = new HashSet<>();
    private Set<String> links = new HashSet<>();
    private List<String> pagesToVisit = new LinkedList<>();
    private Set<String> mailAddresses = new HashSet<>();

    public void run(String startUrl) {
        Leg leg = new Leg();
        pagesVisited.add(startUrl);

        leg.crawl(startUrl);
        pagesToVisit.addAll(leg.getLinks());
        links.addAll(leg.getLinks());
        mailAddresses.addAll(leg.getMailAddresses());

        while(pagesVisited.size() < MAX_PAGES_TO_SEARCH){
            leg.crawl(nextUrl());
            pagesToVisit.addAll(leg.getLinks());
            links.addAll(leg.getLinks());
            mailAddresses.addAll(leg.getMailAddresses());
        }

        System.out.printf("%n Total links %d%n",links.size());
        for(String l : links) System.out.println(l);
        System.out.printf("Total mail addresses %d%n", mailAddresses.size());
        for(String ma : mailAddresses) System.out.println(ma);
    }

    private String nextUrl()
    {
        String nextUrl;
        do
        {
            nextUrl = pagesToVisit.remove(0);
        } while(pagesVisited.contains(nextUrl));
        pagesVisited.add(nextUrl);
        return nextUrl;
    }
}
