import java.util.ArrayList;
import java.util.List;

class Restaurant extends User
{
    private List<Item> menu = new ArrayList<Item>();
    private List<Order> activeOrders = new ArrayList<Order>();

    public Restaurant(String username, String pw, String email)
    {
        super(username, pw, email);
    }

    public void printRestaurant() {
        System.out.println(getUsername() + ";" + getPassword() + ";" + getEmail());
    }

    public List<Order> getActiveOrders()
    {
        return activeOrders;
    }

    public void setActiveOrders(List<Order> activeOrders)
    {
        this.activeOrders = activeOrders;
    }

    public List<Item> getMenu()
    {
        return menu;
    }

    public void addItemToMenu(Item item)
    {
        menu.add(item);
    }

    public void listCommands()
    {
        System.out.println("\n----------------------------");
        System.out.println("Choose an action!");
        System.out.println("0. Exit application");
        System.out.println("1. Change name");
        System.out.println("2. Change e-mail");
        System.out.println("3. Change password");
        System.out.println("4. List my menu");
        System.out.println("5. Edit menu");
        System.out.println("6. List active orders");
        System.out.println("7. Modify an order");
        System.out.println("8. Logout");
        System.out.println("----------------------------");
    }

    public void receiveOrder(Order o)
    {
        activeOrders.add(o);
    }

    public void setOrderReady(Order o) throws Exception
    {
        if (activeOrders.contains(o))
        {
            try
            {
                o.setStatus("READY");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            throw new Exception("This restaurant does not have this order.");
        }
    }

    public void listRestaurantMenu()
    {
        int index = 1;
        for (Item item : menu)
        {
            System.out.println(index + ". - " + item.getName() + ", " + item.getPrice() +
                               ", category: " + item.getCategory());
            index++;
        }
    }

    public void addToActiveOrders(Order order)
    {
        activeOrders.add(order);
    }

    public void removeItemFromMenu(int index)
    {
        menu.remove(index);
    }

    public void listActiveOrders()
    {
        int i = 1;
        for (Order order : activeOrders)
        {
            System.out.println(i + ". " + order.toString());
            i += 1;
        }
    }

    public void removeFromActiveOrders(Order o) 
    {
        activeOrders.remove(o);
    }
}
