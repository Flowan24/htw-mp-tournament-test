package de.sb.tournament.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * Application for sanity checking the tournament-model JPA setup.
 */
public class SanityCheck {

	public static void main (final String[] args) {
		//TODO: DO sanityCheck
		final EntityManagerFactory emf = Persistence.createEntityManagerFactory("tournament");
		final EntityManager em = emf.createEntityManager();
		
		final Query query1 = em.createQuery("select t from Tournament as t");
		System.out.println(query1.getResultList());
		
		
		final Query query2 = em.createQuery("select t from TournamentType as t");
		System.out.println(query2.getResultList());
		
		final Query query3 = em.createQuery("select m from Match as m");
		System.out.println(query3.getResultList());
		
		final Query query4 = em.createQuery("select c from Competitor as c");
		System.out.println(query4.getResultList());
		
		final Query query5 = em.createQuery("select d from Document as d");
		System.out.println(query5.getResultList());
		
		final Query query6 = em.createQuery("select g from Group as g");
		System.out.println(query6.getResultList());
		
		final Query query7 = em.createQuery("select t from Token as t");
		System.out.println(query7.getResultList());
		
		final Query query8 = em.createQuery("select b.identity from BaseEntity as b");
		System.out.println("***********\n***********\n***********\n***********");
		System.out.println("Final Result, the Amount of Base-Entities in the Database should be 642. ==> " + query8.getResultList().size());
	}
}
