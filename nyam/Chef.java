package nyam;

import java.util.Random;
import java.util.UUID;

public class Chef extends FoodShelf {
    public Chef() {
        for (int i = 1; i <= 17; i++) {
            Food food = new Food(
                    Food.Variant.BREAKFAST,
                    UUID.randomUUID().toString(),
                    randomNumber(1, 17) + ".jpg");
            add(food);
        }

        for (int i = 18; i <= 34; i++) {
            Food food = new Food(
                    Food.Variant.LUNCH,
                    UUID.randomUUID().toString(),
                    randomNumber(18, 34) + ".jpg");
            add(food);
        }

        for (int i = 35; i <= 52; i++) {
            Food food = new Food(
                    Food.Variant.DINNER,
                    UUID.randomUUID().toString(),
                    randomNumber(35, 52) + ".jpg");
            add(food);
        }
    }

    public String randomNumber(int min, int max) {
        Random random = new Random();
        int randomInt = random.ints(min, max)
                .findFirst()
                .getAsInt();
        if (randomInt < 10) {
            return "0" + randomInt;
        }
        return String.valueOf(randomInt);
    }
}
