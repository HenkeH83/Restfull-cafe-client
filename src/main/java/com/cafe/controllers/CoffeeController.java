package com.cafe.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cafe.CafeClientEndPoint;
import com.cafe.beans.CoffeeBean;
import com.cafe.beans.OrderBean;

@Named
@SessionScoped
public class CoffeeController implements Serializable{
	private static final long serialVersionUID = -1899230453312620402L;
	
	private String coffeeChoiceName;
	private long coffeeChoicePrice;
	private List<CoffeeBean> everyCoffee;
	private CoffeeBean currentBean;
	
	@Inject
	CafeClientEndPoint coffeeClient;
	@Inject
	OrderBean order;
	
	@PostConstruct
	public void init() {
		setEveryCoffee();
		getEveryCoffee();
	}
	
	
	public void setCoffeeChoiceName(String coffeeChoiceName) {
		this.coffeeChoiceName = coffeeChoiceName;
	}
	
	public void setCoffeeChoicePrice(long coffeeChoicePrice) {
		this.coffeeChoicePrice = coffeeChoicePrice;
	}
	
	public String getCoffeeChoiceName() {
		return coffeeChoiceName;
	}
	
	public long getCoffeeChoicePrice() {
		return coffeeChoicePrice;
	}
	
	public void setCurrentBean(CoffeeBean bean) {
		this.currentBean = bean;
	}
	
	public CoffeeBean getCurrentBean() {
		return currentBean; 
	}
	
	public void setEveryCoffee() {
		this.everyCoffee = coffeeClient.getCoffees();
	}

	public List<CoffeeBean> getEveryCoffee(){
		return everyCoffee;
	}
	
	public void orderCoffee() {
		order.setOrder(getCoffee(coffeeChoiceName));
	}
	
	public void purchase() {
		/* Här skickas massa info till en fet bockförings backend så ägarna kan se hur mycket de tjänat 
		 * Men vi nöjer oss med att att bara rensa listen just nu.*/
		order.executeOrder66();
	}
	
	public void removeCoffeeFromOrder() {
		order.removeFromOrder(coffeeChoiceName);
	}
	
	// CRUD methods below.
	
	public void createCoffee() {
		CoffeeBean newCoffee = new CoffeeBean(coffeeChoiceName,coffeeChoicePrice); 
		setCurrentBean(coffeeClient.createCoffee(newCoffee));
		coffeeChoiceName = "";
		coffeeChoicePrice = 0;
		init();
	}
	

	public CoffeeBean getCoffee(String name) {
		return coffeeClient.getCoffee(name);
	}
	
	public void deleteCoffee() {
		coffeeClient.deleteCoffee(coffeeChoiceName);
		coffeeChoiceName = "";
		coffeeChoicePrice = 0;
		init();
	}
	

}
