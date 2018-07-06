package de.sb.tournament.persistence;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

public class TournamentEntityTest extends EntityTest {

	@Test
	public void testConstraints() {
		//WE AGREED THAT WE DONOT IMPLEMENT FURTHER TEST CASES
	}

	@Test
	public void testLifeCycle() {
		EntityManager entityManager = this.getEntityManagerFactory().createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Tournament tournament = new Tournament(null, null);
			entityManager.persist(tournament);

			entityManager.getTransaction().commit();

			System.out.println("Persisted " + tournament);
			getWasteBasket().add(tournament.getIdentity());

			Assert.assertNotEquals(0, tournament.getIdentity());
		} catch (Exception ex) {

		} finally {
			entityManager.close();
		}
	}
}
