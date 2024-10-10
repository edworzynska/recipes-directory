import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.startSession();
        RecipeRepository recipeRepository = new RecipeRepository(sessionFactory);

        recipeRepository.all().forEach(recipe -> System.out.println(recipe.getName() + recipe.getRating()));

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("from Recipe", Recipe.class)
                    .getResultList()
                    .forEach(recipe -> System.out.println(recipe.getName() + " - " + recipe.getRating()));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}