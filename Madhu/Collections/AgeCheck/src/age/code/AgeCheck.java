package age.code;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public final class AgeCheck {
    private AgeCheck() {
    }
    public static void main(final  String[] args) {
        List arrlist = new ArrayList();
        arrlist.add(new Student(1 , "Balu" , 22));
        arrlist.add(new Student(2 , "raju" , 21));
        arrlist.add(new Student(3 , "babu" , 20));
        arrlist.add(new Student(4 , "anil" , 19));
        arrlist.add(new Student(5 , "manu" , 18));
        if (args.length != 1) {
            System.out.println(" please provide the required arguments ");
        } else {
            Student.removeAge(arrlist , Integer.parseInt(args[0]));
            AgeCheck.display(arrlist);
        }
    }
    public static void display(final List arrlist) {
        Iterator itr = arrlist.iterator();
        System.out.println("The list of students after apply age check:");
        while (itr.hasNext()) {
            Student st = (Student) itr.next();
            System.out.println(st.sid + " " + st.sname + " " + st.sage);
        }
    }
}
class Student  {
    int sid , sage;
    String sname;
    Student(final int sid , final String sname , final int sage) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
    }
    public static void removeAge(final List arrlist , final int age) {
        Iterator ie = arrlist.iterator();
        while (ie.hasNext()) {
            Student st = (Student) ie.next();
            if (st.sage >= age) {
                ie.remove();
            }
        }
    }
}
