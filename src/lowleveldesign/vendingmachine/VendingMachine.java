package lowleveldesign.vendingmachine;

import java.util.Map;

public class VendingMachine {
    Map<Coffee, Double> menu;
    Map<Ingredient, Double> inventory;
    Map<Coffee, Map<Ingredient, Double>> recipe;

    public VendingMachine(Map<Coffee, Double> menu, Map<Ingredient, Double> inventory, Map<Coffee, Map<Ingredient, Double>> recipe) {
        this.menu = menu;
        this.inventory = inventory;
        this.recipe = recipe;
    }

    public void showMenu() {
        menu.forEach(((coffee, amount) -> {
            System.out.println("Coffee " + coffee.name + " ->  " + amount);
        }));
    }

    public Coffee selectCoffee(String name) {
        for(Coffee coffee: menu.keySet()) {
            if(coffee.name.equals(name)) {
                return coffee;
            }
        }
        return null;
    }

    public Coffee dispenseCoffee(Coffee coffee, double amount) {
        double price = menu.get(coffee);
        if(menu.get(coffee) <= amount) {
            if(hasEnoughIngredients(coffee)) {
                updateIngredients(coffee);
                double change = amount - price;
                if(change > 0) {
                    System.out.println("Take your change");
                }
            } else {
                System.out.println("Coffee " + coffee.name + " is out of order");
            }
        }

        return coffee;
    }

    private boolean hasEnoughIngredients(Coffee coffee) {
        Map<Ingredient, Double> coffeeRecipe = this.recipe.get(coffee);
        for(Map.Entry<Ingredient, Double> entry : coffeeRecipe.entrySet()) {
            if(!inventory.containsKey(entry.getKey()) || inventory.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }

        return true;
    }

    private void updateIngredients(Coffee coffee) {
        Map<Ingredient, Double> coffeeRecipe = this.recipe.get(coffee);
        for(Map.Entry<Ingredient, Double> entry : coffeeRecipe.entrySet()) {
            if(inventory.containsKey(entry.getKey())) {
                inventory.put(entry.getKey(), entry.getValue() - coffeeRecipe.get(entry.getKey()));
            }
        }
    }
}
