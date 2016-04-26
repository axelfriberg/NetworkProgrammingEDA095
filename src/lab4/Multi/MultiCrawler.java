package lab4.Multi;

/**
 * Created by axel on 2016-04-26.
 */
public class MultiCrawler {
    private static final int MAX_PAGES_TO_SEARCH = 3;
    private Monitor monitor;

    public void run(String startUrl) {
        monitor = new Monitor();
        monitor.addPageToVisit(startUrl);
        Processor[] p = new Processor[10];
        for (int i = 0; i < 10; i++) {
            p[i] = new Processor(monitor);
            p[i].start();
        }

        for (int i = 0; i < 10; i++) {
            try {
                p[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("%n Total links %d%n",monitor.getLinks().size());
        for(String l : monitor.getLinks()) System.out.println(l);
        System.out.printf("Total mail addresses %d%n", monitor.getMailAddresses().size());
        for(String ma : monitor.getMailAddresses()) System.out.println(ma);
    }
}
