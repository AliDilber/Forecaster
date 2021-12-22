package edu.eskisehir;

import edu.eskisehir.model.Record;
import edu.eskisehir.model.Row;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class LoadDatasetTest {

    @Test
    public void loadTest() throws IOException {
        try (InputStream stream = this.getClass().getResourceAsStream("/Dataset1.csv")) {
            Scanner scanner = new Scanner(stream);
            String headerLine = scanner.nextLine();
            String[] headers = headerLine.split(",");
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] cells = line.split(",");
                Row row = new Row();
                row.setYear(cells[0]);
                for (int i = 1; i < cells.length; i++) {
                    Record record = new Record();
                    record.setMonth(headers[i]);
                    record.setValue(Integer.parseInt(cells[i]));
                    row.getRecords().add(record);
                }
                System.out.println(row);
            }
        }
        InputStream stream = this.getClass().getResourceAsStream("/Dataset2.csv");
        try {
            Scanner scanner = new Scanner(stream);
            String headerLine = scanner.nextLine();
            String[] headers = headerLine.split(",");
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] cells = line.split(",");
                Row row = new Row();
                row.setYear(cells[0]);
                for (int i = 1; i < cells.length; i++) {
                    Record record = new Record();
                    record.setMonth(headers[i]);
                    record.setValue(Integer.parseInt(cells[i]));
                    row.getRecords().add(record);

                }
                System.out.println(row);
            }
        } finally {
            stream.close();
        }

    }
}
