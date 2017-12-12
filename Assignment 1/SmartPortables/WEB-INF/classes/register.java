import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;

public class register extends HttpServlet 
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
                String usr = "Guest";
                PrintWriter out = response.getWriter();

                String hdr = readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\header.html");

                if (usr != null) 
                {
                	out.println(hdr.replaceAll("guest", usr));
            	} 
            	else 
            	{
            		out.println(hdr);
            	}
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\side.html"));
                out.println("<div id='content'>");
                out.println("<h3 align='center'>User Registration</h3><hr>");
				out.println("<center>");
				out.println("<br><br>");
            	out.println("<form method='post' action='index' style='color:#990000;'>");
		        out.println("User Name:&nbsp;<input type='text' name='username'><br>");
		        out.println("<br>");
				out.println("Email Id:&nbsp;<input type='email' name='emailid'><br>");
		        out.println("<br>");
				out.println("Password:&nbsp;<input type='password' name='password'><br>");
				out.println("<br>");
				out.println("<p class='signup'>Type of user:</p>");
				out.println("<br>");
		        out.println("<input type='radio' name='usertype' value='customer' checked='checked'>Customer &nbsp; &nbsp ");
		        out.println("<input type='radio' name='usertype' value='salseman'>Salseman &nbsp; &nbsp");
		        out.println("<input type='radio' name='usertype' value='manager'>Product Manager<br>");
				out.println("<br>");		          
		        out.println("<input type='Submit' value='Submit'><br>");

      			out.println("</form>");

      			out.println("</center>");

                out.println("</div>");
					
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\footer.html"));

    }

}


