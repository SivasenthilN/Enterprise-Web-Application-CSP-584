import java.sql.*;
import java.util.*;
import java.sql.CallableStatement;

public class MySQLDataStoreUtilities {

	public static Connection conn = null;
	public static int getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables","root","Qwerty1!");
			return 1;
		}
		catch(Exception e){
			return 0;
		}
	}


	public static void insertUser(User user){
		try{
			if(getConnection() == 1){
				String insertUser = "INSERT INTO users(firstName,lastName,userId,password,repassword,userType) "+ "VALUES (?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(insertUser);
				pst.setString(1,user.getFirstName());
				pst.setString(2,user.getLastName());
				pst.setString(3,user.getUserId());
				pst.setString(4,user.getPassword());
				pst.setString(5,user.getrePassword());
				pst.setString(6,user.getUserType());
				pst.executeUpdate();

				pst.close();
				conn.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	

	public static HashMap<String, User> getUsers(){
		try{
			if(getConnection() == 1){
				HashMap<String, User> users=new HashMap<String, User>();
				String selectUserQuery ="select * from users";
				PreparedStatement pst = conn.prepareStatement(selectUserQuery);
				ResultSet rs = pst.executeQuery();

				while(rs.next()){
					User user = new User();
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setUserId(rs.getString("userId"));
					user.setPassword(rs.getString("password"));
					user.setrePassword(rs.getString("repassword"));
					user.setUserType(rs.getString("userType"));

					users.put(user.getUserId(),user);
				}

				pst.close();
				conn.close();
				return users;

			}
			else{
				return null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static int insertOrderDetails(Order order){
		try{
			if(getConnection() == 1){

				String insertOrderDetailsQuery = "INSERT INTO Orders(O_Id, u_Id, O_Ddate, O_Status, item, itemCount, f_name, address, city, state, zipcode, card_number) "+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(insertOrderDetailsQuery);
				pst.setInt(1,order.getOrdernumber());
				pst.setString(2,order.getUserid());
				pst.setString(3, order.getDeliveryDate());
				pst.setString(4,"Pending");
				pst.setString(5,order.getItemId());
				pst.setInt(6,order.getQuantity());
				pst.setString(7,order.getName());
				pst.setString(8,order.getAddress());
				pst.setString(9,order.getCity());
				pst.setString(10,order.getState());
				pst.setString(11,order.getZip());
				pst.setString(12,order.getCardNumber());
				//pst.setInt(13,order.getOrderAmt());
				pst.executeUpdate();
				pst.close();
				//updateInventory();

				//String updateInventoryQuery = "{call inventory_update}";
				CallableStatement cStmt = conn.prepareCall("{call inventory_update}");
				//PreparedStatement pst = conn.prepareStatement(updateInventoryQuery);
				cStmt.executeQuery();
				cStmt.close();
				
				
				conn.close();
				return 	1;
			}
			return 0;
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
		/*public static void updateInventory(){
		try{
			if(getConnection() == 1){

				//String updateInventoryQuery = "{call inventory_update}";
				CallableStatement cStmt = conn.prepareCall("{call inventory_update}");
				//PreparedStatement pst = conn.prepareStatement(updateInventoryQuery);
				cStmt.executeQuery();
				cStmt.close();
				conn.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}*/
	
	public static ArrayList<SalesByProduct> getSalesByProduct(){
		try{
		if(getConnection() == 1){
			ArrayList<SalesByProduct> salesByProductList = new ArrayList<SalesByProduct>();
			String selectOrderDetailsQuery ="select o.item ProductName, i.item_unitprice ProductPrice, sum(o.itemCount) ItemsSold from Orders o INNER JOIN Inventory i on o.item = i.item_name group by o.item, i.item_unitprice";
			PreparedStatement pst = conn.prepareStatement(selectOrderDetailsQuery);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				salesByProductList.add(new SalesByProduct(rs.getString("ProductName"), rs.getInt("ItemsSold"), rs.getDouble("ProductPrice")));
			}

			return salesByProductList;

		}
		else{
			return null;
		}

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static ArrayList<ProductInfo> getProductInventoryInfo(boolean onlyOnSale){
		try{
		if(getConnection() == 1){
			ArrayList<ProductInfo> productInfoList = new ArrayList<ProductInfo>();
			String productInfoQuery ="select item_name ProductName, stock_qty Stock, item_unitprice ProductPrice, allow_sale IsOnSale From Inventory";
			if(onlyOnSale)
				productInfoQuery += " where allow_sale = 1";
			PreparedStatement pst = conn.prepareStatement(productInfoQuery);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				
				productInfoList.add(new ProductInfo(rs.getString("ProductName"), rs.getInt("Stock"), rs.getDouble("ProductPrice"), rs.getBoolean("IsOnSale")));
			}

			return productInfoList;

		}
		else{
			return null;
		}

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static ArrayList<DailySales> getDailySales(){
		try{
		if(getConnection() == 1){
			ArrayList<DailySales> dailySalesList = new ArrayList<DailySales>();
			String selectOrderDetailsQuery ="SELECT Left(o.O_Ddate,10) OrderDate, sum(itemCount * i.item_unitprice) TotalSales FROM ORDERS o INNER JOIN INVENTORY i on o.item = i.item_name GROUP BY OrderDate";
			PreparedStatement pst = conn.prepareStatement(selectOrderDetailsQuery);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				dailySalesList.add(new DailySales(rs.getString("OrderDate"), rs.getDouble("TotalSales")));
			}

			return dailySalesList;

		}
		else{
			return null;
		}

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static HashMap<String,ArrayList<Order>> getOrderDetails(){
		try{
		if(getConnection() == 1){
			HashMap<String,ArrayList<Order>> orderDetailsMap = new HashMap<String,ArrayList<Order>>();
			String selectOrderDetailsQuery ="select * from Orders";
			PreparedStatement pst = conn.prepareStatement(selectOrderDetailsQuery);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				if(!orderDetailsMap.containsKey(rs.getString("u_Id"))){
					ArrayList<Order> orderDetailsInfoArr = new ArrayList<Order>();
					orderDetailsMap.put(rs.getString("u_Id"), orderDetailsInfoArr);
				}
				ArrayList<Order> orderDetailsInfoArrList = orderDetailsMap.get(rs.getString("u_Id"));

				Order orderDetailsInfo = new Order();
				orderDetailsInfo.setName(rs.getString("f_name"));
				orderDetailsInfo.setAddress(rs.getString("address"));
				orderDetailsInfo.setCity(rs.getString("city"));
				orderDetailsInfo.setState(rs.getString("state"));
				orderDetailsInfo.setZip(rs.getString("zipcode"));
				//orderDetailsInfo.setPhone(rs.getString("phone"));
				//orderDetailsInfo.setCard_name(rs.getString("card_name"));
				orderDetailsInfo.setCardNumber(rs.getString("card_number"));
				//orderDetailsInfo.setMonth(rs.getString("month"));
				//orderDetailsInfo.setYear(rs.getString("year"));
				//orderDetailsInfo.setSecurityCode(rs.getString("securityCode"));
				orderDetailsInfo.setOrdernumber(rs.getInt("O_Id"));
				orderDetailsInfo.setDeliveryDate(rs.getString("O_Ddate"));
				orderDetailsInfo.setOrderStatus("confirmed");
				orderDetailsInfo.setItemId(rs.getString("item"));
				orderDetailsInfo.setQuantity(rs.getInt("itemCount"));
				orderDetailsInfo.setUserid(rs.getString("u_Id"));

				orderDetailsInfoArrList.add(orderDetailsInfo);

			}

			return orderDetailsMap;

		}
		else{
			return null;
		}

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static void cancelOrder(String order_id){
		try{
			if(getConnection() == 1){

					String deleteOrderQuery = "delete from orders Where O_Id='"+order_id+"'";
					PreparedStatement pst1 = conn.prepareStatement(deleteOrderQuery);
					pst1.executeUpdate();
					pst1.close();
						conn.close();

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
