import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="items")
@XmlAccessorType(XmlAccessType.FIELD)
public class Items {
    @XmlElement(name="item")
    private ArrayList<Item> items = null;

    public Items(ArrayList<Item> items) { this.items=items; }

    //xml gets mad without this
    public Items() {}

    public ArrayList<Item> getItems(){
        return this.items;
    }

    public void setCarts(ArrayList<Cart> carts){
        this.items=items;
    }
}
