class FoodoraApp
{
    private UserContainer users = new UserContainer();
    private User user = new Visitor();

    public static void main(String[] args)
    {
        System.out.println("Welcome to our food ordering application!");
        FoodoraApp app = new FoodoraApp();
        while (true)
        {
            app.user.listCommands();
            String input = System.console().readLine();
            if (app.user instanceof Visitor)
            {
                app.executeCommand((Visitor)app.user, input);
            }
            else
            {
                System.exit(0);
            }
        }
    }

    public void executeCommand(Visitor v, String command)
    {
        switch (command)
        {
        case "1":
            users.add(v.register());
            break;
        case "2":
            String[] loginInfo = v.login().split(";");
            try
            {
                user = users.loginUser(loginInfo[0], loginInfo[1]);
                System.out.println("Successful login!");
                System.out.println("You are now logged in as " + user.getUsername() + " (" +
                                   user.getClass().toString().split(" ")[1] + ")");
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            break;
        case "3":
            users.listRestaurants();
            break;
        case "4":
            try
            {
                System.out.println(
                    "Which restaurants's menu would you like to list. (Give the number of the restaurant)");
                int index = Integer.parseInt(System.console().readLine());
                users.listRestaurantMenu(index);
            }
            catch (NumberFormatException e)
            {
                System.out.println("You have to input an integer!");
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("There is no restaurant with this number!");
            }
            break;
        case "5":
            System.exit(0);
            break;
        default:
            System.out.println("Unknown command!");
            break;
        }
    }
}
