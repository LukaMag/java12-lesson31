package CLASSWORK31;

public class Main {
    public static void main(String[] args) {
        RestaurantOrders restaurantOrders = RestaurantOrders.read("orders_10_000.json");

        Order.groupByName(restaurantOrders.getOrders());

        // протестировать ваши методы вы можете как раз в этом файле (или в любом другом, в котором вам будет удобно)
    }
}
