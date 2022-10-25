package com.qa.woo.ecommerce.controller;

import java.util.List;

import com.qa.woo.ecommerce.exception.InvalidInputException;
import com.qa.woo.ecommerce.exception.ProductAlreadyExistsException;
import com.qa.woo.ecommerce.exception.ProductNotFoundException;
import com.qa.woo.ecommerce.model.Product;
import com.qa.woo.ecommerce.service.ProductServiceImp;

public class ProductController {
	
	ProductServiceImp productService;
	
	public ProductController() {
		this.productService = new ProductServiceImp();
	}
	
	public Product getProductByID(int id) {
		Product product = null;
		try {
			product = this.productService.getProductByID(id);
		}catch(ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(InvalidInputException e) {
			System.out.println(e.getMessage());
		}
		return product;
	}
	
	public List<Product> getAllProducts(){
		return this.productService.getAllProducts();
	}
	
	public Product addProduct(Product product) {
		Product addedProduct = null;
		try {
			addedProduct = this.productService.addProduct(product);
		}catch(ProductAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		return addedProduct;
	}
	
	public Product updateProduct(Product product) {
		Product updatedProduct = null;
		try {
			updatedProduct = this.productService.updateProduct(product);
		}catch(ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return updatedProduct;
	}


}
