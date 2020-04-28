package it.polito.tdp.poweroutages.model;


import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	
	List<EventiBlackOut>ottimo;
	
	public List<EventiBlackOut> trovaSequenza(List<EventiBlackOut>listaEventi, int maxhours, int anni){
		List<EventiBlackOut>parziale=new ArrayList<>();
		ottimo=null;
		cerca(parziale, 0, listaEventi, maxhours, anni);
		
		return ottimo;
	}

	private void cerca(List<EventiBlackOut> parziale, int livello, List<EventiBlackOut> listaEventi, int maxhours, int anni) {
		
		if(ottimo==null)
			ottimo=new ArrayList<>(parziale);
		
		if(calcolaOre(parziale)<=maxhours) {
			
			if(calcolaClienti(ottimo)<calcolaClienti(parziale)) {
				ottimo.clear();
				ottimo.addAll(parziale);
			}
		}
		
		if(livello==listaEventi.size())
			return;
		
		if(aggiuntaValida(maxhours, parziale, anni, listaEventi.get(livello))) {
			
		parziale.add(listaEventi.get(livello));
		cerca(parziale, livello+1, listaEventi, maxhours, anni);
		parziale.remove(listaEventi.get(livello));
		}
		cerca(parziale, livello+1, listaEventi, maxhours, anni);
		
	}

	private boolean aggiuntaValida(int maxhours, List<EventiBlackOut> parziale, int anni, EventiBlackOut nuovo) {
		boolean result=true;
		
		for(EventiBlackOut e: parziale) {
			if( !(Math.abs(e.getData().getYear()-nuovo.getData().getYear())<=anni))
				result=false;
		}
		
		if(calcolaOre(parziale)>maxhours)
			result=false;
		
		
		return result;
	}

	private int calcolaClienti(List<EventiBlackOut> list) {
		int count=0;
		
		for(EventiBlackOut clienti: list) {
			count+=clienti.getCustomers();
		}
		return count;
	}

	private int calcolaOre(List<EventiBlackOut> parziale) {
		int count=0;
		
		for(EventiBlackOut intervallo: parziale) {
			count += intervallo.getoreGuasto();
		}
		return count;
	}
}
