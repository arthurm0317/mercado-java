package Application;

import Entities.Category;
import Entities.Product;
import Entities.enums.Categorys;

import java.util.*;

public class Program {
    private static final Map<Categorys, Set<Product>> categoryMap = new HashMap<>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem vindo ao sistema do mercado imaginário!");
        boolean continuar = true;

        while (continuar) {
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
                    case 1 -> {
                        System.out.print("Digite o ID do produto: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite a categoria do produto (FOOD, FRUITS, VEGETABLES, CLEANING, HYGIENE, DRINKS): ");
                        String categoryInput = scanner.nextLine().toUpperCase();
                        Categorys categoryEnum = Categorys.valueOf(categoryInput); // Casting da String para enum
                        System.out.print("Digite o nome do produto: ");
                        String name = scanner.nextLine();
                        System.out.print("Digite o preço do produto: ");
                        double price = scanner.nextDouble();
                        System.out.print("Digite a quantidade do produto: ");
                        int quantity = scanner.nextInt();

                        // Criar produto
                        Product newProduct = new Product(id, name, price, quantity);

                        // Adicionar produto à categoria correspondente
                        categoryMap.putIfAbsent(categoryEnum, new TreeSet<>()); // Garante que a categoria exista no Map
                        categoryMap.get(categoryEnum).add(newProduct);
                    }
                    case 2 -> {
                        System.out.print("Digite o ID do produto que deseja acrescentar: ");
                        int addId = scanner.nextInt();
                        System.out.print("Digite a quantidade a ser acrescentada: ");
                        int addQuantity = scanner.nextInt();

                        // Percorre todas as categorias e produtos para encontrar o produto pelo ID
                        boolean found = false;
                        for (Set<Product> products : categoryMap.values()) {
                            for (Product product : products) {
                                if (product.getId() == addId) {
                                    product.addProduct(addQuantity);
                                    found = true;
                                    break;
                                }
                            }
                            if (found) break; // Se o produto foi encontrado, sai do loop
                        }
                        if (!found) {
                            System.out.println("Produto não encontrado!");
                        }
                    }
                    case 3 -> {
                        System.out.print("Digite o ID do produto que deseja remover: ");
                        int removeId = scanner.nextInt();
                        System.out.print("Digite a quantidade a ser removida: ");
                        int removeQuantity = scanner.nextInt();

                        // Percorre todas as categorias e produtos para encontrar o produto pelo ID
                        boolean found = false;
                        for (Set<Product> products : categoryMap.values()) {
                            for (Product product : products) {
                                if (product.getId() == removeId) {
                                    product.removeProduct(removeQuantity);
                                    found = true;
                                    break;
                                }
                            }
                            if (found) break; // Se o produto foi encontrado, sai do loop
                        }
                        if (!found) {
                            System.out.println("Produto não encontrado!");
                        }
                    }
                    case 4 -> {
                        if (categoryMap.isEmpty()) {
                            System.out.println("Nenhum produto encontrado!");
                        } else {
                            for (Map.Entry<Categorys, Set<Product>> entry : categoryMap.entrySet()) {
                                Categorys category = entry.getKey();
                                Set<Product> products = entry.getValue();
                                System.out.println("Categoria: " + category);
                                for (Product product : products) {
                                    System.out.println(product);
                                }
                            }
                        }
                    }
                    case 5 -> continuar = false;
                    default -> System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada inválida!\n");
                scanner.nextLine(); // Limpar o buffer
            } catch (IllegalArgumentException e) {
                System.out.println("Erro na digitação!");
            }
        }
    }
}
