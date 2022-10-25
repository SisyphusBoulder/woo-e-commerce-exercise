package com.qa.woo.ecommerce.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;
import com.qa.woo.ecommerce.exception.ProductNotFoundException;
import com.qa.woo.ecommerce.model.Product;


//The repository class handles all the operations related to connecting to the database itself

public class Repository {

	Connection connection = null;
	
	public Connection getDBConnection() {
		
		try {
			/* 1. Register the Driver
			 * com.myql.cj.jdbc.Driver
			 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			/* 2.
			 * DriverManager.getConnection(URL, user, password)
			 * jdbc:myqal protocol
			 * localhost:3306 MySQL
			 */
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "root");
				if(connection != null) {
					System.out.println("Connected successfully to the database.");
				}

			} catch (SQLException e) {
				System.out.println("Invalid databade credentials");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL Connector jar file not found in the class path!");
			e.printStackTrace();
		}

		return connection;
	}
	
	public Product getProductByID(int id){
		Product product = null;						//New Product, product
		try (Connection con = getDBConnection()){	//Try connection to the database
			String selectProductQuery = "select * from product_details where id = ?";  //Query sent to SQL, ? allows for int to be inserted
			PreparedStatement pstmt = con.prepareStatement(selectProductQuery);		   //Prepared statement
			pstmt.setInt(1, id);					//Setting the value of id, designated with the ? symbol
			
			ResultSet rs = pstmt.executeQuery();	//Table representing the results of the SQL query
			if (rs.next()) {						//Iterates through the rows in the result set
				
				//Building the product with the matching ID. Uses Lombok features to assign setters and getters.
				product = Product.builder().id(rs.getInt("id")).name(rs.getString("name")).price(rs.getFloat("price")).rating(rs.getFloat("rating")).category(rs.getString("category"))
						.inStock(rs.getBoolean("in_stock")).discountPercentage(rs.getFloat("discount_percentage")).deliveryMode(rs.getString("delivery_mode"))
						.isReturnAccepted(rs.getBoolean("is_return_accepted")).sellerName(rs.getString("seller_name")).build();
			}
		} catch (SQLException e) {
			
		 System.out.println("Some internal error occurred, please try again!");
		}
		
		return product;
		
	}
	
	public List<Product> getAllProducts(){
		List<Product> productsList = new ArrayList<>();
		try (Connection con = getDBConnection()){
			String selectProductQuery = "select * from product_details";
			PreparedStatement pstmt = con.prepareStatement(selectProductQuery);
			ResultSet rs = pstmt.executeQuery(selectProductQuery);
			
			while (rs.next()){
				Product product = Product.builder().id(rs.getInt("id")).name(rs.getString("name")).price(rs.getFloat("price")).rating(rs.getFloat("rating")).category(rs.getString("category"))
						.inStock(rs.getBoolean("in_stock")).discountPercentage(rs.getFloat("discount_percentage")).deliveryMode(rs.getString("delivery_mode"))
						.isReturnAccepted(rs.getBoolean("is_return_accepted")).sellerName(rs.getString("seller_name")).build();
				productsList.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return productsList;
	}
	
	public Product addProduct(Product product) {
		Product addedProduct = null;
		try (Connection con = getDBConnection()){
			String insertProductQuery = "insert into product_details values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
			PreparedStatement pstmt = con.prepareStatement(insertProductQuery);
			pstmt.setInt(1, product.getId());
			pstmt.setString(2, product.getName());
			pstmt.setFloat(3, product.getPrice());
			pstmt.setFloat(4, product.getRating());
			pstmt.setString(5, product.getCategory());
			pstmt.setBoolean(6, product.isInStock());
			pstmt.setFloat(7, product.getDiscountPercentage());
			pstmt.setString(8, product.getDeliveryMode());
			pstmt.setBoolean(9, product.isReturnAccepted());
			pstmt.setString(10, product.getSellerName());
			
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				addedProduct = product;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return addedProduct;
	}
	
	public Product updateProduct(Product product) {
		Product updatedProduct = null;
		try (Connection con = getDBConnection()){
			String insertProductQuery = "update product_details set name = ?, price = ?, rating = ?, category = ?, in_stock = ?, discount_percentage = ?, delivery_mode = ?, is_return_accepted = ?, seller_name = ? where id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(insertProductQuery);
			pstmt.setInt(10, product.getId());
			pstmt.setString(1, product.getName());
			pstmt.setFloat(2, product.getPrice());
			pstmt.setFloat(3, product.getRating());
			pstmt.setString(4, product.getCategory());
			pstmt.setBoolean(5, product.isInStock());
			pstmt.setFloat(6, product.getDiscountPercentage());
			pstmt.setString(7, product.getDeliveryMode());
			pstmt.setBoolean(8, product.isReturnAccepted());
			pstmt.setString(9, product.getSellerName());
			
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				updatedProduct = product;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return updatedProduct;
	}
	
	/*public Product deleteProduct(int id) {
		
		
	}*/

}
