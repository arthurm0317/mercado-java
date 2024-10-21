package application;

import dao.CategoryDao;
import dao.DaoFactory;
import dao.ProductDao;
import entities.Category;
import entities.Product;
import entities.enums.Categories;

import java.util.*;

public class Program {

    private static final Map<Categories, Set<Product>> categoryMap = new HashMap<>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem vindo ao sistema do mercado jajava!");
        boolean continuar = true;
        CategoryDao categoryDao = DaoFactory.createCatDao();
        ProductDao productDao = DaoFactory.createProdDao();

        while (continuar) {
            System.out.println("O que deseja fazer?");
            try {
                System.out.println("""
                        1- Cadastrar um produto novo\s
                        2- Alterar quantidade do estoque\s
                        3- Alterar o preço do produto\s
                        4- Alterar nome\s
                        5- Excluir produto do estoque\s
                        6- Listar produtos em estoque\s
                        7- Sair""");
                int n = scanner.nextInt();

                switch (n) {
                    case 1 -> {
                        System.out.print("Digite o ID do produto: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        Categories categoryEnum;
                        try {
                            System.out.print("Digite a categoria do produto (FOOD, FRUITS, VEGETABLES, CLEANING, HYGIENE, DRINKS): ");
                            categoryEnum = Categories.valueOf(scanner.nextLine().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Categoria inválida! Escolha uma categoria válida.");
                            break;
                        }

                        System.out.print("Digite o nome do produto: ");
                        String name = scanner.nextLine();

                        System.out.print("Digite o preço do produto: ");
                        double price = scanner.nextDouble();

                        System.out.print("Digite a quantidade do produto: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();

                        Product product = new Product(id, name, price, quantity);

                        Category category = new Category(categoryEnum);
                        categoryDao.insertCategoryIfNotExists(categoryEnum);

                        productDao.insert(product, category.getCategories());
                        System.out.println("Produto cadastrado com sucesso!");
                    }

                    case 2 -> {
                        System.out.print("Digite o ID do produto que deseja alterar: ");
                        int id = scanner.nextInt();
                        System.out.print("Digite a nova quantidade: ");
                        int quantity = scanner.nextInt();
                        productDao.updateQuantity(id, quantity);
                    }
                    case 3->{
                        System.out.print("Digite o ID do produto: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o novo preço do produto: ");
                        Double price = scanner.nextDouble();
                        productDao.updatePrice(id, price);
                    }
                    case 4->{
                        System.out.print("Digite o ID do produto: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o nome do produto: ");
                        String name = scanner.nextLine();
                        productDao.updateName(id, name);
                    }
                    case 5->{
                        System.out.print("Digite o ID do produto que deseja excluir: ");
                        int id = scanner.nextInt();
                        System.out.print("Deseja mesmo excluir o produto: " +productDao.findById(id)+"\n(y/n)\n");
                        String x = scanner.next();
                        if (x.equals("y")){
                            productDao.deleteById(id);
                        }else {
                            System.out.println("Operação cancelada");
                        }
                    }

                    case 6 ->{
                        productDao.printProductsByCategory();
                    }

                    case 7 -> continuar = false;

                    default -> System.out.println("Opção inválida!");
                }

            } catch (InputMismatchException e) {
                System.out.println("\nEntrada inválida!\n");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Erro na digitação!\n");
            }
        }
    }
}
