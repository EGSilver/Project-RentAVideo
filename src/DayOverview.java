import java.util.*;

public class DayOverview {
    private int returns;
    private int rentals;
    private int lateReturns;
    private int newMembers;

    private double income;
    private HashMap<Date, DayOverview> dayOverviewMap = new HashMap<>();
    private HashMap<Date, Double> dayIncomeOverviewMap = new HashMap<>();

    //TODO hashmap with overview of earning of the day.

    public DayOverview(int returns, int rentals, int lateReturns, int newMembers) {
        this.returns = returns;
        this.rentals = rentals;
        this.lateReturns = lateReturns;
        this.newMembers = newMembers;
        this.income = income;
    }

    public void createIncomeOverview(Date currentDate) {
        dayIncomeOverviewMap.put(currentDate, income);
    }

    public void viewIncomeOverview(Date specificDate) {
        Double overviewEarningsOfCertainDate = dayIncomeOverviewMap.get(specificDate);
        System.out.println(specificDate
                + ":\nTotal amount of income: €" + income);
    }
    public void createOverview(Date currentDate) {
        dayOverviewMap.put(currentDate, new DayOverview(returns,rentals,lateReturns,newMembers));
    }

    // Iterate over dayOverview / incomeOverview be able to remove keys & pairs from the hashmap
    public void removeDayOverviewFromMap(Date specificDate) {
        Iterator<Date> it = dayOverviewMap.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().equals(specificDate)) {
                it.remove();
            }
        }
    }

    public void removeIncomeOverviewFromMap(Date specificDate) {
        Iterator<Date> it = dayIncomeOverviewMap.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().equals(specificDate)) {
                it.remove();
            }
        }
    }

    public void viewDayOverview(Date specificDate) {
        DayOverview overviewOfCertainDate = dayOverviewMap.get(specificDate);
        int rentals = overviewOfCertainDate.getRentals();
        int returns = overviewOfCertainDate.getReturns();
        int lateReturns = overviewOfCertainDate.getLateReturns();
        int newMembers = overviewOfCertainDate.getNewMembers();
        System.out.println(specificDate
               + "\nAmount of rentals: " + rentals
               + "\nAmount of returns: " + returns
               + "\nAmount of late returns: " + lateReturns
               + "\nAmount of New Members: " + newMembers);
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
