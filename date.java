import java.util.Scanner;

public class DateValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a date (yyyy-MM-dd):");
        String dateString = scanner.nextLine();

        String[] dateParts = dateString.split("-");
        if (dateParts.length != 3) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            return;
        }

        int year, month, day;
        try {
            year = Integer.parseInt(dateParts[0]);
            month = Integer.parseInt(dateParts[1]);
            day = Integer.parseInt(dateParts[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid date format. Please enter numeric values for year, month, and day.");
            return;
        }

        if (!isValidDate(year, month, day)) {
            System.out.println("Invalid date. Please enter a valid date.");
            return;
        }

        System.out.println("Enter the number of days to be added:");
        int daysToAdd;
        try {
            daysToAdd = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter a valid integer.");
            return;
        }

        int[] updatedDate = addDays(year, month, day, daysToAdd);
        System.out.printf("Updated Date: %04d-%02d-%02d%n", updatedDate[0], updatedDate[1], updatedDate[2]);
    }

    private static boolean isValidDate(int year, int month, int day) {
        if (month < 1 || month > 12) {
            return false;
        }

        int[] daysInMonth = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return day > 0 && day <= daysInMonth[month - 1];
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private static int[] addDays(int year, int month, int day, int daysToAdd) {
        int[] daysInMonth = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        day += daysToAdd;
        while (day > daysInMonth[month - 1]) {
            day -= daysInMonth[month - 1];
            month++;
            if (month > 12) {
                month = 1;
                year++;
                daysInMonth[1] = isLeapYear(year) ? 29 : 28;
            }
        }

        return new int[]{year, month, day};
    }
}
