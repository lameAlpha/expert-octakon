import java.util.Scanner;


public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Classroom finalYearCSE ;
    public static final String LOGINID = "javaproject";
    public static final String PASSWORD = "1234567";


    public static void main(String[] args) {


        finalYearCSE = new Classroom("CSE 4G",0);

        System.out.println(" RUDRANIL KUNDU \n OOP JAVA PROJECT \n <- ATTENDANCE MANAGEMENT SYSTEM ->");
        System.out.println("-----------------------------------------");
        if (login()) {

            System.out.println("Enter the date in the following format : DD/MM/YY or DD/MM/YYYY \t");
            Date date = new Date(sc.nextLine());
            validateEnteredDate(date);

            System.out.println("Enter Working days for this month : \t");
            finalYearCSE.setWorkingDaysForCurrentMonth(sc.nextInt());

            boolean quit = false;
            printMenu();

            while (!quit) {
                System.out.println("Enter your choice(1 to  8) :\t");
                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {

                    case 1:
                        addStudent();
                        break;
                    case 2:
                        deleteStudent();
                        break;
                    case 3:
                        seeDetails();
                        break;
                    case 4:
                        addAttendanceForTheDate(date);
                        break;
                    case 5:
                        checkAttendance();
                        break;
                    case 6:
                        displayStudentList();
                        break;
                    case 7:
                        printMenu();
                        break;
                    case 8:
                        quit = true;
                        break;
                }
            }
        } else {
            System.out.println(" NO TRESSPASSING !!!!");
            System.exit(-1);
        }
    }

    private static void validateEnteredDate(Date date){
        String dateFormat = date.getDate();
        if(dateFormat.equals("0/0/0"))          //invalid date
            System.exit(-1);
    }

    public static void addStudent() {
        System.out.println("Enter name of the student \t");
        String name = sc.nextLine();
        System.out.println("Enter his/her roll number( needs to be unique and in the range of [ 1 - 90 ] ) \t");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter address of the student \t");
        String address = sc.nextLine();
        finalYearCSE.addstudent(new Student(name, roll,address));
    }


    public static void addAttendanceForTheDate(Date date){
        int roll = rollInput();
        System.out.println("Do you want to add attendance for today ? \n " +
                "Type 'T' for marking attendance for today \n" +
                "Type 'O' for marking attendance for any other day \t");
        String choice = sc.nextLine();
        switch (choice.toUpperCase()){
            case "Y" : {
                finalYearCSE.addAttendance(roll, date);
                break;
            }
            case "O" : { System.out.println("Enter the date in the following format : DD/MM/YY or DD/MM/YYYY \t");
                finalYearCSE.addAttendance(roll,new Date(sc.nextLine()));
                break;
            }
            default:
                System.out.println("Wrong Choice \n");
        }
    }

    public static void deleteStudent() {
        boolean status = finalYearCSE.deleteStudentRecord(rollInput());
        if (status)
            System.out.println("Record deleted");
        else
            System.out.println("Error deleting record");
    }

    public static void displayStudentList() {
        finalYearCSE.studentsList();
    }

    public static void seeDetails() {
        boolean status = finalYearCSE.getDetails(rollInput());
        if (!status)
            System.out.println("Unsuccessful query");
    }

    public static void checkAttendance() {
        boolean status = finalYearCSE.checkAttendance(rollInput());
        if (!status)
            System.out.println("Unsuccessful query");
    }

    public static int rollInput() {
        System.out.println("Enter roll number of the student \t");
        int roll = sc.nextInt();
        sc.nextLine();
        return roll;
    }

    public static void printMenu() {
        System.out.println("\nEnter \n 1 to Add a student \n 2 to delete a student \n 3 to see details of a student" +
                " \n 4 to mark attendance for a student \n 5 to check attendance of a student \n " +
                "6 to see the details of all the students \n 7 to see the menu again \n And 8 to Quit ");
    }


    public static boolean login() {
        System.out.println(" Enter login ID ");
        String id = sc.nextLine();
        System.out.println("Enter Password ");
        String password = sc.nextLine();
        if (id.equals(LOGINID) && password.equals(PASSWORD)) {
            System.out.println("Welcome.. \n");
            return true;
        }else
            return false;
    }
}
