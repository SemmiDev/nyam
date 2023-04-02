package nyam;

import java.util.ArrayList;

public class Service extends FoodShelf {
    private int totalCustomer;
    private Chef chef;
    private ArrayList<ArrayList<Food>> customerFoodList;

    private final static int DEFAULT_TOTAL_FOOD = 7;

    public Service(int totalCustomer){
        this.totalCustomer = totalCustomer;
        customerFoodList = new ArrayList<>();
        chef = new Chef();
    }

    public void initService(){
        chef.shuffle();
        for(int i = 0; i < totalCustomer; i++){
            ArrayList<Food> customerFood = new ArrayList<>();
            for(int j = 0; j < DEFAULT_TOTAL_FOOD; j++){
                customerFood.add(chef.takeFood());
            }
            customerFoodList.add(customerFood);
        }
    }

    public int getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(int totalCustomer) {
        this.totalCustomer = totalCustomer;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public ArrayList<ArrayList<Food>> getCustomerFoodList() {
        return customerFoodList;
    }

    public void setCustomerFoodList(ArrayList<ArrayList<Food>> customerFoodList) {
        this.customerFoodList = customerFoodList;
    }
}
