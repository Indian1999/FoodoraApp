class User
{
    private String username;
    private String password;
    private String email;
    User(String username, String pw, String email)
    {
        this.username = username;
        this.password = pw;
        this.email = email;
    }

    public String getUsername() { return username; }
    public void setUsername(String newName) { username = newName; }
    public String getPassword() { return password; }
    public void setPassword(String newPw) { password = newPw; }
    public String getEmail() { return email; }
    public void setEmail(String newMail) { email = newMail; }

}