package Enum;
    public enum Day {
SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
THURSDAY, FRIDAY, SATURDAY;	//can be missed
class DayInWeek{
Day d; // Using the enum Day 
DayInWeek (Day d){
this.d= d;
}

public void tellItLikeItIs() {
    switch (d) {
        case MONDAY: 
            System.out.println("Mondays are bad.");
            break;
        case FRIDAY: 
            System.out.println("Fridays are better.");
            break;
        case SATURDAY:
        case SUNDAY: 
            System.out.println	("Weekends are best.");
            break;
        default:
            System.out.println	("Midweek days are so-so.");
            break;
}
}
class UseDay {
public static void main(String[l args){ 
    DayInWeek obj= new DayInWeek(Day.MONDAY);
    obj.tellItLikeItIs();
    obj= new DayInWeek (Day.WEDNESDAY) ; 
    obj.tellItLikeItIs();
}
}
}
