import java.text.NumberFormat;

class FoodoraApp
{
    private UserContainer users = new UserContainer();
    private User user = null;
    boolean loggedIn = false;

    public static void main(String[] args)
    {
        System.out.println("Welcome to our food ordering application!");
        FoodoraApp app = new FoodoraApp();
        Visitor visitor = new Visitor();
        // app.createTestUsers();
        while (true)
        {
            if (!app.loggedIn)
            {
                visitor.listCommands();
                String input = System.console().readLine();
                app.executeCommand(visitor, input);
            }
            else
            {
                app.user.listCommands();
                String input = System.console().readLine();
                if (app.user instanceof Customer)
                {
                    app.executeCommand((Customer)app.user, input);
                }
                else if (app.user instanceof Restaurant)
                {
                    app.executeCommand((Restaurant)app.user, input);
                }
                else
                {
                    System.exit(0);
                }
            }
        }
    }
    public void executeCommand(Restaurant r, String command)
    {
        switch (command)
        {
            case "0":
                exitApplication();
                break;
            case "1": // new name
                System.out.println("Enter your new name:");
                users.getRestaurants().remove(r);
                r.setUsername(System.console().readLine());
                users.getRestaurants().add(r);
                break;
            case "2": // new amil
                System.out.println("Enter your new email:");
                users.getRestaurants().remove(r);
                r.setEmail(System.console().readLine());
                users.getRestaurants().add(r);
                break;
            case "3": // new pw
                System.out.println("Enter your new password:");
                users.getRestaurants().remove(r);
                r.setPassword(System.console().readLine());
                users.getRestaurants().add(r);
                break;
            case "4": // list menu
                System.out.println("The menu of your restaurant:");
                r.listRestaurantMenu();
                break;
            case "5": // Modify menu
                System.out.println("Choose an action!");
                System.out.println("1. Add a new item to the menu");
                System.out.println("2. Remove an item from the menu");
                System.out.println("3. Edit a menu item");
                System.out.println("4. Cancel");
                String input = System.console().readLine();
                switch (input)
                {
                    case "1":
                        try
                        {

                            System.out.println("Enter the name of the item:");
                            String itemName = System.console().readLine();
                            System.out.println("Enter the price of the item:");
                            double itemPrice = Double.parseDouble(System.console().readLine());
                            System.out.println("Enter the category of the item:");
                            String itemCategory = System.console().readLine();
                            r.addItemToMenu(new Item(itemName, itemPrice, itemCategory));
                            System.out.println("New menu item created!");
                        }
                        catch (NumberFormatException ex)
                        {
                            System.out.println("Price has to be a number!");
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        break;
                    case "2":
                        r.listRestaurantMenu();
                        System.out.println("Which item would you like to remove from the menu?");
                        try
                        {
                            int index = Integer.parseInt(System.console().readLine());
                            r.removeItemFromMenu(index - 1);
                            System.out.println("Item deleted!");
                        }
                        catch (NumberFormatException ex)
                        {
                            System.out.println("You have to give the number of the item!");
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    case "3":
                        r.listRestaurantMenu();
                        try
                        {
                            System.out.println("Which item would you like to modify?");
                            int index = Integer.parseInt(System.console().readLine());
                            Item item = r.getMenu().get(index - 1);
                            System.out.println(item.toString());
                            System.out.println("Enter the new name of the item:");
                            String itemName = System.console().readLine();
                            System.out.println("Enter the new price of the item:");
                            double itemPrice = Double.parseDouble(System.console().readLine());
                            System.out.println("Enter the new category of the item:");
                            String itemCategory = System.console().readLine();
                            r.addItemToMenu(new Item(itemName, itemPrice, itemCategory));
                            System.out.println("Menu item succesfully modified!");
                        }
                        catch (NumberFormatException ex)
                        {
                            System.out.println("Price has to be a number!");
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Unknown command!");
                        break;
                }
                break;
            case "6": // List active orders
                r.listActiveOrders();
                break;
            case "7": // Set order ready for pickup
                System.out.println("Which order would you like to set as ready for delivery?");
                r.listActiveOrders();
                try {
                    int index = Integer.parseInt(System.console().readLine());
                    r.getActiveOrders().get(index-1).setStatus("READY");
                }
                catch (NumberFormatException ex)
                {
                    System.out.println("Input has to be an integer!");
                } 
                catch (Exception e) {e.printStackTrace();
                }
            case "8":
                loggedIn = false;
                break;
            default:
                System.out.println("Unknown command!");
                break;
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
                                    ((Customer)user).sendOrder(order);
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
                ((Customer)user).listPastOrders();
                break;
            case "9":
                loggedIn = false;
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
                    loggedIn = true;
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
