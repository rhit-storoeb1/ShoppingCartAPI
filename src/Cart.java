import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;

@XmlRootElement
public class Cart {

    int id;
    String state; //state the end user lives in
    int size;

    double totalPrice;

    //stores an item and the quantity of it in the cart
    HashMap<Item, Integer> items;
    ArrayList<DiscountCode> discounts;

    //xml gets mad without this
    public Cart(){ }

    //on creation, carts should be empty and no discount codes are applied
    public Cart(int id, String state){
        this.id=id;
        this.state=state;
        this.totalPrice = 0;
        this.size=0;
        this.items=new HashMap<>();
        this.discounts = new ArrayList<>();
    }

    //might want to do this every time an item is added
    public double calculateTotalPrice(){
        double price = 0;
        for(Item i: items.keySet()){
            price += (i.price * items.get(i));
        }
        price *= getTotalDiscounts();
        price += price*TaxCalculator.calculateTax(this.state);

        //round to nearest cent
        price=Math.round(price*100.0)/100.0;
        return price;
    }

    //adds new item to the cart.
    public boolean addItem(Item newItem){
        if(newItem.isOutOfStock()){
            System.out.println(newItem.name + " is out of stock.");
            return false;
        }
        for(Item i: items.keySet()) {
            if (i.id == newItem.id) {
                //the item exists in the cart already
                return false;
            }
        }
        this.items.put(newItem, 1);
        this.size = calculateCartSize();
        this.totalPrice = calculateTotalPrice();
        return true;
    }

    //small helper method
    public int calculateCartSize(){
        int size = 0;
        for(Item i: this.items.keySet()){
            size+=this.items.get(i);
        }
        return size;
    }

    //sets the quantity of the item to the new quantity passed in
    public boolean modifyItemQuantity(Item item, int quantity){
        boolean containsItem = false;
        for(Item i: this.items.keySet()){
            if(i.id==item.id){
                containsItem=true;
            }
        }
        if(!containsItem){
            System.out.println("Item is not in the cart");
            return false;
        }else{
            if(item.numInStock-quantity<0){
                return false;
            }else if(quantity==0){
                items.remove(item);
                return true;
            }else{
                for(Item i: this.items.keySet()){
                    if(i.id==item.id){
                        this.items.remove(i);
                    }
                }
                this.items.put(item, quantity);
                this.size = calculateCartSize();
                this.totalPrice = calculateTotalPrice();
                return true;
            }
        }
    }

    //add a new discount to the discounts being used in the cart
    //and recalculate the total price of the cart
    public boolean addDiscount(DiscountCode dc){
        if(dc.isExpired() || this.discounts.contains(dc)){
            return false;
        }
        this.discounts.add(dc);
        this.totalPrice = calculateTotalPrice();
        return true;
    }

    // returns the percent of the original price
    // the end user has to pay
    // i.e. if there are two 20 percent off discounts,
    // the user has to pay 0.8*0.8 = 0.64 of the original price
    public double getTotalDiscounts(){
        if(this.discounts.size()==0){
            //no discounts, pay 100 percent of original price
            return 1;
        }
        double totalDiscounts = 1-discounts.get(0).percentage;
        for(int i = 1; i < this.discounts.size(); i++){
            if(!discounts.get(i).isExpired()){
                totalDiscounts*=(1-discounts.get(i).percentage);
            }
        }
        return totalDiscounts;
    }



    //xml stuff
    @XmlAttribute
    public void setId(int id){
        this.id=id;
    }

    public int getId(){
        return this.id;
    }

    @XmlAttribute
    public void setState(String state){
        this.state=state;
    }

    public String getState(){
        return this.state;
    }

    @XmlAttribute
    public void setTotalPrice(double totalPrice){
        this.totalPrice=totalPrice;
    }

    public double getTotalPrice(){
        return this.totalPrice;
    }

    @XmlElementWrapper(name="items")
    @XmlElement(name="item")
    public void setItems(HashMap<Item,Integer> items){
        this.items=items;
    }

    public HashMap<Item, Integer> getItems(){
        return this.items;
    }

    @XmlElementWrapper(name="discountCodes")
    @XmlElement(name="code")
    public void setDiscounts(ArrayList<DiscountCode> discounts){
        this.discounts=discounts;
    }

    public ArrayList<DiscountCode> getDiscounts(){
        return this.discounts;
    }

    public int getSize(){return this.size;}
    @XmlAttribute
    public void setSize(int size){this.size=size;}

}
