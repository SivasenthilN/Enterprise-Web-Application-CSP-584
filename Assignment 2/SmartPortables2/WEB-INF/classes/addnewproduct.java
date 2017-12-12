import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class addnewproduct extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HashMap<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();
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
			//pw.println("<li class=''><a href='#'>Hello  " + role + "</a></li>");
			pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}

		pw.println("</ul>");
		pw.println("</nav>");
		try {
			String pcode = request.getParameter("pcode");
			String productname = request.getParameter("productname");

			Integer price = Integer.parseInt(request.getParameter("price"));

			String shortdesc = request.getParameter("shortdesc");

			String longdesc = request.getParameter("longdesc");

			//Product product = new Product(pcode, productname, shortdesc, longdesc, 1, price);

			ServletContext context = request.getSession().getServletContext();
			//Products products = (Products) context.getAttribute("products");
			//products.getProducts().put(productname, product);

			//context.setAttribute("products", products);

			pw.println("<h3><br /><br />The Product has been added succesfully </h3><br /><br />");
		} catch (Exception e) {
			pw.println("<h3><br /><br />The Product Price should be Integer</h3><br /><br />");
		}

	}
}
