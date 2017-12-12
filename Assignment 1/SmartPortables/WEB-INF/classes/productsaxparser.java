
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class productsaxparser extends DefaultHandler 
{

  
    private List<product> productlist = null;
   
    private product productobj = null;


    //getter method for employee list
   

    public List<product> getproductlist() 
    {
        return productlist;
    }

    boolean bid = false;
    boolean btype = false;

    boolean bimage = false;
    boolean bname = false;
    boolean bcondition = false;
    boolean bprice = false;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
    {

        if (qName.equalsIgnoreCase("product")) 
        {
            //create a new Employee and put it in Map
            String id = attributes.getValue("id");
            String type= attributes.getValue("type");
            //initialize  object and set id attribute
            productobj = new product();
            productobj.setId(Integer.parseInt(id));
            productobj.setType(type);
            //initialize list
            if (productlist == null)
                productlist = new ArrayList<>();
        }

        else if (qName.equalsIgnoreCase("image")) 
        {
            //set boolean values for fields, will be used in setting Employee variables
            bimage = true;
        } 

        else if (qName.equalsIgnoreCase("name")) 
        {
            bname = true;
        }

        else if (qName.equalsIgnoreCase("condition")) 
        {
            bcondition = true;
        }

        else if (qName.equalsIgnoreCase("price")) 
        {
            bprice = true;
        }
        
    }

    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
        if (qName.equalsIgnoreCase("product")) 
        {
            //add Employee object to list
            productlist.add(productobj);
        }
    }

 
    public void characters(char ch[], int start, int length) throws SAXException 
    {

        if (bimage) 
        {
            //age element, set Employee age
            productobj.setImage(new String(ch, start, length));
            bimage = false;
        } 
        
        else if (bname) 
        {
            productobj.setName(new String(ch, start, length));
            bname = false;
        } 
        
        else if (bcondition) 
        {
            productobj.setCondition(new String(ch, start, length));
            bcondition = false;
        } 
        
        else if (bprice) 
        {
            productobj.setPrice(Double.parseDouble(new String(ch, start, length)));
            bprice = false;
        }
    }
}

