package lab2.p2.v3;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Downloads a single pdf.
 */
public class Runner implements Runnable {
    private URL url;

    public Runner(URL url){
        this.url = url;
    }

    @Override
    public void run() {
        Pattern pattern = Pattern.compile("([^/]*\\.\\w+)$");
        Matcher matcher;
        InputStream in;
        try {
            in = url.openStream();
            matcher = pattern.matcher(url.toString());
            if (matcher.find())
                System.out.println("Downloading " + matcher.group(1));
            Files.copy(in, Paths.get("pdfs/" + matcher.group(1)), StandardCopyOption.REPLACE_EXISTING);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
