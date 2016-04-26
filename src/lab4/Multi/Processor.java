package lab4.Multi;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Set;

/**
 * Created by axel on 2016-04-26.
 */
public class Processor extends Thread {
    private final Leg leg;
    private List<String> pagesToVisit;
    private Set<String> pagesVisited;
    private Set<String> mailAddresses;
    private Set<String> links;

    public Processor(Set<String> pagesVisited,
                     Set<String> links,
                     List<String> pagesToVisit,
                     Set<String> mailAddresses){
        this.leg = new Leg();
        this.pagesVisited = pagesVisited;
        this.pagesToVisit = pagesToVisit;
        this.mailAddresses = mailAddresses;
        this.links = links;
    }

    @Override
    public void run() {
        while(pagesVisited.size() < 20) {
            while (pagesToVisit.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            leg.crawl(nextUrl());
            synchronized (this) {
                links.addAll(leg.getLinks());
                pagesToVisit.addAll(leg.getLinks());
                mailAddresses.addAll(leg.getMailAddresses());
            }
            notifyAll();
        }
    }

    private synchronized String nextUrl()
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
