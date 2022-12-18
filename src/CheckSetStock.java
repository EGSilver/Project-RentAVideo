import java.io.*;
import java.util.ArrayList;

public class CheckSetStock {
    DayOverview overview = new DayOverview(0,0,0,0);

    //reads the amount in stock for a certain item in the CSV file
    public int getStockFromCSV(RentalItem item) throws IOException {
        int stock = 0;
        if (item instanceof Movie) {
            File gameData = new File(("G:\\Git\\Project-RentAVideo\\data\\test.csv"));
            try (BufferedReader reader = new BufferedReader(new FileReader(gameData))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (((Movie) item).getTitle().equals(parts[4])) {
                        stock = Integer.parseInt(parts[3]);
                    }
                }
            }
            //System.out.print("Amount of " + ((Movie) item).getTitle() + " in stock: ");
        } else {
            File gameData = new File(("G:\\Git\\Project-RentAVideo\\data\\games.csv"));
            try (BufferedReader reader = new BufferedReader(new FileReader(gameData))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    stock = Integer.parseInt(parts[3]);
                }
                //System.out.print("Amount of " + ((Game) item).getTitle() + " in stock: ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return stock;
    }

    public void setStockMinusOne(RentalItem item) throws IOException {
        String filePath = "G:\\Git\\Project-RentAVideo\\data\\test.csv";
        ArrayList<String> arrayLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                arrayLines.add(line);
            }
        }
        for (int i = 0; i < arrayLines.size(); i++) {
            String line = arrayLines.get(i);
            String[] parts = line.split(",");
            if (item instanceof Movie && parts[4].equals(((Movie) item).getTitle()) && getStockFromCSV(item) > 0
                    || item instanceof Game && parts[4].equals(((Game) item).getTitle()) && getStockFromCSV(item) > 0) {
                parts[3] = String.valueOf(getStockFromCSV(item)-1);
                arrayLines.set(i, String.join(",", parts));
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (String line : arrayLines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    //customer can return a rented item, updates the stock field in the CSV file
    public void returnItem(RentalItem item) throws IOException {
        String filePath = "G:\\Git\\Project-RentAVideo\\data\\test.csv";
        ArrayList<String> arrayLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                arrayLines.add(line);
            }
        }
        for (int i = 0; i < arrayLines.size(); i++) {
            String line = arrayLines.get(i);
            String[] parts = line.split(",");
            if (item instanceof Movie && parts[4].equals(((Movie) item).getTitle()) && getStockFromCSV(item) > 0
                    || item instanceof Game && parts[4].equals(((Game) item).getTitle()) && getStockFromCSV(item) > 0) {
                parts[3] = String.valueOf(getStockFromCSV(item)+1);
                arrayLines.set(i, String.join(",", parts));
                overview.setReturns(overview.getReturns()+1);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (String line : arrayLines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
