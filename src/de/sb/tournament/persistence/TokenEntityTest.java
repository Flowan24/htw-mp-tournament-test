package de.sb.tournament.persistence;
import javax.persistence.EntityManager;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

import de.sb.tournament.persistence.Token;

public class TokenEntityTest extends EntityTest {

	@Test
	public void testConstraints() {
		Validator validator = this.getEntityValidatorFactory().getValidator();
		
		try {
			Token entity;
			
			//test incorrect
			entity = new Token("test",-1000);
			Assert.assertEquals(validator.validate(entity).size(), 1);
			//test correct
			entity = new Token("test",1000);
			Assert.assertEquals(validator.validate(entity).size(), 0);

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			//TODO: Cannot close validator --> validator.close() does not exist 
		}
	}

	@Test
	public void testLifeCycle() {
		EntityManager entityManager = this.getEntityManagerFactory().createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Token entity = new Token("test",1000);
			entityManager.persist(entity);

			entityManager.getTransaction().commit();

			System.out.println("Persisted " + entity);
			getWasteBasket().add(entity.getIdentity());

			Assert.assertNotEquals(0, entity.getIdentity());
		} catch (Exception ex) {

		} finally {
			entityManager.close();
		}
	}
}