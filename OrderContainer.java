import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

class OrderContainer
{
    private List<Order> orders;
    // clang-format off
    OrderContainer() {}
    // clang-format on

    public void add(Order o)
    {
        orders.add(o);
    }

    public void exportToFile(String path)
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(path);
            for (Order order : orders)
            {
                // clang-format off
                writer.println( 
                        order.getCustomer() + ";" + 
                        order.getRestaurant() + ";" + 
                        order.getCourier() + ";" + 
                        order.getStatus() + ";" + 
                        order.getOrderPlace() + ";" + 
                        order.getOrderReady() + ";" + 
                        order.getOrderDeliveryPickup() + ";" + 
                        order.getOrderDelivered()
                    );
                // clang-format on
                int i = 0;
                for (Item item : order.getItems())
                {
                    if (i < order.getItems().size() - 1)
                    {
                        writer.print(item.getName() + ":" + item.getPrice() +
                                     ":" + item.getCategory() + ";");
                    }
                    else
                    {
                        writer.print(item.getName() + ":" + item.getPrice() +
                                     ":" + item.getCategory());
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public List<Order> getOrders()
    {
        return orders;
    }
}
