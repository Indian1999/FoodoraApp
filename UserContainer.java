import java.io.*;
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
        /*
        Az orderek beolvasása miatt nem működik, null értékekkel tölti fel az Order osztály
        Restaurant, Courier, Customer paramétereit, mert ezeket név alapján kérdezzük le a
        listájukból, ahol még nem léteznek. Megoldás: Az Ordereket lehetne külön fileban tárolni és
        csak azután beolvasni, hogy a usereket már beolvastuk, ehhez újra kell struktúrálni a filba
        írást és olvasást is egy kicsit amihez már hulla vagyok a kevés alvás miatt így estére,
        úgyhogy folyt köv holnap.*/
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("users.txt"), StandardCharsets.UTF_16));
            readAdmins(reader);
            readCouriers(reader);
            readRestaurants(reader);
            readCustomers(reader);

            reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("orders.txt"), StandardCharsets.UTF_16));
            readOrders(reader);
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
                reader.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
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

    public Admin getAdminByName(String name)
    {
        for (Admin admin : admins)
        {
            if (admin.getUsername().equals(name))
            {
                return admin;
            }
        }
        return null;
    }

    public Courier getCourierByName(String name)
    {
        for (Courier courier : couriers)
        {
            if (courier.getUsername().equals(name))
            {
                return courier;
            }
        }
        return null;
    }

    public Restaurant getRestaurantByName(String name)
    {
        for (Restaurant restaurant : restaurants)
        {
            if (restaurant.getUsername().equals(name))
            {
                return restaurant;
            }
        }
        return null;
    }

    public Customer getCustomerByEmail(String email)
    {
        for (Customer customer : customers)
        {
            if (customer.getEmail().equals(email))
            {
                return customer;
            }
        }
        return null;
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

    private void exportAdmins(PrintWriter writer)
    {
        writer.println("admins");
        for (Admin admin : admins)
        {
            writer.write(admin.getUsername() + ";" + admin.getPassword() + ";" + admin.getEmail() +
                         "\n");
        }
    }
    private void exportCouriers(PrintWriter writer)
    {
        writer.println("couriers");
        for (Courier courier : couriers)
        {
            writer.println(courier.getUsername() + ";" + courier.getPassword() + ";" +
                           courier.getEmail());
        }
    }

    private void exportRestaurants(PrintWriter writer)
    {
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
                    writer.write(item.getName() + ";" + item.getPrice() + ";" + item.getCategory() +
                                 "\n");
                }
            }
        }
    }

    private void exportCustomers(PrintWriter writer)
    {
        writer.println("customers");
        for (Customer customer : customers)
        {
            writer.write(customer.getUsername() + ";" + customer.getPassword() + ";" +
                         customer.getEmail() + ";" + customer.getAddress() + "\n");
        }
    }

    private void exportOrders(String filename)
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(new File(filename)), StandardCharsets.UTF_16));
            for (Customer customer : customers)
            {
                for (Order order : customer.getPastOrders())
                {
                    try
                    {
                        writer.write(order.getCustomer().getEmail() + ";" +
                                     order.getRestaurant().getUsername() + ";" +
                                     order.getCourier() + ";" + order.getStatus());
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
        catch (IOException e)
        {
            System.out.println("Error when writing orders to file!");
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

    public void exportToFile(String path)
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(new File("users.txt")), StandardCharsets.UTF_16));
            exportAdmins(writer);
            exportCouriers(writer);
            exportRestaurants(writer);
            exportCustomers(writer);

            exportOrders("orders.txt");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found! (" + path + ")");
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

    private void readAdmins(BufferedReader usersFile) throws IOException
    {
        String line = usersFile.readLine(); // burning first line 'admin'
        // read admins
        while (line != null)
        {

            line = usersFile.readLine();
            if (line.equals("couriers"))
                break;
            // System.out.println(line);

            String[] elements = line.split(";");
            String name = elements[0];
            String pw = elements[1];
            String email = elements[2];

            admins.add(new Admin(name, pw, email));
        }
    }
    private void readCouriers(BufferedReader usersFile) throws IOException
    {
        String line = "";
        while (line != null)
        {

            line = usersFile.readLine();
            if (line.equals("restaurants"))
                break;

            String[] elements = line.split(";");
            String name = elements[0];
            String pw = elements[1];
            String email = elements[2];

            couriers.add(new Courier(name, pw, email));
        }
    }
    private void readRestaurants(BufferedReader usersFile) throws IOException
    {
        String line = "";
        while (line != null)
        {
            line = usersFile.readLine();
            if (line.equals("customers"))
                break;

            String[] elements = line.split(";");
            String name = elements[0];
            String pw = elements[1];
            String email = elements[2];

            Restaurant res = new Restaurant(name, pw, email);

            // get restaurant menu size and fill menu with items
            int menuSize = Integer.parseInt(usersFile.readLine());
            for (int i = 0; i < menuSize; i++)
            {
                String[] menuItem = usersFile.readLine().split(";");
                Item item = new Item(menuItem[0], Double.parseDouble(menuItem[1]), menuItem[2]);
                res.addItemToMenu(item);
            }
            restaurants.add(res);

            /*
            List<Order> activeOrders = new ArrayList<>();
            int numberOfOrders = Integer.parseInt(usersFile.readLine());
            for (int i = 0; i < numberOfOrders; i++)
            {
                line = usersFile.readLine();
                String[] orderElements = line.split(";");
                String customerEmail = orderElements[0];
                String restaurantName = orderElements[1];
                String courierName = orderElements[2];
                String status = orderElements[3];
                Order order = new Order(getCustomerByEmail(customerEmail),
                                        getRestaurantByName(restaurantName),
                                        getCourierByName(courierName), status);
                for (int j = 4; j < orderElements.length; j += 3)
                {
                    order.addItem(new Item(orderElements[j],
                                           Double.parseDouble(orderElements[j + 1]),
                                           orderElements[j + 2]));
                }
                activeOrders.add(order);
            }
            res.setActiveOrders(activeOrders);*/
        }
    }
    private void readCustomers(BufferedReader usersFile) throws IOException
    {
        String line = "";
        while (line != null)
        {
            line = usersFile.readLine();
            if (line == null)
                break;
            String[] elements = line.split(";");
            if (elements.length == 1)
                break;
            String name = elements[0];
            String pw = elements[1];
            String email = elements[2];
            String address = elements[3];

            Customer customer = new Customer(name, pw, email);
            customer.setAddress(address);
            customers.add(customer);
        }
    }

    private void readOrders(BufferedReader reader)
    {
        try
        {
            String line = "";
            while (line != null)
            {
                line = reader.readLine();
                if (line == null)
                    break;
                String[] orderElements = line.split(";");
                String customerEmail = orderElements[0];
                String restaurantName = orderElements[1];
                String courierName = orderElements[2];
                String status = orderElements[3];
                Order order = new Order(getCustomerByEmail(customerEmail),
                                        getRestaurantByName(restaurantName),
                                        getCourierByName(courierName), status);
                for (int j = 4; j < orderElements.length; j += 3)
                {
                    order.addItem(new Item(orderElements[j],
                                           Double.parseDouble(orderElements[j + 1]),
                                           orderElements[j + 2]));
                }
                order.getCustomer().addToPastOrders(order);
                try
                {
                    order.getRestaurant().addToActiveOrders(order);
                }
                catch (NullPointerException e)
                {
                }
                try
                {

                    order.getCourier().addToPastDeliveries(order);
                }
                catch (NullPointerException e)
                {
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
