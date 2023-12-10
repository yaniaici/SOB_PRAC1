<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Database SQL Load</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f4f4f4;
                text-align: center;
                margin: 20px;
            }

            h2 {
                color: #333;
            }

            .error {
                color: red;
            }

            pre {
                color: green;
                margin: 10px;
                padding: 10px;
                background-color: #e0e0e0;
                border-radius: 5px;
            }

            button {
                background-color: #4caf50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
            }

            button:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <h2>Database SQL Load</h2>
        <%
            /* How to customize:
             * 1. Update the database name on dbname.
             * 2. Create the list of tables, under tablenames[].
             * 3. Create the list of table definition, under tables[].
             * 4. Create the data into the above table, under data[]. 
             * 
             * If there is any problem, it will exit at the very first error.
             */
            String dbname = "homework1";
            String schema = "ROOT";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            /* this will generate the database if not exist */
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + dbname, "root", "root");
            Statement stmt = con.createStatement();
            
            /* inserting data */
            String data[] = new String[]{
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'Assassins Creed Valhalla', 1, 1, 49.99, 'Explore the Viking Age', 'Action-Adventure', 'Store 8')",
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'Cyberpunk 2077', 3, 0, 59.99, 'Open-world RPG in Night City', 'Role-Playing', 'Store 9')",
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'FIFA 22', 4, 1, 39.99, 'Football simulation game', 'Sports', 'Store 10')",
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'The Legend of Zelda: Breath of the Wild', 5, 1, 54.99, 'Explore Hyrule', 'Action-Adventure', 'Store 11')",
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'The Sims 4', 1, 0, 29.99, 'Life simulation game', 'Simulation', 'Store 12')",
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'Call of Duty: Warzone', 2, 1, 0.0, 'Free-to-play battle royale', 'Shooter', 'Store 13')",
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'Halo Infinite', 3, 0, 69.99, 'Next installment in the Halo series', 'Shooter', 'Store 14')",
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'Animal Crossing: New Horizons', 4, 1, 44.99, 'Create your own paradise', 'Simulation', 'Store 15')",
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'World of Warcraft', 2, 1, 12.99, 'Explore Azeroth', 'MMORPG', 'Store 16')",
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'Spider-Man: Miles Morales', 1, 0, 49.99, 'Superhero action-adventure', 'Action', 'Store 17')",
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'Minecraft', 3, 1, 29.99, 'Build and explore block worlds', 'Adventure', 'Store 18')",
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'The Witcher 3: Wild Hunt', 4, 1, 39.99, 'Epic fantasy RPG', 'Role-Playing', 'Store 19')",
                "INSERT INTO " + schema + ".VIDEOGAME (id, gameName, console, availability, weeklyRentalPrice, description, gameType, storeAddress) VALUES (NEXT VALUE FOR VIDEOGAME_GEN, 'God of War', 3, 0, 54.99, 'Action-adventure with Kratos', 'Action', 'Store 20')",
                "INSERT INTO " + schema + ".CUSTOMER (dni, name, address, password, phoneNumber) VALUES ('12345678A', 'John Doe', '123 Main St', 'pass123', '555-1234')",
                "INSERT INTO " + schema + ".CUSTOMER (dni, name, address, password, phoneNumber) VALUES ('98765432B', 'Jane Smith', '456 Oak St', 'pass456', '555-5678')",
                "INSERT INTO " + schema + ".CUSTOMER (dni, name, address, password, phoneNumber) VALUES ('56789012C', 'Bob Johnson', '789 Pine St', 'pass789', '555-9012')",
                "INSERT INTO " + schema + ".CUSTOMER (dni, name, address, password, phoneNumber) VALUES ('34567890D', 'Alice Brown', '234 Cedar St', 'pass234', '555-3456')",
                "INSERT INTO " + schema + ".CUSTOMER (dni, name, address, password, phoneNumber) VALUES ('01234567E', 'Charlie White', '789 Elm St', 'pass789', '555-6789')",
                "INSERT INTO " + schema + ".CREDENTIALS (id, username, password) VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'sob', 'sob')"
            };        
            for (String datum : data) {
                if (stmt.executeUpdate(datum) <= 0) {
                    out.println("<span class='error'>Error inserting data: " + datum + "</span>");
                    return;
                }
                out.println("<pre> -> " + datum + "<pre>");
            }
        %>
        <button onclick="window.location='<%=request.getSession().getServletContext().getContextPath()%>'">Go home</button>
    </body>
</html>
