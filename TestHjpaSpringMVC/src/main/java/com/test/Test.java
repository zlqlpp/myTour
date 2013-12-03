package com.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.Pojo;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_test");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Pojo pojo = new Pojo();
		pojo.setId(1);
		pojo.setName("test");
		pojo.setPassword("password");
		
		em.persist(pojo);
		
		em.getTransaction().commit();
	}

}
