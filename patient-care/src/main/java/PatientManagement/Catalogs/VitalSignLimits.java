package PatientManagement.Catalogs;

import java.util.HashMap;

public class VitalSignLimits {
    String name;
    HashMap<AgeGroup, Limits> limits;

    public VitalSignLimits(String name) {
        this.name = name;
        limits = new HashMap<AgeGroup, Limits>();
    }

    public void addLimits(AgeGroup a, int upper, int lower) {
        limits.put(a, new Limits(upper, lower));
    }

    public Boolean isNormal(int age, int value) {
        for (AgeGroup ag : limits.keySet()) {
            if (ag.isInGroup(age))
                return limits.get(ag).isWithinLimits(value);
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Limits getLimits(AgeGroup ag) {
        return limits.get(ag);
    }
}
