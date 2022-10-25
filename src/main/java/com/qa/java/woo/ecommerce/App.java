package com.qa.java.woo.ecommerce;


import java.util.List;


import com.qa.woo.ecommerce.controller.ProductController;
import com.qa.woo.ecommerce.model.Product;
import com.qa.woo.ecommerce.util.UI;



public class App 
{
	public static void main( String[] args )
	{
		ProductController controller = new ProductController();
		UI ui = new UI();
		char exit = 'Y';

		while(exit != 'N') {
			int choice = ui.mainMenu();
			switch(choice) {
			case 1:
				int productID = ui.productIdInput();
				Product productByID = controller.getProductByID(productID);
				if(productByID != null) {
					ui.displayProduct(productByID);
					break;
				}
			case 2:
				List<Product> productsList = controller.getAllProducts();
				ui.displayProducts(productsList);
				break;
			case 3:
				Product product = ui.productInput();
				Product addedProduct = controller.addProduct(product);
				ui.displayMessage("Product added successfully!");
				ui.displayProduct(addedProduct);
				break;
				
			case 4:
				Product updateProduct = ui.productInput();
				Product updatedProduct = controller.updateProduct(updateProduct);
				ui.displayMessage("Product updated successfully!");
				ui.displayProduct(updatedProduct);
				break;
				}
			

		}
		
		System.out.println("Do you wish to continue? (Y/N)");
		exit = ui.continueMenu();
		if(exit == 'N') {
			System.exit(0);
		}
	}
}
