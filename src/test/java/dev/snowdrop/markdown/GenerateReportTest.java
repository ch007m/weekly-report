package dev.snowdrop.markdown;

import org.junit.Test;
import static dev.snowdrop.github.Helper.PopulateReport;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GenerateReportTest {

    private static String REPORT_SAMPLE = "I am normal\n" +
            "Heading with level 1\n" +
            "====================\n" +
            "Heading with level 2\n" +
            "--------------------\n" +
            "### Heading with level 3\n" +
            "- Item 1\n" +
            "- Item 2\n" +
            "  - Item 2.1\n" +
            "  - Item 2.2\n" +
            "  - Item 2.3\n" +
            "- Item 3- [x] Task 1\n" +
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
            "|    20 | TODO   |";
    @Test
    public void VerifyReport() {
        String result = PopulateReport();

        assertNotNull(result);
        assertEquals(REPORT_SAMPLE,result);
    }
}
