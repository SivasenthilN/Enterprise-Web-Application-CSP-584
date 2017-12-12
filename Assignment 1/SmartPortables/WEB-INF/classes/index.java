import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class index extends HttpServlet 
{
	public void init() throws ServletException
	{
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
                //out.println(" <div id='content'> ");
                //out.println("<img src='images\\tablet_laptop.jpg'  alt='smiley face' style='width: 673px; height: 313px; margin-top: 10px;'>");
                //out.println("</div>");
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\body.html"));
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\footer.html"));

    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{

	    String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");

		PrintWriter out = response.getWriter();
		
		HashMap<String, alluser> usrhm=new HashMap<String, alluser>();

		try
		{
			FileInputStream fileInputStream= new FileInputStream(new File("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\userserialize.txt")); 
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			usrhm= (HashMap)objectInputStream.readObject();
		}
                catch (Exception ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
                }

		if(usrhm.containsKey(username))
		{ 
            String error_msg = "Username already exist as " + usertype;
	
		}
		
		else
		{
            alluser User= new alluser(username,password,usertype);
			
			usrhm.put(username, User);
			FileOutputStream fileOutputStream= new FileOutputStream(new File("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\userserialize.txt")); 
			ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(usrhm);
			objectOutputStream.flush();
			objectOutputStream.close();
			fileOutputStream.close();
			out.println("");
		}	
                String hdr = readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\header.html");


            		out.println(hdr);

                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\side.html"));
				out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\body.html"));              
                out.println(readFile("C:\\Users\\nsiva\\Documents\\Sivasenthil\\MCS\\Fall 2017\\Enterprise Web Application\\Tomcat\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\SmartPortables\\footer.html"));
	}
}


