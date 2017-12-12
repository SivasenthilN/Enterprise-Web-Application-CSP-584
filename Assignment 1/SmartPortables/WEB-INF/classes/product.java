//package ecom;

import java.util.HashMap;

public class product implements java.io.Serializable
{
    Integer id;
    String retailer;
    String image;
    String name;
    String condition;
    double price;
    double discount;
    String type;
	HashMap<String,product> accessories = new HashMap<String,product>();
    
    public product(Integer id,String type, String image, String name, String condition, double price,double discount, HashMap<String, product> accessories)
    {
        this.id=id;
        this.type=type;
        this.image=image;
        this.name=name;
        this.condition=condition;
        this.price=price; 
        this.discount = discount;
		this.setAccessories(accessories);
    }
    
    public product()
    {
        
    }
    
    public Integer getId() 
    {
        return id;
    }
    public void setId(Integer id) 
    {
        this.id = id;
    }

    public String getType() 
    {
        return type;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    
    public String getImage() 
    {
        return image;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getName() 
    {
        return name;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

     public String getCondition() 
    {
        return condition;
    }
    public void setCondition(String condition) 
    {
        
        this.condition = condition;
    }

     public double getPrice() 
    {
        return price;
    }
    public void setPrice(double price) 
    {
        this.price = price;
    }
    

    public double getDiscount() 
    {
        return discount;
    }

    public void setDiscount(double discount) 
    {
        this.discount = discount;
    }

	
	public void setAccessories(HashMap<String, product> accessories) 
    {
        this.accessories = accessories;
    }
	public HashMap<String, product> getAccessories(Integer id) 
    {
       return accessories;
    }
     public String toString() 
     {
        

        return "product:: ID="+this.id+"         Name="+this.name+"        Image="+ this.image + "            Price=" + this.price + "           Condition = " + this.condition;        
    }
}

