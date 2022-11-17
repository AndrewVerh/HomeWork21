import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        String[] listOfProducts = {"Хлеб", "Яблоки", "Молоко 2,5%"};
        int[] prices = {40, 95, 49}; // 0 - 40, 1 - 95, 2 - 49
        int[] countIndex = new int[prices.length]; //массив хранения количества товара по позициям (индексам)
        int[] sumIndex = new int[prices.length]; //массив хранения общей суммы по позициям

        System.out.println("Список возможных продуктов:");
        for (int i = 0; i < listOfProducts.length; i++) {
            System.out.println((i + 1) + ". " + listOfProducts[i] + " - " + prices[i] + " руб./шт");
        }

        //int sumProducts = 0; //итоговая сумма продуктов
        int productNumber = 0;
        int productCount = 0;


        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.println("Выберите товар и количество в формате ХХ ХХ или введите «end»");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                break;
            }

            String[] parts = input.split(" ");
            productNumber = Integer.parseInt(parts[0]) - 1; //это числовой индекс продукта
            productCount = Integer.parseInt(parts[1]); //это множитель количества этого продукта

            Basket basket = new Basket(prices, listOfProducts, countIndex);
            basket.addToCart(productNumber, productCount);
            basket.printCart(productNumber, productCount);
            basket.saveTxt(new File("basket.txt"));

            System.out.println();
            System.out.println("Извлечение из файла");
            Basket.loadFromTxtFile(new File("basket.txt"));

        }
    }

//        System.out.println();
//        System.out.println("Ваша корзина:");
//
//        for (int i = 0; i < listOfProducts.length; i++) {
//            System.out.println(listOfProducts[i] + " " + countIndex[i] + " шт." + " " + prices[i] + " руб./шт " + sumIndex[i] + " р. в сумме");
//            sumProducts += sumIndex[i];
//        }
//
//        System.out.println("Итого: " + sumProducts + " руб.");

}



