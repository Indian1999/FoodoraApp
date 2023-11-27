import java.util.ArrayList;
import java.util.List;

class UserContainer
{
    private List<Admin> admins = new ArrayList<Admin>();
    private List<Restaurant> restaurants = new ArrayList<Restaurant>();
    private List<Courier> couriers = new ArrayList<Courier>();
    private List<Customer> customers = new ArrayList<Customer>();

    // clang-format off
    UserContainer() {}
    // clang-format on

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
        if (i > restaurants.size()) throw new IndexOutOfBoundsException();
        int index = 1;
        for (Item item : restaurants.get(i - 1).getMenu())
        {
            System.out.println(index + ". - " + item.getName() + ", " + item.getPrice() +
                               ", category: " + item.getCategory());
        }
    }
}
