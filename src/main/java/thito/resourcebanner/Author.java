package thito.resourcebanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import thito.resourcebanner.server.HttpField;

public class Author {
	public static Author getAuthor(String id) {
		try {
			final URL url = new URL("https://api.spiget.org/v2/authors/" + id + "?fields=name");
			final HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.addRequestProperty(HttpField.UserAgent.toString(), "ResourceBanner");
			final BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String l;
			final StringBuilder builder = new StringBuilder();
			while ((l = r.readLine()) != null) {
				builder.append(l);
			}
			return new Gson().fromJson(builder.toString(), Author.class);
		} catch (final Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(getAuthor("1").id);
	}

	public long id;

	public String name;

}
