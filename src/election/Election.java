package election;


import election.citizen.AdultCitizen;
import election.citizen.Candidate;
import election.citizen.Human;
import election.citizen.YoungCitizen;
import election.district.District;
import election.district.ElectoralDistrict;
import election.district.QuarantineElectoralDistrict;
import election.district.SpecialityElectoralDistrict;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Election {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final LocalDate electionDate;
    private final Set<Human> citizenList;
    private final Set<Party> partyList;
    private final Set<District> districtsList;

    public Election (String electionDate){
        this.electionDate = LocalDate.parse(electionDate,Election.DATE_TIME_FORMATTER);
        citizenList = new TreeSet<>();
        partyList = new TreeSet<>();
        districtsList = new HashSet<>();
    }



    //--------------------1---------------------//

    public void addDistrict(District district){
        districtsList.add(district);
    }

    public void loadDistrictFromFile(){
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("src\\election\\ioDoc\\district.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> strings = reader.lines().collect(Collectors.toList());
        for (String temp : strings){
            String[] districtArray = temp.split(";");
            switch (districtArray[1]){
                case "Normal" :
                    districtsList.add(new ElectoralDistrict(districtArray[0]));
                    break;
                case  "Quarantine" :
                    districtsList.add(new QuarantineElectoralDistrict(districtArray[0]));
                    break;
                case  "Speciality" :
                    districtsList.add(new SpecialityElectoralDistrict(districtArray[0]));
            }
        }
    }

    public void writeDistrictToFile(){
        String path = "src\\election\\ioDoc\\district.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (District district : districtsList){
                bw.write(district.getDistrictInfo() + '\n');
            }
            bw.flush();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    //--------------------2---------------------//

    public void addCitizen(Human citizen){
        citizenList.add(citizen);
    }

    public void loadCitizenFromFile(){
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("src\\election\\ioDoc\\citizens.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> strings = reader.lines().collect(Collectors.toList());
        for (String temp : strings){
            String[] citizenArray = temp.split(";");
            LocalDate localDate = LocalDate.now();
            LocalDate citizenBornDate = LocalDate.parse(citizenArray[3],Election.DATE_TIME_FORMATTER);
            if ((localDate.getYear() - citizenBornDate.getYear())<18){
                citizenList.add(new YoungCitizen(citizenArray[0],citizenArray[3]));
                continue;
            } else if (citizenArray[7].length()>1){
                citizenList.add(new Candidate(citizenArray[0],Integer.parseInt(citizenArray[1]),Long.parseLong(citizenArray[2]),citizenArray[3],Integer.parseInt(citizenArray[4])
                        ,Boolean.parseBoolean(citizenArray[5]),Boolean.parseBoolean(citizenArray[6]),citizenArray[7]));
                continue;
            } else {
                citizenList.add(new AdultCitizen(citizenArray[0],Integer.parseInt(citizenArray[1]),Long.parseLong(citizenArray[2]),citizenArray[3],Integer.parseInt(citizenArray[4])
                        ,Boolean.parseBoolean(citizenArray[5]),Boolean.parseBoolean(citizenArray[6])));
            }
        }
    }

    public void writeCitizenToFile(){
        String path = "src\\election\\ioDoc\\citizens.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Human human : citizenList){
                bw.write(human.getCitizenInfo() + '\n');
            }
            bw.flush();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    //--------------------3---------------------//

    public boolean addParty (Party party){
        return partyList.add(party);
    }

    public boolean removeParty (Party party){
        return partyList.remove(party);
    }

    public void loadPartyFromFile (){
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("src\\election\\ioDoc\\party.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> strings = reader.lines().collect(Collectors.toList());
        for (String temp : strings){
            String[] partyArray = temp.split(";");
            switch (partyArray[1]){
                case "LEFT" :
                    partyList.add(new Party(partyArray[0], Party.FractionType.LEFT,partyArray[2]));
                    break;
                case  "CENTER" :
                    partyList.add(new Party(partyArray[0], Party.FractionType.CENTER,partyArray[2]));
                    break;
                case  "RIGHT" :
                    partyList.add(new Party(partyArray[0], Party.FractionType.RIGHT,partyArray[2]));
            }
        }
        for (Human citizen : citizenList){
            if (citizen.getClass().equals(AdultCitizen.class)){
                addCitizenToDistrict(citizen);
            } else if (citizen.getClass().equals(Candidate.class)){
                addCitizenToDistrict(citizen);
                addMemberOfParty((Candidate) citizen, ((Candidate) citizen).getMemberOfParty());
            }
        }
    }

    public void writePartyToFile(){
        String path = "src\\election\\ioDoc\\party.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Party party : partyList){
                bw.write(party.getPartyInfo() + '\n');
            }
            bw.flush();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    //--------------------4---------------------//

    public Human raiseToCandidate(Human citizen, String partyName){
        LocalDate localDate = LocalDate.now();
        try {
            if ((localDate.getYear() - citizen.getBornDate().getYear())<18){
                throw new Exception();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        citizen = new Candidate(citizen, partyName);
        return citizen;
    }
    //--------------------5---------------------//

    public void showAllDistricts(){
        for (District district : districtsList){
            System.out.println(district);
        }
    }

    public void showCitizenOnDistrict(int districtNumber){
        for (District district : districtsList){
            if (district.getDistrictNumber()==districtNumber){
                System.out.println(district.getElectorate());
            }
        }
    }

    public void addCitizenToDistrict(Human citizen){
        for (District district : districtsList){
            if (district.getDistrictNumber()==citizen.getElectionDistrictAttachNumber()){
                district.addElector(citizen);
                break;
            }
        }
    }

    public void removeCitizenToDistrict(AdultCitizen citizen){
        for (District district : districtsList){
            if (district.getDistrictNumber()==citizen.getElectionDistrictAttachNumber()){
                district.getElectorate().remove(citizen);
                break;
            }
        }
    }
    //--------------------6---------------------//

    public void showAllCitizen(){
        for (Human human : citizenList){
            System.out.println(human);
        }
    }
    //--------------------7---------------------//
    public void showAllParty(){
        for (Party party : partyList){
            System.out.println(party.getPartyName());
            party.showCandidateList();
            System.out.println("-----------------------------------");
        }
    }

    public void showMemberOfParty (String partyName){
        for (Party party : partyList){
            if (party.getPartyName().equals(partyName)){
                System.out.println(party.getCandidateList());
                break;
            }
        }
    }

    public void addMemberOfParty (Candidate candidate, String partyName){
        for (Party party : partyList){
            if (party.getPartyName().equals(partyName)){
                party.getCandidateList().add(candidate);
                break;
            }
        }
    }

    public void removeMemberOfParty (Candidate candidate, String partyName){
        for (Party party : partyList){
            if (party.getPartyName().equals(partyName)){
                party.getCandidateList().remove(candidate);
                break;
            }
        }
    }

    //--------------------8---------------------//

    public void startElection(){
        for (District district : districtsList){
            district.startElection(partyList);
        }
    }
    //--------------------9---------------------//

    public Map<String,Integer> getResultElection(){
        List<Map<Party,Integer>> resultElectionFromDistricts = new ArrayList<>();
        Map<String,Integer> resultElection = new TreeMap<>();
        for (District district : districtsList){
            resultElectionFromDistricts.add(district.getElectionResult());
            district.getElectionResult();
        }
        for (Party party : partyList){
            int countPerParty = getCountPerParty(party,resultElectionFromDistricts);
            resultElection.put(party.getPartyName(),countPerParty);
        }
        return resultElection;
    }

    private int getCountPerParty(Party party, List<Map<Party, Integer>> resultElectionFromDistricts) {
        int result = 0;
        for (Map<Party, Integer> temp : resultElectionFromDistricts){
            if (temp.get(party)!=null){
                result = result+temp.get(party);
            }
        }
        return result;
    }

    public Map<String,Integer> getResultElectionWithStream(){                           // with Stream
        Map<String,Integer> resultElection = new TreeMap<>();
        resultElection = districtsList.stream()
                .map(District::getElectionResult)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(e->e.getKey().getPartyName(), Collectors.summingInt(Map.Entry::getValue)));
        return resultElection;
    }



    public void showPercentElection (){
        double result = 0;
        int countElectedCitizen = 0;
        int totalCountCitizenInDistrict = 0;
        for (District district : districtsList){
            countElectedCitizen += district.getCountWhoWasElected();
            totalCountCitizenInDistrict += district.getElectorate().size();
        }
        result = (double) countElectedCitizen/(totalCountCitizenInDistrict == 0 ? 1 : totalCountCitizenInDistrict);
        String formattedDouble = new DecimalFormat("#0.00").format(result*100);
        System.out.println(formattedDouble + "%");
        System.out.println(countElectedCitizen + " who took part in election / " + totalCountCitizenInDistrict + " total electorate");
        System.out.println();
    }

    //--------------------10--------------------//

    public boolean end(){
        return true;
    }
}
