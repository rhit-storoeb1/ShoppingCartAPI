import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {
    int id;
    String name;
    String description;
    double price;
    String pictureURL;

    //not sure about this one
    int numInStock;

    public Item(int id, String name, String description, double price, String pictureURL, int numInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureURL = pictureURL;
        this.numInStock = numInStock;
    }

    //xml gets mad without this
    public Item() {

    }

    public boolean isOutOfStock() {
        return numInStock == 0;
    }

    public int getId() {
        return this.id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    @XmlAttribute
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    @XmlAttribute
    public void setPrice(double price) {
        this.price = price;
    }

    public String getPictureURL() {
        return this.pictureURL;
    }

    @XmlAttribute
    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public int getNumInStock() {
        return this.numInStock;
    }

    @XmlAttribute
    public void setNumInStock(int numInStock) {
        this.numInStock = numInStock;
    }
}
