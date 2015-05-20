package yemekmenu.parser;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		List<GununYemegi> ayinYemegi = new LinkedList<GununYemegi>();
		Document doc = Jsoup.connect(ADDRESS).get();
		Elements headlines = doc.select("table tr td table tr[height=30]");
		for (int i = 1; i < headlines.size(); i++) {
			Element dayRow = headlines.get(i);
			Elements dayCells = dayRow.select("> td");
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
			String[] meals = new String[dayCells.size()-1];
			for (int j = 1; j < dayCells.size(); j++) {
				Element element = dayCells.get(j);
				
				Elements innerTable = element.select("table tr td");
				if(innerTable.size() == 0){				
					meals[j-1] = element.text();
				}else{
					StringBuilder builder = new StringBuilder();
					builder.append(innerTable.get(0).text());
					for(int k=1; k<innerTable.size(); k++){
						String text = innerTable.get(k).text();
						text = text.replace("\u00A0"," ").replaceAll("&nbsp;", "").trim();
						if(text.length()>0){
							builder.append(" / ");
							builder.append(text);
						}
					}
					meals[j-1] = builder.toString();
				}
			}
			g.setMeal(meals);
			ayinYemegi.add(g);
		}
		return ayinYemegi;
	}
}
