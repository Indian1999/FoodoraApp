class FoodoraApp
{
    public static void main(String[] args)
    {
        System.out.println("Köszöntjük az ételrendelő applikációban!");
        Visitor visitor = new Visitor();
        while (true)
        {
            visitor.listCommands();
            String input = System.console().readLine();
            switch (input) {
                case "1":
                    visitor.register();
                    break;
            
                case "2":
                    visitor.login();
                    break;
                default:
                    break;
            }
        }
    }
}
