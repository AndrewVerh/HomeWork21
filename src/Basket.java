import java.io.*;
import java.util.List;

import static java.lang.System.out;

public class Basket {

    protected int[] prices;
    protected String[] nameProduct;
    protected int[] prodAmount;

    public Basket(int[] prices, String[] nameProduct, int[] prodAmount) {
        this.prices = prices;
        this.nameProduct = nameProduct;
        this.prodAmount = prodAmount;

    }

    public void addToCart(int productNumber, int amount) {  //номер продукта, штук продукта
        prodAmount[productNumber] = amount;
        //prices[productNumber] = prices[productNumber]* amount;

    }

    public void printCart(int productNumber, int amount) {
        out.println("В корзину добавлено: " + amount + " шт. " + nameProduct[productNumber] + " - " +
                prices[productNumber] + "руб./шт, на сумму - " + prices[productNumber] * amount + " руб.");
    }

    public void saveTxt(File textFile) throws IOException {

        try (PrintWriter out = new PrintWriter(textFile)) {
            for (int e : prices) {
                out.write(e + "@");
            }
            out.write("\n");
            for (String s : nameProduct) {
                out.write(s + "@");
            }
            out.write("\n");
            for (int i : prodAmount) {
                out.write(i + "@");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Basket loadFromTxtFile(File textFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
            //считываем построчно данные, переданные текстФайлом
            String line1 = reader.readLine();
            String line2 = reader.readLine();
            String line3 = reader.readLine();

            //Создание массива строк из 1й строки и перевод его значений в число
            String[] exeptedPriceArr = line1.split("@");
            int[] exeptedPriceArrInt = new int[exeptedPriceArr.length];
            for (int i = 0; i < exeptedPriceArr.length; i++) {
                exeptedPriceArrInt[i] = Integer.parseInt(exeptedPriceArr[i]);
            }
            //Создание массива строк из 2й строки
            String[] exeptedNameProduct = line2.split("@");

            //Создание массива строк из 3й строки и перевод его значений в число
            String[] exeptedCountProduct = line3.split("@");
            int[] exeptedCountProductInt = new int[exeptedCountProduct.length];
            for (int i = 0; i < exeptedCountProduct.length; i++) {
                exeptedCountProductInt[i] = Integer.parseInt(exeptedCountProduct[i]);
            }

            //Вывод на экран всех строк, извлеченных из файла
            for (String s : exeptedPriceArr) {
                System.out.print(s + " руб./шт., ");
            }
            System.out.print("\n");
            for (String s : exeptedNameProduct) {
                System.out.print(s + ", ");
            }
            System.out.print("\n");
            for (String s : exeptedCountProduct) {
                System.out.print(s + " шт., ");
            }
            return new Basket(exeptedPriceArrInt, exeptedNameProduct, exeptedCountProductInt);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
