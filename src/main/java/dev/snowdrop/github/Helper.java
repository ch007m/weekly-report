package dev.snowdrop.github;

import net.steppschuh.markdowngenerator.MarkdownBuilder;
import net.steppschuh.markdowngenerator.MarkdownSerializationException;
import net.steppschuh.markdowngenerator.list.*;
import net.steppschuh.markdowngenerator.table.Table;
import net.steppschuh.markdowngenerator.text.Text;
import net.steppschuh.markdowngenerator.text.heading.Heading;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Helper {

    private static String CR = "\n";

    public static String PopulateReport() throws MarkdownSerializationException {
        StringBuilder sb = new StringBuilder();

        sb.append(addText("I am normal"))
          .append(CR)
          .append(addHeadingTitle("Heading with level 1", 1))
          .append(CR)
          .append(addHeadingTitle("Heading with level 2", 2))
          .append(CR)
          .append(addHeadingTitle("Heading with level 3", 3))
          .append(CR)
          .append(toMarkdown(new String[]{"Item 1", "Item 2", "Item 3"}))
          .append(CR);


        List<TaskListItem> taskItems = Arrays.asList(
                new TaskListItem("Task 1", true),
                new TaskListItem("Task 2", false),
                new TaskListItem("Task 3")
        );
        sb.append(new TaskList(taskItems)).append(CR);

        Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
                .withRowLimit(7)
                .addRow("Index", "String");

        for (int i = 1; i <= 20; i++) {
            tableBuilder.addRow(i, "TODO");
        }
        sb.append(tableBuilder.build()).append(CR);

        return sb.toString();
    }

    public static StringBuilder addHeadingTitle(String title, int level) {
        return new StringBuilder()
                .append(new Heading(title, level));
    }
    public static StringBuilder addText(String txt) {
        return new StringBuilder()
                .append(new Text(txt));
    }

    public static ListBuilder populateNestedLists(ListBuilder b, Object[] items) {
        if ( b == null) {
            b = new ListBuilder();
        }
        for (Object item : items) {
            if (item instanceof String) {
                b.append(item);
            } else if (item instanceof Object[]) {
                return populateNestedLists(new ListBuilder(b), (Object[]) item);
            }
        }
        return b;
    }
    public static UnorderedList getUnorderedList(Object... items) {
        List list = new LinkedList();

        for (Object item : items) {
            if (item instanceof String) {
                list.add(new UnorderedListItem(item));
            } else if (item instanceof Object[]) {
                list.add(getUnorderedList(item));
            }
        }
        return new UnorderedList(list);
    }

    public static String toMarkdown(Object... items) throws MarkdownSerializationException {
        //return getUnorderedList(items).serialize();
        ListBuilder b = populateNestedLists(null, items);
        return iterateParentBuilder(null, b).toString();
    }

    protected static StringBuilder iterateParentBuilder(StringBuilder sb, MarkdownBuilder b) {
        if (sb == null) {
            sb = new StringBuilder();
        }
        MarkdownBuilder p = b.getParentBuilder();
        if (p != null) {
            sb.append(p.build()).append(CR);
            iterateParentBuilder(sb, p);
        }
        return sb.append(b.build());
    }

    public static StringBuilder addActionItemsTable() {
        Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
                .addRow("Item","Description","Who","Status")
                .addRow("001","TODO","chm","-");


        return new StringBuilder().append(tableBuilder.build());
    }

}
