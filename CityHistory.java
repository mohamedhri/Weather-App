import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CityHistory {
    private int historicalDataId;
    private int cityId;
    private String eventDate;
    private double temperature;

    public int getHistoricalDataId() {
        return historicalDataId;
    }

    public void setHistoricalDataId(int historicalDataId) {
        this.historicalDataId = historicalDataId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public CityHistory(int historicalDataId, int cityId, String eventDate, double temperature) {
        this.historicalDataId = historicalDataId;
        this.cityId = cityId;
        this.eventDate = eventDate;
        this.temperature = temperature;
    }

    public void addHistoricalRecord(Connection connection) throws SQLException {
        try {
            String query = "INSERT INTO historical_data (historical_data_id, city_id, event_date, temperature) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, this.historicalDataId);
                preparedStatement.setInt(2, this.cityId);
                preparedStatement.setString(3, this.eventDate);
                preparedStatement.setDouble(4, this.temperature);

                preparedStatement.executeUpdate();
                System.out.println("Historical record added successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readHistoricalRecords(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM historical_data");

            while (resultSet.next()) {
                int historicalDataId = resultSet.getInt("historical_data_id");
                int cityId = resultSet.getInt("city_id");
                String eventDate = resultSet.getString("event_date");
                double temperature = resultSet.getDouble("temperature");

                System.out.println("Historical Data ID: " + historicalDataId + "\nCity ID: " + cityId + ",\nEvent Date: " + eventDate
                        + "\nTemperature: " + temperature+"\n-----------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateHistoricalRecord(Connection connection) {
        try {
            String query = "UPDATE historical_data SET event_date = ?, temperature = ? WHERE historical_data_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, this.eventDate);
                preparedStatement.setDouble(2, this.temperature);
                preparedStatement.setInt(3, this.historicalDataId);

                preparedStatement.executeUpdate();
                System.out.println("Historical record updated success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHistoricalRecord(Connection connection) {
        try {
            String query = "DELETE FROM historical_data WHERE historical_data_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, this.historicalDataId);

                preparedStatement.executeUpdate();
                System.out.println("Historical record deleted success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
