import java.util.ArrayList;
import java.util.List;

public class Student {


    private String name;
    private int rollNo ;
    private int daysAttended;
    private String address;
    private List<Date> dates;

    public Student(String name, int rollNo, String address) {
        try{
                name = name.toUpperCase();
                if(name.equals("") | name.equals("UNKNOWN"))
                    throw new IllegalArgumentException("Invalid name");
                else this.name = name;

                if(rollNo > 0 && rollNo < 90)
                    this.rollNo = rollNo;
                else
                    throw new IllegalArgumentException("not a valid roll number");

                this.address = (address.equals("") | address.equals("unknown")) ? "blank" :address;
                this.daysAttended = 0;
                this.dates = new ArrayList<>();

        }catch(IllegalArgumentException e){
            System.out.println("Error in adding student : " +e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        try {
            name = name.toUpperCase();
            if (name.equals("") | name.equals("UNKNOWN"))
                throw new IllegalArgumentException("Invalid name");
            else this.name = name;
        }catch(IllegalArgumentException e){
            System.out.println("Error renaming student : " +e.getMessage());
        }
    }

    protected int checkForDate( Date date){
            for(int i = 0; i < dates.size();i++){
                if(date.getDate().equals(dates.get(i).getDate()))
                    return i;
            }
            return -1;
    }

    public int getRollNo() {
        return rollNo;
    }

    public int getDaysAttended() {
        return daysAttended;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? "blank":address;
    }


    //will have to find a way to display attendance % with displayStudentDetail()

    public void displayStudentDetail(int workingdays){
        System.out.println(" Name : " + this.name );
        System.out.println(" Roll : " + this.rollNo );
        System.out.println(" Attendance : " + calculateAttendance(workingdays) );
    }

    public double calculateAttendance(int workingdays){
        double result =(( (double) getDaysAttended() / (double) workingdays )*100);
        return result;
    }

    public boolean addAttendance(Date date, int workingdays ){
        if(this.daysAttended > workingdays ) {
            System.out.println("ERROR , attended days exceeding working days ");
            return false;
        }else{          //adding the date in list of attended dates....
            String dateFormat = date.getDate();
                if(dateFormat.equals("0/0/0"))          //invalid date
                    return false;
                else {
                    this.daysAttended += 1;
                    this.dates.add(date);
                    return true;
                }
        }
    }


    protected void displaydates(){
        if(dates.size() != 0 && daysAttended != 0) {
            System.out.println("Classes attended on :");
            for(Date date : dates)
                System.out.println(date.getDate()+" ");
        }else
            System.out.println("Did not attend any class");
    }


}
