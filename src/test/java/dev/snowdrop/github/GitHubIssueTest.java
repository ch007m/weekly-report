package dev.snowdrop.github;

import org.junit.Before;
import org.junit.Test;
import org.kohsuke.github.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GithubIssueTest {

    private GitHub hub;
    private static String TEAM_REPO = "snowdrop/snowdrop-team";

    protected GHUser getUser() {
        return getUser(hub);
    }

    @Before
    public void setUp() throws IOException {
        hub = new GitHubBuilder().fromPropertyFile().build();
    }

    @Test
    public void testGitHubWithOauthToken() throws Exception {
        assertEquals("https://api.github.com", hub.getApiUrl());
    }

    @Test
    public void testGitHubIssue() throws Exception {
        GHRepository repository = hub.getRepository(TEAM_REPO);
        GHIssue o = repository.createIssue("DUMMY")
                .body("this is body")
                .assignee(getUser())
                .label("bug")
                .label("question")
                .create();
        assertNotNull(o);
        o.close();
    }



    protected static GHUser getUser(GitHub gitHub) {
        try {
            return gitHub.getMyself();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
