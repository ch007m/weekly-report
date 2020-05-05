package dev.snowdrop.github;

import org.kohsuke.github.*;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class CreateIssue {

    private static String TEAM_REPO = "snowdrop/snowdrop-team";

    public static void main(String[] args) {

        try {
            GitHub github = new GitHubBuilder().fromPropertyFile().build();

            // List repositories of Snowdrop Org
            /*
            PagedIterable<GHRepository> repos = org.listRepositories();
            for (GHRepository repo : repos) {
                System.out.println("Repo name : " + repo.getFullName());
            }
            */

            // Get issues for the Github repo
            GHRepository repo = github.getRepository(TEAM_REPO);
            PagedIterable<GHIssue> issues = repo.listIssues(GHIssueState.ALL);
            for (GHIssue issue : issues) {
                System.out.println("Issue title : " + issue.getTitle());
            }

            // Render the string to Markdown / TODO
            /*
            InputStreamReader isr = (InputStreamReader) github.renderMarkdown(Helper.PopulateReport());
            String result = new BufferedReader(isr)
                    .lines().collect(Collectors.joining("\n"));
            System.out.println("Result : " + result);
            */

            // Create Weekly report Issue
            GHRepository teamRepo = github.getRepository(TEAM_REPO);
            GHIssue issue = teamRepo.createIssue("Weekly Reporting : TEST")
               .body(Helper.PopulateReport())
               .assignee("cmoulliard")
               .label("report")
               .create();
            assertNotNull(issue);
            System.out.printf("Issue %s!%n",issue.getTitle());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
