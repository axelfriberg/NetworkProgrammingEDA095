package lab5;

/**
 * Created by axel on 2016-05-03.
 */
public class MCServer {
    public static void main(String[] args) {
        if (args.length!=1) {
            System.out.println("Syntax: java MCServer <port>");
            System.exit(1);
        }

        // Create a DatagramSocket on the given port

        //new MCServerOffer().start();
        int port = Integer.parseInt(args[0]);
        new TimeThread(port).start();
    }
}
