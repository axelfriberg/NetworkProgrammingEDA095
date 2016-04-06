package lab2;

import lab2.v1.Runner;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A program for downloading all the pdfs that are linked on a website.
 */
public class PDFdownloaderMT {
    private URL url;
    private final String file = "pdfdownload.html";

    public PDFdownloaderMT(URL url){
        this.url = url;
    }

    /**
     * Downloads the HTML from a website specified by the URL
     * given to the constructor of the class.
     */
    public void downloadHTML(){
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            ArrayList<String> lines = new ArrayList<>();

            while ((inputLine = in.readLine()) != null)
                lines.add(inputLine);

            Path file = Paths.get("pdfdownload.html");
            Files.write(file, lines, Charset.forName("UTF-8"));

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts all the hrefs that link a pdf from the given HTML file.
     * @return An ArrayList of all the URLs.
     */
    public List<URL> extractPDFs() {
        ArrayList<URL> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("<a href=\"(.*?\\.pdf)\"");

        try  {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            boolean found = false;
            Matcher matcher;
            while ((line = br.readLine()) != null) {
                matcher = pattern.matcher(line);
                while (matcher.find()) {
                    System.out.format("I found the text" +
                                    " %s starting at " +
                                    "index %d and ending at index %d.%n",
                            matcher.group(),
                            matcher.start(),
                            matcher.end());
                    list.add(new URL(url, matcher.group(1)));
                    found = true;
                }
            }
            if (!found)
                System.out.format("No match found.%n");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String args[]) throws MalformedURLException {
        if(args.length < 1 || args.length > 1){
            System.out.println("You have to enter one URL, no more, no less.");
        } else {
            URL url = new URL(args[0]);
            PDFdownloaderMT pdfd = new PDFdownloaderMT(url);
            pdfd.downloadHTML();
            List<URL> pdfs = pdfd.extractPDFs();
            //Regex for extracting the name of the pdf
            Pattern pattern = Pattern.compile("([^/]*\\.\\w+)$");
            Matcher matcher;
            //Version one of runner
            for (URL u : pdfs) {
                new Thread(new Runner(u)).start();
            }

            //Version two of runner
            //new Thread(new lab2.v2.Runner(pdfs)).start();
        }
    }
}
