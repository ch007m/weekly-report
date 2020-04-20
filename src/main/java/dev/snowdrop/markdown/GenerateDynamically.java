package dev.snowdrop.markdown;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class GenerateDynamically {

    public static void main(String[] args) {
        Document doc = new Document();

        Paragraph p = new Paragraph();
        Link link = new Link();
        link.setDestination("/exampleUrl");
        p.appendChild(link);
        doc.appendChild(p);

        BulletList bl = new BulletList();
        ListItem li = new ListItem();
        li.appendChild(new Text("Task 1"));
        bl.appendChild(li);

        li = new ListItem();
        li.appendChild(new Text("Task 2"));
        bl.appendChild(li);

        doc.appendChild(bl);

        HtmlRenderer renderer = HtmlRenderer.builder().build();
        System.out.println(renderer.render(doc));  // "<p><a href="/exampleUrl"></a></p>"
    }

    private static Node parse(String input) {
        Parser parser = Parser.builder().build();
        return parser.parse(input);
    }
}
