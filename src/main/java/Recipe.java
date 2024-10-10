import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Recipes")

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_seq")
    @SequenceGenerator(name = "recipe_seq", sequenceName = "recipe_sequence", allocationSize = 1)
    private Long id;
    @Getter
    @Setter
    @Column(name="name")
    private String name;
    @Getter
    @Setter
    @Column(name="average_cooking_time")
    private int averageCookingTime;
    @Getter
    @Setter
    @Column(name="rating")
    private int rating;

    public Recipe(){}

    public Recipe(String name, int averageCookingTime, int rating) {
        this.name = name;
        this.averageCookingTime = averageCookingTime;
        this.rating = rating;
    }
}
