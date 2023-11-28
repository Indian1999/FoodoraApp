import java.util.ArrayList;
import java.util.List;

class Customer extends User
{
    private String address;
    private List<Order> pastOrders = new ArrayList<Order>();
    private List<Restaurant> favouriteRestaurants = new ArrayList<Restaurant>();

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
        System.out.println("0. Exit application");
        System.out.println("1. Change address");
        System.out.println("2. Change name");
        System.out.println("3. Change e-mail");
        System.out.println("4. Change password");
        System.out.println("5. List restaurants");
        System.out.println("6. List restaurant menu");
        System.out.println("7. Choose restaurant");
        System.out.println("8. Logout");
    }
}
