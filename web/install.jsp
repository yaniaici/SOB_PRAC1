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
            
            /* creating sequences */
            String sequences[] = new String[]{
                "CREATE SEQUENCE " + schema + ".VIDEOGAME_GEN START WITH 1 INCREMENT BY 1",
                "CREATE SEQUENCE " + schema + ".RENTAL_GEN START WITH 1 INCREMENT BY 1"
            };
            for (String sequence : sequences) {
                if (stmt.executeUpdate(sequence) <= 0) {
                    out.println("<span class='error'>Error creating sequence: " + sequence + "</span>");
                    return;
                }
                out.println("<pre> -> " + sequence + "<pre>");
            }
            
            /* inserting data */
            String data[] = new String[]{
                "INSERT INTO " + schema + ".CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'sob', 'sob')"
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
