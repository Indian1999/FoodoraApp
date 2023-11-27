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
    
}
