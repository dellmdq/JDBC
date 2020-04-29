package edu.utn;

import com.mysql.cj.xdevapi.Result;

import java.sql.*;

public class Main {

    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mail";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root1";
    //private static final String MAX_POOL = "250"; //limite de conexiones


    public static void main(String[] args) {


        try {

            Class.forName(DATABASE_DRIVER);
            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            //Ejecutar un query de manera simple
            Statement st = conn.createStatement();//el connection crea un statement para mysql

            ResultSet rs = st.executeQuery("select * from messages");//el statement va a generar un resultSet
            //rs.getMetaData(). podes obtener todos los datos de la tabla.

            while(rs.next()) {  //dentro de next va un cursor
                Integer idMessage = rs.getInt("id_message");
                Integer idFrom = rs.getInt("id_from");
                Integer idTo = rs.getInt("id_to");
                String subject = rs.getString("subject");
                String body = rs.getString("body");
                Date date = rs.getDate("message_date");

                System.out.println(String.format("%d %d %d %s %s %s", idMessage, idFrom, idTo, subject, body, date.toString()));

            }
            st.close();
            rs.close();//cerramos el cursor
            conn.close();

            //insertar un dato
            Statement stInsert = conn.createStatement();
            //int rowsAffected = stInsert.executeUpdate("insert into countries (country_name) values ('Chile')");//esta mal porque esta hardcodeado.
            //los parametros se ponen en el Statement. Los parametros los agregamos al string del query del
            //statement.
            System.out.println(String.format("Rows affected : %d",rowsAffected));


            //eliminar un dato




            System.out.println("Conectado correctamente.");

        }catch(ClassNotFoundException e){
            System.out.println("No se pudo cargar el driver");
        }
        catch(SQLException e){
            System.out.println("No se pudo conectar con la base de datos");
            System.out.println("SQL State : " + e.getSQLState());
            System.out.println("Message : " + e.getMessage() );
        }


    }
}
