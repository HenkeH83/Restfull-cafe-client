package com.cafe.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author henke
 *
 */
@Named
@RequestScoped
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CoffeeBean implements Serializable{
	private static final long serialVersionUID = -6145621900656841534L;
	
	@XmlElement
	private String name;
	@XmlElement
	private long price;
	
	public CoffeeBean() {	}
	
	public CoffeeBean(String name, long price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CoffeeBean [name=" + name + ", price=" + price + "]";
	}	
	
	

}

