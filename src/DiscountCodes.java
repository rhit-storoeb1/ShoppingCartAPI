import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="codes")
@XmlAccessorType(XmlAccessType.FIELD)
public class DiscountCodes {
    @XmlElement(name="code")
    private ArrayList<DiscountCode> codes = null;

    public DiscountCodes(ArrayList<DiscountCode> codes) { this.codes=codes; }

    //xml gets mad without this
    public DiscountCodes() {}

    public ArrayList<DiscountCode> getDiscountCodes(){
        return this.codes;
    }

    public void setDiscountCodes(ArrayList<DiscountCode> codes){
        this.codes=codes;
    }
}
