
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecipeRepositoryTest {
    private HibernateUtil hibernateUtil;
    private RecipeRepository recipeRepository;
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        hibernateUtil = new HibernateUtil();
        sessionFactory = hibernateUtil.startSession();
        sessionFactory.getSchemaManager().truncateMappedObjects();
    }
    @Test
    void returnsAllRecipes() {
        recipeRepository = new RecipeRepository(sessionFactory);
        var result = recipeRepository.all().size();
        assertEquals(4, result);
    }
    @Test
    void addsRecipeAndReturnsAll(){

        Recipe newRecipe = new Recipe("water", 0, 5);
        recipeRepository = new RecipeRepository(sessionFactory);
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(newRecipe);
        session.getTransaction().commit();
        var result = recipeRepository.all().size();

        assertEquals(5, result);
    }

    @Test
    void findsRecipesByRating() {
        Recipe recipe;
        recipeRepository = new RecipeRepository(sessionFactory);
        Recipe resultRecipe = recipeRepository.find(5);
        assertEquals("salmon", resultRecipe.getName());

    }
}