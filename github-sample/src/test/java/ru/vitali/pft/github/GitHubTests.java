package ru.vitali.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("a30f9d9649bcd0aab6e367ea9f9837c514612e3d");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("vlucenco", "java_learn")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
      System.out.println("----------------------------------------");
    }
  }
}
