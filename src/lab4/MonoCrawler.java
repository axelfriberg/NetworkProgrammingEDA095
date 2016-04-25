package lab4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MonoCrawler {
    public static void main(String[] args) {
        InputStream is = null;
        Document doc = null;
        try {
            URL url = new URL("http://cs.lth.se/eda095/");
            is = url.openStream();
            doc = Jsoup.parse(is, "UTF-8", "http://cs.lth.se/");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Elements base = doc.getElementsByTag("base");
        System.out.println("Base : " + base);
        Elements links = doc.getElementsByTag("a");

        ArrayList<URL> urls = new ArrayList<>();
        ArrayList<URL> mailAddresses = new ArrayList<>();
        System.out.println("Gathering links, please wait...");
        System.out.print("Links gathered: 0");
        int count = 0;
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkAbsHref = link.attr("abs:href");
            String linkText = link.text();
            //System.out.println("href: " + linkHref + " abshref: "
            //        + linkAbsHref + " text: " + linkText);
            try {
                //System.out.println(linkAbsHref);
                //System.out.println(uc.getContentType());
                URL u = new URL(linkAbsHref);
                URLConnection uc = u.openConnection();

                if(uc != null && uc.getContentType() != null){
                        if(linkAbsHref.contains("@")){
                            mailAddresses.add(u);
                        } else if(uc.getContentType().contains("text/html")){
                            urls.add(u);
                        }
                }
                count++;
                System.out.printf("\rLinks gathered: %d",count);
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("Error getting link.");
            }
        }
        try {
            is.close();
            System.out.printf("%n Total links %d%n",urls.size());
            for(URL k : urls){
                System.out.println(k.toString());
            }
            System.out.printf("Total mail addresses %d%n", mailAddresses.size());
            for(URL l : mailAddresses){
                System.out.println(l.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
