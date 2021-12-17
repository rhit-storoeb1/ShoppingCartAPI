import org.junit.Test;
import sun.awt.image.ShortInterleavedRaster;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShoppingCartAPITest {

    public static void main(String[] args){
        try {
            setupTest();
        } catch (FileNotFoundException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void setupTest() throws FileNotFoundException, JAXBException {
        addItemsToFile();
        addCartsToFile();
        addCodesToFile();
    }

    public static void addItemsToFile() throws FileNotFoundException, JAXBException {
        FileWorks fw = new FileWorks();
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(0, "beans", "just a can of beans", 2.99, "www.google.com/beans.png", 7));
        items.add(new Item(1, "milk", "half a gallon of milk", 3.99, "www.google.com/milk.png", 3));
        items.add(new Item(2, "apples", "three apples", 1.99, "www.google.com/apples.png", 2));
        fw.storeItems(items);
    }

    public static void addCartsToFile() throws FileNotFoundException, JAXBException {
        FileWorks fw = new FileWorks();
        ArrayList<Cart> carts = new ArrayList<>();
        Cart cart = new Cart(0,"IL");
        cart.addItem(fw.getItem(0));
        carts.add(cart);
        fw.storeCarts(carts);
    }

    public static void addCodesToFile() throws FileNotFoundException, JAXBException {
        FileWorks fw = new FileWorks();
        ArrayList<DiscountCode> codes = new ArrayList<>();
        codes.add(new DiscountCode("abcd1234", 0.1, LocalDate.now().plusDays(4).toString()));
        codes.add(new DiscountCode("zyxw987", 0.2, LocalDate.now().plusDays(7).toString()));
        fw.storeCodes(codes);
    }

    //SHOPPINGCARTAPI TESTS

    //test sendViewCartRequest(cartId) endpoint method
    @Test
    public void testSendViewCartRequest() throws FileNotFoundException, JAXBException {
        setupTest();
        ShoppingCartAPI api = new ShoppingCartAPI();
        Cart cart = api.sendViewCartRequest(0);
        assertEquals(cart.id, 0);
    }

    //test sendAddItemRequest(cartId, itemId) endpoint method
    //done by adding the milk item to the cart
    @Test
    public void testSendAddItemRequest() throws FileNotFoundException, JAXBException {
        setupTest();
        ShoppingCartAPI api = new ShoppingCartAPI();
        assertTrue(api.sendAddItemRequest(0,1));
    }

    //test sendApplyDiscountCodeRequest endpoint method
    @Test
    public void testSendApplyDiscountCodeRequest() throws FileNotFoundException, JAXBException {
        setupTest();
        ShoppingCartAPI api = new ShoppingCartAPI();
        assertTrue(api.sendApplyDiscountCodeRequest(0, "abcd1234"));
    }

    //test sendModifyQuantityRequest
    //modify the first item in the cart to have 4
    //try to set it to a number greater than that in stock
    @Test
    public void testSendModifyQuantityRequest() throws FileNotFoundException, JAXBException {
        setupTest();
        ShoppingCartAPI api = new ShoppingCartAPI();
        assertTrue(api.sendModifyCartRequest(0,0,4));
        assertFalse(api.sendModifyCartRequest(0,0,9));
    }

    //CART TESTS

    //testing Cart.calculateTotalPrice() method
    @Test
    public void testCalculateTotalCost(){
        Cart cart = new Cart(9,"AL");
        cart.addItem(new Item(9,"cake", "birthday cake!", 9.99, "www.google.com/birthdaycake.png",9));
        assertEquals(9.99+(9.99*TaxCalculator.calculateTax("AL")), cart.calculateTotalPrice(), 0.01);
    }

    //testing Cart.addItem(itemId) method
    @Test
    public void testAddingItemToCart(){
        Cart cart = new Cart(9,"AL");
        cart.addItem(new Item(9,"cake", "birthday cake!", 9.99, "www.google.com/birthdaycake.png",9));
        assertEquals(1, cart.items.size());
    }

    //testing Cart.modifyItemQuantity() method
    @Test
    public void testModifyItemQuantity(){
        Cart cart = new Cart(9,"AL");
        Item cake = new Item(9,"cake", "birthday cake!", 9.99, "www.google.com/birthdaycake.png",9);
        cart.addItem(cake);
        cart.modifyItemQuantity(cake,3);
        assertEquals(3, cart.calculateCartSize());
    }

    //test Cart.addDiscount() method
    @Test
    public void testAddDiscount(){
        Cart cart = new Cart(9,"AL");
        Item cake = new Item(9,"cake", "birthday cake!", 9.99, "www.google.com/birthdaycake.png",9);
        DiscountCode code = new DiscountCode("1234", 0.1, LocalDate.now().plusDays(1).toString());
        cart.addItem(cake);
        cart.addDiscount(code);
        assertEquals(cake.price*(1-code.percentage)*(1+TaxCalculator.calculateTax("AL")), cart.calculateTotalPrice(), 0.01);
        assertEquals(1, cart.getDiscounts().size());
    }

    //test Cart.getTotalDiscounts() method
    @Test
    public void testGetTotalDiscounts(){
        Cart cart = new Cart(9,"AL");
        Item cake = new Item(9,"cake", "birthday cake!", 9.99, "www.google.com/birthdaycake.png",9);
        DiscountCode code = new DiscountCode("1234", 0.1, LocalDate.now().plusDays(1).toString());
        cart.addItem(cake);
        cart.addDiscount(code);
        assertEquals(0.9, cart.getTotalDiscounts(), 0.01);
    }

    //test Cart.calculateCartSize() method
    @Test
    public void testCalculateCartSize(){
        Cart cart = new Cart(9,"AL");
        Item cake = new Item(9,"cake", "birthday cake!", 9.99, "www.google.com/birthdaycake.png",9);
        cart.addItem(cake);
        assertEquals(1, cart.calculateCartSize());
    }

    //ITEM TESTS

    //test Item.isOutOfStock() method
    @Test
    public void testIsOutOfStock(){
        Item cake = new Item(9,"cake", "birthday cake!", 9.99, "www.google.com/birthdaycake.png",9);
        assertFalse(cake.isOutOfStock());
        cake.setNumInStock(0);
        assertTrue(cake.isOutOfStock());
    }

    //TAXCALCULATOR TESTS

    //test TaxCalculator.calculateTax(state) method
    @Test
    public void testCalculateTax(){
        assertEquals(0, TaxCalculator.calculateTax("DE"), 0.01);
        assertEquals(0.0914, TaxCalculator.calculateTax("AL"), 0);
        //todo: this
    }

}
