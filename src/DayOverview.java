import java.util.*;

public class DayOverview {
    private int returns;
    private int rentals;
    private int lateReturns;
    private int newMembers;
    private HashMap<Date, DayOverview> dayOverviewMap = new HashMap<>();

    //TODO hashmap with overview of earning of the day.

    public DayOverview(int returns, int rentals, int lateReturns, int newMembers) {
        this.returns = returns;
        this.rentals = rentals;
        this.lateReturns = lateReturns;
        this.newMembers = newMembers;
    }

    public void createOverview(Date today) {
        dayOverviewMap.put(today, new DayOverview(returns,rentals,lateReturns,newMembers));
    }

    public void viewOverview(Date date) {
        DayOverview overviewOfCertainDate = dayOverviewMap.get(date);
        int rentals = overviewOfCertainDate.getRentals();
        int returns = overviewOfCertainDate.getReturns();
        int lateReturns = overviewOfCertainDate.getLateReturns();
        int newMembers = overviewOfCertainDate.getNewMembers();
        System.out.println(date
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
