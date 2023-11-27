import java.util.List;

class Customer extends User
{
    private String address;
    private List<Order> pastOrders;
    private List<Restaurant> favouriteRestaurants;

    Customer(String username, String pw, String email)
    {
        super(username, pw, email);
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String newAddress)
    {
        address = newAddress;
    }

    public void addFavouriteRestaurant(Restaurant r)
    {
        favouriteRestaurants.add(r);
    }

    public void sendOrder(Order o)
    {
        pastOrders.add(o);
        try
        {
            o.setStatus("PLACED");
            o.getRestaurant().receiveOrder(o);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void listCommands()
    {
        // TO BE IMPLEMENTED
    }
}
