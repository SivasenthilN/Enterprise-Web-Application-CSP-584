import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class checkout extends HttpServlet 
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
	        		}
	        		return "";
	        
	    		}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{

				response.setContentType("text/html;charset=UTF-8");
        
        		HttpSession session = request.getSession();
        
        		cart shoppingCart;

        		shoppingCart = (cart) session.getAttribute("cart");
        
		
        
				

                PrintWriter out = response.getWriter();

                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\header.html"));
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\side.html"));
                out.println(" <div id='content'> ");
              
            	out.println("<hr>");
            
            	out.println("<hr>");
            	out.println("<h2>Cart</h2>");

            	HashMap<String, Double> items = shoppingCart.getcartitems();

             	out.println("<table class='table' border='1px'>");
           
	            out.println("<tr>");
	            out.println("<th>");
	            out.println("Product");
	            out.println("</th>");
	            out.println("<th>");
	            out.println("price");
	            out.println("</th>");  
	            out.println("<th>");
	            out.println("Action");
	            out.println("</th>");       
	            out.println("</tr>");
	           
	           for (String key: items.keySet())
	            {
	             
	                String itemkey =key;

	                String value = items.get(key).toString();  
	                out.println("<tr>");
	                out.println("<td>");
	                out.println(itemkey);
	                out.println("</td>");
	                out.println("<td>");
	                out.println(value);
	                out.println("</td>");

	    			Double totalamt = 0.0;
	    			totalamt=totalamt + Double.parseDouble(value);


	                out.println("<td>"); 
	                out.println("<form  method = 'get' action = 'deletefromcart'>");
	                out.println("<input  type = 'hidden' name = 'name' value = '"+key+"'>");
	                out.println("<input  type = 'submit'  value = 'delete'>");
	                out.println("</form>");
	                out.println("</td>");
	                out.println("</tr>");

	            } 
            
          
         
            		out.println("</table>");
            		out.println("<br><hr><br>");

            		out.println("<h3 align='center'>Customer Information</h3>");
            		out.println("<form id='register' action='showorder' method='get' >");
            		out.println("<table>");

					
					
					

					out.println("<tr>");
					out.println("<th>");
					out.println("<label for='name' >   Your Full Name*   : </label>");
					out.println("</th>");
					out.println("<td>");
					out.println("<input type='text' name='name' id='name' maxlength='50' /><br>");
					out.println("</td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<th>");
					out.println("<label for='address'> Address*          :</label>");
					out.println("</th>");
					out.println("<td>");
					out.println("<input type='text' name='address' id='address' maxlength='50' /><br>");
					out.println("</td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<th>");
					out.println("<label for='Eid'>   Email Id*         :</label>");
					out.println("</th>");
					out.println("<td>");
					out.println("<input type='email' name='emailid' id='username' maxlength='50' /><br>");

					out.println("</td>");
					out.println("</tr>");


					out.println("<tr>");
					out.println("<th>");
				
					out.println("<label for='number'>Phone Number*:</label>");
					out.println("</th>");
					out.println("<td>");
					out.println("<input type='number' name='number' maxlength='50' /><br>");
					out.println("</td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<th>");
					out.println("<label for='ccnumber'>Credit card Number*:</label>");
					out.println("</th>");
					out.println("<td>");
					out.println("<input type='text' name='cardnumber' id='cardnumber' maxlength='50' /><br>");
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr >");
					out.println("<td colspan='2' align='center'>");



                                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                                        Calendar c = Calendar.getInstance();
                                        c.setTime(new Date()); 
                                        c.add(Calendar.DATE, 10); 
                                        String deliverydate = sdf.format(c.getTime());

                                        Random rand = new Random();

                                        int  ordernumber = rand.nextInt(50) + 1;

                    out.println("<input  type = 'hidden' name = 'deliverydate' value = '"+deliverydate+"'>");
                    out.println("<input  type = 'hidden' name = 'ordernumber' value = '"+ordernumber+"'>");
					out.println("<input type='submit' name='confirmorder' value='Confirm Order'>");
					out.println("</td>");
					out.println("</tr>");			
					out.println("</table>");
					out.println("</form>");					
					out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\footer.html"));
			
    

	}				

	}


