class FoodoraApp
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to our food ordering app!");
        User user = new Visitor();
        while (true)
        {
            user.listCommands();
            String input = System.console().readLine();
        }
    }
}
