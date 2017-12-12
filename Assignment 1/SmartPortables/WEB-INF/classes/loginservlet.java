import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.catalina.User;

public class loginservlet extends HttpServlet 
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
	        		}
	        		return "";
	        
	    		}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{

      
				String username = request.getParameter("uname");
                String userpassword = request.getParameter("upassword");

                String msg = "";
                boolean success = false;
                HashMap<String, alluser> usrhm=new HashMap<String, alluser>();

				try
				{
					FileInputStream fileInputStream= new FileInputStream(new File("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\userserialize.txt")); 
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
					usrhm= (HashMap)objectInputStream.readObject();
				}
		        catch (Exception ex) 
		        {
		            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
		        }

				if(usrhm.containsKey(username))
				{ 
		           
					alluser user = usrhm.get(username);
					System.out.println(user.getpassword());
					System.out.println(userpassword);
					if ((user.getpassword()).equals(userpassword)) 
					{
						msg = "Logged in Successfully!";
						success = true;
			
						RequestDispatcher rd=request.getRequestDispatcher("index");  
						rd.forward(request, response);
					} 
					else 
					{
						msg = "incorrect username/password!";
					} 
				}
				else
				{
					msg = "New User! Please register before trying to Login!";
				}

				String usr = null;

				if (success) {

                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                

                usr = (String)session.getAttribute("username");
               }
				

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
                out.println(msg);
             
			
		        out.println("</div>");
                
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\footer.html"));

    }
}