public class BooleanDemo {
    public static void main(String[] args) {
        int score = 75;
        int attendance = 90;
        boolean pass = score >= 60;
        boolean goodAttendance = attendance >= 80;
        boolean passCourse = pass && goodAttendance;

        System.out.println("Score: " + score);
        System.out.println("Attendance: " + attendance);
        System.out.println("Pass: " + pass);
        System.out.println("Good attendance: " + goodAttendance);
        System.out.println("Pass course: " + passCourse);
    }
}
