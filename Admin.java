class Admin extends User
{
    public Admin(String username, String pw, String email)
    {
        super(username, pw, email);
    }

    public void printAdmin() {
        System.out.println(getUsername() + ";" + getPassword() + ";" + getEmail());
    }

    public void createRestaurant(String username, String pw, String email, UserContainer users)
    {
        users.add(new Customer(username, pw, email));
    }

    public void createCourier(String username, String pw, String email, UserContainer users)
    {
        users.add(new Courier(username, pw, email));
    }

    public void createAdmin(String username, String pw, String email, UserContainer users)
    {
        users.add(new Admin(username, pw, email));
    }

    public void listCommands()
    {
        System.out.println("0. Exit application");
        System.out.println("1. Add new User account");
        System.out.println("2. Delete existing User account");
        System.out.println("3. Logout");
    }


    public void deleteUser(User u)
    {
        // TO BE IMPLEMENTED
    }

}
