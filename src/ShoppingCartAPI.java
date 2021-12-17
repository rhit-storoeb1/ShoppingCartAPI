import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCartAPI {

    //given a cart id, send a request to see contents of the cart, which includes:
    //  -total number of items
    //  -list of items (name,description,price,picture,if in stock)
    //  -total price of items
    //if the cart does not exist, an error should be printed (or error code returned)
    public Cart sendViewCartRequest(int cartId){
        try {
            FileWorks fw = new FileWorks();
            Cart cart = fw.getCart(cartId);
            if(cart==null){
                //error code would be returned here
                return null;
            }
            return cart;
        } catch (FileNotFoundException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    //check it item exists in item file.
    public boolean sendAddItemRequest(int cartId, int itemId){
        try {
            FileWorks fw = new FileWorks();
            Cart cart = fw.getCart(cartId);
            if(cart==null){
                return false;
            }
            Item item = fw.getItem(itemId);
            if(item==null){
                return false;
            }
            if(cart.addItem(item)){
                fw.updateCart(cart);
                return true;
            }
        } catch (FileNotFoundException | JAXBException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sendApplyDiscountCodeRequest(int cartId, String discountCode){
        FileWorks fw = null;
        try {
            fw = new FileWorks();
            Cart cart = fw.getCart(cartId);
            if(cart==null){
                return false;
            }
            DiscountCode code = fw.getCode(discountCode);
            if(code==null){
                return false;
            }
            if(cart.addDiscount(code)){
                fw.updateCart(cart);
                return true;
            }
        } catch (FileNotFoundException | JAXBException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sendModifyCartRequest(int cartId, int itemId, int newQuantity){
        try {
            FileWorks fw = new FileWorks();
            Cart cart = fw.getCart(cartId);
            if (cart == null) {
                return false;
            }
            Item item = fw.getItem(itemId);
            if(item==null){
                return false;
            }
            if(cart.modifyItemQuantity(item, newQuantity)){
                fw.updateCart(cart);
                return true;
            }else{
                return false;
            }
        } catch (FileNotFoundException | JAXBException e) {
            e.printStackTrace();
        }
        return false;
    }

}
