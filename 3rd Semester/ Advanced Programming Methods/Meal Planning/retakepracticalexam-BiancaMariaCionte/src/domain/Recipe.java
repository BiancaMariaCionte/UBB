package domain;

import java.util.List;

public class Recipe {
    private String name;
    private  Integer cookingTime;
    private List<String> ingredients;

    public Recipe(String name, Integer cookingTime, List<String> ingredients) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", cookingTime=" + cookingTime +
                ", ingredients=" + ingredients +
                '}';
    }
}
