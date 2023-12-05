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
        System.out.println("0. Exit application");
        System.out.println("1. Change name");
        System.out.println("2. Change e-mail");
        System.out.println("3. Change password");
        System.out.println("4. List past deliveries");
        System.out.println("5. List orders ready for pickup");
        System.out.println("6. Accept a delivery");
        System.out.println("7. Set current delivery as completed");
        System.out.println("8. Logout");
    }

    public void listReadyOrders()
    {
        // TO BE IMPLEMENTED
        // List all orders from all restaurants that are ready to be picked up
    }
}
