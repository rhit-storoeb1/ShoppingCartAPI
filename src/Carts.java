import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="carts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Carts {
    @XmlElement(name="cart")
    private ArrayList<Cart> carts = null;

    public Carts(ArrayList<Cart> carts) { this.carts=carts; }

    //xml gets mad without this
    public Carts() {}

    public ArrayList<Cart> getCarts(){
        return this.carts;
    }

    public void setCarts(ArrayList<Cart> carts){
        this.carts=carts;
    }
}
