package it.polito.tdp.poweroutages.model;

import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	Ricerca ricerca;
	public Model() {
		ricerca= new Ricerca();
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<EventiBlackOut> getEventiBlackOut(Nerc n){
		return podao.getBlackOut(n);
	}
	
	public List<EventiBlackOut> trovaSequenza(Nerc n, int maxHours, int anni){
		return ricerca.trovaSequenza(this.getEventiBlackOut(n), maxHours, anni);
	}
	
	

}
