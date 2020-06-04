package dev.snowdrop.github;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GitHubDiscussionTest {

    private GitHub hub;
    private static String ORG = "snowdrop";
    private static String TEAM_NAME = "Team snowdrop";
    private static String TEAM_SLUG = "team-snowdrop";

    @Before
    public void setUp() throws IOException {
        hub = new GitHubBuilder().fromPropertyFile().build();
    }

    @Test
    public void test1GitHubWithOauthToken() throws Exception {
        assertEquals("https://api.github.com", hub.getApiUrl());
    }

    @Test
    public void test2GitHubDiscussionWithMarkdownBody() throws Exception {
        String markdownText = "cmoulliard\n" +
                "----------\n" +
                "- 1 - Community engagement\n" +
                "  - <span style=\"color:green\">[open]</span> Work on CF on k8s - [https://github.com/snowdrop/snowdrop-team/issues/17](https://github.com/snowdrop/snowdrop-team/issues/17)";

        GHOrganization org = hub.getOrganization(ORG);
        GHTeam team = org.getTeamByName(TEAM_NAME);

        GHDiscussion d = team.createDiscussion("Weekly report - 22")
                .body(markdownText)
                .create();

        assertNotNull(d);
    }

    @Test
    public void test3GitHubUpdateDiscussion() throws Exception {
        GHOrganization org = hub.getOrganization(ORG);
        GHTeam team = org.getTeamByName(TEAM_NAME);
        for (GHDiscussion d : team.listDiscussions()) {
            if (d.getTitle().equals("Weekly report - 22")) {
                team.updateDiscussion()
                    .body("this is the new body")
                    .title("this is the new title")
                    .update(d.getNumber());
            }
        };
    }

    @Test
    public void test4GitHubListDiscussions() throws Exception {
        GHOrganization org = hub.getOrganization(ORG);
        GHTeam team = org.getTeamByName(TEAM_NAME);
        Set<GHDiscussion> all = new HashSet<GHDiscussion>();

        for (GHDiscussion d : team.listDiscussions()) {
            all.add(d);
        };
        assertFalse(all.isEmpty());
    }

    @Test
    public void test5GitHubDeleteDiscussions() throws Exception {
        GHOrganization org = hub.getOrganization(ORG);
        GHTeam team = org.getTeamByName(TEAM_NAME);
        GHDiscussion discussion;

        for (GHDiscussion d : team.listDiscussions()) {
            if ( d.getTitle().equals("Weekly report - 22") || d.getTitle().equals("this is the new title")) {
                team.deleteDiscussion(d.getNumber());
            }
        };
    }
}
