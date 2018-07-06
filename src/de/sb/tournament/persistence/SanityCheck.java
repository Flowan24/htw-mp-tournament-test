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
	}
}
