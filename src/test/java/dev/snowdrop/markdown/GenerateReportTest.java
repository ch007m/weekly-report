package dev.snowdrop.markdown;

import org.junit.Test;

import java.util.HashMap;

import static dev.snowdrop.github.Helper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GenerateReportTest {

    private static Object[] nestedList = new Object[]{
            "Item 1",
            "Item 2",
            new Object[]{
                    "Item 1.1",
                    "Item 1.2",
                    new Object[]{
                            "Item 1.1.1",
                            "Item 1.1.2"
                    }
            }
    };
    private static String CR = "\n";
    private static String DUMMY_SAMPLE = "I am normal\n" +
            "Heading with level 1\n" +
            "====================\n" +
            "Heading with level 2\n" +
            "--------------------\n" +
            "### Heading with level 3\n" +
            "- Item 1\n" +
            "- Item 2\n" +
            "- Item 3\n" +
            "- [x] Task 1\n" +
            "- [ ] Task 2\n" +
            "- [ ] Task 3\n" +
            "| Index | String |\n" +
            "| -----:| ------ |\n" +
            "|     1 | TODO   |\n" +
            "|     2 | TODO   |\n" +
            "|     3 | TODO   |\n" +
            "| ~~~~~ | ~~~~~~ |\n" +
            "|    18 | TODO   |\n" +
            "|    19 | TODO   |\n" +
            "|    20 | TODO   |\n";

    private static String WEEKLY_REPORT_SAMPLE = "Snowdrop weekly report : 01-Mar-2020\n" +
            "====================================\n" +
            "\n" +
            "Action items\n" +
            "------------\n" +
            "| Item | Description | Who | Status |\n" +
            "| ----:| ----------- | --- | ------ |\n" +
            "|  001 | TODO        | chm | -      |\n" +
            "\n" +
            "---" +
            "\n" +
            "Gytis Trikleris\n" +
            "---------------\n" +
            "- Item 1\n" +
            "- Item 2\n" +
            "  - Item 1.1\n" +
            "  - Item 1.2\n" +
            "Georgios Andrianakis\n" +
            "--------------------\n" +
            "- Item 1\n" +
            "- Item 2\n" +
            "  - Item 1.1\n" +
            "  - Item 1.2" +
            "\n";

    @Test
    public void VerifyDummySample() {
        String result = PopulateReport();

        assertNotNull(result);
        assertEquals(DUMMY_SAMPLE,result);
    }

    @Test
    public void VerifyWeeklyReportSample() {
        String[] associates = {"Gytis Trikleris", "Georgios Andrianakis"};
        HashMap<String, Object> teamTasks = new HashMap<String, Object>();

        StringBuilder sb = new StringBuilder();
        sb.append(addHeadingTitle("Snowdrop weekly report : 01-Mar-2020",1))
          .append(CR).append(CR)
          .append(addHeadingTitle("Action items",2))
          .append(CR)
          .append(addActionItemsTable())
          .append(CR).append(CR)
          .append("---")
          .append(CR);

        for(String associate: associates) {
            sb.append(addHeadingTitle(associate,2)).append(CR);
            sb.append(addUnorderedList(null,nestedList)).append(CR);
        }

        String result = sb.toString();

        assertNotNull(sb);
        assertEquals(WEEKLY_REPORT_SAMPLE,result);
    }
}
