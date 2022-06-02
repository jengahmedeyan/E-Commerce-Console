package com.company;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException{

        Customer customer = new Customer();

        System.out.println("Welcome to the Murshid's Shop");
        customer.run();

    }
}
