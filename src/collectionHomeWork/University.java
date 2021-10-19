package collectionHomeWork;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class University {
    private Set<Student> students=new TreeSet<>();

    public boolean addStudent(Student stud){
        return students.add(stud);
    }

    public boolean deleteStudent(Student stud){
        return students.remove(stud);
    }

    public List<Student> task_a(String faculty){
        List<Student> result = students.stream().filter(w->w.getFaculty().equals(faculty)).collect(Collectors.toList());
        result.forEach(System.out::println);
        return result;
    }

    public List<Student> task_b(int year){
        List<Student> result = students.stream().filter(w->w.getDateBorn().getYear()>year).collect(Collectors.toList());
        result.forEach(System.out::println);
        return result;
    }

    public List<Student> task_c(int group){
        List<Student> result = students.stream().filter(r->r.getGroup()==(group)).collect(Collectors.toList());
        result.forEach(System.out::println);
        return result;
    }

    public List<Student> task_d(){
        List<Student> result = students.stream().sorted((w,y)->w.getFaculty().compareTo(y.getFaculty())*1237+w.getDateBorn().compareTo(y.getDateBorn())).collect(Collectors.toList());
        result.forEach(System.out::println);
        return result;
    }

    public void task_e(){
        students.stream().map(w->w.getFaculty()).distinct().forEach(System.out::println);
    }

    public void task_f(){
        students.stream().map(w->w.getFaculty()).distinct().forEach(r->{
            long count=students.stream().filter(w->w.getFaculty().equals(r)).count();
            System.out.println(r+" - "+count);
        });
    }

    public void writeStudentToFile(){
        String path = "D:\\Java\\Projects\\TrackEnsureStart\\src\\collectionHomeWork\\test.txt";
        List<Student> studentList = students.stream().distinct().collect(Collectors.toList());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Student temp : studentList){
            bw.write(temp.getStudentInfo() + '\n');
            }
            bw.flush();
        }  catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public List<Student> getStudentFromFile () throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("D:\\Java\\Projects\\TrackEnsureStart\\src\\collectionHomeWork\\test.txt"), StandardCharsets.UTF_8);
        List<Student> result = new ArrayList<>();
        List<String> strings = reader.lines().collect(Collectors.toList());
        for (String temp : strings){
            String[] elementArray = temp.split(";");
            result.add(new Student(elementArray[0],elementArray[1],elementArray[2],elementArray[3],elementArray[4],elementArray[5],elementArray[6],Integer.parseInt(elementArray[7]),Integer.parseInt(elementArray[8])));
        }
        result.stream().forEach(System.out::println);
        return result;
    }
}
