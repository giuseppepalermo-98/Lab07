package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class EventiBlackOut {
	
	private int event_id;
	private int customers;
	private LocalDate data;
	private LocalDateTime data_inizio;
	private LocalDateTime data_fine;
	private int oreGuasto;
	
	public EventiBlackOut(LocalDate data, int event_id, int customers, LocalDateTime data_inizio, LocalDateTime data_fine) {
		this.event_id = event_id;
		this.data=data;
		this.customers = customers;
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
		this.oreGuasto=(int) Duration.between(data_inizio, data_fine).toHours();
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public int getoreGuasto() {
		return oreGuasto;
	}
	
	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public int getCustomers() {
		return customers;
	}

	public void setCustomers(int customers) {
		this.customers = customers;
	}

	public LocalDateTime getData_inizio() {
		return data_inizio;
	}

	public void setData_inizio(LocalDateTime data_inizio) {
		this.data_inizio = data_inizio;
	}

	public LocalDateTime getData_fine() {
		return data_fine;
	}

	public void setData_fine(LocalDateTime data_fine) {
		this.data_fine = data_fine;
	}
	
	
}
