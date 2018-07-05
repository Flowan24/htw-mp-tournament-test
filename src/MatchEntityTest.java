import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
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
			Set<ConstraintViolation<Match>> constraintViolations = validator.validate(entity);
			Assert.assertEquals(constraintViolations.size(), 0);
		} catch (Exception e) {
			
		} finally {
			
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

		} finally {
			entityManager.close();
		}
	}
}
