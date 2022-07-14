package CLASSWORK31;


import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.*;

public class Order {
    // Этот блок кода менять нельзя! НАЧАЛО!
    private final Customer customer;
    private final List<Item> items;
    private final boolean homeDelivery;
    private transient double total = 0.0d;

    public Order(Customer customer, List<Item> orderedItems, boolean homeDelivery) {
        this.customer = customer;
        this.items = List.copyOf(orderedItems);
        this.homeDelivery = homeDelivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return homeDelivery == order.homeDelivery &&
                Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, homeDelivery);
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isHomeDelivery() {
        return homeDelivery;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotal() {
        return total;
    }
    // Этот блок кода менять нельзя! КОНЕЦ!

    //----------------------------------------------------------------------
    //------   Реализация ваших методов должна быть ниже этой линии   ------
    //----------------------------------------------------------------------

    public static void calculateTotal(Order order) {
        var products = order.getItems().stream()
                .mapToDouble(e -> e.getPrice() * e.getAmount())
                .sum();
        order.setTotal(products);
    }
    public static void printOrders(List<Order> orders){
        orders.forEach(System.out::println);
    }
    public static void printMaxOrders(List<Order> orders,int n) {
        var maxOrders = orders.stream()
                .sorted(Comparator.comparing(Order::getTotal).reversed())
                .limit(n)
                .toList();
        maxOrders.forEach(System.out::println);
    }
    public static void printMinOrders(List<Order> orders,int n) {
        var maxOrders = orders.stream()
                .sorted(Comparator.comparing(Order::getTotal))
                .limit(n)
                .toList();
        maxOrders.forEach(System.out::println);
    }
    public static void printToDelivery(List<Order> orders){
        var toDelivery = orders.stream()
                .filter(e -> e.homeDelivery)
                .toList();
        toDelivery.forEach(System.out::println);
    }

    public static void chooseOrdersMoreMax(List<Order> orders,double max){
        var filtered = orders.stream()
                .filter(e -> e.getTotal() < max)
                .toList();
        filtered.forEach(System.out::println);
    }
    public static void chooseOrdersMoreMin(List<Order> orders,double min){
        var filtered = orders.stream()
                .filter(e -> e.getTotal() < min)
                .toList();
        filtered.forEach(System.out::println);
    }
    public static void totalOfOrderTotal(List<Order> orders){
        var total = orders.stream()
                .collect(summarizingDouble(Order::getTotal));
        System.out.println("Total is : " + total.getSum());
    }
    public static void groupByName(List<Order> orders){
        var groupByName = orders.stream()
                .distinct()
                .collect(groupingBy(Order::getCustomer));

        groupByName.forEach((k,v) -> System.out.printf("\n%s - order is %s\n",k.getFullName(),v));
    }
    public static void groupByNameWithTotal(List<Order> orders){
        var groupByName = orders.stream()
                .distinct()
                .collect(groupingBy(Order::getCustomer,summingDouble(Order::getTotal)));

        groupByName.forEach((k,v) -> System.out.printf("\n%s - total is %f\n",k.getFullName(),v));
    }
    public static void groupByNameWithMax(List<Order> orders){
        var groupByName = orders.stream()
                .collect(groupingBy(Order::getCustomer,summingDouble(Order::getTotal)));
        var max = groupByName.entrySet().stream()
                .sorted((e1 , e2) -> e1.getValue().compareTo(e2.getValue()))
                .toList();
        System.out.println(max.get(max.size()-1).getKey().getFullName() + " total is " + max.get(max.size()-1).getValue());;
    }
    public static void groupByNameWithMin(List<Order> orders){
        var groupByName = orders.stream()
                .collect(groupingBy(Order::getCustomer,summingDouble(Order::getTotal)));
        var max = groupByName.entrySet().stream()
                .sorted((e1 , e2) -> e1.getValue().compareTo(e2.getValue()))
                .toList();
        System.out.println(max.get(0).getKey().getFullName() + " total is " + max.get(0).getValue());;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", items=" + items +
                ", homeDelivery=" + homeDelivery +
                ", total=" + total +
                '}';
    }
}
