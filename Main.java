import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static boolean exit = false;
    public static void main(String[] args) {
        try (Connection connection = City.connectToDatabase()) {
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {

                System.out.println("\n===== Weather App Menu =====");
                System.out.println("1. City Record");
                System.out.println("2. Historical Record");
                System.out.println("0. Exit\n-----------------------");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:

                        CityRecordOperations(connection, scanner);
                        break;

                    case 2:

                        HistoricalRecordOperations(connection, scanner);
                        break;

                    case 0:
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            System.out.println("Exiting Weather Application1");
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void CityRecordOperations(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n===== City Record Operations =====");
        System.out.println("1. Add City Record");
        System.out.println("2. Read City Records");
        System.out.println("3. Update City Record");
        System.out.println("4. Delete City Record");
        System.out.println("0. Back to Main Menu\n-----------------------");

        System.out.print("Enter your choice: ");
        int cityChoice = scanner.nextInt();
        switch (cityChoice) {
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
                System.out.print("Enter City ID to update: ");
                int updateCityId = scanner.nextInt();
                System.out.print("Enter new City Name: ");
                String updatedCityName = scanner.next();
                System.out.print("Enter new Current Temperature: ");
                double updatedTemperature = scanner.nextDouble();
                System.out.print("Enter new Current Humidity: ");
                double updatedHumidity = scanner.nextDouble();
                System.out.print("Enter new Current Wind Speed: ");
                double updatedWindSpeed = scanner.nextDouble();

                City cityToUpdate = new City(updateCityId, updatedCityName, updatedTemperature, updatedHumidity, updatedWindSpeed);
                cityToUpdate.updateCityRecord(connection);
                break;

            case 4:
                System.out.print("Enter City ID to delete: ");
                int deleteCityId = scanner.nextInt();

                City cityToDelete = new City(deleteCityId, "", 0.0, 0.0, 0.0);
                cityToDelete.deleteCityRecord(connection);
                break;
            case 0:
                exit = true;
                break;
            default:
                System.out.println("Invalid choice try again");

        }}
    private static void HistoricalRecordOperations(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n===== Historical Record Operations =====");
        System.out.println("1. Add Historical Record");
        System.out.println("2. Read Historical Records");
        System.out.println("3. Update Historical Record");
        System.out.println("4. Delete Historical Record");
        System.out.println("0. Back to Main Menu\n-----------------------");

        System.out.print("Enter your choice: ");
        int historicalChoice = scanner.nextInt();

        switch (historicalChoice) {
            case 1:
                System.out.print("Enter Historical Data ID: ");
                int historicalDataId = scanner.nextInt();
                System.out.print("Enter City ID: ");
                int historicalCityId = scanner.nextInt();
                System.out.print("Enter Event Date (YYYY-MM-DD): ");
                String eventDate = scanner.next();
                System.out.print("Enter Temperature: ");
                double historicalTemperature = scanner.nextDouble();

                CityHistory newHistoricalRecord = new CityHistory(historicalDataId, historicalCityId, eventDate, historicalTemperature);
                newHistoricalRecord.addHistoricalRecord(connection);
                break;

            case 2:
                CityHistory.readHistoricalRecords(connection);
                break;

            case 3:
                System.out.print("Enter Historical Data ID to update: ");
                int updateHistoricalDataId = scanner.nextInt();
                System.out.print("Enter new Event Date (YYYY-MM-DD): ");
                String updatedEventDate = scanner.next();
                System.out.print("Enter new Temperature: ");
                double updatedHistoricalTemperature = scanner.nextDouble();

                CityHistory historicalRecordToUpdate = new CityHistory(updateHistoricalDataId, 0, updatedEventDate, updatedHistoricalTemperature);
                historicalRecordToUpdate.updateHistoricalRecord(connection);
                break;

            case 4:
                // Supprimer les historiques
                System.out.print("Enter Historical Data ID to delete: ");
                int deleteHistoricalDataId = scanner.nextInt();

                CityHistory historicalRecordToDelete = new CityHistory(deleteHistoricalDataId, 0, "", 0.0);
                historicalRecordToDelete.deleteHistoricalRecord(connection);
                break;

            case 0:

                exit = true;
                break;

            default:
                System.out.println("Invalid choice try again");
        }

            }

}
