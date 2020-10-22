
package ippo.assignment1;
import ippo.assignment1.library.PictureViewer;

public class Main {

    public static void main(String[] args) {
        System.getProperties().put("http.proxyHost", "localhost");
        System.getProperties().put("http.proxyPort", "7890");

        PictureViewer.main(args);
    }
}
