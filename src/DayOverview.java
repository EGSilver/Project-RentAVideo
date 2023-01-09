import java.util.*;

public class DayOverview {
    private int returns;
    private int rentals;
    private int lateReturns;
    private int newMembers;

    private double income;
    private HashMap<String, DayOverview> dayOverviewMap = new HashMap<String, DayOverview>();
    private HashMap<String, Double> dayIncomeOverviewMap = new HashMap<String, Double>();

    public DayOverview(int returns, int rentals, int lateReturns, int newMembers) {
        this.returns = returns;
        this.rentals = rentals;
        this.lateReturns = lateReturns;
        this.newMembers = newMembers;
        this.income = income;
    }

    public void createIncomeOverview(String currentDate) {
        dayIncomeOverviewMap.put(currentDate, income);
    }

    public void createIncomeOverviewTest(Date currentDate, DayOverview overview) {
        dayIncomeOverviewMap.put(String.valueOf(currentDate), income);
    }

    public void updateIncomeOverview(String currentDate) {
        //double value = dayIncomeOverviewMap.get(currentDate);
        //income += value;
        dayIncomeOverviewMap.replace(currentDate, income);
    }


    public String viewDayOverview(String specificDate) {
        DayOverview overviewOfCertainDate = dayOverviewMap.get(specificDate);
        int rentals = overviewOfCertainDate.getRentals();
        int returns = overviewOfCertainDate.getReturns();
        int lateReturns = overviewOfCertainDate.getLateReturns();
        int newMembers = overviewOfCertainDate.getNewMembers();
        return specificDate
                + "\nAmount of rentals: " + rentals
                + "\nAmount of returns: " + returns
                + "\nAmount of late returns: " + lateReturns
                + "\nAmount of New Members: " + newMembers;
    }

    public Object viewIncomeOverview(String specificDate) {
        return dayIncomeOverviewMap.get(specificDate);
    }

    public String viewIncomeOverviewTestLateReturn(Date specificDate, DayOverview overview) {
        String date = String.valueOf(specificDate);
        Double overviewEarningsOfCertainDate = dayIncomeOverviewMap.get(date);
        return specificDate + "\nTotal amount\nof income:\n€" + overviewEarningsOfCertainDate;
    }

    public void testHashMap() {
        String key = "2023-01-06";
        DayOverview overview = new DayOverview(5,5,5,5);
        dayOverviewMap.put(key, overview);
    }

    public DayOverview createOverview(String currentDate, DayOverview overview) {
      return dayOverviewMap.put(currentDate, overview);
    }

     /**
     This function removes the specified date from the dayOverviewMap.
     @param specificDate the date to be removed from the map
     */
    public void removeDayOverviewFromMap(String specificDate) {
        Iterator<String> it = dayOverviewMap.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().equals(specificDate)) {
                it.remove();
            }
        }
    }

    public void removeIncomeOverviewFromMap(String specificDate) {
        Iterator<String> it = dayIncomeOverviewMap.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().equals(specificDate)) {
                it.remove();
            }
        }
    }

    public int getReturns() {
        return returns;
    }

    public void setReturns(int returns) {
        this.returns = returns;
    }

    public int getRentals() {
        return rentals;
    }

    public void setRentals(int rentals) {
        this.rentals = rentals;
    }

    public int getLateReturns() {
        return lateReturns;
    }

    public void setLateReturns(int lateReturns) {
        this.lateReturns = lateReturns;
    }

    public int getNewMembers() {
        return newMembers;
    }

    public void setNewMembers(int newMembers) {
        this.newMembers = newMembers;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "OverviewDay{" +
                "returns=" + returns +
                ", rentals=" + rentals +
                ", lateReturns=" + lateReturns +
                ", newMembers=" + newMembers +
                '}';
    }
}
