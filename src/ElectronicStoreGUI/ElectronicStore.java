package ElectronicStoreGUI;

//Class representing an electronic store
//Has an array of products that represent the items the store can sell
import java.util.ArrayList;
import java.util.Arrays;

public class ElectronicStore{
  public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  public final int POPULAR_PRODUCTS = 3; //Max number of products that can go in the popular array
  private int curProducts; //Num of products added
  private String name;
  private Product[] stock; //Array to hold all products
  private double revenue;
  private ArrayList<Product> cart;
  private int sold; //# of Sales
  private double revPerSale; //Revenue per sale
  private double cartTotal; //Total price of products in the cart
  private Product[] popular; //Array to hold most popular products
  
  public ElectronicStore(String initName){
    revenue = 0.00;
    name = initName;
    stock = new Product[MAX_PRODUCTS];
    curProducts = 0;
    cart = new ArrayList<>();
    sold = 0;
    revPerSale = 0.00;
    cartTotal = 0.00;
    popular = new Product[POPULAR_PRODUCTS];
  }

  //get curProducts
  public int getCurProducts() {
    return curProducts;
  }

  //given index of stock as parameter, adds the specific product to cart, then reduce stock quantity by 1
  public void addToCart(String s) {
    int index = 0;
    for (int i=0; i<curProducts; i++) {
      if (stock[i].toString().equals(s)) {
        index = i;
      }
    }
    cart.add(stock[index]);
    stock[index].reduceStockQuantity();
    cartTotal += stock[index].getPrice();
  }

  //complete sale for the products in the cart. Removes all items in the cart, increment sold by 1, increase revenue, and increases revenue per sale
  public void completeSale() {
    sold++;
    revenue += cartTotal;
    for (Product product : cart) {
      int j = Arrays.asList(stock).indexOf(product);
      stock[j].increaseSoldQuantity();
    }
    cart.clear();
    cartTotal = 0;
  }

  //given index of cart as parameter, removes the specific product from cart, then increment stock quantity by 1
  public void removeFromCart(int i) {
    int j = Arrays.asList(stock).indexOf(cart.get(i));
    cart.remove(cart.get(i));
    stock[j].increaseStockQuantity();
    cartTotal -= stock[j].getPrice();
  }

  public String getCartTotal() {
    return String.format("%.2f", cartTotal);
  }

  public ArrayList<Product> getCart() {
    return cart;
  }

  public Product[] getStock() {
    return stock;
  }

  public int getSold() {
    return sold;
  }

  public Product[] getPopular() {
    calcPopular();
    return popular;
  }

  //make a duplicate of the products array then bubble sort it according to soldQuantity, then take the first two items and put it into popular
  private void calcPopular(){
    Product[] pop = new Product[curProducts];

    System.arraycopy(stock, 0, pop, 0, curProducts);

    Product temp;
    for (int i=0; i<pop.length; i++) {
      for (int j=0; j<pop.length-i-1; j++) {
        if (pop[j].getSoldQuantity() < pop[j+1].getSoldQuantity()) {
          temp = pop[j];
          pop[j] = pop[j+1];
          pop[j+1] = temp;
        }
      }
    }

    System.arraycopy(pop, 0, popular, 0, popular.length);
  }

  public double getRevPerSale(){
    calcRevPerSale();
    return revPerSale;
  }

  //private method, only used by getRevPerSale() to calculate revenue per sale.
  private void calcRevPerSale(){
    revPerSale = revenue/sold;
  }
  
  public String getName(){
    return name;
  }
  
  //Adds a product and returns true if there is space in the array
  //Returns false otherwise
  public void addProduct(Product newProduct){
    if(curProducts < MAX_PRODUCTS){
     stock[curProducts] = newProduct;
     curProducts++;
    }
  }
  
  public double getRevenue(){
    return revenue;
  }
  
  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }
} 