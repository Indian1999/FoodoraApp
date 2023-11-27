class Visitor extends User
{
    Visitor()
    {
        super("", "", "");
    }

    public void login()
    {
        System.out.println("Bejelentkezés:");
        System.out.println("Adja meg a nevét:");
        String name = System.console().readLine();

    }

    public void register()
    {
        System.out.println("Adja meg a nevét:");
        String name = System.console().readLine();
        System.out.println("Adja meg a jelszavát:");
        String pw = System.console().readLine();
        System.out.println("Adja meg az email címét:");
        String email = System.console().readLine();
        new User(name, pw, email);
    }

    public void listCommands()
    {
        System.out.println("1. Regisztráció");
        System.out.println("2. Bejelentkezés");
    }
}
