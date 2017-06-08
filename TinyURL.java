import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 * Generates a unique shortened url for a given url
 * @author Morgan Werner
 * @version 06/08/17
 */

public class TinyURL {
	
	/** Key: URL  Value: TinyURL */
	private static Hashtable<String, String> urlToTiny;
	
	/** Key: TinyURL  Value: URL */
	private static Hashtable<String, String> tinyToUrl;

	public static void main(String[] args) {
		urlToTiny = new Hashtable<String, String>();
		tinyToUrl = new Hashtable<String, String>();
	}
	
	public static String getTinyURL(String url) {
		if(urlToTiny.containsKey(url)) {
			return "http://tinyurl.com/" + urlToTiny.get(url);
		}
		String id = getIdentifier();
		while(urlToTiny.contains(id)) {
			id = getIdentifier();
		}
		urlToTiny.put(url, id);
		tinyToUrl.put(id, url);
		return "http://tinyurl.com/" + id;
	}
	
	public static String getIdentifier() {
		Random rand = new Random();
		char[] s = new char[6];
		for(int i = 0; i < 6; i++) {
			char c = (char) (rand.nextInt(75) + 48);
			while((c > '9' && c < 'A') || (c > 'Z' && c < 'a')) {
				c = (char) (rand.nextInt(75) + 48);
			}
			s[i] = c;
		}
		return String.valueOf(s);
	}
	
	/** Find original url by iterating through table to find tinyurl */
	public static String getUrlByIterate(String tinyurl) {
		tinyurl = tinyurl.substring(tinyurl.length()-6);
		if(!urlToTiny.contains(tinyurl)) {
			return null;
		}
		String url = "";
		for(Map.Entry e: urlToTiny.entrySet()) {
			if(tinyurl.equals(e.getValue())){
				url = e.getKey().toString();
			}
		}
		return url;
	}
	
	/** Find original url by searching second hashtable where the key is the tinyurl*/
	public static String getUrlByTable(String tinyurl) {
		return tinyToUrl.get(tinyurl.substring(tinyurl.length()-6));
	}

}
