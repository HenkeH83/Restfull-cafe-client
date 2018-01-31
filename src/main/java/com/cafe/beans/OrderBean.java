package com.cafe.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class OrderBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<CoffeeBean> coffeeList = new ArrayList<>();
	private long total; 

	public List<CoffeeBean> getOrder() {
		return coffeeList;
	}

	public void setOrder(CoffeeBean coffee) {
		total = 0;
		coffeeList.add(coffee);
		for(CoffeeBean bean : coffeeList) {
			total += bean.getPrice();
		}
	}
	
	public void removeFromOrder(String name) {
		for (Iterator<CoffeeBean> iterator = coffeeList.iterator(); iterator.hasNext(); ) {
		    CoffeeBean bean = iterator.next();
		    if (bean.getName().equals(name)) {
		    	total -= bean.getPrice();
		        iterator.remove();
		        break;
		    }
		}
	}

	public long getTotal() {
		return total;
	}
	
	public void executeOrder66() {
		coffeeList.clear();
		total = 0;
	}
	
}
