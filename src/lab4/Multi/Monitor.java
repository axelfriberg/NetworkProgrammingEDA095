package lab4.Multi;

import java.util.*;

/**
 * Created by dat12afr on 26/04/16.
 */
public class Monitor {
    private Set<String> pagesVisited = new HashSet<>();

    public Set<String> getLinks() {
        return links;
    }

    private Set<String> links = new HashSet<>();
    private List<String> pagesToVisit = new LinkedList<>();

    public Set<String> getMailAddresses() {
        return mailAddresses;
    }

    private Set<String> mailAddresses = new HashSet<>();

    public Monitor(){

    }

    synchronized public void addPageToVisit(String s){
        pagesToVisit.add(s);
        notifyAll();
    }

    synchronized public int nbrPagesVisited(){
        return pagesVisited.size();
    }

    synchronized public void addVisited(String url){
        pagesVisited.add(url);
    }

    synchronized public void addLinks(ArrayList<String> l){
        links.addAll(l);
    }

    synchronized public void addPagesToVisit(ArrayList<String> l){
        pagesToVisit.addAll(l);
        notifyAll();
    }

    synchronized public void addMailAddresses(ArrayList<String> ma){
        mailAddresses.addAll(ma);
    }

    synchronized public String nextUrl()
    {
        String nextUrl;
        while(pagesToVisit.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        do
        {
            nextUrl = pagesToVisit.remove(0);
        } while(pagesVisited.contains(nextUrl));
        pagesVisited.add(nextUrl);
        return nextUrl;
    }
}
