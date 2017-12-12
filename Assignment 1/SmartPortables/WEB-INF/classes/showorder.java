import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.util.HashMap;

public class showorder extends HttpServlet 
{
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

				HttpSession session = request.getSession();
        
        		cart shoppingCart;

        		shoppingCart = (cart) session.getAttribute("cart");
				if(shoppingCart == null)
				{
				  shoppingCart = new cart();
				  session.setAttribute("cart", shoppingCart);
				}
				HashMap<String, Double> items = shoppingCart.getcartitems();
				double totalCartValue = 0.0d;
				for (String key: items.keySet())
            {
				String value = items.get(key).toString();
totalCartValue += 				Double.parseDouble(value);
			}
                PrintWriter out = response.getWriter();

                
                out.println(" <div id='content'> ");
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\header.html"));
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\side.html"));
				out.println("<h2>Order Placed Successfully!</h2>");
				out.println("<h4>Order Details</h4>");
                String deliverydate=  request.getParameter("deliverydate");
                int  ordernumber=  Integer.parseInt(request.getParameter("ordernumber"));
                String username= request.getParameter("name");
                String address= request.getParameter("address");
         
               
                 out.println("<table class='table' border='1px'>");
                 
                 out.println("<tr>");
                 out.println("<th>");
                 out.println("Order Number");
                 out.println("</th>");
                 out.println("<td>");
                 out.println(ordernumber);
                 out.println("</td>");
                 out.println("</tr>");
                 
                 out.println("<tr>");
                 out.println("<th>");
                  out.println("Name");
                 out.println("</th>");
                 out.println("<td>");
                  out.println(username);
                 out.println("</td>");
                 out.println("</tr>");

                 out.println("<tr>");
                 out.println("<th>");
                  out.println("Total Amount");
                 out.println("</th>");
                 out.println("<td>");
                  out.println(totalCartValue);
                 out.println("</td>");
                 out.println("</tr>");

                 out.println("<tr>");
                 out.println("<th>");
                  out.println("deliverydate");
                 out.println("</th>");
                 out.println("<td>");
                  out.println(deliverydate);
                 out.println("</td>");
                 out.println("</tr>");

                 out.println("<tr>");
                 out.println("<th>");
                 out.println("Address");
                 out.println("</th>");
                 out.println("<td>");
                 out.println(address);
                 out.println("</td>");
                 out.println("</tr>");

                 out.println("<tr>");
                 out.println("<th>");
                 out.println("<form  method = 'get' action = 'cancelorder'>");
	             out.println("<input  type = 'submit' name = 'buy' value = 'cancel order'>");
	             out.println("</form>");

                 out.println("</th>");
                 out.println("</tr>");

                 out.println("</table>");


				out.println("</div>");	
				out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\footer.html"));
                
                
                

    }

}


