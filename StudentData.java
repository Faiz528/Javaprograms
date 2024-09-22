import java.util.Scanner;

public class StudentData {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder data = new StringBuilder();

        // Simulate writing data to a file
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter details for student " + (i + 1) + ":");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Class: ");
            String studentClass = scanner.nextLine();

            System.out.print("Roll No: ");
            String rollNo = scanner.nextLine();

            System.out.print("Section: ");
            String section = scanner.nextLine();

            System.out.print("Marks of 6 subjects (Maths, Chemistry, Physics, English, Hindi, Computer) separated by space: ");
            String marks = scanner.nextLine();

            // Ensure that exactly 6 marks are entered
            String[] marksArray = marks.split(" ");
            if (marksArray.length != 6) {
                System.out.println("Please enter exactly 6 marks separated by spaces.");
                i--; // Decrement i to retry this student's input
                continue;
            }

            data.append(name).append(",").append(studentClass).append(",").append(rollNo).append(",").append(section).append(",");
            for (String mark : marksArray) {
                data.append(mark).append(",");
            }
            data.setLength(data.length() - 1); // Remove the trailing comma
            data.append("\n");
        }

        System.out.println("\nData stored in memory. Reading data...\n");

        // Simulate reading data from a file
        Scanner dataScanner = new Scanner(data.toString());
        System.out.printf("%-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s%n", "Name", "Class", "Roll No", "Section", "Maths", "Chemistry", "Physics", "English", "Hindi", "Computer", "Total", "Average");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

        while (dataScanner.hasNextLine()) {
            String line = dataScanner.nextLine();
            String[] details = line.split(",");

            if (details.length == 10) {
                String name = details[0];
                String studentClass = details[1];
                String rollNo = details[2];
                String section = details[3];
                int maths = Integer.parseInt(details[4]);
                int chemistry = Integer.parseInt(details[5]);
                int physics = Integer.parseInt(details[6]);
                int english = Integer.parseInt(details[7]);
                int hindi = Integer.parseInt(details[8]);
                int computer = Integer.parseInt(details[9]);

                int total = maths + chemistry + physics + english + hindi + computer;
                double average = total / 6.0;

                System.out.printf("%-15s %-10s %-10s %-10s %-10d %-10d %-10d %-10d %-10d %-10d %-10d %-10.2f%n", name, studentClass, rollNo, section, maths, chemistry, physics, english, hindi, computer, total, average);
            } else {
                System.out.println("Invalid data format.");
            }
        }
    }
}