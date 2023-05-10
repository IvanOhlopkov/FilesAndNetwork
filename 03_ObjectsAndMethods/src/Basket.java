public class Basket {

    private static int countBasket = 0;
    private String items = "";
    private int totalPrice = 0;
    private int limit;
    private double totalWeight = 0;
    public static double weight = 0;
    public static int allBasketTotalPrice;
    public static int totalAmountProduct;

    public Basket() {
        increaseCountBasket(1);
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static int increaseAllBasketTotalPrice(int price) {
        return allBasketTotalPrice = allBasketTotalPrice + price;
    }

    public static int increaseAmountProduct(int countProduct) {
        return totalAmountProduct = totalAmountProduct + countProduct;
    }

    public static int averagePriceProductOfAllBasket() {
        return allBasketTotalPrice / totalAmountProduct;
    }

    public static int averagePriceBasket() {
        return allBasketTotalPrice / countBasket;
    }

    public static int getCountBasket() {
        return countBasket;
    }

    public static void increaseCountBasket(int countBasket) {
        Basket.countBasket = Basket.countBasket + countBasket;
    }

    public void add(String name, int price) {
        add(name, price, 1, 0);
    }

    public void add(String name, int price, int count) {
        add(name, price, count, 0);
    }

    public void add(String name, int price, int count, double weight) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
                count + " шт. - цена: " + price + " - вес: " + weight + " кг. ";
        totalPrice = totalPrice + count * price;
        increaseAllBasketTotalPrice(count * price);
        increaseAmountProduct(count);
        totalWeight = totalWeight + count * weight;
    }

    public void clear() {
        items = "";
        totalPrice = 0;
        totalWeight = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
            System.out.println("Масса всех товаров в корзине: " + getTotalWeight());
        }
    }
}
