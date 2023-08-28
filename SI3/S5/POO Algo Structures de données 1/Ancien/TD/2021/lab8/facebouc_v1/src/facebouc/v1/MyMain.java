package facebouc.v1;

import java.util.stream.IntStream;

/**
 * @author Florian Latapie
 */
public class MyMain {
    public static void main(String[] args) {
        /*final MessagePost tsmp = new MessagePost("Taylor Twit", "Party Like It's 1989\\n(c)1989 Taylor Twit");
        IntStream.range(0, 12345).forEach(i -> tsmp.addComment("That sucks."));
        tsmp.display();*/

        PhotoPost pp = new PhotoPost("Alexander Graham Bell", "handset.png", "Coming soon: the Samsung Galaxy S13.");
        pp.like();
        pp.like();
        pp.like();
        pp.like();
        pp.addComment("Hope it doesn't explode.");
        pp.display();
    }
}
