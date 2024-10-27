import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RecipeRepository {

    private SessionFactory sessionFactory;

    public RecipeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
//findAll
    public List<Recipe> all() {
        List<Recipe> allRecipes = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            allRecipes = session.createQuery("from Recipe", Recipe.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return allRecipes;
    }
    public Recipe find(int rating){
        Recipe recipe;
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            recipe = session.createQuery(String.format("from Recipe where rating=%d", rating), Recipe.class).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return recipe;
    }
}
