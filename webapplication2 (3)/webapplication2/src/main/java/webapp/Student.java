package webapp;

import java.util.Objects;

public class Student {
    private String name;
    private String id;
    private String password;

    public static Student creatStudent(String id, String password){
        return new Student(id, password);
    }

    private Student(String id, String password){
        this.id =id;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id +"";
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", ID=" + id +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(password, student.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, password);
    }
}
