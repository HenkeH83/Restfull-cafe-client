package com.cafe.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class OrderBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<CoffeeBean> coffeeList = new ArrayList<>();
	private long total = 0;

	public List<CoffeeBean> getOrder() {
		return coffeeList;
	}

	public void setOrder(CoffeeBean coffee) {
		coffeeList.add(coffee);
		for(CoffeeBean cb : coffeeList) {
			total += cb.getPrice();
		}
	}

	public long getTotal() {
		return total;
	}
	
}
