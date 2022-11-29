package com.revature.project2.dao;

import com.revature.project2.Main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionUtility {
    public static Connection getConnection() throws SQLException {
        InputStream props = Main.class.getClassLoader().getResourceAsStream("database_config.properties");
        Properties properties = new Properties();
        try {
            properties.load(props);
        }catch (Exception e){
            System.out.println(e);
        }

        boolean useH2 = Boolean.parseBoolean(properties.getProperty("use-h2"));

        if(useH2){
         return DriverManager.getConnection("jdbc:h2:mem:test");
        }
        String url = "jdbc:postgresql://104.154.144.63:5432/postgres";
        String username = "postgres";
        String password = "T/Q+?XV06$)08^Rz";

        return DriverManager.getConnection(url,username,password);
    }
    public static void populateH2Database(Connection con) throws SQLException, IOException {
        String createAccountsTableSql = "create table accounts (\n" +
                "\tuid VARCHAR(200) primary key,\n" +
                "\tf_name VARCHAR(200) not null,\n" +
                "\tl_name varchar(200) not null,\n" +
                "\tstreet_address varchar(200) not null,\n" +
                "\tcity varchar(200) not null,\n" +
                "\tstate varchar(200) not null,\n" +
                "\trole varchar(20)\n" +
                ")";

        String insertAccountsSql = "insert into accounts (uid, f_name, l_name, street_address, city, state, role)\n" +
                "values \n" +
                "('PJuTs9hFfvfMNVlWmR17t3LYEVN2', 'fakeFname1', 'fakeLname1', 'fake street1', 'fake city1', 'fake state1', NULL ),\n" +
                "('O0tTRkhTJLN3Ne0rbOZOureOSD83', 'fakeFname3', 'fakeLname3', 'fake street3', 'fake city3', 'fake state3', NULL ),\n" +
                "('46CCSw43M5ZxzKRs2MHsbtl9ybw2', 'fakeFname2', 'fakeLname2', 'fake street2', 'fake city2', 'fake state2', NULL )";

        String createCatsTableSql = "create table cats (\n" +
                "\tcat_id int primary key,\n" +
                "\tcat_name VARCHAR(200) not null,\n" +
                "\tcat_breed varchar(200) not null,\n" +
                "\tcat_age int not null,\n" +
                "\tcat_color varchar(200) not null,\n" +
                "\tcat_gender varchar(200) not null,\n" +
                "\tcat_imagefile varchar(200) not null,\n" +
                "\tcat_costs int not null,\n" +
                "\tcat_purchased varchar(200)\n" +
                ")";

        String insertCatsSql = "insert into cats (cat_id, cat_name, cat_breed, cat_age, cat_color, cat_gender, cat_imagefile, cat_costs, cat_purchased)\n" +
                "values \n" +
                "(1, 'Kitty Kitty Bang Bang', 'Ragdoll', 4, 'yellow', 'Male', 'assets/img/cat13.jpg', 60, 'PJuTs9hFfvfMNVlWmR17t3LYEVN2'),\n" +
                "(2, 'Kitty Kitty Bang Bang', 'Abyssian', 5, 'yellow', 'Male', 'assets/img/cat13.jpg', 70, NULL),\n" +
                "(3, 'Kitty Kitty Bang Bang', 'Himalayan', 6, 'yellow', 'Male', 'assets/img/cat13.jpg', 80, NULL)";


        PreparedStatement ps1 = con.prepareStatement(createAccountsTableSql);
        ps1.execute();

        PreparedStatement ps2 = con.prepareStatement(insertAccountsSql);
        ps2.execute();

        PreparedStatement ps3 = con.prepareStatement(createCatsTableSql);
        ps3.execute();

        PreparedStatement ps4 = con.prepareStatement(insertCatsSql);
        ps4.execute();


    }
    public static void clearH2Database(Connection con) throws SQLException {
        String sql = "DROP ALL OBJECTS"; // H2 specific

        Statement statement = con.createStatement();
        statement.execute(sql);
    }

    public static void createAdoptionOrderH2DB(Connection con) throws SQLException, IOException{
        String createAdoptionOrdersTable = "create table adoption_orders(\n" +
                "adoption_id SERIAL primary key,\n" +
                "user_id varchar(50) not null,\n" +
                "cat_id integer unique not null,\n" +
                "adoption_date varchar(50) not null\n" +
                ");";


    }


}
