import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class City {
    private int cityId;
    private String cityName;
    private double currentTemperature;
    private double currentHumidity;
    private double currentWindSpeed;

    public City() {}

    public City(int cityId, String cityName, double currentTemperature, double currentHumidity, double currentWindSpeed) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.currentTemperature = currentTemperature;
        this.currentHumidity = currentHumidity;
        this.currentWindSpeed = currentWindSpeed;
    }



    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public double getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(double currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public double getCurrentWindSpeed() {
        return currentWindSpeed;
    }

    public void setCurrentWindSpeed(double currentWindSpeed) {
        this.currentWindSpeed = currentWindSpeed;
    }

    public void addCityRecord(Connection connection) throws SQLException {
        try {
            String query = "INSERT INTO city (cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, this.cityId);
                preparedStatement.setString(2, this.cityName);
                preparedStatement.setDouble(3, this.currentTemperature);
                preparedStatement.setDouble(4, this.currentHumidity);
                preparedStatement.setDouble(5, this.currentWindSpeed);

                preparedStatement.executeUpdate();
                System.out.println("City record added successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readCityRecords(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM city");

            while (resultSet.next()) {
                int cityId = resultSet.getInt("cityId");
                String cityName = resultSet.getString("cityName");
                double currentTemperature = resultSet.getDouble("currentTemperature");
                double currentHumidity = resultSet.getDouble("currentHumidity");
                double currentWindSpeed = resultSet.getDouble("currentWindSpeed");

                System.out.println("---------------\nCity ID: " + cityId + "\nName: " + cityName + "\nTemperature: " + currentTemperature
                        + "\nHumidity: " + currentHumidity + "\nWind Speed: " + currentWindSpeed+"\n------------- ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCityRecord(Connection connection) {
        try {
            String query = "UPDATE city SET cityName = ?, currentTemperature = ?, currentHumidity = ?, currentWindSpeed = ? WHERE cityId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, this.cityName);
                preparedStatement.setDouble(2, this.currentTemperature);
                preparedStatement.setDouble(3, this.currentHumidity);
                preparedStatement.setDouble(4, this.currentWindSpeed);
                preparedStatement.setInt(5, this.cityId);

                preparedStatement.executeUpdate();
                System.out.println("City record updated successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCityRecord(Connection connection) {
        try {
            String query = "DELETE FROM city WHERE cityId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, this.cityId);

                preparedStatement.executeUpdate();
                System.out.println("City record deleted successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connectToDatabase() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/weather?serverTimezone=UTC",
                    "Mohammed",
                    "weather12");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish a database connection.", e);
        }
    }
}
