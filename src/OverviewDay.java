import java.util.Date;
import java.util.HashMap;

public class OverviewDay {
    private int returns;
    private int rentals;
    private int lateReturns;
    private int newMembers;

    public OverviewDay(int returns, int rentals, int lateReturns, int newMembers) {
        this.returns = returns;
        this.rentals = rentals;
        this.lateReturns = lateReturns;
        this.newMembers = newMembers;
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
