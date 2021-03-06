package hash.code;
import java.util.HashMap;
import java.util.Map;
public final class HashCodeExample {
    private HashCodeExample() {
    }
    public static void main(final String[] agrs) {
        Map mapobj = new HashMap();
        String value = HashCodeExample.getHashVal(mapobj);
        System.out.println("the value is:" + value);
    }
    public static String getHashVal(final Map mapobj) {
        Employee empobj1 = new Employee("Student1" , 10);
        mapobj.put(empobj1 , "mapval1");
        Employee empobj2 = new Employee("Student1" , 20);
        mapobj.put(empobj2 , "mapval2");
        String val = (String) mapobj.get(empobj1);
        System.out.println(mapobj);
        return val;
    }
}
class Employee {
    private String empname;
    private int empid;
    Employee(final String empname , final int empid) {
        this.empname = empname;
        this.empid = empid;
    }
    public boolean equals(final Object obj) {
        if (!(obj instanceof Employee)) {
            return false;
        } else if (obj == this) {
            return true;
        }
        Employee empobj = (Employee) obj;
        return (this.empid == empobj.empid && this.empname.equals(empobj.empname));
    }
    public int hashCode() {
        return this.empid;
    }
}
