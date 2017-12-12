import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class deleteproduct extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		pw.println("<html><head>");
		pw.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
		pw.println("<title>Smart Portables</title>");
		pw.println("<link rel='shortcut icon' href='images/icon.jpg'/>");
		pw.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<div id='container'>");
		pw.println("<header>");
		pw.println("<h1><a href='/'>Smart <span> Portables</span></a></h1>");
		pw.println("</header>");
		pw.println("<nav>");
		pw.println("<ul>");
		pw.println("<li  class=''><a href='managestore'>Add Products</a></li>");
		pw.println("<li class='start selected'><a href='modifyproduct'>Modify Product</a></li>");

		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role == null) {
			pw.println("<li class='' ><a href='login'>Sign in</a></li>");
		} else {

			pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}

		pw.println("</ul>");
		pw.println("</nav>");
		String pcode = request.getParameter("pcode");
		String productname = request.getParameter("productname");
		if (false)

		{
			if(role!=null){
		  pw.println("<br /><h3>Hello  "+role+"</h3>");
			}

			pw.println("<br /><h3>Product doesn't exist!<h3>");

		} else {
			if(role!=null){
		  pw.println("<h3>Hello  "+role+"</h3>");
			}
			pw.println("<br /><h3>Product has been deleted !<h3>");
		}

	}

}
