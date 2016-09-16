package ru.vitali.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import ru.vitali.pft.rest.model.Issue;

public class TestBase {
  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }

  public boolean isIssueOpen(int issueId) {
    String json = RestAssured.get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)).asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issueJson = parsed.getAsJsonObject().get("issues");
    Issue[] issue = new Gson().fromJson(issueJson, new TypeToken<Issue[]>() {}.getType());

    return !(issue[0].state_name.equals("Resolved") || issue[0].state_name.equals("Closed"));
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
