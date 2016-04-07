package lab2.p2.v2;

import lab2.PDFdownloaderMT;

import java.net.URL;
import java.util.List;

/**
 * Downloads multiple pdfs.
 */
public class Runner implements Runnable {
    private PDFdownloaderMT pdfd;
    private List<URL> urls;

    public Runner(List<URL> urls, PDFdownloaderMT pdfd){
        this.urls = urls;
        this.pdfd = pdfd;
    }

    @Override
    public void run() {
        pdfd.downloadPDFs(urls);
    }
}
