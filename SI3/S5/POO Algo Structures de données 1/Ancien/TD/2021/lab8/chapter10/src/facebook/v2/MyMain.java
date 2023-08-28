package facebook.v2;

import java.util.stream.IntStream;

/**
 * @author Florian Latapie
 */
public class MyMain {
    public static void main(String[] args) {
        /*Post mp = new Post("Homer Simpson");
        mp.like();
        mp.like();
        mp.unlike();
        mp.addComment("Doh - go Donald!");
        mp.display();*/

        /*MessagePost mp = new MessagePost("Leonardo da Vinci", "Code this...!");
        mp = new MessagePost("Leonardo da Vinci", "Code this...!");
        mp.display();*/

        /*NewsFeed nf = new NewsFeed();
        Post mp = new MessagePost("Homer Simpson", "Facts are meaningless. You can use facts to prove anything that's even remotely true!");
        mp.like();
        mp.like();
        mp.unlike();
        mp.addComment("Doh - go Donald!");
        nf.addPost(mp);
        Post pp = new PhotoPost("Alexander Graham Bell", "handset.png", "Coming soon: the Samsung Galaxy S13.");
        pp.like();
        pp.like();
        pp.like();
        pp.like();
        pp.addComment("Hope it doesn't explode.");
        nf.addPost(pp);
        nf.show();*/

        final MessagePost tsmp = new MessagePost("Taylor Twit", "Party Like It's 1989\n(c)1989 Taylor Twit");
        IntStream.range(0, 12345).forEach(i -> tsmp.addComment("That sucks."));
        tsmp.display();
        System.out.println("\n\n");
        Post mp = new MessagePost("Leonardo da Vinci", "Code this...!");
        mp.display();
    }
}
