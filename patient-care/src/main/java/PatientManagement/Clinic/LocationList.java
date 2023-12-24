
package PatientManagement.Clinic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class LocationList {
    ArrayList<Location> locations;

    public LocationList() {
        locations = new ArrayList<Location>();
    }

    public Optional<Location> getLocation(String locname) {
        // search for a location object by locname;
        Location l = null;
        return locations.stream().filter( s -> Objects.equals(s.gps, locname)).findAny();
    }

    public void addNewLocation(Location location) {
        locations.add(location);
    }

    public List<Location> getAllLocations() {
        return this.locations;
    }
}
