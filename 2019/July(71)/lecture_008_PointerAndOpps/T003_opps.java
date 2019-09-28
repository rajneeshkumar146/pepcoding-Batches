import java.util.Arrays;
public class T003_opps {

    public static void main(String[] args) {

        // System.out.print(args[3] + " ");
        // System.out.print(args[4]);

        // T003_opps p = new T003_opps();
        // Student st = new Student();
        // Student st1 = new Student(1235,"ABC","II");
        // Student st2= new Student(1236,"ABC","II");
        // Student st3 = new Student(1237,"ABC","II");
        // System.out.print(st.rollno + " " + st.Name + " " + st.Branch);

        parent p = new parent();
        child c=new child();
        parent p1=new child();
        child c1=new parent();
         
        Arrays.binarySearch(a, key)
syso(a);
    }

     static int a=0;
//     static{
//    a=10;
//     }

    public static class Student {
        int rollno = 2332;
        String Name;
        String Branch;

        public Student(String Name, String Branch) {
            // this.rollno = rollno;
            this.Name = Name;
            this.Branch = Branch;
        }
    }

    public static class Pair {
        int a;
        int b;

        pair(int a,int b){
      this.a=a;
      this.b=b;
        }
    }

    public static void swap(pair p1) {
        int temp = p1.a;
        p1.a = p1.b;
        p1.b = temp;

    }

    public static class dog {

        String bread;
        String color;

        public void print() {
            System.out.println(bread + " " + color);
        }
    }

    public static class germansefferd extends dog {
        String ID;

        public void print() {
            super.print();
            System.out.println(bread + " " + color);
        }
    }

    public static class parent {
        int a;
        int b;

        void print() {
            System.out.println("hi");
        }
    }

    public static class child extends parent {
        int b;
        int c;

        void print() {
            System.out.println("hi");
        }

        void print1() {
            System.out.println("hi_there");
        }
    }



}