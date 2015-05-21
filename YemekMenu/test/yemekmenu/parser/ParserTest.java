package yemekmenu.parser;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import yemekmenu.domain.GununYemegi;

public class ParserTest {
	
	Parser p = new Parser();

	@Test
	public void test() throws Exception {
		List<GununYemegi> ayinYemegi = p.aylikYemekleriCek();
		filtrele(ayinYemegi);
		
		for(GununYemegi g : ayinYemegi){
			System.err.println(g.getDay() + " -- "+ g.getMeal()[0] + "+" + g.getMeal()[1] + "+" + g.getMeal()[2] + "+" + g.getMeal()[3] + "+" +g.getMeal()[4] );
		}
		
	}

	private void filtrele(List<GununYemegi> ayinYemegi) {
		List<GununYemegi> toRemove = new LinkedList<GununYemegi>();
		for (GununYemegi gununYemegi : ayinYemegi) {
			if(gununYemegi.getMeal() == null){
				toRemove.add(gununYemegi);
			}
			boolean removeThis = true;
			for(int i=0; i<gununYemegi.getMeal().length; i++){
				if(gununYemegi.getMeal()[i] != null){
					if(gununYemegi.getMeal()[i].trim().isEmpty() || gununYemegi.getMeal()[i].trim().contains("TAT�L") ) {
					} else {
						removeThis = false;
						break;
					}
				}
			}
			if(removeThis){
				toRemove.add(gununYemegi);
			}
		}
		for (GununYemegi gununYemegi : toRemove) {
			ayinYemegi.remove(gununYemegi);
		}
	}
}