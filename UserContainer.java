import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class UserContainer
{
    private List<Admin> admins = new ArrayList<Admin>();
    private List<Restaurant> restaurants = new ArrayList<Restaurant>();
    private List<Courier> couriers = new ArrayList<Courier>();
    private List<Customer> customers = new ArrayList<Customer>();

    UserContainer()
    {
        // TO BE IMPLEMENTED
        // IMPORT FROM FILE IF POSSIBLE
    }

    public List<Admin> getAdmins()
    {
        return admins;
    }

    public List<Courier> getCouriers()
    {
        return couriers;
    }

    public List<Customer> getCustomers()
    {
        return customers;
    }

    public List<Restaurant> getRestaurants()
    {
        return restaurants;
    }

    public void add(Admin a)
    {
        admins.add(a);
    }

    public void add(Restaurant r)
    {
        restaurants.add(r);
    }

    public void add(Courier c)
    {
        couriers.add(c);
    }

    public void add(Customer c)
    {
        customers.add(c);
    }

    public Restaurant getRestaurantByIndex(int i)
    {
        return restaurants.get(i);
    }

    public Admin getAdminByIndex(int i)
    {
        return admins.get(i);
    }

    public Courier getCourierByIndex(int i)
    {
        return couriers.get(i);
    }

    public Customer getCustomerByIndex(int i)
    {
        return customers.get(i);
    }

    public User loginUser(String email, String pw) throws Exception
    {
        for (Admin account : admins)
        {
            if (account.getEmail().equals(email) && account.getPassword().equals(pw))
            {
                return account;
            }
        }
        for (Restaurant account : restaurants)
        {
            if (account.getEmail().equals(email) && account.getPassword().equals(pw))
            {
                return account;
            }
        }
        for (Courier account : couriers)
        {
            if (account.getEmail().equals(email) && account.getPassword().equals(pw))
            {
                return account;
            }
        }
        for (Customer account : customers)
        {
            if (account.getEmail().equals(email) && account.getPassword().equals(pw))
            {
                return account;
            }
        }
        throw new Exception("E-mail or password is incorrect!");
    }

    public void listRestaurants()
    {
        for (int i = 1; i <= restaurants.size(); i++)
        {
            System.out.println(i + ". - " + restaurants.get(i - 1).getUsername());
        }
    }

    public void listRestaurantMenu(int i)
    {
        if (i > restaurants.size())
            throw new IndexOutOfBoundsException();
        int index = 1;
        for (Item item : restaurants.get(i - 1).getMenu())
        {
            System.out.println(index + ". - " + item.getName() + ", " + item.getPrice() +
                               ", category: " + item.getCategory());
            index++;
        }
    }


    public void addItemToRestaurantMenu(Restaurant r)
    {
        // restaurants.
    }

    public void exportToFile(String path)
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(new File("users.txt")), StandardCharsets.UTF_16));
            writer.println("admin");
            for (Admin admin : admins)
            {
                writer.write(admin.getUsername() + ";" + admin.getPassword() + ";" +
                             admin.getEmail() + "\n");
            }
            writer.println("couriers");
            for (Courier courier : couriers)
            {
                writer.println(courier.getUsername() + ";" + courier.getPassword() + ";" +
                               courier.getEmail());
                if (courier.getPastDeliveries() == null)
                {
                    writer.println(0);
                }
                else
                {
                    writer.println(courier.getPastDeliveries().size());
                    for (Order order : courier.getPastDeliveries())
                    {
                        try
                        {
                            writer.write(order.getCustomer().getEmail() + ";" +
                                         order.getRestaurant() + ";" + order.getCourier() + ";" +
                                         order.getStatus());
                            for (Item item : order.getItems())
                            {
                                writer.write(";" + item.getName() + ";" + item.getPrice() + ";" +
                                             item.getCategory());
                            }
                            writer.write("\n");
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                if (courier.getActiveDelivery() == null)
                {
                    writer.println(0);
                }
                else
                {
                    try
                    {
                        writer.write(courier.getActiveDelivery().getCustomer().getEmail() + ";" +
                                     courier.getActiveDelivery().getRestaurant() + ";" +
                                     courier.getActiveDelivery().getCourier() + ";" +
                                     courier.getActiveDelivery().getStatus());
                        for (Item item : courier.getActiveDelivery().getItems())
                        {
                            writer.write(";" + item.getName() + ";" + item.getPrice() + ";" +
                                         item.getCategory());
                        }
                        writer.write("\n");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            writer.println("restaurants");
            for (Restaurant restaurant : restaurants)
            {
                writer.write(restaurant.getUsername() + ";" + restaurant.getPassword() + ";" +
                             restaurant.getEmail() + "\n");
                if (restaurant.getMenu() == null)
                {
                    writer.println(0);
                }
                else
                {
                    writer.println(restaurant.getMenu().size());
                    for (Item item : restaurant.getMenu())
                    {
                        writer.write(item.getName() + ";" + item.getPrice() + ";" +
                                     item.getCategory() + "\n");
                    }
                }
                if (restaurant.getActiveOrders() == null)
                {
                    writer.println(0);
                }
                else
                {
                    writer.println(restaurant.getActiveOrders().size());
                    for (Order order : restaurant.getActiveOrders())
                    {
                        try
                        {
                            if (order.getCustomer() == null)
                            {
                                writer.write("null;");
                            }
                            else
                            {
                                writer.write(order.getCustomer().getEmail() + ";");
                            }
                            if (order.getRestaurant() == null)
                            {
                                writer.write("null;");
                            }
                            else
                            {
                                writer.write(order.getRestaurant().getUsername() + ";");
                            }
                            if (order.getCourier() == null)
                            {
                                writer.write("null;");
                            }
                            else
                            {
                                writer.write(order.getCourier().getUsername() + ";");
                            }
                            writer.write(order.getStatus());
                            for (Item item : order.getItems())
                            {
                                writer.write(";" + item.getName() + ";" + item.getPrice() + ";" +
                                             item.getCategory());
                            }
                            writer.write("\n");
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
            writer.println("customers");
            for (Customer customer : customers)
            {
                writer.write(customer.getUsername() + ";" + customer.getPassword() + ";" +
                             customer.getEmail() + ";" + customer.getAddress() + "\n");
                if (customer.getPastOrders() == null)
                {
                    writer.println(0);
                }
                else
                {
                    writer.println(customer.getPastOrders().size());
                }
                for (Order order : customer.getPastOrders())
                {
                    try
                    {
                        writer.write(order.getCustomer().getEmail() + ";" + order.getRestaurant() +
                                     ";" + order.getCourier() + ";" + order.getStatus());
                        for (Item item : order.getItems())
                        {
                            writer.write(";" + item.getName() + ";" + item.getPrice() + ";" +
                                         item.getCategory());
                        }
                        writer.write("\n");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                if (customer.getFavouriteRestaurants() == null)
                {
                    writer.println(0);
                }
                else
                {
                    writer.println(customer.getFavouriteRestaurants().size());
                }
                for (int i = 0; i < customer.getFavouriteRestaurants().size() - 1; i++)
                {
                    writer.write(customer.getFavouriteRestaurants().get(i).getUsername() + ";");
                }
                if (customer.getFavouriteRestaurants().size() != 0)
                {
                    writer.write(customer.getFavouriteRestaurants()
                                     .get(customer.getFavouriteRestaurants().size() - 1)
                                     .getUsername());
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found! (" + path + ")");
        }
        finally
        {
            writer.close();
        }
    }
}
