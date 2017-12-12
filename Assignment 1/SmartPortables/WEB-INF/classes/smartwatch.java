import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class smartwatch extends HttpServlet 
{

	productsaxparser productsaxparserobj;

	public void init() throws ServletException
	{
  		try 
        {
        	 SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    
	        SAXParser saxParser = saxParserFactory.newSAXParser();

	        productsaxparserobj = new productsaxparser();

	        saxParser.parse(new File("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\WEB-INF\\product.xml"), productsaxparserobj);
	  
        } 

        catch (ParserConfigurationException | SAXException | IOException e) 
        {
            e.printStackTrace();
        }

	}
	public String readFile(String filename) 
				{
		   			File f = new File(filename);
		   			try 
		   			{
			           byte[] bytes = Files.readAllBytes(f.toPath());
				       return new String(bytes, "UTF-8");
	        		} 
	        		catch (Exception e) 
	        		{
		       		//	e.printStackTrace();
	        		}
	        		return "";
	        
	    		}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{

           //   HttpSession session = request.getSession();

				

                PrintWriter out = response.getWriter();

                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\header.html"));
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\side.html"));
                out.println(" <div id='content'> ");
                out.println("<h2>Watches</h2>");
                List<product> productlist = productsaxparserobj.getproductlist();
                out.println("<table>");
                for(product productobj : productlist) 
	        	{
	        		String type = productobj.getType();
	        		

	        		String img = productobj.getImage();
	        		String name = productobj.getName();
	        		Double price = productobj.getPrice();


	        		if (type.equals("watch")) 
	        		{
		        		out.println("<tr>");
		        		out.println("<td>");
		          		out.print("<img src='"+img+"' alt='image' height='85' width='85' />");
		          		out.println("</td>");
		          		out.println("<td>");
		          		out.println(name+"<br>");
		          		out.println(price);
		          		out.println("</td>");
		          		out.println("<td>");
		          		out.println("<form  method = 'get' action = 'addtocart'>");
						out.println("<input  type = 'submit' name = 'Add to Cart' value = 'Buy Now'>");
		                out.println("<input type = 'hidden' name='name' value="+name+">");
		                out.println("<input type = 'hidden' name='price' value="+price+">");
		                out.println("</form>");
		          		out.println("</td>");
		          		out.println("</tr>");
	          	    }
	      		}
                out.println("</table>");
                out.println("</div>");
                
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\footer.html"));

    }

}


