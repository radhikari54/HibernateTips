package org.thoughts.on.java.model;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAttributeMapping {

	Logger log = Logger.getLogger(this.getClass().getName());

	private EntityManagerFactory emf;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
	}

	@After
	public void close() {
		emf.close();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void persistAndLoad() {
		log.info("... persistAndLoad ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("John");
		a.setLastName("Doe");
		a.setDateOfBirth(new Date(80, 0, 1));
		
		em.persist(a);
		
		em.getTransaction().commit();
		em.close();
		
		em = emf.createEntityManager();
		em.getTransaction().begin();

		a = em.find(Author.class, a.getId());
		log.info(a);
		
		em.getTransaction().commit();
		em.close();
	}
}
