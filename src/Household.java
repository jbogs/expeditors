import java.util.ArrayList;
import java.util.List;

// Manages a household at a specific address and its occupants
public class Household {
    private final String fullAddress;
    private final List<Person> occupants;

    public Household(String fullAddress) {
        this.fullAddress = fullAddress;
        this.occupants = new ArrayList<>();
    }

    public void addOccupant(Person person) {
        occupants.add(person);
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public List<Person> getOccupants() { return occupants; }

    public int getOccupantCount() {
        return occupants.size();
    }
}