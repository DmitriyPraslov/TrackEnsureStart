package collectionHomeWork;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student implements Comparable<Student>{
    private final int id;
    private String firstName;
    private String surname;
    private String patronymic;
    private LocalDate dateBorn;
    private String address;
    private String phoneNumber;
    private String faculty;
    private int course;
    private int group;
    static int currentId;
    public Student(String firstName, String surname, String patronymic, String dateBorn, String address, String phoneNumber, String faculty, int course, int group) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.id=currentId++;
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateBorn = LocalDate.parse(dateBorn,dateFormat);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    @Override
    public int compareTo(Student std) {
        return (surname+firstName+patronymic).compareTo(std.surname+std.firstName+std.patronymic);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateBorn'" + dateBorn + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", faculty='" + faculty + '\'' +
                ", course=" + course +
                ", group=" + group +
                '}';
    }

    public String getStudentInfo(){
        char delimiter = ';';
        StringBuilder build = new StringBuilder("");
        build.append(this.firstName+delimiter+this.surname+delimiter+this.patronymic+delimiter+dateBorn+delimiter
                +this.address+delimiter+this.phoneNumber+delimiter+this.faculty+delimiter+this.course+delimiter+this.group);
        return build.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public LocalDate getDateBorn() {
        return dateBorn;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getCourse() {
        return course;
    }

    public int getGroup() {
        return group;
    }
}
