class Visitor extends User
{
    Visitor()
    {
        super("", "", "");
    }

    public String login()
    {
<<<<<<< HEAD
        System.out.println("Login:");
        System.out.println("Please give me your e-mail:");
        String email = System.console().readLine();
        System.out.println("Enter your password:");
        String pw = System.console().readLine();
        return email + ";" + pw;
=======
        System.out.println("Bejelentkezés:");
        System.out.println("Adja meg a nevét:");
        String name = System.console().readLine();

>>>>>>> 5ad4cd67f8aab998e875f5d7d7277c2cbddfbae4
    }

    public Customer register()
    {
<<<<<<< HEAD
        System.out.println("Enter your name:");
        String name = System.console().readLine();
        System.out.println("Enter your password:");
        String pw = System.console().readLine();
        System.out.println("Enter your e-mail address:");
        String email = System.console().readLine();
        return new Customer(name, pw, email);
=======
        System.out.println("Adja meg a nevét:");
        String name = System.console().readLine();
        System.out.println("Adja meg a jelszavát:");
        String pw = System.console().readLine();
        System.out.println("Adja meg az email címét:");
        String email = System.console().readLine();
        new User(name, pw, email);
>>>>>>> 5ad4cd67f8aab998e875f5d7d7277c2cbddfbae4
    }

    public void listCommands()
    {
<<<<<<< HEAD
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. List restaurants");
        System.out.println("4. List restaurant menu");
=======
        System.out.println("1. Regisztráció");
        System.out.println("2. Bejelentkezés");
>>>>>>> 5ad4cd67f8aab998e875f5d7d7277c2cbddfbae4
    }
}
