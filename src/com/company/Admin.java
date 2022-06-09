package com.company;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Admin {

  public void addProduct(Map<Long, Product> products) throws FileNotFoundException {
    File file = new File("src/com/company/input.txt");

    Scanner inputKeyboard = new Scanner(file);
    // Declaring local bindings within the scope of addProduct to create abstraction
    String optionOneProductName, optionTwoProductName, optionThreeProductName;
    int optionOneProductPrice, optionTwoProductPrice, optionThreeProductPrice;
    int n = 1;
    long pId1, pId2, pId3;
    String productCategory = "";

    // iterate over and assign value from the input file for category and product
    // details

    while (inputKeyboard.hasNext()) {

      for (; n <= 3; n++) {

        try {
          productCategory = inputKeyboard.next();
          optionOneProductName = inputKeyboard.next();
          optionTwoProductName = inputKeyboard.next();
          optionThreeProductName = inputKeyboard.next();
          optionOneProductPrice = inputKeyboard.nextInt();
          optionTwoProductPrice = inputKeyboard.nextInt();
          optionThreeProductPrice = inputKeyboard.nextInt();

          // assigning product ID
          if (n == 2) {
            pId1 = 4l;
            pId2 = 5l;
            pId3 = 6l;
          } else if (n == 3) {
            pId1 = 7l;
            pId2 = 8l;
            pId3 = 9l;
          } else {
            pId1 = 1l;
            pId2 = 2l;
            pId3 = 3l;
          }

          // creating new products with different names,category and option. And also
          // adding
          // them to the list of products or product items.
          Product categoryOption1 = new Product(optionOneProductName, productCategory, optionOneProductPrice);
          Product categoryOption2 = new Product(optionTwoProductName, productCategory, optionTwoProductPrice);
          Product categoryOption3 = new Product(optionThreeProductName, productCategory, optionThreeProductPrice);
          products.put((pId1), categoryOption1);
          products.put((pId2), categoryOption2);
          products.put((pId3), categoryOption3);

          // Gives a warning to the user and starts again from that category
          // rather than starting the programming fresh.

        } catch (InputMismatchException e) {
          System.out.println("InputMismatchException: Please provide the right data type");
          inputKeyboard.nextLine();
        } catch (IllegalArgumentException ex) {
          System.out.println(ex.getMessage());
        }
      }

    }

  }
}
