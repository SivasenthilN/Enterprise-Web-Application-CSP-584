import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;

public class login extends HttpServlet 
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

				PrintWriter out = response.getWriter();
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\header.html"));
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\side.html"));
                out.println("<div id='content'>");
				out.println("<center>");
				out.println("<FORM action='loginservlet' method='get'>");
				
				//out.println("<br>");
                out.println("<h3 class='SignIn'>SignIn</h3><hr>");
				//out.println("<fieldset>");

				out.println("<br><br>");
                                out.println("<P>");
                                        out.println("<LABEL for='username'>Username:</LABEL>");
                                        out.println("<INPUT type='text' id='Username' name='uname'><BR>");
										out.println("<br>");
                                        out.println("<LABEL for='password'>Password:</LABEL>");
                                        out.println("<INPUT type='password' name='upassword' id='Password'><BR>");
										out.println("<br>");
                                        out.println("<INPUT type='submit' value='Submit'>");
                                out.println("</P>");
				//				out.println("<fieldset>");
                                out.println("</FORM>");

								out.println("</center>");
                out.println("</div>");
                
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\footer.html"));

    }

}


