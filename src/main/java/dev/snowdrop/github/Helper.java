package dev.snowdrop.github;

import net.steppschuh.markdowngenerator.list.TaskList;
import net.steppschuh.markdowngenerator.list.TaskListItem;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import net.steppschuh.markdowngenerator.table.Table;
import net.steppschuh.markdowngenerator.text.Text;
import net.steppschuh.markdowngenerator.text.heading.Heading;

import java.util.Arrays;
import java.util.List;

public class Helper {

    private static String CR = "\n";

    public static String PopulateReport() {
        StringBuilder sb = new StringBuilder()
                .append(addText("I am normal"))
                .append(addHeadingTitle("Heading with level 1", 1))
                .append(addHeadingTitle("Heading with level 2", 2))
                .append(addHeadingTitle("Heading with level 3", 3));

        List<Object> items = Arrays.asList(
                "Item 1",
                "Item 2",
                new UnorderedList<>(Arrays.asList(
                        "Item 2.1",
                        "Item 2.2",
                        "Item 2.3"
                )),
                "Item 3"
        );
        sb.append(new UnorderedList<>(items));

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
        sb.append(tableBuilder.build());

        return sb.toString();
    }

    private static StringBuilder addHeadingTitle(String title, int level) {
        return new StringBuilder()
                .append(new Heading(title, level))
                .append(CR);
    }
    private static StringBuilder addText(String txt) {
        return new StringBuilder()
                .append(new Text(txt))
                .append(CR);
    }

}
