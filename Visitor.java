class Visitor extends User
{
    Visitor()
    {
        super("", "", "");
    }

    public String login()
    {
        System.out.println("Login:");
        System.out.println("Please give me your e-mail:");
        String email = System.console().readLine();
        System.out.println("Enter your password:");
        String pw = System.console().readLine();
        return email + ";" + pw;
    }

    public Customer register()
    {
        System.out.println("Enter your name:");
        String name = System.console().readLine();
        System.out.println("Enter your password:");
        String pw = System.console().readLine();
        System.out.println("Enter your e-mail address:");
        String email = System.console().readLine();
        return new Customer(name, pw, email);
    }

    public void listCommands()
    {
        System.out.println("\n----------------------------");
        System.out.println("Choose an action!");
        System.out.println("0. Exit application");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. List restaurants");
        System.out.println("4. List restaurant menu");
        System.out.println("----------------------------");
    }
}
