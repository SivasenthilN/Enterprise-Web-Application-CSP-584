import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.catalina.User;

public class logout extends HttpServlet 
{
	public void init() throws ServletException
	{
		
    } // Get a set of the entries
     
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
				String username = request.getParameter("uname");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                String usr = (String)session.getAttribute("username");
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
                out.println(" <div id='content'> ");
	            session.invalidate(); 	
	            out.print("<center><br><h3>Logged out successfully!</h3></center>");  
                out.println("</div>");
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\footer.html"));

    }

    
}


