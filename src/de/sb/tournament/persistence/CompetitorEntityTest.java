import javax.persistence.EntityManager;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

import de.sb.tournament.persistence.Competitor;

public class CompetitorEntityTest extends EntityTest {

	@Test
	public void testConstraints() {
		Validator validator = this.getEntityValidatorFactory().getValidator();
		
		try {
			Competitor entity = new Competitor(null);
			
			//test incorrect
			entity.setAlias("abcd");
			Assert.assertEquals(validator.validate(entity).size(), 1);
			//test correct
			entity.setAlias("abc");
			Assert.assertEquals(validator.validate(entity).size(), 0);
			
			//test incorrect
			entity.setName("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "); //80 characters
			Assert.assertEquals(validator.validate(entity).size(), 1);
			//test correct
			entity.setAlias("Lorem ipsum dolor si"); //20 Characters
			Assert.assertEquals(validator.validate(entity).size(), 0);
			
			//test incorrect
			entity.setName("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "); //80 characters
			Assert.assertEquals(validator.validate(entity).size(), 1);
			//test correct
			entity.setAlias("Lorem ipsum dolor si"); //20 Characters
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
