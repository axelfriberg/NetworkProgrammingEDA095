package lab4.Multi;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by axel on 2016-04-26.
 */
public class Leg {
    private ArrayList<String> links = new ArrayList<>();
    private ArrayList<String> mailAddresses = new ArrayList<>();

    public ArrayList<String> getLinks() {
        return links;
    }

    public ArrayList<String> getMailAddresses() {
        return mailAddresses;
    }

    public void crawl(String url) {
        Document htmlDoc = null;
        try {
            Connection connection = Jsoup.connect(url);
            htmlDoc = connection.get();
        } catch (IOException e) {
            System.out.println("Connection failed " + e);
        }
        Elements base = htmlDoc.getElementsByTag("base");
        System.out.println("Base : " + base);
        Elements links = htmlDoc.getElementsByTag("a");

        System.out.println("Gathering links from "+ url + ", please wait...");
        System.out.print("Links gathered: 0");
        int count = 0;
        for (Element link : links) {
            String linkAbsHref = link.attr("abs:href");
            try {
                URL u = new URL(linkAbsHref);
                URLConnection uc = u.openConnection();
                if (uc != null && uc.getContentType() != null) {
                    if (linkAbsHref.contains("@")) {
                        mailAddresses.add(u.toString());
                    } else if (uc.getContentType().contains("text/html")) {
                        this.links.add(u.toString());
                    }
                }
                count++;
                System.out.printf("\rLinks gathered: %d", count);
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("\nError getting link.");
            }
        }
    }
}
