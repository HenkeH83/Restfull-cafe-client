package com.cafe;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cafe.beans.CoffeeBean;

@ApplicationScoped
public class CafeClientEndPoint implements Serializable {
	private static final long serialVersionUID = -9102351800267852455L;

	private Client client = ClientBuilder.newClient();
	private final WebTarget base = client.target("http://localhost:8080/cafe-server/webapi/cafeserver");
	private final WebTarget read = base.path("/coffee");
	private final WebTarget create = base.path("/create");
	private final WebTarget readAll = base.path("/coffees");
	private final WebTarget delete = base.path("/delete");

	public CoffeeBean getCoffee(String name) {
		return read.path(name).request(MediaType.APPLICATION_XML).get(CoffeeBean.class);
	}

	public CoffeeBean createCoffee(CoffeeBean bean) {
		return create.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(Entity.xml(bean),
				CoffeeBean.class);
	}

	public void deleteCoffee(String name) {
		delete.path("{name}").resolveTemplate("name", name).request(MediaType.APPLICATION_XML).delete();

//		delete.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).delete(bean.getClass());
	}

	public List<CoffeeBean> getCoffees() {
		return readAll.request(MediaType.APPLICATION_XML).get(new GenericType<List<CoffeeBean>>() {});
	}

}
