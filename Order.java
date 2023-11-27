import java.util.List;
import java.time.LocalDateTime;

class Order {
    private Customer customer;
    private Restaurant restaurant;
    private Courier courier;
    private List<Item> items;

    private enum status {
        PLACED, READY, BEING_DELIVERED, DELIVERED
    }

    private LocalDateTime orderPlace;

    private LocalDateTime orderReady;
    private LocalDateTime orderDeliveryPickup;
    private LocalDateTime orderDelivered;

    Order(Customer c, Restaurant r, List<Item> items) {
        this.customer = c;
        this.restaurant = r;
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer c) {
        customer = c;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant r) {
        restaurant = r;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier c) {
        courier = c;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public LocalDateTime getOrderPlace() {
        return orderPlace;
    }

    public void setOrderPlace(LocalDateTime orderPlace) {
        this.orderPlace = orderPlace;
    }

    public LocalDateTime getOrderReady() {
        return orderReady;
    }

    public void setOrderReady(LocalDateTime orderReady) {
        this.orderReady = orderReady;
    }

    public LocalDateTime getOrderDeliveryPickup() {
        return orderDeliveryPickup;
    }

    public void setOrderDeliveryPickup(LocalDateTime orderDeliveryPickup) {
        this.orderDeliveryPickup = orderDeliveryPickup;
    }

    public LocalDateTime getOrderDelivered() {
        return orderDelivered;
    }

    public void setOrderDelivered(LocalDateTime orderDelivered) {
        this.orderDelivered = orderDelivered;
    }

}
