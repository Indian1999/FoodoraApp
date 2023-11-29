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
        try
        {
            activeDelivery.setStatus("BEING_DELIVERED");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Order getActiveDelivery()
    {
        return activeDelivery;
    }

    public List<Order> getPastDeliveries()
    {
        return pastDeliveries;
    }
    
    public void setPastDeliveries(List<Order> pastDeliveries) 
    {
        this.pastDeliveries = pastDeliveries;
    }

    public void addToPastDeliveries(Order order)
    {
        pastDeliveries.add(order);
    }

    public void completeDelivery()
    {
        try
        {
            activeDelivery.setStatus("DELIVERED");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        pastDeliveries.add(activeDelivery);
        activeDelivery = null;
    }

    public void listCommands()
    {
        // TO BE IMPLEMENTED
    }

    public void listReadyOrders()
    {
        // TO BE IMPLEMENTED
    }

    public void executeCommand(String command)
    {
    }

}
