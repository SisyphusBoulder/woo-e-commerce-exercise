package com.qa.woo.ecommerce.service;

import java.util.List;

import com.qa.woo.ecommerce.exception.InvalidInputException;
import com.qa.woo.ecommerce.exception.ProductAlreadyExistsException;
import com.qa.woo.ecommerce.exception.ProductNotFoundException;
import com.qa.woo.ecommerce.model.Product;
import com.qa.woo.ecommerce.repository.Repository;
import com.qa.woo.ecommerce.util.Validation;

public class ProductServiceImp implements ProductService{
	
	Repository repository;
	
	public ProductServiceImp(){
		this.repository = new Repository();
	}

	@Override
	public Product getProductByID(int id) throws ProductNotFoundException, InvalidInputException{
		
		boolean validInput = Validation.validInt(id);
		if(!validInput) {
			throw new InvalidInputException("Invalid Product ID :" + id + "... Should be a positive int > 0");
		}
		Product product = this.repository.getProductByID(id);
		if(product == null) {
			throw new ProductNotFoundException("Product not found with ID :" + id);
		}
		else {
			return product;
		}
		
	}
	
	@Override
	public List<Product> getAllProducts(){
		return this.repository.getAllProducts();
		
	}
	
	@Override
	public Product addProduct(Product product) throws ProductAlreadyExistsException{
		Product getProduct = this.repository.getProductByID(product.getId());
		if(getProduct != null) {
			throw new ProductAlreadyExistsException("Product already exists with ID : " + product.getId());
		}
		else {
			Product addProduct = this.repository.addProduct(getProduct);
			return addProduct;
		}
		
	}
	
	@Override
	public Product updateProduct(Product product) throws ProductNotFoundException{
		Product updatedProduct = null;
		Product productById = this.repository.getProductByID(product.getId());
		if(productById == null) {
			throw new ProductNotFoundException("Product not found with ID : " + product.getId());
		}
		else {
			updatedProduct = this.repository.updateProduct(product);
		}
		
		return updatedProduct;
	}
	
	/*public Product deleteProduct(int id) {
		
		
	}*/

}
