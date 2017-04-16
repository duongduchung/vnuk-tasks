package vn.edu.vnuk.tasks.dao;

import com.sun.xml.registry.uddi.bindings_v2_2.Contact;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import vn.edu.vnuk.tasks.jdbc.ConnectionFactory;
import vn.edu.vnuk.tasks.model.Task;

public class TaskDao {
	
    private Connection connection;

    public TaskDao(){
        this.connection = new ConnectionFactory().getConnection();
    }


    //  CREATE
    public void create(Task task) throws SQLException{

        String sqlQuery = "insert into tasks (description, achieved, date_of_achievement) "
                        +	"values (?, ?, ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, task.getDescription());
                statement.setBoolean(2, false);
                statement.setDate(3, null);

                // 	Executing statement
                statement.execute();

                System.out.println("New record in DB !");

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                System.out.println("Done !");
                connection.close();
        }

    }
    
    
    //  READ (List of Tasks)
    @SuppressWarnings("finally")
    public List<Task> read() throws SQLException {

        String sqlQuery = "select * from tasks";
        PreparedStatement statement;
        List<Task> tasks = new ArrayList<Task>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                Task task = new Task();
                task.setId(results.getLong("id"));
                task.setDescription(results.getString("description"));
                task.setAchieved(results.getBoolean("achieved"));

                Date dateOfAchievement = results.getDate("date_of_achievement");
                
                if (dateOfAchievement == null){
                    task.setDateOfAchievement(null);
                }
                
                else{
                    Calendar date = Calendar.getInstance();
                    date.setTime(dateOfAchievement);
                    task.setDateOfAchievement(date);
                }

                tasks.add(task);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return tasks;
        }


    }


    //  READ (Single Task)
    @SuppressWarnings("finally")
    public Task read(int id) throws SQLException{

        String sqlQuery = "select * from tasks where id=?";

        PreparedStatement statement;
        Task task = new Task();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                task.setId(results.getLong("id"));
                task.setDescription(results.getString("description"));
                task.setAchieved(results.getBoolean("achieved"));

                Date dateOfAchievement = results.getDate("date_of_achievement");
                
                if (dateOfAchievement == null){
                    task.setDateOfAchievement(null);
                }
                
                else{
                    Calendar date = Calendar.getInstance();
                    date.setTime(dateOfAchievement);
                    task.setDateOfAchievement(date);
                }

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return task;
        }

    }


    //  UPDATE
    public void update(Task task) throws SQLException {
        String sqlQuery = "update tasks set description=?, achieved=?," 
                            + "date_of_achievement=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, task.getDescription());
            statement.setBoolean(2, task.isAchieved());
            statement.setDate(3, new Date(task.getDateOfAchievement().getTimeInMillis()));
            statement.setLong(4, task.getId());
            statement.execute();
            statement.close();
            
            System.out.println("Task successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        finally {
            connection.close();
        }
        
    }
    
    
    //  DELETE
    public void delete(int id) throws SQLException {
        String sqlQuery = "delete from tasks where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Task successfully deleted.");

        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        finally {
            connection.close();
        }

    }
    
    
    //  OTHERS
    public void complete(int id) throws SQLException{
        Task task = this.read(id);
        System.out.println("QWERTY : " + task.getDescription());
        task.setAchieved(true);
        task.setDateOfAchievement(Calendar.getInstance());
        
        this.connection = new ConnectionFactory().getConnection();
        this.update(task);
        
    }
    
}