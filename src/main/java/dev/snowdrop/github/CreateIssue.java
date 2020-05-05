package dev.snowdrop.github;

import org.kohsuke.github.*;

import java.io.IOException;

public class CreateIssue {

    // OAuth personal token
    private static String OAUTH = "b72038ec1978dc6cdef70a2396c2390efc49ae63";
    private static String ORG = "snowdrop";
    private static String TEAM_REPO = "snowdrop/snowdrop-team";

    public static void main(String[] args) {

        try {
            GitHub github = new GitHubBuilder().withOAuthToken(OAUTH).build();
            GHUser org = github.getUser(ORG);

            // List repositories of Snowdrop Org
            PagedIterable<GHRepository> repos = org.listRepositories();
            for (GHRepository repo : repos) {
                System.out.println("Repo name : " + repo.getFullName());
            }
            // Get issues for the Github repo
            GHRepository repo = github.getRepository(TEAM_REPO);
            PagedIterable<GHIssue> issues = repo.listIssues(GHIssueState.ALL);
            for (GHIssue issue : issues) {
                System.out.println("Issue title : " + issue.getTitle());
            }

            // Render
            // github.renderMarkdown("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
