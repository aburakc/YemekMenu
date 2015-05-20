package yemekmenu.parser;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import yemekmenu.domain.GununYemegi;

public class Parser {

	private static final String ADDRESS = "http://wwweski.tubitak.gov.tr/idari/tabldot.php";

	public List<GununYemegi> aylikYemekleriCek() throws IOException {
		List<GununYemegi> ayinYemegi = new ArrayList<GununYemegi>();
		Document doc = Jsoup.connect(ADDRESS).get();
		Elements headlines = doc.select("table tr td table tr");
		for (int i = 1; i < headlines.size(); i++) {
			Element dayRow = headlines.get(i);
			Elements dayCells = dayRow.getElementsByTag("td");
			Element day = dayCells.get(0);
			DateFormat instance = new SimpleDateFormat("dd MMM yyyy EEE", new Locale("tr", "TR"));
			GununYemegi g = new GununYemegi();
			try {
				Date parse = instance.parse(day.text());
				g.setDay(parse);
			} catch (ParseException e) {
				// pass
				continue;
			}
			
			List<String> mealsList = new ArrayList<String>();
			for (int j = 1; j < dayCells.size(); j++) {
				Element element = dayCells.get(j);
				if(element.children().size() == 0){
					if(element.text() != null && element.text().length() != 0)
						mealsList.add(element.text());
				}
			}
			g.setMeal(mealsList.toArray(new String[mealsList.size()]));
			ayinYemegi.add(g);
		}
		return ayinYemegi;
	}
}
