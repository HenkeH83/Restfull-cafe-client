package com.cafe.controllers;

import java.io.Serializable;
import java.util.ArrayList;
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
	
	private String coffeeChoice;
	private String tempName;
	private long tempPrice;
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
	
	public void orderCoffee() {
		order.setOrder(getCoffee(coffeeChoice));
	}
	
	public void setCoffeeChoice(String coffeeChoice) {
		this.coffeeChoice = coffeeChoice;
	}
	
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	
	public void setTempPrice(long tempPrice) {
		this.tempPrice = tempPrice;
	}
	
	public String getCoffeeChoice() {
		return coffeeChoice;
	}
	
	public String getTempName() {
		return tempName;
	}
	
	public long getTempPrice() {
		return tempPrice;
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

	//Detta är helt och hållet mitt fel, jag var lat när jag gjorde databasen 
	//och har inte lagt till "id" med auto increment (som alla databaser någonsin alltid har)så nu får jag loopa igenom efter namn :S
	// Vet att jag ganska enkelt kan uppdatera databasen, 
	//men då måste jag in och skriva om servern och jag känner inte att jag hinner det just nu....
	public CoffeeBean findCoffeeByName(String name) {
		for(CoffeeBean bean : everyCoffee) {
			if(bean.getName().equals(name));
			return bean;
		}
		return null;
	}
	
	// CRUD methods below.
	
	public void createCoffee() {
		CoffeeBean newCoffee = new CoffeeBean(tempName,tempPrice); 
		setCurrentBean(coffeeClient.createCoffee(newCoffee));
		init();
	}

	public CoffeeBean getCoffee(String name) {
		return coffeeClient.getCoffee(name);
	}
	
	//Möjligtvis en update metod
	
	public void deleteCoffee() {
//		setCurrentBean(findCoffeeByName(coffeeChoice));
//		coffeeClient.deleteCoffee(currentBean);
		coffeeClient.deleteCoffee(coffeeChoice);
		init();
	}
	

}
