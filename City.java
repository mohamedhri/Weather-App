import java.sql.*;

public class City {
    int cityId;
    String cityName;
    double currentTemperature;
    double currentHumidity;
    double currentWindSpeed;
    public City (int cityId,String cityName,double currentTemperature,double currentHumidity,double currentWindSpeed) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.currentTemperature = currentTemperature;
        this.currentHumidity = currentHumidity;
        this.currentWindSpeed = currentWindSpeed;
    }
        public void enregistrementVille  (Connection connection){
        try {
            String query = "insert into city (cityId,cityName,currentTemperature,currentHumidity,currentWinSpeed)"+ "values(?,?,?,?,?)";
        try (PreparedStatement preparedStatement=connection.prepareStatement(query)){
            preparedStatement.setInt(1,this.cityId);
            preparedStatement.setString(2,this.cityName);
            preparedStatement.setDouble(3,this.currentTemperature);
            preparedStatement.setDouble(4,this.currentHumidity);
            preparedStatement.setDouble(5,this.currentWindSpeed);

            preparedStatement.executeUpdate();
            System.out.println("l'enregidtrement des villes a ete ajouter en succes");
            }
        }



    }
 static void Connection() {
            Statement s;
            try {
                Connection c = DriverManager.getConnection(
                        "jdbc:mysql://localhost/weather?serverTimezone=UTC",
                        "Mohammed",
                        "weather12"
                );
                System.out.println("OK !!");
                s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet r = s.executeQuery("SELECT `cityId`, `cityName`, currentTemperature, `currentHumidity`, `currentWindSpeed` FROM `city`");

            } catch (SQLException e) {
                throw new RuntimeException(e);

            }}}













