import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Classroom {

    private String className ;
    private List<Student> students;
    private int workingDaysForCurrentMonth;
    private static Scanner sc = new Scanner(System.in);

    public Classroom(String className,int workingDays) {
        this.className = className;
        this.students = new ArrayList<>();
        if(workingDays > 0 && workingDays <= 27)        //Excluding 4 sundays.
            this.workingDaysForCurrentMonth = workingDays;
        else {
            this.workingDaysForCurrentMonth = 25;
        }
    }

    public void setWorkingDaysForCurrentMonth(int workingDays) {
        if(workingDays > 0 && workingDays <= 27)        //Excluding 4 sundays.
            this.workingDaysForCurrentMonth = workingDays;
        else {
            System.out.println("Invalid working days : "+workingDays+ " isn't a valid number!");
            this.workingDaysForCurrentMonth = 25;
            System.out.println("Setting working days for this month as : " +workingDaysForCurrentMonth);
        }
    }

    public void studentsList() {
        if (students.size() == 0)
            System.out.println("No students added in this class");
        else {
            for (Student student : students) {
                student.displayStudentDetail(this.workingDaysForCurrentMonth);
            }
        }
    }

    public boolean addAttendance(int roll , Date date){
        if(roll > 0 && roll <= 90) {
            for (Student student : students) {
                if (student.getRollNo() == roll) {
                    int position = student.checkForDate(date);
                  //  System.out.println("position for "+date.getDate()+" : "+position);
                    if(position == -1){
                        boolean flag = student.addAttendance(date, this.workingDaysForCurrentMonth);
                        if(flag)
                            System.out.println("Attendance marked for roll "+roll);
                        else {
                            System.out.println("Something went wrong ");
                            return false;
                        }
                    }else {
                        System.out.println("Attendance already marked for roll " + roll);
                    }
                    return true;
                }
            }
            System.out.println(roll + " not found");
        }else System.out.println("Invalid roll number");

        return false;
    }


//    public boolean addAttendance(int roll , Double date){          //this function will be commented out....
//        for (int i = 0; i < students.size(); i++) {
//            if(students.get(i).getRollNo() == roll){
//                students.get(i).addAttendance(date,25);
//                return true;
//            }
//        }
//        System.out.println( roll+ " not found");
//        return false;
//    }

//    public boolean checkAttendance(int roll){                     //this function will be commented out....
//        for (int i = 0; i < students.size(); i++) {
//            if(students.get(i).getRollNo() == roll){
//                System.out.println( "Attendance percentage " + students.get(i).calculateAttendance(25) +" % ");
//                students.get(i).displaydates();
//                return true;
//            }
//        }
//        System.out.println( roll+ " not found");
//        return false;
//    }

    public boolean checkAttendance(int roll){
        if(roll > 0 && roll <= 90) {
            for (Student student : students) {
                if (student.getRollNo() == roll) {
                    System.out.println("Attendance percentage " + student.calculateAttendance(this.workingDaysForCurrentMonth) + " % ");
                    student.displaydates();
                    return true;
                }
            }
            System.out.println("Roll "+roll + " not found");
        }else System.out.println("Invalid roll number");

        return false;
    }

    public boolean deleteStudentRecord(int roll){
        if(roll > 0 && roll <= 90) {
            for (int i = 0; i < students.size(); i++) {
                if(students.get(i).getRollNo() == roll){
                    System.out.println("Enter Student's name : \t");
                    String name = sc.nextLine();
                    if(name.toUpperCase().equals(students.get(i).getName())) {
                        students.remove(i);
                        System.out.println("Student "+name+" has been removed from the class");
                        return true;
                    }else {
                        System.out.println("The entered name does not match with the corresponding name associated with the roll number "+roll);
                        return false;
                    }
                }
            }
            System.out.println( roll+ " not found");
        }else System.out.println("Invalid roll number");

        return false;
    }

//    public boolean deleteStudentRecord(int roll){
//        for (int i = 0; i < students.size(); i++) {
//            if(students.get(i).getRollNo() == roll){
//                students.remove(i);
//                return true;
//            }
//        }
//        System.out.println( roll+ " not found");
//        return false;
//    }


    public void addstudent( Student student){
        if(this.students.size() >= 90 )
        {
            System.out.println("You can not fit any more students in this batch/classroom");
        } else {
            for (Student temp : students) {
                if (temp.getRollNo() == student.getRollNo() || temp.getName().equals(student.getName())) {
                    System.out.println("Roll no : " +temp.getRollNo()+" : "+temp.getName()+ " already exits in the class");
                    return;
                }
            }
            this.students.add(student);
        }
    }


    public boolean getDetails( int roll){
        if(roll > 0 && roll <= 90) {
            for (Student student : students) {
                if (student.getRollNo() == roll) {
                    student.displayStudentDetail(this.workingDaysForCurrentMonth);
                    return true;
                }
            }
            System.out.println( roll+ " not found");
            return false;
        }else System.out.println("Invalid roll number");

        return false;
    }

}
