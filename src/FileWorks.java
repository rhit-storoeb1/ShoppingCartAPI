import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class FileWorks {

    FileOutputStream file = new FileOutputStream(new File("carts.xml"), true);

    public FileWorks() throws FileNotFoundException { }

    public void storeCarts(ArrayList<Cart> carts) throws FileNotFoundException {
        try{

            JAXBContext context = JAXBContext.newInstance(Carts.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new Carts(carts), new File("carts.xml"));
        }catch(JAXBException ex){
            ex.printStackTrace();
        }
    }

    public ArrayList<Cart> getCarts() throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(Carts.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Carts cartsObj = (Carts)unmarshaller.unmarshal(new File("carts.xml"));
        return cartsObj.getCarts();
    }

    public Cart getCart(int cartId) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(Carts.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Carts cartsObj = (Carts)unmarshaller.unmarshal(new File("carts.xml"));

        ArrayList<Cart> carts = cartsObj.getCarts();
        for(Cart c: carts){
            if(c.id==cartId){
                //the cart does exist
                return c;
            }
        }
        System.out.println("error: Cart " + cartId + "does not exist");
        return null;
    }

    //same as storecarts but for items
    public void storeItems(ArrayList<Item> items) throws FileNotFoundException {
        try{
            JAXBContext context = JAXBContext.newInstance(Items.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new Items(items), new File("items.xml"));
        }catch(JAXBException ex){
            ex.printStackTrace();
        }
    }

    public Item getItem(int itemId) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Items.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Items itemsObj = (Items)unmarshaller.unmarshal(new File("items.xml"));

        ArrayList<Item> items = itemsObj.getItems();
        for(Item i: items){
            if(i.id==itemId){
                //the item does exist
                return i;
            }
        }
        System.out.println("error: Item " + itemId + " does not exist");
        return null;
    }

    public void storeCodes(ArrayList<DiscountCode> codes)throws FileNotFoundException {
        try{
            JAXBContext context = JAXBContext.newInstance(DiscountCodes.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new DiscountCodes(codes), new File("codes.xml"));
        }catch(JAXBException ex){
            ex.printStackTrace();
        }
    }

    public DiscountCode getCode(String code) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(DiscountCodes.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        DiscountCodes codesObj = (DiscountCodes)unmarshaller.unmarshal(new File("codes.xml"));

        ArrayList<DiscountCode> codes = codesObj.getDiscountCodes();
        for(DiscountCode dc: codes){
            if(dc.code.equals(code)){
                //the item does exist
                return dc;
            }
        }
        System.out.println("error: Code " + code + " does not exist");
        return null;
    }

    //removes previous version of cart and adds new version (based on id of cart)
    public void updateCart(Cart cart) throws JAXBException, FileNotFoundException {
        //the cart was changed
        ArrayList<Cart> carts = getCarts();
        for(Cart c: carts){
            if(c.id==cart.id){
                carts.remove(c);
                break;
            }
        }
        carts.add(cart);
        storeCarts(carts);
    }

}
