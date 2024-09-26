import java.io.*;
import java.util.Scanner;

public class StudentData {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        StringBuilder data = new StringBuilder();
        String fileName = "student_data.txt";

        // Write data to a file
        FileWriter writer = new FileWriter(fileName);
        for (int i = 0; i < 1; i++) {
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

            data.append("Name: ").append(name).append("\n")
                .append("Class: ").append(studentClass).append("\n")
                .append("Roll No: ").append(rollNo).append("\n")
                .append("Section: ").append(section).append("\n")
                .append("Marks: \n");

            String[] subjects = {"Maths", "Chemistry", "Physics", "English", "Hindi", "Computer"};
            for (int j = 0; j < marksArray.length; j++) {
                data.append(subjects[j]).append(": ").append(marksArray[j]).append("\n");
            }
            data.append("\n"); // Add a newline to separate each student's data

            // Write data to file
            writer.write(data.toString());
            data.setLength(0); // Clear the StringBuilder for the next student
        }
        writer.close();

        System.out.println("\nData stored in file. Reading data...\n");

        // Read and display data from the file in tabular form
        FileReader reader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        System.out.printf("%-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s%n", "Name", "Class", "Roll No", "Section", "Maths", "Chemistry", "Physics", "English", "Hindi", "Computer", "Total", "Average");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith("Name: ")) {
                String name = line.substring(6);
                String studentClass = bufferedReader.readLine().substring(7);
                String rollNo = bufferedReader.readLine().substring(9);
                String section = bufferedReader.readLine().substring(9);
                bufferedReader.readLine(); // Skip "Marks: " line

                int[] marks = new int[6];
                for (int i = 0; i < 6; i++) {
                    marks[i] = Integer.parseInt(bufferedReader.readLine().split(": ")[1]);
                }

                int total = 0;
                for (int mark : marks) {
                    total += mark;
                }
                double average = total / 6.0;

                System.out.printf("%-15s %-10s %-10s %-10s %-10d %-10d %-10d %-10d %-10d %-10d %-10d %-10.2f%n",
                        name, studentClass, rollNo, section, marks[0], marks[1], marks[2], marks[3], marks[4], marks[5], total, average);
            }
        }
        bufferedReader.close();
    }
}
