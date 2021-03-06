package de.sb.tournament.persistence;

import javax.persistence.EntityManager;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

import de.sb.tournament.persistence.Document;

public class DocumentEntityTest extends EntityTest {

	@Test
	public void testConstraints() {
		Validator validator = this.getEntityValidatorFactory().getValidator();

		Document entity;
		try {
			
			// test incorrect
			// 64 characters & 16777216 byte array
			entity = new Document("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed dia",new byte[16777216]);
			Assert.assertEquals(validator.validate(entity).size(), 2);
			// test correct
			// 63 characters & 16777215 byte array
			entity = new Document("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed di",new byte[16777215]);
			Assert.assertEquals(validator.validate(entity).size(), 0);
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Test
	public void testLifeCycle() {
		EntityManager entityManager = this.getEntityManagerFactory().createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Document entity = new Document(null, null);
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
