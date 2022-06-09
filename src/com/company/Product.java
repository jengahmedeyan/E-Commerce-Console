package com.company;

public class Product {
  private int productPrice;
  private String productName;
  private String productCategory;

  public Product(String productName, String productCategory, int productPrice) {
    this.productName = productName;
    // this.productPrice = productPrice;
    this.productCategory = productCategory;
    
    if (productPrice < 0) {
      throw new IllegalArgumentException("Product price cannot be less than 0");
    }

    this.productPrice = productPrice;

  }

  public String getProductName() {
    return productName;
  }

  public int getProductPrice() {
    return productPrice;
  }

  public String getProductCategory() {
    return productCategory;
  }

}
