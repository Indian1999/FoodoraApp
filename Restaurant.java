import java.util.List;

class Restaurant extends User
{
    private List<Item> menu;
    private List<Order> activeOrders;

    public Restaurant(String username, String pw, String email)
    {
        super(username, pw, email);
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
            try {
                o.setStatus("READY");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            throw new Exception("This restaurant does not have this order.");
        }
    }
}
