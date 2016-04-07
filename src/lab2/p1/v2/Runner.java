package lab2.p1.v2;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Downloads multiple pdfs.
 */
public class Runner implements Runnable {
    List<URL> urls;

    public Runner(List<URL> urls){
        this.urls = urls;
    }

    @Override
    public void run() {
        Pattern pattern = Pattern.compile("([^/]*\\.\\w+)$");
        Matcher matcher;
        for (URL u : urls) {
            InputStream in;
            try {
                in = u.openStream();
                matcher = pattern.matcher(u.toString());
                if (matcher.find())
                    System.out.println("Downloading " + matcher.group(1));
                Files.copy(in, Paths.get("pdfs/" + matcher.group(1)), StandardCopyOption.REPLACE_EXISTING);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
