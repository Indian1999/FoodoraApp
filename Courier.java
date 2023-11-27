import java.util.List;

class Courier extends User
{
    private List<Order> pastDeliveries;
    private Order activeDelivery;

    Courier(String username, String pw, String email)
    {
        super(username, pw, email);
    }

    public void setActiveDelivery(Order o)
    {
        activeDelivery = o;
    }
    public void completeDelivery()
    {
        //set activeDelivery to completed
        pastDeliveries.add(activeDelivery);
        activeDelivery = null;
    }
    public void listCommands() 
    {
        //TO BE IMPLEMENTED
    }
    public void listReadyOrders()
    {
        //TO BE IMPLEMENTED
    }
}
