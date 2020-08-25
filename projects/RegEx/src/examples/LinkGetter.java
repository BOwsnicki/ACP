package examples;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkGetter {
	private Pattern htmltag;
	private Pattern link;

	public LinkGetter() {
		htmltag = Pattern.compile("<a\\b[^>]*href=\"[^>]*>(.*?)</a>");
		link = Pattern.compile("href=\"[^>]*\">");
	}

	public List<String> getLinks(String url) {
		List<String> links = new ArrayList<String>();
		try {
			Scanner s = new Scanner(new File("buttons.htm"));
			String line;
			StringBuilder builder = new StringBuilder();
			while (s.hasNextLine()) {
				line = s.nextLine();
				System.out.println("line = " + line);
				builder.append(line);
			}

			Matcher tagmatch = htmltag.matcher(builder.toString());
			while (tagmatch.find()) {
				Matcher matcher = link.matcher(tagmatch.group());
				matcher.find();
				String link = matcher.group().replaceFirst("href=\"", "").replaceFirst("\">", "")
						.replaceFirst("\"[\\s]?target=\"[a-zA-Z_0-9]*", "");
				if (valid(link)) {
					// links.add(makeAbsolute(url, link));
					links.add(link);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return links;
	}

	private boolean valid(String s) {
		if (s.matches("javascript:.*|mailto:.*")) {
			return false;
		}
		return true;
	}

	private String makeAbsolute(String url, String link) {
		if (link.matches("http://.*")) {
			return link;
		}
		if (link.matches("/.*") && url.matches(".*$[^/]")) {
			return url + "/" + link;
		}
		if (link.matches("[^/].*") && url.matches(".*[^/]")) {
			return url + "/" + link;
		}
		if (link.matches("/.*") && url.matches(".*[/]")) {
			return url + link;
		}
		if (link.matches("/.*") && url.matches(".*[^/]")) {
			return url + link;
		}
		throw new RuntimeException("Cannot make the link absolute. Url: " + url + " Link " + link);
	}

	public void showLinks(List<String> links) {
		for (String st : links)
			System.out.println(st);
	}

	public static void main(String[] args) {
		LinkGetter lg = new LinkGetter();
		List<String> links = lg.getLinks("asdf");
		lg.showLinks(links);
	}
}