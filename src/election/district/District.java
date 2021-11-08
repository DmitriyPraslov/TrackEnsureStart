package election.district;

import election.citizen.Human;
import election.Party;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public abstract class District {
    protected String description;
    protected int countWhoWasElected;
    protected Map<Party, Integer> electionResult;
    protected int districtNumber;
    protected String address;
    protected Set<Human> electorate;
    protected static int countDistrict = 1;

    public District(String address) {
        this.districtNumber=countDistrict++;
        this.electorate = new TreeSet<>();
        this.address = address;
        countWhoWasElected = 0;
        electionResult = new TreeMap<>();
    }

    public final boolean isAdultCitizenCheck(Human citizen){
        LocalDate localDate = LocalDate.now();
        if ((localDate.getYear() - citizen.getBornDate().getYear()) < 18){
            return false;
        }
        return true;
    }
    public abstract boolean isMilitaryCheck(Human citizen);
    public abstract boolean isOnQuarantineStatusCheck(Human citizen);


    public final void addElector (Human citizen){
        try {
            if (!isAdultCitizenCheck(citizen)){
                throw new Exception("Error");                                     // Придумать Ексепшин
            } else if (isMilitaryCheck(citizen)){
                throw new Exception("Error");                                     // Придумать Ексепшин
            } else if (isOnQuarantineStatusCheck(citizen)){
                throw new Exception("Error");                                     // Придумать Ексепшин
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        electorate.add(citizen);
    }

    public abstract Map<Party, Integer> startElection(Set<Party> partyList);

    public abstract String getPercentElection();

    public abstract int showCountElectionCitizen();

    public Map<Party, Integer> getElectionResult() {
        return electionResult;
    }

    public int getDistrictNumber() {
        return districtNumber;
    }

    public String getAddress() {
        return address;
    }

    public Set<Human> getElectorate() {
        return electorate;
    }

    public int getCountWhoWasElected() {
        return countWhoWasElected;
    }

    @Override
    public String toString() {
        return "District{" +
                "districtNumber=" + districtNumber +
                ", address='" + address + '\'' +
                '}';
    }

    public String getDistrictInfo(){
        char delimiter = ';';
        StringBuilder build = new StringBuilder("");
        build.append(this.address+delimiter+this.description);
        return build.toString();
    }
}
