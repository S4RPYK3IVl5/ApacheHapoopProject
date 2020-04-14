import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class CSVParser {
    public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException {

        Random random = new Random();
        String[] names = {"Bread", "Bear", "Cheese", "Wine", "PC", "MacBook", "Mouse", "Food", "Coach", "Table"};
        String[] price = {"100", "200", "300", "400", "500", "600", "700", "800", "900", "1000"};
        String[] category = {"basic", "hard", "medium", "clothes", "food", "things", "tech", "some", "well", "bad"};
        int[] hour = {7, 8, 12, 13, 14, 12, 13, 14, 18, 20};
        int[] minute = {0, 10, 20, 30, 25, 40, 50, 45, 55, 35};

        Writer writerEvents = new BufferedWriter(new FileWriter("file/out/events.csv"));
        StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writerEvents)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();

        Reader readerIp = new FileReader("file/in/ip.csv");
        CSVReader csvReaderIp = new CSVReaderBuilder(readerIp)
                .withSkipLines(1).build();
        List<String[]> strings = csvReaderIp.readAll();

        for (int i = 0; i < 3000; i++) {

            int indexName = random.nextInt(10);
            int indexPrice = random.nextInt(10);
            int indexDate = random.nextInt(7);
            int indexCategory = random.nextInt(10);
            int indexHour = random.nextInt(10);
            int indexMin = random.nextInt(10);

            Events events = new Events(names[indexName],
                    price[indexPrice],
                    new Timestamp(new Date(110, Calendar.JANUARY,
                            indexDate + 1, hour[indexHour], minute[indexMin]).getTime()).getTime(),
                    category[indexCategory],
                    strings.get(i)[0]);
            System.out.println(events);
            sbc.write(events);
            writerEvents.flush();

            Thread.sleep(900);
        }
        
    }
}
