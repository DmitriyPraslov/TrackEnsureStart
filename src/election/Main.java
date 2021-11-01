package election;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Election election = new Election("22-12-2021");
        Scanner scanner = new Scanner(System.in);
        boolean flagExit = false;

        while (!flagExit){
            System.out.println("1. Add District");
            System.out.println("2. Add Citizen");
            System.out.println("3. Add Party");
            System.out.println("4. Set Citizen as Candidate");
            System.out.println("5. Show All District");
            System.out.println("6. Show Citizen");
            System.out.println("7. Show Party");
            System.out.println("8. Start Election");
            System.out.println("9. Show Election Result");
            System.out.println("10. Exit");
            int inNumber = scanner.nextInt();
            switch (inNumber){
                case 1 :
                    boolean flag1 = false;
                    Scanner scannerDistrict = new Scanner(System.in);
                    while (!flag1) {
                        System.out.println("1. Add District from Keyboard");
                        System.out.println("2. Add District from File");
                        System.out.println("3. Back");
                        int choice1 = scanner.nextInt();
                        switch (choice1){
                            case 1 :
                                                                    // in Progress
                            case 2 :
                                election.loadDistrictFromFile();
                                flag1 = election.end();
                                break;
                            case 3 :
                                flag1 = election.end();
                                break;
                            }
                        }
                    break;
                case 2 :
                    boolean flag2 = false;
                    Scanner scannerCitizen = new Scanner(System.in);
                    while (!flag2) {
                        System.out.println("1. Add Citizen from Keyboard");
                        System.out.println("2. Add Citizen from File");
                        System.out.println("3. Back");
                        int choice2 = scanner.nextInt();
                        switch (choice2){
                            case 1 :
                                                                     // in Progress
                            case 2 :
                                election.loadCitizenFromFile();
                                flag2 = election.end();
                                break;
                            case 3 :
                                flag2 = election.end();
                                break;
                            }
                        }
                    break;
                case 3 :
                    boolean flag3 = false;
                    Scanner scannerParty = new Scanner(System.in);
                    while (!flag3) {
                        System.out.println("1. Add Party from Keyboard");
                        System.out.println("2. Add Party from File");
                        System.out.println("3. Back");
                        int choice3 = scanner.nextInt();
                        switch (choice3){
                            case 1 :
                                                                    // in Progress
                            case 2 :
                                election.loadPartyFromFile();
                                flag3 = election.end();
                                break;
                            case 3 :
                                flag3 = election.end();
                                break;
                            }
                        }
                    break;
                case 4 :
//                    election.addMemberOfParty();
                    break;
                case 5 :
                    election.showAllDistricts();
                    break;
                case 6 :
                    election.showAllCitizen();
                    break;
                case 7 :
                    election.showAllParty();
                    break;
                case 8 :
                    election.startElection();
                    break;
                case 9 :
                    System.out.println(election.getResultElection());
                    election.showPercentElection();
                    break;
                case 10 :
                    flagExit = election.end();
                    break;
            }
        }
    }
}
