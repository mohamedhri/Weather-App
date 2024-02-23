import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = City.connectToDatabase()) {
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\n===== Weather app Menu =====");
                System.out.println("1. Add City Record");
                System.out.println("2. Read City Records");
                System.out.println("3. Update City Record");
                System.out.println("4. Delete City Record");
                System.out.println("5. Add Historical Record");
                System.out.println("6. Read Historical Records");
                System.out.println("7. Update Historical Record");
                System.out.println("8. Delete Historical Record");
                System.out.println("0. Exit\n-----------------------");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter City ID: ");
                        int cityId = scanner.nextInt();
                        System.out.print("Enter City Name: ");
                        String cityName = scanner.next();
                        System.out.print("Enter Current Temperature: ");
                        double currentTemperature = scanner.nextDouble();
                        System.out.print("Enter Current Humidity: ");
                        double currentHumidity = scanner.nextDouble();
                        System.out.print("Enter Current Wind Speed: ");
                        double currentWindSpeed = scanner.nextDouble();

                        City newCity = new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed);
                        newCity.addCityRecord(connection);
                        break;

                    case 2:
                        new City().readCityRecords(connection);
                        break;

                    case 3:

                        break;

                    case 4:

                        break;

                    case 5:

                        break;

                    case 6:

                        break;

                    case 7:

                        break;

                    case 8:

                        break;

                    case 0:

                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice try again");
                }
            }

            System.out.println("You Exite the Weather application");
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
