package pl.quider.pixell.issues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import pl.quider.pixell.github.Issue;

import com.google.gson.Gson;

public class APIHandler {

	public enum URI_TYPE {
		ISSUE, USER, LABEL;
	}

	public APIHandler() {

	}

	/**
	 * Creates connection with github
	 * 
	 * @param method
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private HttpsURLConnection getConnection(String method) throws MalformedURLException, IOException {
		StringBuilder sb = new StringBuilder("https://api.github.com/repos/quider/warehouse/");
		URI_TYPE type = URI_TYPE.ISSUE;
		switch (type) {
		case ISSUE:
			sb.append("issues");
		default:
			break;
		}
		HttpsURLConnection connection = (HttpsURLConnection) new URL(sb.toString()).openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Authorization", "Basic cGl4ZWxsaXNzdWU6YmFyZHpvIHRham5lIGhhc2xv");
		connection.setRequestMethod(method);
		connection.connect();
		return connection;
	}

	/**
	 * Gets all issues from repo
	 * 
	 * @return
	 */
	public Issue[] getAllIssues() {
		try {
			Gson g = new Gson();
			HttpsURLConnection connection = getConnection("GET");
			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			Issue[] fromJson = g.fromJson(response.toString(), Issue[].class);
			return fromJson;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * adds new issue to repo
	 * 
	 * @param issue
	 * @return
	 */
	public Issue sendIssue(Issue issue) {
		try {

			Gson g = new Gson();
			HttpsURLConnection connection = getConnection("POST");

			// Send request
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
			String json = g.toJson(issue);
			wr.write(json);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			Issue fromJson = g.fromJson(response.toString(), Issue.class);
			// System.out.println(fromJson.getTitle());
			return fromJson;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
