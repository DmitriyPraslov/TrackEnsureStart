package collectionHomeWork;


import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        University u1 = new University();
        u1.addStudent(new Student("Ivan", "A", "A", "1996-02-10", "Street 2", "1", "Economy", 1,105));
        u1.addStudent(new Student("Petr", "P", "B", "1997-02-10", "Street 1", "2", "Economy", 1,105));
        u1.addStudent(new Student("Pavel", "B", "C", "1996-02-10", "Street 5", "3", "Economy", 1,105));
        u1.addStudent(new Student("Igor", "M", "W", "1995-02-10", "Street 2", "4", "Economy", 2,205));
        u1.addStudent(new Student("Jon", "P", "A", "1996-02-10", "Street 1", "5", "Economy", 2,205));
        u1.addStudent(new Student("Andrey", "O", "C", "1996-02-10", "Street 5", "6", "Economy", 2,205));
        u1.addStudent(new Student("Alex", "R", "P", "1995-02-10", "Street 10", "7", "Politology", 2,201));
        u1.addStudent(new Student("Vitaliy", "K", "X", "1994-02-10", "Street 11", "8", "Politology", 2,201));
        u1.addStudent(new Student("Irina", "I", "U", "1995-02-10", "Street 6", "9", "Politology", 2,201));
        u1.addStudent(new Student("Vitaliy", "L", "H", "1997-02-10", "Street 11", "10", "Politology", 3,301));
        u1.addStudent(new Student("Irina", "Z", "F", "1996-02-10", "Street 6", "11", "Politology", 3,301));
        u1.addStudent(new Student("Olga", "Q", "G", "1992-02-10", "Street 12", "12", "Computer Science", 4,415));
        u1.addStudent(new Student("Dmitriy", "G", "S", "1992-02-10", "Street 27", "13", "Computer Science", 4,415));
        u1.addStudent(new Student("Evgen", "V", "O", "1993-02-10", "Street 43", "14", "Computer Science", 4,415));
        u1.addStudent(new Student("Evgen", "W", "J", "1991-02-10", "Street 43", "15", "Computer Science", 5,515));

        System.out.println("--------------------1---------------------");
        u1.task_a("Computer Science");
        System.out.println("--------------------2---------------------");
        u1.task_b(1994);
        System.out.println("--------------------3---------------------");
        u1.task_c(415);
        System.out.println("--------------------4---------------------");
        u1.task_d();
        System.out.println("--------------------5---------------------");
        u1.task_e();
        System.out.println("--------------------6---------------------");
        u1.task_f();

        u1.writeStudentToFile();

        try {
            u1.getStudentFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
