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
    private String ip;

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

    public String getIp() {
        return ip;
    }

    public Events(String name, String price, long date, String category, String ip) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.category = category;
        this.ip = ip;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", name, price, category, ip, date);
    }
}
