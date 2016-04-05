package lab1;

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
public class PDFdownloader {
    private URL url;
    private final String file = "pdfdownload.html";

    public PDFdownloader(URL url){
        this.url = url;
    }

    /**
     * Downloads the HTML from a website specified by the URL
     * given to the constructor.
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

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
                    list.add(new URL(matcher.group(1)));
                    found = true;
                }
            }
            if (!found)
                System.out.format("No match found.%n");
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String args[]) throws MalformedURLException {
        if(args.length < 1 || args.length > 1){
            System.out.println("You have to enter one URL, no more, no less.");
        } else {
            try {
                URL url = new URL(args[0]);
                PDFdownloader pdfd = new PDFdownloader(url);
                pdfd.downloadHTML();
                List<URL> pdfs = pdfd.extractPDFs();
                //Regex for extracting the name of the pdf
                Pattern pattern = Pattern.compile("([^/]*\\.\\w+)$");
                Matcher matcher;
                for(URL u : pdfs){
                    InputStream in = u.openStream();
                    matcher = pattern.matcher(u.toString());
                    if(matcher.find())
                        System.out.println(matcher.group(1));
                        Files.copy(in, Paths.get(matcher.group(1)), StandardCopyOption.REPLACE_EXISTING);
                    in.close();
                }
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
