package election;

import election.citizen.Candidate;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Party implements Comparable<Party>{
    private String partyName;
    private FractionType fractionType;
    private LocalDate creationDate;
    private Set<Candidate> candidateList;

    public Party(String partyName, FractionType fractionType, String creationDate) {
        this.partyName = partyName;
        this.fractionType = fractionType;
        this.creationDate = LocalDate.parse(creationDate,Election.DATE_TIME_FORMATTER);
        Comparator<Candidate> comparator = Comparator.comparingInt(Candidate::getPrimaryPartyID);
        this.candidateList = new TreeSet<>(comparator);
    }

    public void addCandidate(Candidate candidate) {
        try {
            if (!candidate.getMemberOfParty().equals(partyName)){
                throw new Exception("Error");                                       // Придумать Ексепшин
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        candidateList.add(candidate);
    }
    public void showCandidateList(){
        for (Candidate temp: candidateList) {
            System.out.println(temp);
        }
    }

    public boolean removeCandidate(Candidate candidate){
        return candidateList.remove(candidate);
    }

    public Set<Candidate> getCandidateList(){
        return candidateList;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getFraction() {
        return fractionType.name();
    }

    public void setFraction(FractionType fractionType) {
        this.fractionType = fractionType;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public int compareTo(Party o) {
        return this.getPartyName().compareTo(o.getPartyName());
    }

    public String getPartyInfo(){
        char delimiter = ';';
        StringBuilder build = new StringBuilder("");
        build.append(this.partyName+delimiter+this.fractionType+delimiter+creationDate.format(Election.DATE_TIME_FORMATTER));
        return build.toString();
    }

    @Override
    public String toString() {
        return "Party{" +
                "partyName='" + partyName + '\'' +
                ", fractionType=" + fractionType +
                ", creationDate=" + creationDate +
                '}';
    }

    enum FractionType{
        LEFT,
        RIGHT,
        CENTER
    }
}
