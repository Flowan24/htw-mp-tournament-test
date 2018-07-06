package de.sb.tournament.persistence;

import javax.persistence.EntityManager;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

import de.sb.tournament.persistence.Match;

public class MatchEntityTest extends EntityTest {

	@Test
	public void testConstraints() {
		Validator validator = this.getEntityValidatorFactory().getValidator();

		try {
			
			Match entity = new Match(null, null, (byte) 0, null, (byte) 0);
			Short shortTest = -1;
			// test incorrect
			entity.setLeftScore(shortTest);
			Assert.assertEquals(validator.validate(entity).size(), 1);
			// test correct
			shortTest = 0;
			entity.setLeftScore(shortTest);
			Assert.assertEquals(validator.validate(entity).size(), 0);
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Test
	public void testLifeCycle() {
		EntityManager entityManager = this.getEntityManagerFactory().createEntityManager();

		try {
			
			entityManager.getTransaction().begin();
			Match entity = new Match(null, null, (byte) 0, null, (byte) 0);
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
