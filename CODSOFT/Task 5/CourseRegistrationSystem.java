import java.util.ArrayList;
import java.util.List;

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean registerForCourse(Course course) {
        return course.addStudent(this);
    }

    public boolean dropCourse(Course course) {
        return course.removeStudent(this);
    }

    @Override
    public String toString() {
        return "Student(ID: " + id + ", Name: " + name + ")";
    }
}

class Course {
    private String code;
    private String title;
    private int capacity;
    private List<Student> students;

    public Course(String code, String title, int capacity) {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        if (students.size() < capacity && !students.contains(student)) {
            students.add(student);
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        return students.remove(student);
    }

    public void listRegisteredStudents() {
        System.out.println("Registered students for " + title + ":");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public String getCourseDetails() {
        return code + " - " + title + " (Capacity: " + capacity + ", Registered: " + students.size() + ")";
    }
}

public class CourseRegistrationSystem {
    private List<Course> courses;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void listAllCourses() {
        for (Course course : courses) {
            System.out.println(course.getCourseDetails());
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        Course math101 = new Course("MATH101", "Calculus I", 30);
        Course cs101 = new Course("CS101", "Intro to Programming", 25);

        system.addCourse(math101);
        system.addCourse(cs101);

        Student alice = new Student("S001", "Alice Smith");
        Student bob = new Student("S002", "Bob Johnson");

        alice.registerForCourse(math101);
        bob.registerForCourse(cs101);

        system.listAllCourses();

        math101.listRegisteredStudents();
        cs101.listRegisteredStudents();

        alice.dropCourse(math101);

        system.listAllCourses();

        math101.listRegisteredStudents();
    }
}
