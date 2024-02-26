package entities;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Sale {
	private int saleId;
	private LocalDateTime date;
	private int userId;
	private String state;
	private User user;
	private LinkedList<SaleDetails> saleDetails;
	
	public LinkedList<SaleDetails> getSaleDetails() {
		return saleDetails;
	}
	public void setSaleDetails(LinkedList<SaleDetails> saleDetails) {
		this.saleDetails = saleDetails;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
