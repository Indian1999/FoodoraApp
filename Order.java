import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Order
{
    private enum Status
    {
        BEING_ORDERED,
        PLACED,
        READY,
        BEING_DELIVERED,
        DELIVERED
    }
    private Customer customer;
    private Restaurant restaurant;
    private Courier courier;
    private List<Item> items = new ArrayList<Item>();
    private Status status;
    private LocalDateTime orderPlace;
    private LocalDateTime orderReady;
    private LocalDateTime orderDeliveryPickup;
    private LocalDateTime orderDelivered;

    Order(Customer customer, Restaurant restaurant)
    {
        this.customer = customer;
        this.restaurant = restaurant;
        this.status = Status.BEING_ORDERED;
    }

    Order(Customer customer, Restaurant restaurant, Courier courier, String status)
    {
        this.customer = customer;
        this.restaurant = restaurant;
        this.courier = courier;
        try
        {
            setStatus(status);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer c)
    {
        customer = c;
    }

    public Restaurant getRestaurant()
    {
        return restaurant;
    }

    public void setRestaurant(Restaurant r)
    {
        restaurant = r;
    }

    public Courier getCourier()
    {
        return courier;
    }

    public void setCourier(Courier c)
    {
        courier = c;
    }

    public List<Item> getItems()
    {
        return items;
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public void removeItem(int i)
    {
        items.remove(i);
    }

    public void setItems(List<Item> items)
    {
        this.items = items;
    }

    public LocalDateTime getOrderPlace()
    {
        return orderPlace;
    }

    public void setOrderPlace(LocalDateTime orderPlace)
    {
        this.orderPlace = orderPlace;
    }

    public LocalDateTime getOrderReady()
    {
        return orderReady;
    }

    public void setOrderReady(LocalDateTime orderReady)
    {
        this.orderReady = orderReady;
    }

    public LocalDateTime getOrderDeliveryPickup()
    {
        return orderDeliveryPickup;
    }

    public void setOrderDeliveryPickup(LocalDateTime orderDeliveryPickup)
    {
        this.orderDeliveryPickup = orderDeliveryPickup;
    }

    public LocalDateTime getOrderDelivered()
    {
        return orderDelivered;
    }

    public void setOrderDelivered(LocalDateTime orderDelivered)
    {
        this.orderDelivered = orderDelivered;
    }

    public String getStatus() throws Exception
    {
        if (status.equals(Status.BEING_ORDERED))
            return "BEING_ORDERED";
        else if (status.equals(Status.PLACED))
            return "PLACED";
        else if (status.equals(Status.READY))
            return "READY";
        else if (status.equals(Status.BEING_DELIVERED))
            return "BEING_DELIVERED";
        else if (status.equals(Status.DELIVERED))
            return "DELIVERED";
        else
            throw new Exception("Status is not set!");
    }

    public void setStatus(String status) throws Exception
    {
        if (status.equals("BEING_ORDERED"))
            this.status = Status.BEING_ORDERED;
        else if (status.equals("PLACED"))
            this.status = Status.PLACED;
        else if (status.equals("READY"))
            this.status = Status.READY;
        else if (status.equals("BEING_DELIVERED"))
            this.status = Status.BEING_DELIVERED;
        else if (status.equals("DELIVERED"))
            this.status = Status.DELIVERED;
        else
            throw new Exception("Invalid status to set (" + status + ")");
    }

    public void listItems()
    {
        int i = 1;
        for (Item item : items)
        {
            System.out.println(i + ". " + item.getName() + " " + item.getPrice() + " " +
                               item.getCategory());
            i += 1;
        }
    }

    public String toString()
    {
        String output = "";
        try
        {
            output +=
                this.getCustomer().getEmail() + ";" + this.getRestaurant().getUsername() + ";";
            if (this.getCourier() == null)
            {
                output += "null" + ";" + this.getStatus();
            }
            else
            {
                output += this.getCourier().getUsername() + ";" + this.getStatus();
            }
            for (Item item : this.getItems())
            {
                output += ";" + item.getName() + ";" + item.getPrice() + ";" + item.getCategory();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return output;
    }
}
