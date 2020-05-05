package dev.snowdrop.markdown;

import org.commonmark.node.ListItem;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;

public class Helper {

    protected static Node parse(String input) {
        Parser parser = Parser.builder().build();
        return parser.parse(input);
    }

    protected static ListItem addItem(String item) {
        ListItem li = new ListItem();
        li.appendChild(new Text(item));
        return li;
    }
}
