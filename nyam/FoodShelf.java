package nyam;


import java.util.ArrayList;
import java.util.Collections;

public class FoodShelf {
    private ArrayList<Food> foodShelf;

    public FoodShelf(){
        foodShelf = new ArrayList<>();
    }

    public int getNumFood(){
        return foodShelf.size();
    }

    public Food takeFood(){
        if(getNumFood() == 0){
            return null;
        }else {
            int lastPos = getNumFood() - 1;
            Food lastFood = foodShelf.get(lastPos);
            foodShelf.remove(lastPos);
            return lastFood;
        }
    }

    public void shuffle(){
        Collections.shuffle(foodShelf);
    }

    public void add(Food card){
        foodShelf.add(card);
    }
}