package dev.snowdrop.markdown;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.renderer.text.TextContentRenderer;

import static dev.snowdrop.markdown.Helper.addItem;

public class GenerateDynamically {

    public static void main(String[] args) {
        Document doc = new Document();

        Heading h = new Heading();
        h.setLevel(1);
        h.appendChild(new Text("Snowdrop Weekly report"));
        doc.appendChild(h);

        h = new Heading();
        h.setLevel(2);
        h.appendChild(new Text("Georgios"));
        doc.appendChild(h);

        BulletList bl = new BulletList();
        bl.appendChild(addItem("Task 1"));
        bl.appendChild(addItem("Task 2"));
        doc.appendChild(bl);

        /*
        Paragraph p = new Paragraph();
        Link link = new Link();
        link.setDestination("/exampleUrl");
        p.appendChild(link);
        doc.appendChild(p);
        */

        // Out RAW format
        TextContentRenderer textRenderer = TextContentRenderer.builder().build();
        System.out.println(textRenderer.render(doc));

        // Convert to HTML
        HtmlRenderer htlmRenderer = HtmlRenderer.builder().build();
        System.out.println(htlmRenderer.render(doc));
    }
}
