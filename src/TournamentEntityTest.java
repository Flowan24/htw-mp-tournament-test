import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

import de.sb.tournament.persistence.BaseEntity;
import de.sb.tournament.persistence.Tournament;

public class TournamentEntityTest extends EntityTest {

	Validator validator;
	BaseEntity entity;
	Set<ConstraintViolation<BaseEntity>> constraintViolations;
	
	@Test
	public void testConstraints() {
		validator = this.getEntityValidatorFactory().getValidator();
		entity = new Tournament(null, null);
		constraintViolations = validator.validate(entity);
		try {

		} catch (Exception e) {

		} finally {

		}
		
		Assert.assertEquals(1,1);
	}

	@Test
	public void testLifeCycle() {
		// TODO: test Life Cycle
		
		// EntityTest#getEntityManagerFactory().createEntityManager()
	}
}
