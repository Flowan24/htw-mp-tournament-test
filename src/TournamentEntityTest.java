import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

import de.sb.tournament.persistence.Tournament;

public class TournamentEntityTest extends EntityTest {

	@Test
	public void testConstraints() {
		Validator validator = this.getEntityValidatorFactory().getValidator();
		
		try {
			Tournament entity = new Tournament(null, null);
			Set<ConstraintViolation<Tournament>> constraintViolations = validator.validate(entity);
			Assert.assertEquals(constraintViolations.size(), 0);
		} catch (Exception e) {
			
		} finally {
			//TODO: Cannot close validator --> validator.close() does not exist 
		}
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
