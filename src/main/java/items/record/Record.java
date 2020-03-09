package items.record;

import java.text.DecimalFormat;
import java.util.Objects;

public class Record {
    private static final DecimalFormat FORMAT = new DecimalFormat("#,###,###,##0.00");

    private int num;
    private Double value;
    private String text;

    public Record(int num, Double value, String text) {
        this.num = num;
        this.value = value;
        this.text = text;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double countSum() {
        return value == null ? num : (num + value);
    }

    @Override
    public String toString() {
        return "{" + num + "/" + (value == null ? "NULL" : FORMAT.format(value)) + ":\"" + text + "\" (" + FORMAT.format(countSum()) +  ")}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;
        Record record = (Record) o;
        return num == record.num &&
                Objects.equals(value, record.value) &&
                Objects.equals(text, record.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, value, text);
    }
}
