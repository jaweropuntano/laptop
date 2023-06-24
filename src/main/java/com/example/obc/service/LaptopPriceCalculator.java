package com.example.obc.service;

import com.example.obc.entiti.Laptop;

public class LaptopPriceCalculator {
    public double calcularPrecio(Laptop laptop) {
        double price = laptop.getPrecio();
        if(laptop.getPrecio() > 300) {
            price += 100;
        }
        price += 5;
        return price;
    }
}
