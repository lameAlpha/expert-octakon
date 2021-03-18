public class Date  {
    private int DAY;
    private int MONTH;
    private int YEAR;

    public Date(String date){
        String[] arrOfStr = date.split("/", 3);     //        DD/MM/YY  ->  String str = "19/10/2020";
        int day = Integer.parseInt(arrOfStr[0]);
        int month = Integer.parseInt(arrOfStr[1]);
        int year = Integer.parseInt(arrOfStr[2]);

        if(validateDate(day,month,year)){
            this.DAY = day;
            this.MONTH = month;
            if( year >= 0 && year <= 20) {
                year += 2000;
            }else if(year >= 90 && year<= 99)
                year+= 1900;
            this.YEAR = year;
        }
    }

    public Date(short day, short month, short year){

        this(""+day+"/"+month+"/"+year);                    //Shitty code I know, but didn't wanna write the same lines in it.

//        if(validateDate(day,month,year)){
//            this.DAY = day;
//            this.MONTH = month;
//            if( year >= 0 && year <= 20) {
//                year += 2000;
//            }else if(year >= 90 && year<= 99)
//                year+= 1900;
//            this.YEAR = year;
//        }


//        StringBuilder date = new StringBuilder();
//        date.append(day);
//        date.append('/');
//        date.append(month);
//        date.append('/');
//        date.append(year);
//        date.append('/');
    }

    public int getDAY() {
        return DAY;
    }


    private static boolean validateDate(int day, int month, int year){
        boolean flag = false;
        try {
            if(day > 0 && day <= 31 && month > 0 && month <= 12 & year > 0 & year <= 9999) {
                switch(month){
                    case 1 : case 3 : case 5 : case 7: case 8: case 10: case 12: {
                        flag = true;
                        break;
                    }
                    case 2 : if((day == 29 & isLeapYear(year)) || day <= 28) {
                                flag = true;
                                break;
                            }
                    case 4 : case 6 : case 9 : case 11: if(day > 30) {
                        throw new IllegalArgumentException("Month " + month + " can't have day "+day);
                    }else{
                        flag = true;
                    }

                }
//                System.out.println(" flag : "+flag+ " year :" +year);

//                if(month == 2 ){
//                    if(day == 29 & isLeapYear(year))
//                        flag =true;
//                    else if(day > 28)
//                        throw new IllegalArgumentException("Month of Feburary can't have this date");
//                }else {
//                    flag = true;
//                }

            }else{
                throw new IllegalArgumentException("Invalid values in date format");
            }
        }catch (IllegalArgumentException e){
                System.out.println("Error in adding date : " +e.getMessage());
        }
        return flag;
    }

    public static boolean validateDate(String date){
        String[] arrOfStr = date.split("/", 3);     //        DD/MM/YY  ->  String str = "19/10/2020";
        int day = Integer.parseInt(arrOfStr[0]);
        int month = Integer.parseInt(arrOfStr[1]);
        int year = Integer.parseInt(arrOfStr[2]);
        return validateDate(day,month,year);
    }

    public static boolean isLeapYear(int year){
        boolean flag = false;
        if(year % 4 == 0){
            flag = true;
            if(year % 100 == 0 && year % 400 != 0)
                flag = false;
        }
        return flag;
    }

    public String getDate(){
        return (""+this.DAY+"/"+this.MONTH+"/"+this.YEAR);
    }

}
