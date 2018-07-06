package de.sb.tournament.persistence;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

public class TournamentTypeEntityTest extends EntityTest {

	@Test
	public void testConstraints() {
		//WE AGREED THAT WE DONOT IMPLEMENT FURTHER TEST CASES
	}

	@Test
	public void testLifeCycle() {
		EntityManager entityManager = this.getEntityManagerFactory().createEntityManager();

		try {
			entityManager.getTransaction().begin();
			TournamentType entity = new TournamentType(null, null);
			entityManager.persist(entity);

			entityManager.getTransaction().commit();

			System.out.println("Persisted " + entity);
			getWasteBasket().add(entity.getIdentity());

			Assert.assertNotEquals(0, entity.getIdentity());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			entityManager.close();
		}
	}
}
