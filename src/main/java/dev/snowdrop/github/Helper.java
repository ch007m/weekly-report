package dev.snowdrop.github;

import net.steppschuh.markdowngenerator.list.TaskList;
import net.steppschuh.markdowngenerator.list.TaskListItem;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import net.steppschuh.markdowngenerator.table.Table;
import net.steppschuh.markdowngenerator.text.emphasis.BoldText;
import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.emphasis.StrikeThroughText;
import net.steppschuh.markdowngenerator.text.heading.Heading;

import java.util.Arrays;
import java.util.List;

public class Helper {
    public static String PopulateReport() {
        StringBuilder sb = new StringBuilder()
                .append(new net.steppschuh.markdowngenerator.text.Text("I am normal")).append("\n")
                .append(new BoldText("I am bold")).append("\n")
                .append(new ItalicText("I am italic")).append("\n")
                .append(new StrikeThroughText("I am strike-through")).append("\n")
                .append(new Heading("Heading with level 1", 1)).append("\n")
                .append(new Heading("Heading with level 2", 2)).append("\n")
                .append(new Heading("Heading with level 3", 3)).append("\n");

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
        sb.append(new TaskList(taskItems));

        Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
                .withRowLimit(7)
                .addRow("Index", "Boolean");

        for (int i = 1; i <= 20; i++) {
            tableBuilder.addRow(i, Math.random() > 0.5);
        }
        sb.append(tableBuilder.build());

        return sb.toString();
    }
}
