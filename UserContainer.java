import java.util.List;

class UserContainer
{
    private List<Admin> admins;
    private List<Restaurant> restaurants;
    private List<Courier> couriers;
    private List<Customer> customers;

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
}
