import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import de.sb.tournament.persistence.BaseEntity;

public class EntityTest {

	private static ArrayList<Long> wasteBasket;
	
	private static EntityManagerFactory entityManagerFactory;
	private static ValidatorFactory validatorFactory;

	
	@BeforeClass
	public static void beforeEachTest() {
	    entityManagerFactory = Persistence.createEntityManagerFactory("BaseEntity");		
	    validatorFactory =  Validation.buildDefaultValidatorFactory();
	}
	
	@AfterClass
	public static void afterEachText() {
		entityManagerFactory.close();
		validatorFactory.close();
		
		entityManagerFactory = null;
	    validatorFactory =  null;
	}
	
	@After
	public static void afterTests() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseEntity");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		for(long identity : getWasteBasket()) {
			Object obj = em.find(BaseEntity.class,identity,LockModeType.NONE);
			em.remove(obj);
		}
		em.getTransaction().commit();
		getWasteBasket().clear();
		em.close();
		emf.close();
	}
	
	public EntityManagerFactory getEntityManagerFactory()
	{
		return entityManagerFactory;
	}
	
	public ValidatorFactory getEntityValidatorFactory()
	{
		return validatorFactory;
	}
	
	public static ArrayList<Long> getWasteBasket() {
		return wasteBasket;
	}
}
