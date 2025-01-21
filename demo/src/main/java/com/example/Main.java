package com.example;

import com.example.dao.ProductRepository;
import com.example.models.ServiceProduct;
import com.example.services.ShoppingCart;

public class Main {
    public static void main(String[] args) {
        try {
            ProductRepository productRepo = new ProductRepository();
            ShoppingCart cart = new ShoppingCart(1);

            // Salvar produtos no banco usando o ProductRepository
            productRepo.saveProduct(new ServiceProduct("Consulting", 200.0, "Business consulting service", 7));
            productRepo.saveProduct(new ServiceProduct("Design Service", 150.0, "UI/UX design service", 10));

            // Listar todos os produtos disponíveis
            productRepo.listProducts();

            // Adicionando um produto ao carrinho usando seu ID (apenas como exemplo)
            cart.addProduct(1, 2); // 2 unidades do produto com ID 1
            cart.addProduct(2, 1); // 1 unidade do produto com ID 2

            // Listar o conteúdo do carrinho
            cart.listCartContents();

            // Mostrar o total do carrinho
            System.out.printf("Total: %.2f%n", cart.calculateTotal());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}