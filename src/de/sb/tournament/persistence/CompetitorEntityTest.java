package de.sb.tournament.persistence;

import javax.persistence.EntityManager;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

import de.sb.tournament.persistence.Competitor;

public class CompetitorEntityTest extends EntityTest {

	@Test
	public void testConstraints() {
		Validator validator = this.getEntityValidatorFactory().getValidator();

		Document document = new Document("img", new byte[] { 1, 4, 4 });
		Competitor entity = new Competitor(document);
		
		try {
			// test incorrect
			entity.setAlias("abcd");
			Assert.assertEquals(validator.validate(entity).size(), 1);
			// test correct
			entity.setAlias("abc");
			Assert.assertEquals(validator.validate(entity).size(), 0);

			// test incorrect
			entity.setName("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "); // 80
																												// characters
			Assert.assertEquals(validator.validate(entity).size(), 1);
			// test correct
			entity.setName("Lorem ipsum dolor si"); // 20 Characters
			Assert.assertEquals(validator.validate(entity).size(), 0);

			// test incorrect
			entity.setAvatar(null);
			Assert.assertEquals(validator.validate(entity).size(), 1);
			// test correct
			entity.setAvatar(document);
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
			Competitor entity = new Competitor(null);
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
