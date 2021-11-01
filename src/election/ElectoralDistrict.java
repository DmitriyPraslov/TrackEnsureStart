package election;

import java.util.*;

public class ElectoralDistrict extends District{
    public ElectoralDistrict(String address) {
        super(address);
        description = "Normal";
    }

    @Override
    public boolean isMilitaryCheck(Human citizen) {
        if (citizen.isMilitarySpecCitizen()){
            return true;
        }
        return false;
    }

    @Override
    public boolean isOnQuarantineStatusCheck(Human citizen) {
        if (citizen.isOnQuarantineStatus()){
            return true;
        }
        return false;
    }

    @Override
    public Map<Party, Integer> startElection(Set<Party> partyList) {
        for (Party temp : partyList){
            electionResult.put(temp,0);
        }
        for (Human citizen : electorate){
            String resultChoice = citizen.doChoice(partyList);
            for (Party temp : electionResult.keySet()){
                if (resultChoice.equals(temp.getPartyName())){
                    electionResult.put(temp,electionResult.get(temp)+1);
                    countWhoWasElected++;
                }
            }
        }
        return electionResult;
    }

    @Override
    public String getPercentElection(){
        return electorate.size()>0?countWhoWasElected / electorate.size() * 100 + "%" : 0 + "%";
    }

    @Override
    public int showCountElectionCitizen() {
        return countWhoWasElected;
    }
}
