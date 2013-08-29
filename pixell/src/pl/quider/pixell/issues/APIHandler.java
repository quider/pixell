package pl.quider.pixell.issues;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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

	public static void main(String[] args) {

		APIHandler a = new APIHandler();
	}

	public APIHandler() {
		try {
			HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.github.com/repos/quider/pixell/issues").openConnection();
			// new URL("https://google.pl/").openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestMethod("GET");
			connection.connect();
//			connection.
			// Send request
//			 OutputStreamWriter wr = new OutputStreamWriter (
//			 connection.getOutputStream ());
//			 wr.write ("{'title':'tytul z api'}");
//			 wr.flush ();
//			 wr.close ();

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
			Gson g = new Gson();
			Issue[] fromJson = g.fromJson(response.toString(), Issue[].class);
//			
//			Collection values = fromJson.values();
//			Iterator iterator = values.iterator();
//			while(iterator.hasNext()){
//				Issue next = (Issue) iterator.next();
//				System.out.println(next.getTitle());
//			}
		
			System.out.println(fromJson[0].getTitle());
//			Issue fromJson2 = g.fromJson(fromJson.get(0).toString(), Issue.class);
//			System.out.println(fromJson2.getTitle());
			System.out.println(response.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
