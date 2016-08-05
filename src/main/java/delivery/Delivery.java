package delivery;

public class Delivery {

    String key;
    String value;

    public Delivery(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Delivery() {}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
