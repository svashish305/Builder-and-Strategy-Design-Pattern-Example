import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

interface PayBehaviour {
  void pay();
}

class PayWithCreditCard implements PayBehaviour {
  public void pay() {
    System.out.println("Pay the total amount using Credit Card!");
  }
}

class PayWithPayPal implements PayBehaviour {
  public void pay() {
    System.out.println("Pay the total amount using PayPal!");
  }
}

class PayWithNetBanking implements PayBehaviour {
  public void pay() {
    System.out.println("Pay the total amount using NetBanking!");
  }
}

class Item {
  private static final AtomicInteger count = new AtomicInteger(0);
  private final int itemId;
  private String category;
  private String name;
  private int quantity;
  private double price;
  private double discount;

  public Item(String category, String name, int quantity, double price, double discount) {
    this.itemId=count.incrementAndGet();
    this.category=category;
    this.name=name;
    this.quantity=quantity;
    this.price=price;
    this.discount=discount;
  }

  public int getItemId() {
    return itemId;
  }

  public String getCategory() {
    return category;
  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getPrice() {
    return price;
  }

  public double getDiscount() {
    return discount;
  }

  @Override
  public String toString() {
    return "Item"+this.itemId+": " 
           +"\nCategory: "+this.category
           +"\nName: "+this.name
           +"\nQuantity: "+this.quantity
           +"\nPrice: "+this.price
           +"\nDiscount: "+this.discount
           +"\n================================";
  }
}

class Cart{
  private PayBehaviour payBehaviour;
  private final List<Item> items;

  public Cart(List<Item> items, PayBehaviour payBehaviour) {
    this.items=items;
    this.payBehaviour = payBehaviour;
  }

  public List<Item> getItemsInCart() {
    return items;
  }

  public void performPay() {
    double totalAmount=0.0;
    for(Item item: this.items) {
      totalAmount += item.getQuantity() * (item.getPrice() * (100.00 - item.getDiscount())/100.00);
    }
    System.out.println("Total Amount is " + totalAmount);
    this.payBehaviour.pay();
  }

  public void display() {
    for(Item item: this.items) {
      System.out.println(item);
    }
  }
}

public class ShoppingCart {
  public static void main(String[] args) {
    Item i1 = new Item("Electronics", "iPad", 1, 450.50, 5.00);
    Item i2 = new Item("Stationary", "Pencil", 40, 25.00, 10.00);
    Item i3 = new Item("Grocery", "Freshly Diced Sugarcane", 2, 30.00, 2.50);

    List<Item> items = new ArrayList<Item>();
    items.add(i1);
    items.add(i2);
    items.add(i3);
    
    PayBehaviour[] payBehaviours = {new PayWithCreditCard(), new PayWithPayPal(), new PayWithNetBanking()};
    Cart amazonCart1 = new Cart(items, payBehaviours[0]);
    Cart amazonCart2 = new Cart(items, payBehaviours[1]);
    Cart amazonCart3 = new Cart(items, payBehaviours[2]);
    amazonCart1.display();
    amazonCart1.performPay();
    System.out.println("==========================================");
    amazonCart2.display();
    amazonCart2.performPay();
    System.out.println("==========================================");
    amazonCart3.display();
    amazonCart3.performPay();
    System.out.println("==========================================");
  }
}
