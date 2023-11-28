import java.io.*;

class User
{
    private String username;
    private String password;
    private String email;

    User(String username, String pw, String email)
    {
        PrintWriter userWriter = null;
        try
        {

            userWriter = new PrintWriter(new FileOutputStream(new File("Felhasználók.txt"), true));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        this.username = username;
        this.password = pw;
        this.email = email;
        userWriter.println(username);
        userWriter.println(pw);
        userWriter.println(email);
        userWriter.close();
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String newName)
    {
        username = newName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String newPw)
    {
        password = newPw;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String newMail)
    {
        email = newMail;
    }

    public void listRestaurants()
    {
    }

    public void listRestaurantMenu()
    {
    }

    public void listCommands()
    {
    }

    public void executeCommand(String command)
    {
    }
}