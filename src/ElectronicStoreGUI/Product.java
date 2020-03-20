package ElectronicStoreGUI;

//Base class for all products the store will sell
public class Product{
 private double price;
 private int stockQuantity;
 private int soldQuantity;
 
 public Product(double initPrice, int initQuantity){
   price = initPrice;
   stockQuantity = initQuantity;
 }

 public void reduceStockQuantity() {
     stockQuantity -= 1;
 }

 public void increaseStockQuantity() {
     stockQuantity += 1;
 }

 public int getStockQuantity(){
   return stockQuantity;
 }
 
 public int getSoldQuantity(){
   return soldQuantity;
 }

 public void increaseSoldQuantity() {
     soldQuantity += 1;
 }
 
 public double getPrice(){
   return price;
 }

}