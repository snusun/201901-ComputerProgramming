import java.util.*;

public class Main {

    public static void main(String[] args) {
        student stu = new student();
        Scanner scan = new Scanner(System.in);
        System.out.println("input surname");
        stu.surname = scan.nextLine();
        System.out.println("input firstname");
        stu.firstname = scan.nextLine();
        System.out.println("input studentid");
        stu.studentid = scan.nextInt();
        System.out.println("input age");
        stu.setAge(scan.nextInt());
        scan.nextLine();
        System.out.println("input address");
        stu.address = scan.nextLine();

        System.out.println(stu.surname);
        System.out.println(stu.firstname);
        System.out.println(stu.studentid);
        System.out.println(stu.address);
        System.out.println(stu.isOld());
    }
}

class student {
    public String surname;
    public String firstname;
    public int studentid;
    private int age;
    public String address;
    private int height;

    public void setAge(int age) {
        this.age = age;
    }
    public boolean isOld() {
        if(this.age > 19) return true;
        else return false;
    }
}

