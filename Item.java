class Item
{
    private String name;
    private double price;
    private String category;

    public Item(String name, double price, String category)
    {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String toString()
    {
        return name + " " + price + " " + category;
    }
}
