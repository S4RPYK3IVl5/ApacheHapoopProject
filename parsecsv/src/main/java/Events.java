import com.opencsv.bean.CsvBindByName;

import java.sql.Timestamp;
import java.util.Date;

public class Events {
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String price;
    @CsvBindByName
    private long date;
    @CsvBindByName
    private String category;
    @CsvBindByName
    private String id;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public long getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public Events(String name, String price, long date, String category, String id) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.category = category;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", name, price, date, category, id);
    }
}
