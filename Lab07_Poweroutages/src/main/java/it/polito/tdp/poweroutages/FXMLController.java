package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.EventiBlackOut;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class FXMLController {

	Model model=new Model();
	List<Nerc> list=new ArrayList<>();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Nerc> comboBox;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnWorst;
    
    @FXML
    private TextArea txtResult;

    @FXML
    void doWorst(ActionEvent event) {
    	this.txtResult.clear();
    	int totalePersone=0;
    	int totaleore=0;
    	
    	Nerc n=this.comboBox.getValue();
    	int maxHours=Integer.parseInt(this.txtHours.getText());
    	int anni=Integer.parseInt(this.txtYears.getText());
    	
    	StringBuilder sb= new StringBuilder();
    	for(EventiBlackOut e: model.trovaSequenza(n, maxHours, anni)) {
    		totalePersone += e.getCustomers();
    		totaleore += e.getoreGuasto();
    		sb.append(String.format("%-5s ", e.getData().getYear()) );
    		sb.append(String.format("%-25s ", e.getData_inizio()) );
    		sb.append(String.format("%-25s ", e.getData_fine()) );
    	    sb.append(String.format("%-10s ",e.getoreGuasto()) );
    	    sb.append(String.format("%-10s ", e.getCustomers()) );
    	    sb.append("\n");
    	}
    	this.txtResult.appendText("Total people affected: "+totalePersone+"\n"+
    	                           "Total hours of outage: "+totaleore+"\n");
    	this.txtResult.appendText(sb.toString());
    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnWorst != null : "fx:id=\"btnWorst\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }

	public void setModel(Model model) {
		this.model=model;
		setList();
	}
	
	private void setList() {
		this.list=model.getNercList();
		comboBox.getItems().addAll(list);
	}
}
