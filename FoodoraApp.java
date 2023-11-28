class FoodoraApp
{
    private UserContainer users = new UserContainer();
    private User user = new Visitor();

    public static void main(String[] args)
    {
        System.out.println("Welcome to our food ordering application!");
        FoodoraApp app = new FoodoraApp();
        app.createTestUsers();
        while (true)
        {
            app.user.listCommands();
            String input = System.console().readLine();
            if (app.user instanceof Visitor)
            {
                app.executeCommand((Visitor)app.user, input);
            }
            else if (app.user instanceof Customer)
            {
                app.executeCommand((Customer)app.user, input);
            }
            else
            {
                System.exit(0);
            }
        }
    }
    public void executeCommand(Customer c, String command)
    {
        switch (command)
        {
            case "0":
                exitApplication();
                break;
            case "1":
                System.out.println("Enter your address:");
                users.getCustomers().remove(c);
                c.setAddress(System.console().readLine());
                users.getCustomers().add(c);
                break;
            case "2":
                System.out.println("Enter your new name:");
                users.getCustomers().remove(c);
                c.setUsername(System.console().readLine());
                users.getCustomers().add(c);
                break;
            case "3":
                System.out.println("Enter your new email:");
                users.getCustomers().remove(c);
                c.setEmail(System.console().readLine());
                users.getCustomers().add(c);
                break;
            case "4":
                System.out.println("Enter your new password:");
                users.getCustomers().remove(c);
                c.setPassword(System.console().readLine());
                users.getCustomers().add(c);
                break;
            case "5":
                users.listRestaurants();
                break;
            case "6":
                try
                {
                    System.out.println(
                        "Which restaurants's menu would you like to list? (Give the number of the restaurant)");
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
            case "7":
                try
                {
                    System.out.println(
                        "Which restaurant menu would you like to order from? (Give the number of the restaurant)");
                    int index = Integer.parseInt(System.console().readLine());
                    Restaurant restaurant = users.getRestaurantByIndex(index - 1);
                    System.out.println("You selected: " + restaurant.getUsername());
                    System.out.println("The menu of " + restaurant.getUsername() + ":");
                    restaurant.listRestaurantMenu();
                    boolean ordering = true;
                    Order order = new Order((Customer)user, restaurant);
                    while (ordering)
                    {
                        System.out.println("Choose an action!");
                        System.out.println("0. Cancel");
                        System.out.println("1. Add item to basket");
                        System.out.println("2. Remove item from basket");
                        System.out.println("3. Send order");
                        String input = System.console().readLine();
                        switch (input)
                        {
                            case "0": // Cancel
                                ordering = false;
                                break;
                            case "1": // Add item
                                System.out.println(
                                    "Which item would you like to add to your basket? (Number of the item)");
                                try
                                {
                                    int itemIndex = Integer.parseInt(System.console().readLine());
                                    System.out.println(
                                        "How many of this item would you like to add to the basket?");
                                    int amount = Integer.parseInt(System.console().readLine());
                                    while (amount <= 0)
                                    {
                                        System.out.println(
                                            "You have to add at least 1 to your basket!");
                                        amount = Integer.parseInt(System.console().readLine());
                                    }
                                    for (int i = 0; i < amount; i++)
                                    {
                                        order.addItem(restaurant.getMenu().get(itemIndex - 1));
                                    }
                                }
                                catch (NumberFormatException ex)
                                {
                                    System.out.println("Input has to be an integer!");
                                }
                                catch (ArrayIndexOutOfBoundsException ex)
                                {
                                    System.out.println("There are no items with this number!");
                                }
                                break;
                            case "2": // Remove item
                                System.out.println(
                                    "Which item would you like to remove from your basket? (Number of the item)");
                                order.listItems();
                                try
                                {
                                    int itemNumber = Integer.parseInt(System.console().readLine());
                                    order.removeItem(itemNumber - 1);
                                }
                                catch (NumberFormatException ex)
                                {
                                    System.out.println("Input has to be an integer!");
                                }
                                catch (IndexOutOfBoundsException ex)
                                {
                                    System.out.println("There are no items with this number!");
                                }
                                break;
                            case "3": // Send order
                                try
                                {
                                    ((Customer) user).sendOrder(order);
                                    ordering = false;
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                break;
                        }
                    }
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
            case "8":
                user = new Visitor();
                break;
            default:
                System.out.println("Unknown command!");
                break;
        }
    }

    public void executeCommand(Visitor v, String command)
    {
        switch (command)
        {
            case "0":
                exitApplication();
                break;
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
                        "Which restaurant would you like to order from? (Give the number of the restaurant)");
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
            default:
                System.out.println("Unknown command!");
                break;
        }
    }

    public void exitApplication()
    {
        users.exportToFile("users.txt");
        System.exit(0);
    }

    public void createTestUsers()
    {
        users.add(new Admin("John Doe", "12345", "john.doe@foodapp.com"));
        users.add(new Admin("Kukor Ica", "almafa", "kukor.ica@foodapp.com"));
        users.add(new Customer("Indian", "almafa1234", "indian@gmail.com"));
        users.add(new Customer("Meggyecske", "Eperke", "meggy@kokusz.golyo"));
        users.add(new Customer("Arany Aron", "sokpenz", "arany.aron@gmail.com"));
        users.add(new Restaurant("pizzaplace", "pineapple", "pizza@pizza.com"));
        users.add(new Restaurant("sushiplace", "salmon", "sushi@nigiri.com"));
        users.add(new Restaurant("meki", "mcdonalds", "meki@mcdonalds.com"));
        users.add(new Restaurant("KFC Veszprem", "chickensForKFC", "veszprem@kfc.com"));
        users.add(new Courier("Istvan", "pityu", "istvan@freemail.hu"));
        users.add(new Courier("Janos", "jani", "janos@citromail.hu"));
        users.add(new Courier("Karoly", "karcsi", "karoly@gmail.com"));
        users.add(new Courier("Kala Pal", "haha", "palko@funny.com"));

        users.getRestaurantByIndex(0).addItemToMenu(new Item("szalamis32", 1870, "32cm normal"));
        users.getRestaurantByIndex(0).addItemToMenu(new Item("sonkas32", 1990, "32cm normal"));
        users.getRestaurantByIndex(0).addItemToMenu(new Item("szalamis45", 3200.12, "45cm normal"));
        users.getRestaurantByIndex(0).addItemToMenu(new Item("sonkas45", 3500, "45cm normal"));
    }
}
