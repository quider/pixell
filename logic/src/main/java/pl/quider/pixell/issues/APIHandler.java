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
import pl.quider.pixell.github.Label;

import com.google.gson.Gson;

public class APIHandler {

	public enum URI_TYPE {
		ISSUE, USER, LABEL;
	}

	public static void main(String[] args) {

		APIHandler a = new APIHandler();
		Issue i = new Issue();
		i.setBody("body from api");
		i.setTitle("title from api Zażółć gęślą jaźń");
		System.out.println(a.sendIssue(i));
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
	private HttpsURLConnection getConnection(String method, URI_TYPE type) throws MalformedURLException, IOException {
		StringBuilder sb = new StringBuilder("https://api.github.com/repos/quider/pixell/");
		// URI_TYPE type = URI_TYPE.ISSUE;
		switch (type) {
		case ISSUE:
			sb.append("issues");
			break;
		case LABEL:
			sb.append("labels");
			break;
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

	public Label[] getLabels() {
		try {
			HttpsURLConnection connection = getConnection("GET", URI_TYPE.LABEL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets all issues from repo
	 * 
	 * @return
	 */
	public Issue[] getAllIssues() {
		try {
			Gson g = new Gson();
			HttpsURLConnection connection = getConnection("GET", URI_TYPE.ISSUE);

			// return fromJson;
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
			HttpsURLConnection connection = getConnection("POST", URI_TYPE.ISSUE);

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

	private <T extends Object> T getResposne(HttpsURLConnection connection, T type) {
		try {
			// Get Response
			Gson g = new Gson();
			InputStream is;
			is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			T fromJson = (T) g.fromJson(response.toString(), type.getClass());
			return fromJson;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
