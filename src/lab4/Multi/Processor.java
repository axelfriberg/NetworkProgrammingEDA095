package lab4.Multi;


/**
 * Created by axel on 2016-04-26.
 */
public class Processor extends Thread {
    private final Leg leg;
    private Monitor monitor;

    public Processor(Monitor monitor){
        this.leg = new Leg();
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(monitor.nbrPagesVisited() < 3) {
            String next = monitor.nextUrl();
            monitor.addVisited(next);
            leg.crawl(next);
            monitor.addLinks(leg.getLinks());
            monitor.addPagesToVisit(leg.getLinks());
            monitor.addMailAddresses(leg.getMailAddresses());
        }
    }
}
