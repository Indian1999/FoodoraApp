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
        // TO BE IMPLEMENTED
    }

    public void executeCommand(String command)
    {
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
}
