public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40, 1, 1);
        basket.add("Potato", 33, 2, 5);
        Basket basket1 = new Basket();
        basket1.add("Bread", 30, 2, 3);

        System.out.println("Общая цена всех корзин: " + Basket.allBasketTotalPrice);
        System.out.println("Общее кол-во товаров во всех корзинах: " + Basket.totalAmountProduct);
        System.out.println("Средняя цена товара во всех корзинах: " + Basket.averagePriceProductOfAllBasket());
        System.out.println("Средняя стоимость корзины: " + Basket.averagePriceBasket());

    }
}
