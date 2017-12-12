import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class managestore extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		pw.println("<li  class='start selected'><a href='managestore'>Add Products</a></li>");
		pw.println("<li class=''><a href='modifyproduct'>Update Product</a></li>");

		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role == null) {
			pw.println("<li class='' ><a href='login'>Sign in</a></li>");
		} else {

			pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}

		pw.println("</ul>");
		pw.println("</nav>");
		if(role!=null){
		pw.println("<br /><h3>Hello  "+role+"</h3>");
		}
		pw.println("<form action='addnewproduct'>");
		pw.println("<table>");
		pw.println("<tr>");
		pw.println("<td> Product Code:-</td>");
		pw.println("<td><select name='pcode'><option value='smartphone' >smartphone</option></option><option value='tablet'>Tablet</option><option value='tv'>Tv</option><option value='laptop'>Laptop</option></select></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<tr>");
		pw.println("<td> Product Model Name :-</td>");
		pw.println("<td> <input type='text' name='productname' </td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td> Product Price :- </td>");
		pw.println("<td> $<input type='text' name='price' </td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td> Short Description:-</td>");
		pw.println("<td>  <input type='text' name='shortdesc' </td>");
		pw.println("<tr>");
		pw.println("<tr>");
		pw.println("<td> Product Spec:-</td>");
		pw.println("<td> <input type='text' name='longdesc' </td>");
		pw.println("<tr>");
		pw.println("<tr>");

		pw.println("<td colspan='2' align='center'>");
		pw.println("<input class = 'submit-button' type = 'submit'  value = 'Add New Product'></form>");
		pw.println("</td>");
		pw.println("</tr>");
		pw.println("</tr></table>");

		pw.println("</body>");
		pw.println("</html>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		pw.println("<li  class='start selected'><a href='managestore'>Add Products</a></li>");
		pw.println("<li class=''><a href='modifyproduct'>Modify Product</a></li>");
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role == null) {
			pw.println("<li class='' ><a href='login'>Sign in</a></li>");
		} else {

			pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}

		pw.println("</ul>");
		pw.println("</nav>");
		if(role!=null){
		pw.println("<br /><h3>Hello  "+role+"</h3>");
		}
		pw.println("<form action='addnewproduct'>");
		pw.println("<table>");
		pw.println("<tr>");
		pw.println("<td> Product Code:-</td>");
		pw.println("<td><select name='pcode'><option value='smartphone' >smartphone</option></option><option value='tablet'>Tablet</option><option value='tv'>TV</option><option value='laptop'>Laptop</option></select></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<tr>");
		pw.println("<td> Product Model Name :-</td>");
		pw.println("<td> <input type='text' name='productname' </td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td> Product Price :-</td>");
		pw.println("<td> <input type='number' name='price' </td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td> Short Description:-</td>");
		pw.println("<td>  <input type='text' name='shortdesc' </td>");
		pw.println("<tr>");
		pw.println("<tr>");
		pw.println("<td> Product Spec:-</td>");
		pw.println("<td> <input type='text' name='longdesc' </td>");
		pw.println("<tr>");
		pw.println("<tr>");
		pw.println("<tr>");
		pw.println("<td colspan='2' align='center'>");
		pw.println("<input class = 'submit-button' type = 'submit'  value = 'Add New Product'></form>");
		pw.println("</td>");
		pw.println("</tr>");
		pw.println("</tr></table>");

		pw.println("</body>");
		pw.println("</html>");

	}

}
