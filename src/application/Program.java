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
                        2- Acrescentar produtos no estoque\s
                        3- Reduzir estoque de produto\s
                        4- Excluir produto do estoque\s
                        5- Listar produtos em estoque\s
                        6- Sair""");
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

                        categoryMap.putIfAbsent(categoryEnum, new TreeSet<>(Comparator.comparing(Product::getId)));
                        categoryMap.get(categoryEnum).add(product);
                        System.out.println("Produto cadastrado com sucesso!");
                    }

                    case 2 -> {
                        System.out.print("Digite o ID do produto que deseja alterar: ");
                        int addId = scanner.nextInt();
                        System.out.print("Digite a quantidade a ser acrescentada: ");
                        int addQuantity = scanner.nextInt();

                        for (Set<Product> products : categoryMap.values()) {
                                for(Product product : products){
                                    if (product.getProductById(product, addId)){
                                        product.addProduct(addQuantity);
                                    }else System.out.println("Produto não encontrado!");
                                }

                            }
                    }

                    case 3 -> {
                        System.out.print("Digite o ID do produto que deseja reduzir: ");
                        int removeId = scanner.nextInt();
                        System.out.print("Digite a quantidade a ser removida: ");
                        int removeQuantity = scanner.nextInt();

                        for (Set<Product> products : categoryMap.values()) {
                                for(Product product : products){
                                    if (product.getProductById(product, removeId)){
                                        product.removeProduct(removeQuantity);
                                    }else System.out.println("Produto não encontrado!");
                                }

                        }

                    }
                    case 4->{
                        System.out.print("Digite o ID do produto que deseja excluir: ");
                        int id = scanner.nextInt();
                       productDao.deleteById(id);

                    }

                    case 5 ->{
                        productDao.printProductsByCategory();
                    }

                    case 6 -> continuar = false;

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
