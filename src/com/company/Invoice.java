package com.company;

public class Invoice {
  private Product product;
  private double invoiceTotal;

  public Invoice(Product product, double invoiceTotal) {
    this.product = product;
    this.invoiceTotal = invoiceTotal;
  }

  public Product getProduct() {
    return product;
  }


  public double getInvoiceTotal() {
    return invoiceTotal;
  }

}
