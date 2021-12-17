import java.time.LocalDate;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DiscountCode {

    String code;
    double percentage;
    String expirationDate;

    public DiscountCode(String code, double percentage, String expirationDate){
        this.code=code;
        this.percentage=percentage;
        this.expirationDate=expirationDate;
    }

    //xml gets mad without this
    public DiscountCode(){}

    public boolean isExpired(){
        return LocalDate.parse(expirationDate).isBefore(LocalDate.now());
    }

    //xml stuff
    public String getCode(){ return this.code;}
    @XmlAttribute
    public void setCode(String code){this.code=code;}
    public double getPercentage(){return this.percentage;}
    @XmlAttribute
    public void setPercentage(double percentage){this.percentage=percentage;}
    public String getExpirationDate(){return this.expirationDate;}
    @XmlAttribute
    public void setExpirationDate(String date){this.expirationDate=date;}

}
