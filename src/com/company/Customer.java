package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Date;

public class Customer {
    static Map<Long, Product> products;
    Map<Long, Invoice> invoices;
    private Invoice invoice;
    private Product product;
    private Scanner inputKeyboard;
    private boolean completeTask = false;
    private double total = 0;
    Admin admin = new Admin();
    Date date = new Date();
    File file = new File("src/com/company/output.txt");
    PrintWriter writer = new PrintWriter(file);


public Customer() throws FileNotFoundException{

    products = new HashMap<Long, Product>();
    invoices = new HashMap<Long, Invoice>();
    admin.addProduct(products);
}


    public void run() {

        inputKeyboard = new Scanner(System.in);
        while (!completeTask) {
            System.out.println("Select an option  1 = Show Items ," +
                    "0 = exit ");
            int selectedOption = inputKeyboard.nextInt();
            setSelectedOption(selectedOption);
        }
    }

    public static void showProducts() {
        System.out.println("\n+=========== Products in Stock ======================+");
        System.out.print("|Pid \t Name  \t\t\tItem Category \t   Price   |\t \n");
        System.out.println("=====================================================");
        if (products.isEmpty()) {
            System.out.println("No Product has been added");
        }
        for (Map.Entry<Long, Product> entry : products.entrySet()) {;
            System.out.printf( "%d\t\t %-1s\t\t %9s\t\t %5d\n",
                     entry.getKey(), entry.getValue().getProductName(),
                    entry.getValue().getProductCategory()
                    ,entry.getValue().getProductPrice());
        }
        System.out.println("+=====================================================+");
    }

    private void selectItemCategory() {
        showProducts();
        System.out.println("Select Category you would want buy an item");
        products.forEach((key, value) -> {

            if (key.equals(1l) || key.equals(4l) || key.equals(7l)) {
                System.out.print(key + ")" + value.getProductCategory() + "\t\t");
            }

        });
        long value = inputKeyboard.nextLong();
        selectItem(value);
    }

    public void setSelectedOption(int selectedOption) {
        switch (selectedOption) {
            case 0:
                exit();
                break;
            case 1:
                selectItemCategory();
                break;
            default:
                System.out.println("Illegal Command");
        }
    }

    // created buyAnother method to ask the user if they want to buy
    // another product and their input is case insensitive.
    public void buyAnother() throws FileNotFoundException {
        System.out.println("Would Like another buy, press Y/N");
        String choice = inputKeyboard.next();
        if (choice.equalsIgnoreCase("y")) {
            selectItemCategory();
        } else {
            checkOut();
        }
    }

    // This method takes the product id of which the user wants to buy and
    // the product details to the invoice together with its biling
    public void addToInvoice(long item, Product product, Long pId) {
        product = products.get(item);
        invoice = new Invoice(product, product.getProductPrice());
        invoices.put(pId, invoice);
    }

    public void selectItem(long select) {
        long pId;

        try {
            if (select == 1) {
                System.out.println("choose item to buy");
                // only show Products that are in the selected category list
                // using the lamda expression to get the key and value.
                products.forEach((key, value) -> {
                    if (key == 1l || key == 2l || key == 3l) {

                        System.out.println("\n " + value.getProductCategory() + "\n" +
                                +key + ")" + value.getProductName() + "\tD" + value.getProductPrice());
                    }
                });

                long item = inputKeyboard.nextLong();
                // if the item variable value that the user entered
                // matches the product id in products HashMap then addToInvoice
                if (products.containsKey(item)) {
                    pId =1;
                    addToInvoice(item, product, pId);

                } else if (products.containsKey(item)) {
                    pId =2;
                    addToInvoice(item, product, pId);
                } else {
                    pId =3;
                    addToInvoice(item, product, pId);
                }
                // call to request for another purchase
                buyAnother();
                // a condition to check for the second product category
            } else if (select == 4) {
                products.forEach((key, value) -> {
                    if (key == 4l || key == 5l || key == 6l) {

                        System.out.println("\n " + value.getProductCategory() + "\n" +
                                +key + ")" + value.getProductName() + "\tD" + value.getProductPrice());
                    }
                });

                long item = inputKeyboard.nextLong();
                if (products.containsKey(item)) {

                    pId = 4;
                    addToInvoice(item, product, pId);
                } else if (products.containsKey(item)) {
                    pId =5;
                    addToInvoice(item, product, pId);
                } else {
                    pId =6;
                    addToInvoice(item, product, pId);
                }
                buyAnother();
                // for the third category
            } else if (select == 7) {

                products.forEach((key, value) -> {
                    if (key == 7l || key == 8l || key == 9l) {

                        System.out.println("\n " + value.getProductCategory() + "\n" +
                                +key + ")" + value.getProductName() + "\tD" + value.getProductPrice());
                    }
                });
                long item = inputKeyboard.nextLong();
                if (products.containsKey(item)) {
                    pId =7;
                    addToInvoice(item, product, pId);

                } else if (products.containsKey(item)) {
                    pId =8;
                    addToInvoice(item, product, pId);
                } else {
                    pId =9;
                    addToInvoice(item, product, pId);
                }
                buyAnother();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void checkOut(){

        // dude you can't check out an empty basket, can you?
        if (invoices.isEmpty()) {
            System.out.println("Sorry  no products has been bought yet \n" +
                    "You need to buy first");

        } else {
            // looping through all products in invoice and add their price sum
            for (Map.Entry<Long, Invoice> entry : invoices.entrySet()) {
                total += entry.getValue().getInvoiceTotal();
            }

            writer.println("----------------------------------------");
            writer.println("|\tName\t\t\tPrice\t| ");
            writer.println("=======================================++");
            invoices.forEach((key, value) -> {
                writer.println("| " + value.getProduct().getProductName() + " \t\t\t "
                        + value.getProduct().getProductPrice() );
            });
            writer.println(
                    "|Total = \t\t\tD" + total + "  |\n"
                            +"Time is purchase: "+date.toString()+"\n"
                            + "=========================================|");
            writer.println("GoodBye, Thank you for shopping with us");
            exit();
            writer.close();
        }
    }

    private void exit() {
        completeTask = true;
        System.out.println("Get your receipt!!! ");

    }
}
