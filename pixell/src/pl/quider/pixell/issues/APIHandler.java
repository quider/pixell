package pl.quider.pixell.issues;

import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class APIHandler {

	public static void main (String[] args){
		
		APIHandler a = new APIHandler();
	}
	
	public APIHandler() {
		try {
			GitHub gh = GitHub.connect();
			GHRepository repository = gh.getRepository("quider/pixell");
			List<GHIssue> issues = repository.getIssues(GHIssueState.OPEN);
			for (GHIssue ghIssue : issues) {
				System.out.println(ghIssue.getBody());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
