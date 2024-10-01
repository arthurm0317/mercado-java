package Application;

import Entities.Product;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Program {
    private  static final Set<Product> products = new TreeSet<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem vindo ao sistema do mercado imaginário!");
        boolean continuar = true;
        while (continuar){
        System.out.println("O que deseja fazer?");
        try {
            System.out.println("""
                    1- Cadastrar um produto novo\s
                    2- Acrescentar produtos no estoque\s
                    3- Remover produto do estoque\s
                    4- Listar produtos em estoque\s
                    5- Sair""");
            int n = scanner.nextInt();


            switch (n) {
                case 1:
                    System.out.print("Digite o ID do produto: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do produto: ");
                    String name = scanner.nextLine();
                    System.out.print("Digite o preço do produto: ");
                    double price = scanner.nextDouble();
                    System.out.print("Digite a quantidade do produto: ");
                    int quantity = scanner.nextInt();
                    products.add(new Product(id, name, price, quantity));
                    break;
                case 2:
                    System.out.print("Digite o ID do produto que deseja acrescentar: ");
                    int addId = scanner.nextInt();
                    System.out.print("Quantidade a ser acrescentada: ");
                    int addQuantity = scanner.nextInt();
                    Product productToUpdate = null;
                    for (Product product : products) {
                        if (product.getId() == addId) {
                            productToUpdate = product;
                        }
                    }
                    if (productToUpdate!=null){
                            productToUpdate.addProduct(addQuantity);
                    }else {
                            System.out.println("Produto não encontrado!");
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID do produto que deseja acrescentar: ");
                    int removeId = scanner.nextInt();
                    System.out.print("Quantidade a ser acrescentada: ");
                    int removeQuantity = scanner.nextInt();
                    Product productToRemove =null;
                    for (Product product : products) {
                        if (product.getId() == removeId) {
                            productToRemove = product;
                        }
                    }
                    if (productToRemove!=null){
                        productToRemove.removeProduct(removeQuantity);
                    }else {
                        System.out.println("Produto não encontrado!");
                    }
                    break;
                case 4:
                    if(products.isEmpty()){
                        System.out.println("Nenhum produto encontrado!");
                    }else {
                        for (Product p : products) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida!");

        }
        }catch (InputMismatchException e){
            System.out.println("\nEntrada inválida!\n");
            scanner.nextLine();
        }
        }
    }
}
