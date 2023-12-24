
package PatientManagement.Patient.Encounters;

import java.util.ArrayList;

import PatientManagement.Catalogs.Limits;
import PatientManagement.Patient.Patient;

public class VitalSigns {
    Patient patient;
    Encounter encounter;
    ArrayList<VitalSignMetric> vitalSigns;

    public VitalSigns(Patient p, Encounter e) {
        patient = p;
        encounter = e;
        vitalSigns = new ArrayList<VitalSignMetric>();
    }

    public VitalSignMetric addNewVitals(String name, int value) {
        Limits limits = encounter.getVitalSignLimits(value, name);
        if (limits == null)
            return null;
        VitalSignMetric newVitals = new VitalSignMetric(patient, name, limits, value);
        vitalSigns.add(newVitals);
        return newVitals;
    }

    public Boolean areNormal() {
        boolean normal = true;
        for (VitalSignMetric vsm : vitalSigns) {
            if (!vsm.isNormal())
                normal = false;
        }

        return normal;
    }

}
