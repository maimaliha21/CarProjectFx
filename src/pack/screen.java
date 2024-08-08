package pack;
//last edited

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class screen extends Application {
	 private JComboBox<String> tableComboBox;
	  private ObservableList<ObservableList> data;

	    private TableView tableview;
	    Connection con ;
	
    public void buildData(String Query) {
        data = FXCollections.observableArrayList();
        tableview.getItems().clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Query);

            /**
             * ************
             * TABLE COLUMN ADDED DYNAMICALLY *
             ***********
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /**
             * **********
             * Data added to ObservableList *
             ***********
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
	
	//////////////////////////////////////////////////////////////
    /////////////////////update database
    
    public void buildDataupdate(String Query) {
        data = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
            Statement stmt = con.createStatement();
            int rs1 = stmt.executeUpdate(Query);
ResultSet rs = stmt.executeQuery(Query);
            /**
             * ************
             * TABLE COLUMN ADDED DYNAMICALLY *
             ***********
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /**
             * **********
             * Data added to ObservableList *
             ***********
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

	public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
      
    	  Button openTablesButton = new Button("Open Tables ;)");
          Button exitButton = new Button("Exit");

          // Apply styles to buttons
          openTablesButton.setStyle("-fx-background-color: white; -fx-text-fill: #EC5578; -fx-border-color: #4682B4; -fx-border-width: 2.8;");
          exitButton.setStyle("-fx-background-color: white; -fx-text-fill: #EC5578; -fx-border-color: #4682B4; -fx-border-width: 2.8;");

          HBox hBox = new HBox(356, openTablesButton, exitButton); // 10 is the spacing between buttons
          hBox.setStyle("-fx-background-color: #FDB9C8;");
          hBox.setAlignment(Pos.BOTTOM_CENTER);

          Text displayText = new Text("This DataBase Project is DONE BY: MAI MALIHA\n                           ID:202109066\n\nThe DataBase contains of SEVEN Tables where I have\napplied SQL and Java code for making Operations\non tables such as:\n                              1)Search\n                              2)Update\n                              3)Insert\n                              4)Delete");
          displayText.setStyle("-fx-fill: black; -fx-font-size: 16;"); // Adjust font size and color as needed

          StackPane stackPane = new StackPane();
          stackPane.getChildren().addAll(hBox, displayText);
          stackPane.setStyle("-fx-background-color: #A94064;");
          StackPane.setAlignment(displayText, Pos.CENTER);
          StackPane.setMargin(hBox, new Insets(20, 0, 20,0 )); // Set the bottom margin
          Scene scc = new Scene(stackPane);

          stage.setScene(scc);
          stage.setTitle("DataBase Project");
          stage.setWidth(530);
          stage.setHeight(360);
          stage.show();
       
     
          exitButton.setOnAction(e -> {
             // Close the window
             stage.close();
         });
    	 
          openTablesButton.setOnAction(e -> {
    	 
        	  Button address = new Button("Address");
              Button car_part = new Button("Car Part");
              Button car = new Button("Car");
              Button customer = new Button("Customer");
              Button device = new Button("Device");
              Button manufacture = new Button("Manufacture");
              Button orders = new Button("Orders");
              Button exit = new Button("Exit");
              
              // Set preferred size for buttons
              address.setMinSize(120, 40);
              car_part.setMinSize(120, 40);
              car.setMinSize(120, 40);
              customer.setMinSize(120, 40);
              device.setMinSize(120, 40);
              manufacture.setMinSize(120, 40);
              orders.setMinSize(120, 40);
              exit.setMinSize(120, 40);
              
              // Set button styles and spacing
              String buttonStyle = "-fx-background-color: #A94064; -fx-text-fill: white;";
              address.setStyle(buttonStyle);
              car_part.setStyle(buttonStyle);
              car.setStyle(buttonStyle);
              customer.setStyle(buttonStyle);
              device.setStyle(buttonStyle);
              manufacture.setStyle(buttonStyle);
              orders.setStyle(buttonStyle);
              exit.setStyle(buttonStyle);
              
              // Set spacing around buttons
              VBox.setMargin(address, new Insets(5, 5, 5, 5));
              VBox.setMargin(car_part, new Insets(5, 5, 5, 5));
              VBox.setMargin(car, new Insets(5, 5, 5, 5));
              VBox.setMargin(customer, new Insets(5, 5, 5, 5));
              VBox.setMargin(device, new Insets(5, 5, 5, 60));
              VBox.setMargin(manufacture, new Insets(5, 5, 5, 60));
              VBox.setMargin(orders, new Insets(5, 5, 5, 60));
              VBox.setMargin(exit, new Insets(5, 5, 5, 60));
              
              VBox box1 = new VBox(address, car_part, car, customer);
              VBox box2 = new VBox(device, manufacture, orders, exit);

              box1.setTranslateZ(10);
              box2.setTranslateZ(10);

              HBox hb = new HBox(box1, box2);
              hb.setPadding(new Insets(20, 10, 0, 120));

              Scene sc = new Scene(hb);
              sc.setFill(Color.GRAY);
              stage.setScene(sc);
              stage.setTitle("DataBase Project");
              stage.setWidth(600);
              stage.setHeight(300);
              stage.show();

        exit.setOnAction(o -> {
            // Close the window
        	stage.setScene(scc);
        	 stage.setWidth(530);
             stage.setHeight(360); // Set the initial height of the stage
    	    stage.show();
        });
        
        
        address.setOnAction(o ->{
        	

        	
        	tableview = new TableView();
        	   tableview.setStyle("-fx-control-inner-background: #E6E6FA; ");
            buildData("select * from address");
            
            Label ID_Label = new Label("ID :");  
            TextField textField_ID = new TextField();

           Label Building_Label = new Label("Building :");  
           TextField textField_Building = new TextField();
           
            Label Street_Label = new Label("Street :");  
            TextField textField_Street = new TextField();
            
            Label City_Label = new Label("City :");  
            TextField textField_City = new TextField();
            
             Label Country_Label = new Label("Country :");  
             TextField textField_Country = new TextField();
           

             Button back = new Button("Back");
             Button search = new Button("search");
             Button delete = new Button("delete");
             Button update = new Button("update");
             Button insert = new Button("insert");
             ComboBox<String> id=new ComboBox<>();
           primaryComboBox(id,"address","id");
             
             
             HBox h = new HBox(5);
            
  	       h.getChildren().addAll( delete,update,insert,search);
             
             
             VBox form = new VBox(5);
             HBox h2 = new HBox(5);
             h2.getChildren().addAll( ID_Label,id);
             form.setStyle("-fx-padding: 20; -fx-background-color: #FDB9C8;"); // add padding to the VBox
             form.getChildren().addAll( back,h2,textField_ID , City_Label , textField_City , Country_Label, textField_Country ,Street_Label, textField_Street,Building_Label, textField_Building,h);
             form.getChildren().addAll(tableview);
        	Scene address_scene = new Scene(form);
        	
        	 stage.setScene(address_scene);
        	    stage.setWidth(600); // Set the initial width of the stage
        	    stage.setHeight(600); // Set the initial height of the stage
        	    stage.show();
        	
        		back.setOnAction(k->{

        			 stage.setScene(sc);
                     stage.setTitle("DataBase Project");
                     stage.setWidth(600);
                     stage.setHeight(300);
                     stage.show();
        	  	});
        	  	
        	  	
        	  	
        	  	search.setOnAction(k -> {
        		    tableview.getColumns().clear();
        		    id.setVisible(false);
              		
             		 textField_ID.setVisible(true);
             		
             		 
           		 Building_Label.setVisible(true);
                    textField_Building.setVisible(true);
                    
                    Country_Label.setVisible(true);
         	   		 textField_Country.setVisible(true);
         	   		 
         	   	     City_Label.setVisible(true);
         	   		 textField_City.setVisible(true);

         	   	 Street_Label.setVisible(true);
     	   		 textField_Street.setVisible(true);
             		primaryComboBox(id,"address","id");

        		    String sql = "SELECT * FROM address WHERE";
        		    
        		    if (textField_ID.getText().isEmpty() && textField_Building.getText().isEmpty()  && textField_Street.getText().isEmpty()   && textField_City.getText().isEmpty()    && textField_Country.getText().isEmpty()) {
        		        buildData("SELECT * FROM address");
        		    } else {
        		        if (!textField_ID.getText().isEmpty()) {
        		            sql += " id = '" + textField_ID.getText() + "'";
        		        }
        		        if (!textField_Building.getText().isEmpty()) {
        		            if (sql.length() > 28)
        		            	sql += " and buidling = '" + textField_Building.getText() + "'";
        		             else      sql += " buidling = '" + textField_Building.getText() + "'";
        		            
        		        }
        		        
        		        
        		        if (!textField_Street.getText().isEmpty()) {
        		            if (sql.length() > 28)
        		            	sql += " and street = '" + textField_Street.getText() + "'";
        		             else      sql += " street = '" + textField_Street.getText() + "'";
        		            
        		        }
        		        
        		        if (!textField_City.getText().isEmpty()) {
        		            if (sql.length() > 28)
        		            	sql += " and city = '" + textField_City.getText() + "'";
        		             else      sql += " city = '" + textField_City.getText() + "'";
        		            
        		        }
        		        
        		        if (!textField_Country.getText().isEmpty()) {
        		            if (sql.length() > 28)
        		            	sql += " and country = '" + textField_Country.getText() + "'";
        		             else      sql += " country = '" + textField_Country.getText() + "'";
        		            
        		        }

        		        buildData(sql);
        		    }
        		});
        	  	
        	  	
        	  	insert.setOnAction(l -> {
           		 id.setVisible(false);
                            textField_ID.setVisible(true);
                            Building_Label.setVisible(true);
                            textField_Building.setVisible(true);
                            Country_Label.setVisible(true);
                	         textField_Country.setVisible(true); 
                	   	 City_Label.setVisible(true);
                	         textField_City.setVisible(true);                                                      Street_Label.setVisible(true);
            	         textField_Street.setVisible(true);
           	  		String s = "INSERT INTO address (id, buidling, street, city, country) VALUES(?,?,?,?,?)";
       	 		
             		
             		insert.setOnAction(m -> {
       	 		try { 
       	 			if(textField_ID.getText().isEmpty() ||  textField_Building.getText().isEmpty()||textField_Country.getText().isEmpty()||textField_City.getText().isEmpty() ||textField_Street.getText().isEmpty()) {
                       JOptionPane.showMessageDialog(null, "No such INPUT found for Insert");
                       tableview.getColumns().clear();
                       id.setVisible(false);
                            textField_ID.setVisible(true);
                            Building_Label.setVisible(true);
                            textField_Building.setVisible(true);
                            Country_Label.setVisible(true);
                	         textField_Country.setVisible(true); 
                	   	 City_Label.setVisible(true);
                	         textField_City.setVisible(true);                                                      Street_Label.setVisible(true);
            	         textField_Street.setVisible(true);
            	         
           	    }
       	 		else {   tableview.getColumns().clear();
       	 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
       	 		    PreparedStatement gb = con.prepareStatement(s);
try {

       	 		    gb.setString(3, textField_Street.getText());   
       	 		    gb.setInt(1,  Integer.parseInt(textField_ID.getText()));
                               gb.setInt(2,  Integer.parseInt(textField_Building.getText()));
       	 		    gb.setString(4, textField_City.getText());
       	 		    gb.setString(5,  textField_Country.getText());
       	 		    gb.executeUpdate();}
       	 		 catch(Exception z) { 
       	 		JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id duplicate or is not a number ");
       	 		}
       	 		}
       	 		buildData("SELECT * FROM address");
       	 		} catch (SQLException e1) {
       	 		    e1.printStackTrace();
       	 		}

       		   } );  } );
        	  	update.setOnAction(up -> {
        	  		 
        	  		id.setVisible(true);
              		 textField_ID.setVisible(false);
            		 Building_Label.setVisible(true);
                     textField_Building.setVisible(true);
                     Country_Label.setVisible(true);
          	   		 textField_Country.setVisible(true);
          	   	     City_Label.setVisible(true);
          	   		 textField_City.setVisible(true);
          	   	     Street_Label.setVisible(true);
      	   		     textField_Street.setVisible(true);
      	   		 
              		
                  
                    update.setOnAction(r->{
                    	
              	    String s = "UPDATE address SET buidling=?, street=?, city=?, country=? WHERE id=?";
              	   
              	    try {if(id.getValue() == null ||  textField_Building.getText().isEmpty()||textField_Country.getText().isEmpty()||textField_City.getText().isEmpty() ||textField_Street.getText().isEmpty()) {
              	    	
                            JOptionPane.showMessageDialog(null, "No such INFO found for update");
                            id.setVisible(true);
                      	    textField_ID.setVisible(false);
                      	    Building_Label.setVisible(true);
                            textField_Building.setVisible(true);
                            Country_Label.setVisible(true);
                 	   		textField_Country.setVisible(true);
                 	   		City_Label.setVisible(true);
                 	   		textField_City.setVisible(true);
                 	   		Street_Label.setVisible(true);
             	   		    textField_Street.setVisible(true);
              	          }
              	    else {
              	  	    tableview.getColumns().clear();
              	  	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
              	        PreparedStatement gb = con.prepareStatement(s);
try {
              	        gb.setString(5, id.getValue());
              	        gb.setString(2, textField_Street.getText());
              	        gb.setString(3, textField_City.getText());
              	        gb.setString(4, textField_Country.getText());
              	        gb.setInt(1, Integer.parseInt((textField_Building.getText())));
}catch(Exception f){
	JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id duplicate or is not a number ");
}
              	        int rowsUpdated = gb.executeUpdate();
              	        int updateCount = gb.getUpdateCount();

              	        if (updateCount >=1) {System.out.println("update successfully");     
                        JOptionPane.showMessageDialog(null, "Record updated successfully");
                        gb.executeUpdate();
                        buildData("SELECT * FROM address ");
                     }  
              	        else {
                              System.err.println("No such ID found for update");            	        
                        JOptionPane.showMessageDialog(null, "No such ID found for update");
                        gb.executeUpdate();
                        buildData("SELECT * FROM address ");
                 }   }   
              	    }         	 	   
                        catch (SQLException e1) {
                        	   buildData("SELECT * FROM address ");
              	        
              	    }    
              	});
               	});
        	  	delete.setOnAction(c->{
        	
             		 textField_ID.setVisible(true);
      	   		 Building_Label.setVisible(false);
                 textField_Building.setVisible(false);
                 
                 Country_Label.setVisible(false);
      	   		 textField_Country.setVisible(false);
      	   		 
      	   	     City_Label.setVisible(false);
      	   		 textField_City.setVisible(false);

      	   	 Street_Label.setVisible(false);
  	   		 textField_Street.setVisible(false);
  	   		 id.setVisible(false);

           String deleteQuery = "DELETE FROM " + "customer" + " WHERE " + "address" + " = ? ";
        	    String deleteQuery1 = "DELETE FROM " + "address" + " WHERE " + "id" + " = ? ";
        	   

      	  	delete.setOnAction(m->{
      	 	 textField_ID.setVisible(true);
  	   		 Building_Label.setVisible(false);
             textField_Building.setVisible(false);
             
             Country_Label.setVisible(false);
  	   		 textField_Country.setVisible(false);
  	   		 
  	   	     City_Label.setVisible(false);
  	   		 textField_City.setVisible(false);

  	   	 Street_Label.setVisible(false);
	   		 textField_Street.setVisible(false);
	   		 id.setVisible(false);
        	    try {
        	    	if(textField_ID.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No such ID found for delete");
        	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");}
        	    
        	    	else {
              	  	 
        	    		 tableview.getColumns().clear();
        	        PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
        	        PreparedStatement deleteStatement1 = con.prepareStatement(deleteQuery1);

        	      deleteStatement.setInt(1,Integer.parseInt(textField_ID.getText( )) );
        	        deleteStatement1.setInt(1,Integer.parseInt(textField_ID.getText( )) );

        	        int rowsAffected = deleteStatement.executeUpdate();
        	        int rowsAffected1 = deleteStatement1.executeUpdate();

        	        buildData("SELECT * FROM address");}
        	    
        	    } catch (Exception e1) {
        	       	JOptionPane.showMessageDialog(null, "Record delete unsuccessfully id is not a number ");
        	       	buildData("SELECT * FROM address");
        	    }
      		});	});
        	  	
        	  });
	
        car_part.setOnAction(k ->{
        	
        	tableview = new TableView();
        	 tableview.setStyle("-fx-control-inner-background: #E6E6FA;");
            buildData("select * from Car_part");
            
            Label car_Label = new Label("Car :");  
         
  
         Label part_Label = new Label("Part :");  
       
             Button back = new Button("Back");
             Button search = new Button("search");

             
             Button update = new Button("update");
            
             ComboBox<String> carr=new ComboBox<>();     
             ComboBox<String> part=new ComboBox<>();
             HBox h = new HBox(5);
  	       h.getChildren().addAll( update);
             VBox form = new VBox(5);
             form.setStyle("-fx-padding: 20; -fx-background-color: #FDB9C8;"); // add padding to the VBox
           form.getChildren().addAll(back,car_Label ,carr , part_Label,part,h);
             form.getChildren().addAll(tableview);
        	Scene Car_Part_scene = new Scene(form);
        	 primaryComboBox(carr,"car","name");
             primaryComboBox(part,"device","no");
        	 stage.setScene(Car_Part_scene);
        	    stage.setWidth(600); // Set the initial width of the stage
        	    stage.setHeight(600); // Set the initial height of the stage
        	    stage.show();
        	
        	back.setOnAction(o->{
        	 stage.setScene(sc);
                 stage.setTitle("DataBase Project");
                 stage.setWidth(600);
                 stage.setHeight(300);
                 stage.show();
        	});
	
        	update.setOnAction(up -> {
        		carr.setVisible(true);
       		 part.setVisible(true);
       		
       		
update.setOnAction(f->{
           	    String s = "UPDATE car_part SET part=? WHERE car=?";
           	    try {if(carr.getValue() == null || part.getValue() == null) {
           	     tableview.getColumns().clear();
           	    	JOptionPane.showMessageDialog(null, "No such ID found for update");
                	carr.setVisible(true);
              		 part.setVisible(true);
              		 

           	    }
           	    else {  tableview.getColumns().clear();
           	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
           	        PreparedStatement gb = con.prepareStatement(s);

           	     
           	        gb.setString(2, carr.getValue());
           	   
           	        gb.setInt(1, Integer.parseInt(part.getValue()));

           	        int rowsUpdated = gb.executeUpdate();
           	        int updateCount = gb.getUpdateCount();

           	        if (updateCount >=1) {System.out.println("update successfully");     
                       JOptionPane.showMessageDialog(null, "Record updated successfully");

           	        }  
           	        else { tableview.getColumns().clear();
                                 System.err.println("No such Car found for update");            	        
                       JOptionPane.showMessageDialog(null, "No such Car found for update");
}
           	    
           	    }       buildData("SELECT * FROM car_part ");
           	    }
                      catch (SQLException e1) {
           	        e1.printStackTrace();
           	    }    
});
           	});
           	});
    
  car.setOnAction(k ->{
	  
        	tableview = new TableView();
        	tableview.setStyle("-fx-control-inner-background: #E6E6FA;");
            buildData("select * from Car");
            
          Label name_Label = new Label("name :");
          TextField textField_Name = new TextField();
          Label model_Label = new Label("model :");
          TextField textField_Model = new TextField();
          Label year_Label =  new Label("year :");
          TextField textField_Year = new TextField();
          Label made_Label = new Label("made :");
          TextField textField_Made = new TextField();
            
          ComboBox<String> name=new ComboBox<>();     
          ComboBox<String> made=new ComboBox<>();
            
             Button back = new Button("Back");
             Button search = new Button("search");
             Button delete = new Button("delete");
             Button update = new Button("update");
             Button insert = new Button("insert");
             HBox h = new HBox(5);
             HBox h2 = new HBox(5);
             HBox h3 = new HBox(5);
  	       h.getChildren().addAll( delete,update,insert,search);
  	       h2.getChildren().addAll(name_Label, name);
  	       h3.getChildren().addAll(made_Label,made );
            
  	       VBox form = new VBox(5);
           form.setStyle("-fx-padding: 20; -fx-background-color: #FDB9C8;"); // add padding to the VBox
           form.getChildren().addAll(back,h2, textField_Name, model_Label, textField_Model, year_Label, textField_Year,h3,textField_Made,h);
           form.getChildren().addAll(tableview);
           
        	Scene  car_scene = new Scene(form);
        	primaryComboBox(made,"manufacture","name");
        	primaryComboBox(name,"car","name");
        	stage.setScene(car_scene);
        	    stage.setWidth(600); // Set the initial width of the stage
        	    stage.setHeight(600); // Set the initial height of the stage
        	    stage.show();
        	
        	back.setOnAction(o->{
        		 stage.setScene(sc);
                 stage.setTitle("DataBase Project");
                 stage.setWidth(600);
                 stage.setHeight(300);
                 stage.show();
        	});
        	
        	search.setOnAction(o -> {
        		name.setVisible(true);
          		made.setVisible(false);
          		textField_Made.setVisible(true);
          		textField_Model.setVisible(true);
          		textField_Name.setVisible(false);
          		textField_Year.setVisible(true);
        	 
        	    
        		made_Label.setVisible(true);
        		year_Label.setVisible(true);
        		model_Label.setVisible(true);
        		name_Label.setVisible(true);
        		name.setVisible(true);
        	          tableview.getColumns().clear();
        	          buildData("SELECT * FROM car"); 
        	      	search.setOnAction(n -> {  tableview.getColumns().clear();
        	      		
        	      		name.setVisible(true);
                  		made.setVisible(false);
                  		textField_Made.setVisible(true);
                  		textField_Model.setVisible(true);
                  		textField_Name.setVisible(false);
                  		textField_Year.setVisible(true);
                	 
                	    
                		made_Label.setVisible(true);
                		year_Label.setVisible(true);
                		model_Label.setVisible(true);
                		name_Label.setVisible(true);
        	      		name.setVisible(true);
        	      		
        	      		String sql = "SELECT * FROM car WHERE";
        	      		if (name.getValue() == null && textField_Model.getText().isEmpty()  && textField_Year.getText().isEmpty()   && textField_Made.getText().isEmpty()   ) {
        	      		 
        	      			buildData("SELECT * FROM car");
            		    } else {
        	        if (name.getValue() != null) {
        	            sql += " name = '" + name.getValue() + "'";
        	        }
        	        if (!textField_Model.getText().isEmpty()) {
        	            if (sql.length() > 24)
        	            	sql += " and model = '" + textField_Model.getText() + "'";
        	             else      sql += " model = '" + textField_Model.getText() + "'";
        	            
        	        }
        	        
        	        
        	        if (!textField_Year.getText().isEmpty()) {
        	            if (sql.length() > 24)
        	            	sql += " and year = '" + textField_Year.getText() + "'";
        	             else      sql += " year = '" + textField_Year.getText() + "'";
        	            
        	        }
        	        
        	        if (!textField_Made.getText().isEmpty()) {
        	            if (sql.length() > 24)
        	            	sql += " and made = '" + textField_Made.getText() + "'";
        	             else      sql += " made = '" + textField_Made.getText() + "'";
        	            
        	            
        	    }
        	        System.out.println(sql);
        	        tableview.getColumns().clear();
        	    buildData(sql);}
        	    
        	       	});   	});
        	
        	
        	insert.setOnAction(o -> {
        		
        		name.setVisible(false);
          		made.setVisible(true);
          		textField_Made.setVisible(false);
          		textField_Model.setVisible(true);
          		textField_Name.setVisible(true);
          		textField_Year.setVisible(true);
        		made_Label.setVisible(true);
        		year_Label.setVisible(true);
        		model_Label.setVisible(true);
        		name_Label.setVisible(true);
    	 		String s = "INSERT INTO car (name, model, year, made) VALUES(?,?,?,?)";
    	 		
          		insert.setOnAction(m -> {  

          				name.setVisible(false);
                  		made.setVisible(true);
                  		textField_Made.setVisible(false);
                  		textField_Model.setVisible(true);
                  		textField_Name.setVisible(true);
                  		textField_Year.setVisible(true);
                		made_Label.setVisible(true);
                		year_Label.setVisible(true);
                		model_Label.setVisible(true);
                		name_Label.setVisible(true);
          			
    	 		try { 
    	 			if(textField_Name.getText().isEmpty() || textField_Model.getText().isEmpty()||textField_Year.getText().isEmpty()||made.getValue().isEmpty()) {
                       JOptionPane.showMessageDialog(null, "No such ID found for Insert");
                       tableview.getColumns().clear();
                       buildData("SELECT * FROM car");
                   
        	    }
    	 		else {  tableview.getColumns().clear();
    	 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
    	 		    PreparedStatement gb = con.prepareStatement(s);
try {

    	 		    gb.setString(1,textField_Name.getText());
    	 		    gb.setString(2,textField_Model.getText());
    	 		    gb.setInt(3,  Integer.parseInt(textField_Year.getText()));
    	 		   gb.setString(4,made.getValue());
    	 		
    	 		    gb.executeUpdate();
    		        primaryComboBox(name,"car","name");
    	 		} catch(Exception w) {
    		        	JOptionPane.showMessageDialog(null, "Record insert unsuccessfully enter a valid type of data ");
    		        }    		        buildData("SELECT * FROM car");

    	 		}} catch (SQLException e1) {
    	 		    e1.printStackTrace();
    	 		}

    		   } );  } );
        	
        	
        	update.setOnAction(up -> {
       		 
       		 name.setVisible(true);
       		 made.setVisible(true);
       		 textField_Made.setVisible(false);
       		 textField_Name.setVisible(false);
       		 textField_Model.setVisible(true);
       		 textField_Year.setVisible(true);
       		made_Label.setVisible(true);
    		year_Label.setVisible(true);
    		model_Label.setVisible(true);
    		name_Label.setVisible(true);

            update.setOnAction(r->{
       	    String s = "UPDATE car SET made=?, model=?, year=? WHERE name=?";
       	    try {if(name.getValue() == null || textField_Model.getText().isEmpty()||textField_Year.getText().isEmpty()||made.getValue()== null) {
       	    	JOptionPane.showMessageDialog(null, "No such ID found for update");
                tableview.getColumns().clear();
                name.setVisible(true);
          		 made.setVisible(true);
          		 textField_Made.setVisible(false);
          		 textField_Name.setVisible(false);
          		 textField_Model.setVisible(true);
          		 textField_Year.setVisible(true);
          		made_Label.setVisible(true);
        		year_Label.setVisible(true);
        		model_Label.setVisible(true);
        		name_Label.setVisible(true);
        		 }
       	    else {
       	     tableview.getColumns().clear();
       	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
       	        PreparedStatement gb = con.prepareStatement(s);
try {
       	        gb.setString(1, made.getValue());
       	        gb.setString(2, textField_Model.getText());
       	        gb.setInt(3, Integer.parseInt(textField_Year.getText()));
       	        gb.setString(4, name.getValue());
}catch(Exception p) {
	JOptionPane.showMessageDialog(null, "Record update unsuccessfully enter a valid type of data ");
}
       	        int rowsUpdated = gb.executeUpdate();
       	        int updateCount = gb.getUpdateCount();

       	        if (updateCount >=1) {System.out.println("update successfully");     
                   JOptionPane.showMessageDialog(null, "Record updated successfully");

       	        } 
       	    
       	        else {
                             System.err.println("No such ID found for update");            	        
                   JOptionPane.showMessageDialog(null, "No such ID found for update");
}}
       	    
       	    }         	 	   
                  catch (SQLException e1) {
       	        e1.printStackTrace();
       	        
       	    }    buildData("SELECT * FROM car ");
       	});
        	});
        	
        	
        	delete.setOnAction(c->{
        		   name.setVisible(true);
            		 made.setVisible(false);
            		 textField_Made.setVisible(false);
            		 textField_Name.setVisible(false);
            		 textField_Model.setVisible(false);
            		 textField_Year.setVisible(false);
            			made_Label.setVisible(false);
                		year_Label.setVisible(false);
                		model_Label.setVisible(false);
                		name_Label.setVisible(true);
      	    String deleteQuery = "DELETE FROM " + "car" + " WHERE " + "name" + " = ? ";
     	    String deleteQuery1 = "DELETE FROM " + "orders" + " WHERE " + "car" + " = ? ";
     	    String deleteQuery2 = "DELETE FROM " + "car_part" + " WHERE " + "car" + " = ? ";

   	  	delete.setOnAction(m->{
     	    try { if(name.getValue() == null ) {
     	    	JOptionPane.showMessageDialog(null, "fill all the fields");
     	    	 name.setVisible(true);
        		 made.setVisible(false);
        		 textField_Made.setVisible(false);
        		 textField_Name.setVisible(false);
        		 textField_Model.setVisible(false);
        		 textField_Year.setVisible(false);
    		made_Label.setVisible(false);
    		year_Label.setVisible(false);
    		model_Label.setVisible(false);
    		name_Label.setVisible(true);
     	    	tableview.getColumns().clear();
     	    	
     	    }else {	tableview.getColumns().clear();
     	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
     	        PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
     	        PreparedStatement deleteStatement1 = con.prepareStatement(deleteQuery1);
     	        PreparedStatement deleteStatement2 = con.prepareStatement(deleteQuery2);

     	        deleteStatement.setString(1,name.getValue() );
     	       deleteStatement1.setString(1,name.getValue() );
     	      deleteStatement2.setString(1,name.getValue() );
     	   
     	        int rowsAffected = deleteStatement2.executeUpdate();
     	        int rowsAffected1 = deleteStatement1.executeUpdate();
     	        int rowsAffected2 = deleteStatement.executeUpdate();
     	    }

     	        buildData("SELECT * FROM car");
     	    } catch (SQLException e1) {
     	        e1.printStackTrace();
     	    }
   		});	});
        	
        });
  customer.setOnAction(k ->{
  	
  	tableview = new TableView();
  	 tableview.setStyle("-fx-control-inner-background: #E6E6FA;");
      buildData("select * from Customer");
      
      Label id_Label = new Label("ID:");
    TextField textField_id = new TextField();

    Label FName_Label = new Label("First Name:");
    TextField textField_fname = new TextField();

    Label LName_Label = new Label("Last Name:");
    TextField textField_lname = new TextField();

    Label Address_Label = new Label("Address:");
    TextField textField_address = new TextField();

    Label Job_Label = new Label("Job:");
    TextField textField_job = new TextField();
      
      
       Button back = new Button("Back");
       Button search = new Button("search");
       Button delete = new Button("delete");
       Button update = new Button("update");
       Button insert = new Button("insert");
       
       ComboBox<String> idd=new ComboBox<>();     
       ComboBox<String> addresss=new ComboBox<>();
       
       HBox h = new HBox(5);
       h.getChildren().addAll( delete,update,insert,search);
       VBox form = new VBox(5);
       form.setStyle("-fx-padding: 20; -fx-background-color: #FDB9C8;"); // add padding to the VBox
       form.getChildren().addAll(back,id_Label, idd,textField_id, FName_Label, textField_fname, LName_Label, textField_lname,
             Address_Label, textField_address,addresss, Job_Label, textField_job,h);      
       form.getChildren().addAll(tableview);
  	Scene  customer_scene = new Scene(form);
  	primaryComboBox(addresss,"address","id");
  	primaryComboBox(idd,"customer","id");
  	 stage.setScene(customer_scene);
  	    stage.setWidth(600); // Set the initial width of the stage
  	    stage.setHeight(600); // Set the initial height of the stage
  	    stage.show();
  	
  	back.setOnAction(o->{
  		 stage.setScene(sc);
         stage.setTitle("DataBase Project");
         stage.setWidth(600);
         stage.setHeight(300);
         stage.show();
  	});
 
  	search.setOnAction(o -> {
	    tableview.getColumns().clear();
	    String sql = "SELECT * FROM customer WHERE";
	    Address_Label.setVisible(true);
		FName_Label.setVisible(true);
		LName_Label.setVisible(true);
	Job_Label.setVisible(true);
	textField_id.setVisible(true);
	textField_address.setVisible(true);
	textField_fname.setVisible(true);
	textField_lname.setVisible(true);
	textField_job.setVisible(true);
	idd.setVisible(false);
	addresss.setVisible(false);

	    if (textField_id.getText().isEmpty() && textField_fname.getText().isEmpty()  && textField_lname.getText().isEmpty()   && textField_address.getText().isEmpty()    && textField_job.getText().isEmpty()) {
	        buildData("SELECT * FROM customer");
	    } else {
	        if (!textField_id.getText().isEmpty()) {
	            sql += " id = '" + textField_id.getText() + "'";
	        }
	        if (!textField_fname.getText().isEmpty()) {
	            if (sql.length() > 29)
	            	sql += " and f_name = '" + textField_fname.getText() + "'";
	             else      sql += " f_name = '" + textField_fname.getText() + "'";
	            
	        }
	        
	        
	        if (!textField_lname.getText().isEmpty()) {
	            if (sql.length() > 29)
	            	sql += " and l_name = '" + textField_lname.getText() + "'";
	             else      sql += " l_name = '" + textField_lname.getText() + "'";
	            
	        }
	        
	        if (!textField_address.getText().isEmpty()) {
	            if (sql.length() > 29)
	            	sql += " and address = '" + textField_address.getText() + "'";
	             else      sql += " address = '" + textField_address.getText() + "'";
	            
	        }
	        
	        if (!textField_job.getText().isEmpty()) {
	            if (sql.length() > 29)
	            	sql += " and job = '" + textField_job.getText() + "'";
	             else      sql += " job = '" + textField_job.getText() + "'";
	            
	        }

	        buildData(sql);
	    }
	});
  	
  	insert.setOnAction(o -> {
 		String s = "INSERT INTO customer (id, f_name, l_name, address, job) VALUES(?,?,?,?,?)";
 		addresss.setVisible(true);
		textField_address.setVisible(false);
		 Address_Label.setVisible(true);
			FName_Label.setVisible(true);
			LName_Label.setVisible(true);
		Job_Label.setVisible(true);
		primaryComboBox(idd,"customer","id");
		idd.setVisible(false);
		textField_id.setVisible(true);
		textField_fname.setVisible(true);
		textField_lname.setVisible(true);
		textField_job.setVisible(true);
		insert.setOnAction(m -> {addresss.setVisible(true);
		textField_address.setVisible(false);
		 Address_Label.setVisible(true);
			FName_Label.setVisible(true);
			LName_Label.setVisible(true);
		Job_Label.setVisible(true);
		idd.setVisible(false);
		textField_id.setVisible(true);
		textField_fname.setVisible(true);
		textField_lname.setVisible(true);
		textField_job.setVisible(true);
			if(textField_id.getText().isEmpty() ) {
      	    	        JOptionPane.showMessageDialog(null, "No such ID found for update");}
      	    	        else {
 		try {
 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
 		    PreparedStatement gb = con.prepareStatement(s);
try {
 		    gb.setInt(1, Integer.parseInt(textField_id.getText()));
 		    gb.setString(2, textField_fname.getText());
 		    gb.setString(3, textField_lname.getText());
 		    gb.setInt(4,Integer.parseInt( addresss.getValue()));
 		    gb.setString(5, textField_job.getText());
 		    gb.executeUpdate();
}catch(Exception r) {
	JOptionPane.showMessageDialog(null, "Record insert unsuccessfully enter a valid type of data ");
}
 		   tableview.getColumns().clear();
	        buildData("SELECT * FROM customer");
 		} catch (SQLException e1) {
 			JOptionPane.showMessageDialog(null, "Record insert unsuccessfully enter a valid type of data ");
 		    e1.printStackTrace();
 		}}

		 } ); } );
	update.setOnAction(up -> {
		addresss.setVisible(true);
		textField_address.setVisible(false);
		 Address_Label.setVisible(true);
			FName_Label.setVisible(true);
			LName_Label.setVisible(true);
		Job_Label.setVisible(true);
		idd.setVisible(true);
		textField_id.setVisible(false);
		textField_fname.setVisible(true);
		textField_lname.setVisible(true);
		textField_job.setVisible(true);
    update.setOnAction(r->{
    	addresss.setVisible(true);
		textField_address.setVisible(false);
		 Address_Label.setVisible(true);
			FName_Label.setVisible(true);
			LName_Label.setVisible(true);
		Job_Label.setVisible(true);
		idd.setVisible(true);
		textField_id.setVisible(false);
		textField_fname.setVisible(true);
		textField_lname.setVisible(true);
		textField_job.setVisible(true);
    	if(idd.getValue() == null ) {
  	        JOptionPane.showMessageDialog(null, "No such ID found for update");}
  	        else {

	    String s = "UPDATE customer SET f_name=?, l_name=?, address=? , job=? WHERE id=?";
	    try {
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
	        PreparedStatement gb = con.prepareStatement(s);

	        gb.setInt(5, Integer.parseInt(idd.getValue()));

	        gb.setString(1, textField_fname.getText());
	        gb.setString(2, textField_lname.getText());
	        gb.setInt(3, Integer.parseInt(addresss.getValue()));
	        gb.setString(4, textField_job.getText());
	        int rowsUpdated = gb.executeUpdate();
	        int updateCount = gb.getUpdateCount();

	        if (updateCount >=1) {System.out.println("update successfully");     
           JOptionPane.showMessageDialog(null, "Record updated successfully");
	      
	        }  
	        else {
                     System.err.println("No such ID found for update");            	        
           JOptionPane.showMessageDialog(null, "No such ID found for update");
}
	      
	    }         	 	   
          catch (SQLException e1) {
	        e1.printStackTrace();} 
	        tableview.getColumns().clear();
	       buildData("SELECT * FROM customer ");}
	});
	});

	  delete.setOnAction(c->{
	  		
			idd.setVisible(true);
			
			textField_id.setVisible(false);
			addresss.setVisible(false);
			textField_address.setVisible(false);
			textField_fname.setVisible(false);
			textField_lname.setVisible(false);
			textField_job.setVisible(false);
			Address_Label.setVisible(false);
			FName_Label.setVisible(false);
			LName_Label.setVisible(false);
		Job_Label.setVisible(false);

		
    	    String deleteQuery = "DELETE FROM  address WHERE id = ? ";
   	    String deleteQuery1 = "DELETE FROM  orders WHERE customer = ? ";
   	 String deleteQuery2 = "DELETE FROM  customer WHERE id = ? ";
 	  	delete.setOnAction(m->{
 	  		
		idd.setVisible(true);
			
			textField_id.setVisible(false);
			addresss.setVisible(false);
			textField_address.setVisible(false);
			textField_fname.setVisible(false);
			textField_lname.setVisible(false);
			textField_job.setVisible(false);
			Address_Label.setVisible(false);
			FName_Label.setVisible(false);
			LName_Label.setVisible(false);
		Job_Label.setVisible(false);
 	  		if(idd.getValue()== null ) {
	    	        JOptionPane.showMessageDialog(null, "No such ID found for delete");}
	    	        else {

   	    try {
   	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
   	        PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
   	        PreparedStatement deleteStatement1 = con.prepareStatement(deleteQuery1);
   	     PreparedStatement deleteStatement2 = con.prepareStatement(deleteQuery2);
   	     
   	        deleteStatement.setInt(1,Integer.parseInt(idd.getValue()));
   	        deleteStatement1.setInt(1,Integer.parseInt(idd.getValue()));
   	     deleteStatement2.setInt(1,Integer.parseInt(idd.getValue()));
   	      
   	        int rowsAffected2 = deleteStatement.executeUpdate();
   	       int rowsAffected1 = deleteStatement1.executeUpdate();
   	    int rowsAffected = deleteStatement2.executeUpdate();
   	 tableview.getColumns().clear();
   	        buildData("SELECT * FROM customer");
   	    } catch (SQLException e1) {
   	        e1.printStackTrace();}
   	    }
 		});	});
  	
  });

  device.setOnAction(k ->{
	 	tableview = new TableView();
	  	tableview.setStyle("-fx-control-inner-background: #E6E6FA;");
	      buildData("select * from Device");
	      
	      Label no_Label = new Label("No :");  
        TextField textField_No = new TextField();

       Label name_Label = new Label("Name :");  
       TextField textField_Name = new TextField();
       
        Label price_Label = new Label("Price :");  
        TextField textField_Price = new TextField();

        
        Label weight_Label = new Label("Weight :");  
        TextField textField_Weight = new TextField();
        
         Label made_Label = new Label("Made :");  
         TextField textField_Made = new TextField();	      
	      
	       Button back = new Button("Back");
	       Button search = new Button("search");
	       Button delete = new Button("delete");
           Button update = new Button("update");
           Button insert = new Button("insert");
           ComboBox<String> no=new ComboBox<>();     
           ComboBox<String> made=new ComboBox<>();
           HBox h = new HBox(5);
	       h.getChildren().addAll( delete,update,insert,search);
           VBox form = new VBox(5);
	       form.setStyle("-fx-padding: 20; -fx-background-color: #FDB9C8;"); // add padding to the VBox
	        form.getChildren().addAll(back,no_Label ,no,textField_No , name_Label , textField_Name , price_Label, textField_Price , weight_Label, textField_Weight , made_Label, textField_Made,made,h);

	       form.getChildren().addAll(tableview);
	  	Scene  device_scene = new Scene(form);
	    primaryComboBox(made,"manufacture","name");
	    primaryComboBox(no,"device","no");
	  	 stage.setScene(device_scene );
	  	    stage.setWidth(600); // Set the initial width of the stage
	  	    stage.setHeight(600); // Set the initial height of the stage
	  	    stage.show();
	  	
	  	back.setOnAction(o->{
	  		 stage.setScene(sc);
             stage.setTitle("DataBase Project");
             stage.setWidth(600);
             stage.setHeight(300);
             stage.show();
	  	});
	  	
	  	
	  	

	  	search.setOnAction(o -> {
		    tableview.getColumns().clear();
		    textField_Made.setVisible(true);
	   		 textField_Name.setVisible(true);
	   		textField_No.setVisible(true);
	   		 textField_Price.setVisible(true);
	   		 textField_Weight.setVisible(true);
	   		 made_Label.setVisible(true);
	   		 name_Label.setVisible(true);
	   		no_Label.setVisible(true);
	   		 price_Label.setVisible(true);
	   		 weight_Label.setVisible(true);
	   		 no.setVisible(false);
	   		 made.setVisible(false);
	   		
	   		 
		    String sql = "SELECT * FROM device WHERE";
	  	 if (textField_No.getText().isEmpty() && textField_Name.getText().isEmpty()  && textField_Price.getText().isEmpty()   && textField_Weight.getText().isEmpty()    && textField_Made.getText().isEmpty()) {
		        buildData("SELECT * FROM device");
		    } else {
		        if (!textField_No.getText().isEmpty()) {
		            sql += " no = '" + textField_No.getText() + "'";
		        }
		        if (!textField_Name.getText().isEmpty()) {
		            if (sql.length() > 27)
		            	sql += " and name = '" + textField_Name.getText() + "'";
		             else      sql += " name = '" + textField_Name.getText() + "'";
		            
		        }
		        
		        
		        if (!textField_Price.getText().isEmpty()) {
		            if (sql.length() > 27)
		            	sql += " and price = '" + textField_Price.getText() + "'";
		             else      sql += " price = '" + textField_Price.getText() + "'";
		            
		        }
		        
		        if (!textField_Weight.getText().isEmpty()) {
		            if (sql.length() > 27)
		            	sql += " and weight = '" + textField_Weight.getText() + "'";
		             else      sql += " weight = '" + textField_Weight.getText() + "'";
		            
		        }
		        
		        if (!textField_Made.getText().isEmpty()) {
		            if (sql.length() > 27)
		            	sql += " and made = '" + textField_Made.getText() + "'";
		             else      sql += " made = '" + textField_Made.getText() + "'";
		            
		        }

		        buildData(sql);
		    }
		});
	  	
	  
	  	insert.setOnAction(o -> {
	 		String s = "INSERT INTO device (no, name, price, weight,made) VALUES(?,?,?,?,?)";
		    textField_Made.setVisible(false);
	   		 textField_Name.setVisible(true);
	   		textField_No.setVisible(true);
	   		 textField_Price.setVisible(true);
	   		 textField_Weight.setVisible(true);
	   		 made_Label.setVisible(true);
	   		 name_Label.setVisible(true);
	   		no_Label.setVisible(true);
	   		 price_Label.setVisible(true);
	   		 weight_Label.setVisible(true);
	   		 no.setVisible(false);
	   		 made.setVisible(true);
	   		
	   		 
	    		insert.setOnAction(m -> {	 	  

	    			  textField_Made.setVisible(false);
	    		   		 textField_Name.setVisible(true);
	    		   		textField_No.setVisible(true);
	    		   		 textField_Price.setVisible(true);
	    		   		 textField_Weight.setVisible(true);
	    		   		 made_Label.setVisible(true);
	    		   		 name_Label.setVisible(true);
	    		   		no_Label.setVisible(true);
	    		   		 price_Label.setVisible(true);
	    		   		 weight_Label.setVisible(true);
	    		   		 no.setVisible(false);
	    		   		 made.setVisible(true);if(textField_No.getText().isEmpty() ) {
	 	  	        JOptionPane.showMessageDialog(null, "No such ID found for update");
	 	  	       tableview.getColumns().clear();
	 	  	    buildData("SELECT * FROM device");}
	    		   		 else {
	 		try {
	 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
	 		    PreparedStatement gb = con.prepareStatement(s);
	 			
try {

	 		    gb.setInt(1,Integer.parseInt(textField_No.getText()));
	 		    gb.setString(2,textField_Name.getText());
	 		    gb.setInt(3,  Integer.parseInt(textField_Price.getText()));
	 		    gb.setInt(4,Integer.parseInt( textField_Weight.getText()));
	 		    gb.setString(5, made.getValue());

	 		    gb.executeUpdate();
	 		   tableview.getColumns().clear();
		        buildData("SELECT * FROM device");
}   catch(Exception q) {
		        	JOptionPane.showMessageDialog(null, "Record insert unsuccessfully enter a valid type of data ");
		        }
	 		} catch (SQLException e1) {
	 		    e1.printStackTrace();
	 		   tableview.getColumns().clear();
	 	  	    buildData("SELECT * FROM device");
	 		}}
		   } );   } );
	  	
	  	
	  	update.setOnAction(up -> {
	    	 textField_Made.setVisible(false);
	   		 textField_Name.setVisible(true);
	   		 textField_No.setVisible(false);
	   		 textField_Price.setVisible(true);
	   		 textField_Weight.setVisible(true);
	   		 made_Label.setVisible(true);
	   		 name_Label.setVisible(true);
	   		 no_Label.setVisible(true);
	   		 price_Label.setVisible(true);
	   		 weight_Label.setVisible(true);
	   		 no.setVisible(true);
	   		 made.setVisible(true);
   		 
   		  primaryComboBox(no,"device","no");
          
             update.setOnAction(f->{
            	 textField_Made.setVisible(false);
     	   		 textField_Name.setVisible(true);
     	   	     textField_No.setVisible(false);
     	   		 textField_Price.setVisible(true);
     	   		 textField_Weight.setVisible(true);
     	   		 made_Label.setVisible(true);
     	   		 name_Label.setVisible(true);
     	   	     no_Label.setVisible(true);
     	   		 price_Label.setVisible(true);
     	   		 weight_Label.setVisible(true);
     	   		 no.setVisible(true);
     	   		 made.setVisible(true);
   	    String s = "UPDATE device SET name=?, price=?, weight=?, made=? WHERE no=?";
   	 made.setVisible(true);if(no.getValue()== null) {
	        JOptionPane.showMessageDialog(null, "No such ID found for update");}
	        else {
   	    try {
   	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
   	        PreparedStatement gb = con.prepareStatement(s);
try {

 	 		  gb.setInt(5, Integer.parseInt(no.getValue()));
 	 		    gb.setString(1, textField_Name.getText());
 	 		   gb.setInt(2, Integer.parseInt(textField_Price.getText()));
 	 		  gb.setString(4, made.getValue());
 	 		 gb.setInt(3, Integer.parseInt(textField_Weight.getText()));
}catch(Exception g) {
	JOptionPane.showMessageDialog(null, "Record insert unsuccessfully id duplicate or is not a number ");
}
   	        int rowsUpdated = gb.executeUpdate();
   	        int updateCount = gb.getUpdateCount();

   	        if (updateCount >=1) {System.out.println("update successfully");     
               JOptionPane.showMessageDialog(null, "Record updated successfully");
  tableview.getColumns().clear();
  buildData("SELECT * FROM device ");
   	        }  
   	        else {
                         System.err.println("No such ID found for update");            	        
               JOptionPane.showMessageDialog(null, "No such ID found for update");
}
   	    
   	    }         	 	   
              catch (SQLException e1) {
   	        e1.printStackTrace();
   	    }   
   	 tableview.getColumns().clear();
   	    buildData("SELECT * FROM device ");}
            	});
	   	});
   	   
             
             delete.setOnAction(c->{
     	  		
            	  textField_Made.setVisible(false);
     	   		 textField_Name.setVisible(false);
     	   		textField_No.setVisible(false);
     	   		 textField_Price.setVisible(false);
     	   		 textField_Weight.setVisible(false);
     	   		 made_Label.setVisible(false);
     	   		 name_Label.setVisible(false);
     	   		no_Label.setVisible(true);
     	   		 price_Label.setVisible(false);
     	   		 weight_Label.setVisible(false);
     	   		 no.setVisible(true);
     	   		 made.setVisible(false);
      	    String deleteQuery = "DELETE FROM  car_part WHERE part = ? ";
     	    String deleteQuery1 = "DELETE FROM  device WHERE no = ? ";
     	   
   	  	delete.setOnAction(m->{
   	  	  textField_Made.setVisible(false);
	   		 textField_Name.setVisible(false);
	   		textField_No.setVisible(false);
	   		 textField_Price.setVisible(false);
	   		 textField_Weight.setVisible(false);
	   		 made_Label.setVisible(false);
	   		 name_Label.setVisible(false);
	   		no_Label.setVisible(true);
	   		 price_Label.setVisible(false);
	   		 weight_Label.setVisible(false);
	   		 no.setVisible(true);
	   		 made.setVisible(false);
	   		if(no.getValue( )== null ) {
	 	  	        JOptionPane.showMessageDialog(null, "No such ID found for update");}
	 	  	        else {
     	    try {
     	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
     	        PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
     	        PreparedStatement deleteStatement1 = con.prepareStatement(deleteQuery1);
     	       
     	        deleteStatement.setInt(1,Integer.parseInt(no.getValue( )));
     	        deleteStatement1.setInt(1,Integer.parseInt(no.getValue( )));
     	      
     	      
     	        int rowsAffected2 = deleteStatement.executeUpdate();
     	       int rowsAffected1 = deleteStatement1.executeUpdate();
     	      tableview.getColumns().clear();
     	        buildData("SELECT * FROM device");
     	    } catch (SQLException e1) {
     	        e1.printStackTrace();
     	    }}
   		});	});
         
    });    

  
  manufacture.setOnAction(k ->{
	  	
	 
	  	tableview = new TableView();
	  	 tableview.setStyle("-fx-control-inner-background: #E6E6FA;");
	      buildData("select * from Manufacture");
	      
	      Label name_Label = new Label("Name :");  
        TextField textField_Name = new TextField();

       Label type_Label = new Label("Type :");  
       TextField textField_Type = new TextField();
       
        Label city_Label = new Label("City :");  
        TextField textField_City = new TextField();

        Label country_Label = new Label("Country :");  
        TextField textField_Country = new TextField();     
	       Button back = new Button("Back");
	       Button search = new Button("search");
	       Button delete = new Button("delete");
           Button update = new Button("update");
           Button insert = new Button("insert");
           ComboBox<String> namee=new ComboBox<>(); 
	       HBox h = new HBox(5);
	       h.getChildren().addAll( delete,update,insert,search);
           VBox form = new VBox(5);
	       form.setStyle("-fx-padding: 20; -fx-background-color: #FDB9C8;"); // add padding to the VBox
	        form.getChildren().addAll(back,name_Label ,namee,textField_Name , type_Label , textField_Type ,city_Label, textField_City , country_Label, textField_Country,h);

	       form.getChildren().addAll(tableview);
	  	Scene  manufacture_scene = new Scene(form);
	  	
	  	 stage.setScene(manufacture_scene );
	  	    stage.setWidth(600); // Set the initial width of the stage
	  	    stage.setHeight(600); // Set the initial height of the stage
	  	    stage.show();
	  		  	   		  primaryComboBox(namee,"manufacture","name");

	  	back.setOnAction(o->{
	  		 stage.setScene(sc);
             stage.setTitle("DataBase Project");
             stage.setWidth(600);
             stage.setHeight(300);
             stage.show();
	  	});
	  	
	  	search.setOnAction(o -> {
	  		 textField_Name.setVisible(true);
			 textField_City.setVisible(true);
			textField_Country.setVisible(true);
			 textField_Type.setVisible(true);
			
			city_Label.setVisible(true);
			 name_Label.setVisible(true);
			country_Label.setVisible(true);
			type_Label.setVisible(true);
			
			 namee.setVisible(false);
		  	
		    tableview.getColumns().clear();
		    String sql = "SELECT * FROM manufacture WHERE";
	  	 if (textField_Name.getText().isEmpty() && textField_Type.getText().isEmpty()  && textField_City.getText().isEmpty()   && textField_Country.getText().isEmpty()) {
		        buildData("SELECT * FROM manufacture");
		    } else {
		        if (!textField_Name.getText().isEmpty()) {
		            sql += " name = '" + textField_Name.getText() + "'";
		        }
		        if (!textField_Type.getText().isEmpty()) {
		            if (sql.length() > 32)
		            	sql += " and type = '" + textField_Type.getText() + "'";
		             else      sql += " type = '" + textField_Type.getText() + "'";
		            
		        }
		        
		        
		        if (!textField_City.getText().isEmpty()) {
		            if (sql.length() > 32)
		            	sql += " and city = '" + textField_City.getText() + "'";
		             else      sql += " city = '" + textField_City.getText() + "'";
		            
		        }
		        
		        if (!textField_Country.getText().isEmpty()) {
		            if (sql.length() > 32)
		            	sql += " and country = '" + textField_Country.getText() + "'";
		             else      sql += " country = '" + textField_Country.getText() + "'";
		            
		        }
		        

		        buildData(sql);
		    }
		});
	  	
	  	
	  	insert.setOnAction(o -> {
	  		 textField_Name.setVisible(true);
			 textField_City.setVisible(true);
			textField_Country.setVisible(true);
			 textField_Type.setVisible(true);
			
			city_Label.setVisible(true);
			 name_Label.setVisible(true);
			country_Label.setVisible(true);
			type_Label.setVisible(true);
			
			 namee.setVisible(false);
	 		String s = "INSERT INTO manufacture (name, type, city, country) VALUES(?,?,?,?)";
	 		namee.setVisible(false);
	   		 textField_Name.setVisible(true);
	   		insert.setOnAction(m -> {
	   		 textField_Name.setVisible(true);
			 textField_City.setVisible(true);
			textField_Country.setVisible(true);
			 textField_Type.setVisible(true);
			
			city_Label.setVisible(true);
			 name_Label.setVisible(true);
			country_Label.setVisible(true);
			type_Label.setVisible(true);
			
			 namee.setVisible(false);
				if(textField_Name.getText().isEmpty() ) {
		  	        JOptionPane.showMessageDialog(null, "No such ID found for insert");}
		  	        else {

	 		try {
	 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
	 		    PreparedStatement gb = con.prepareStatement(s);

	 		    gb.setString(1,textField_Name.getText());
	 		    gb.setString(2,textField_Type.getText());
	 		    gb.setString(3, textField_City.getText());
	 		    gb.setString(4,textField_Country.getText());

	 		    gb.executeUpdate();
	 		   tableview.getColumns().clear();
		        buildData("SELECT * FROM manufacture");
	 		} catch (SQLException e1) {
	 		    e1.printStackTrace();
	 		}}

		   } );  } );
	  	

	  	update.setOnAction(up -> {
	  		 textField_Name.setVisible(true);
			 textField_City.setVisible(true);
			textField_Country.setVisible(true);
			 textField_Type.setVisible(true);
			
			city_Label.setVisible(true);
			 name_Label.setVisible(true);
			country_Label.setVisible(true);
			type_Label.setVisible(true);
			
			 namee.setVisible(false);
       
             update.setOnAction(f->{
            		if(textField_Name.getText().isEmpty() ) {
    		  	        JOptionPane.showMessageDialog(null, "No such ID found for update");}
    		  	        else {

            	 textField_Name.setVisible(true);
    			 textField_City.setVisible(true);
    			textField_Country.setVisible(true);
    			 textField_Type.setVisible(true);
    			
    			city_Label.setVisible(true);
    			 name_Label.setVisible(true);
    			country_Label.setVisible(true);
    			type_Label.setVisible(true);
    			
    			 namee.setVisible(false);
   	    String s = "UPDATE manufacture SET type=?, city=?,  country=? WHERE name=?";
   	    try {
   	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
   	        PreparedStatement gb = con.prepareStatement(s);


	 		    gb.setString(4, textField_Name.getText());
	 		   gb.setString(1, textField_Type.getText());
	 		  gb.setString(2, textField_City.getText());
	 		 gb.setString(3, textField_Country.getText());
	 		

   	        int rowsUpdated = gb.executeUpdate();
   	        int updateCount = gb.getUpdateCount();

   	        if (updateCount >=1) {System.out.println("update successfully");     
               JOptionPane.showMessageDialog(null, "Record updated successfully");

   	        }  
   	        else {
                         System.err.println("No such ID found for update");            	        
               JOptionPane.showMessageDialog(null, "No such ID found for update");
}
   	    
   	    }         	 	   
              catch (SQLException e1) {
   	        e1.printStackTrace();}
   	     tableview.getColumns().clear();
   	        buildData("SELECT * FROM manufacture ");}
   	});
	  	}); 
             
             
             delete.setOnAction(c->{
            	 textField_Name.setVisible(false);
    			 textField_City.setVisible(false);
    			textField_Country.setVisible(false);
    			 textField_Type.setVisible(false);
    			
    			city_Label.setVisible(false);
    			 name_Label.setVisible(true);
    			country_Label.setVisible(false);
    			type_Label.setVisible(false);
    			
    			 namee.setVisible(true);
            	 
      	    String deleteQuery = "DELETE FROM " + "car" + " WHERE " + "made" + " = ? ";
     	    String deleteQuery1 = "DELETE FROM " + "device" + " WHERE " + "made" + " = ? ";
     	    String deleteQuery2 = "DELETE FROM " + "manufacture" + " WHERE " + "name" + " = ? ";

   	  	delete.setOnAction(m->{
   	  	 textField_Name.setVisible(false);
		 textField_City.setVisible(false);
		textField_Country.setVisible(false);
		 textField_Type.setVisible(false);
		
		city_Label.setVisible(false);
		 name_Label.setVisible(true);
		country_Label.setVisible(false);
		type_Label.setVisible(false);
		
		 namee.setVisible(true);
   	 	if(namee.getValue()==null) {
  	        JOptionPane.showMessageDialog(null, "No such ID found for delete");}
  	        else {

     	    try {
     	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
     	        PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
     	        PreparedStatement deleteStatement1 = con.prepareStatement(deleteQuery1);
     	        PreparedStatement deleteStatement2 = con.prepareStatement(deleteQuery2);

     	        deleteStatement.setString(1,namee.getValue() );
     	        deleteStatement1.setString(1,namee.getValue() );
     	        deleteStatement2.setString(1,namee.getValue() );

     	       int rowsAffected2 = deleteStatement.executeUpdate();
     	       int rowsAffected1 = deleteStatement1.executeUpdate(); 
     	        int rowsAffected = deleteStatement2.executeUpdate();
     	     

     	       tableview.getColumns().clear();
     	        buildData("SELECT * FROM manufacture");
     	    } catch (SQLException e1) {
     	        e1.printStackTrace();
     	    }}
   		});	     
             
             
             
             
   	
});

  
  });
  
  
  
  orders.setOnAction(k ->{
	  	

	  	
	  	tableview = new TableView();
	  	 tableview.setStyle("-fx-control-inner-background: #E6E6FA;");
	      buildData("select * from Orders");
	      
	      Label id_Label = new Label("ID :");  
        TextField textField_Id = new TextField();

        Label date_Label = new Label("Date :");  
        TextField textField_Date = new TextField();
       
        Label customer_Label = new Label("Customer :");  
        TextField textField_Customer = new TextField();

        Label car_Label = new Label("Car :");  
        TextField textField_Car = new TextField();    
	   
        Label del_label = new Label("Car :");  
        TextField del = new TextField();    
	    
	    
	       Button back = new Button("Back");
	       Button search = new Button("search");
	       Button delete = new Button("delete");
           Button update = new Button("update");
           Button insert = new Button("insert"); 
           ComboBox<String> costumerr=new ComboBox<>(); 
           ComboBox<String> carr=new ComboBox<>(); 
           ComboBox<String> idd=new ComboBox<>(); 
           HBox h=new HBox(5);
           h.getChildren().addAll( delete,update,insert,search);
	       VBox form = new VBox(5);
	       form.setStyle("-fx-padding: 20; -fx-background-color: #FDB9C8;"); // add padding to the VBox
	        form.getChildren().addAll(back,del_label,del,id_Label ,idd, textField_Id , date_Label , textField_Date ,customer_Label,costumerr, textField_Customer , car_Label, textField_Car,carr,h );

	        del_label.setVisible(false);
	        del.setVisible(false);
	       form.getChildren().addAll(tableview);
	  	Scene  orders_scene = new Scene(form);
	  	 primaryComboBox(costumerr,"customer","id");
         primaryComboBox(carr,"car","name");
	  	 stage.setScene(orders_scene );
	  	    stage.setWidth(600); // Set the initial width of the stage
	  	    stage.setHeight(600); // Set the initial height of the stage
	  	    stage.show();
	  	             primaryComboBox(idd,"orders","id");

	  	back.setOnAction(o->{
	  		 stage.setScene(sc);
             stage.setTitle("DataBase Project");
             stage.setWidth(600);
             stage.setHeight(300);
             stage.show();
	  	});
	  	
	  	
	  	
	  	search.setOnAction(o -> {
	  		 textField_Car.setVisible(true);
			 textField_Customer.setVisible(true);
			textField_Date.setVisible(true);
			 textField_Id.setVisible(true);
			
			car_Label.setVisible(true);
			 customer_Label.setVisible(true);
			date_Label.setVisible(true);
			id_Label.setVisible(true);
			 costumerr.setVisible(false);
			 
			 carr.setVisible(false);
		    tableview.getColumns().clear();
		    String sql = "SELECT * FROM  orders WHERE";
	  	 if (textField_Id.getText().isEmpty() && textField_Date.getText().isEmpty()  && textField_Customer.getText().isEmpty()   && textField_Car.getText().isEmpty()) {
		        buildData("SELECT * FROM  orders");
		    } else {
		        if (!textField_Id.getText().isEmpty()) {
		            sql += " id = '" + textField_Id.getText() + "'";
		        }
		        if (!textField_Date.getText().isEmpty()) {
		            if (sql.length() > 28)
		            	sql += " and date = '" + textField_Date.getText() + "'";
		             else      sql += " date = '" + textField_Date.getText() + "'";
		            
		        }
		        
		        
		        if (!textField_Customer.getText().isEmpty()) {
		            if (sql.length() > 28)
		            	sql += " and customer = '" + textField_Customer.getText() + "'";
		             else      sql += " customer = '" + textField_Customer.getText() + "'";
		            
		        }
		        
		        if (!textField_Car.getText().isEmpty()) {
		            if (sql.length() > 28)
		            	sql += " and car = '" + textField_Car.getText() + "'";
		             else      sql += " car = '" + textField_Car.getText() + "'";
		            
		        }
		        

		        buildData(sql);
		    }
		});
	  	
	  	
		insert.setOnAction(o -> {
			 textField_Car.setVisible(false);
			 textField_Customer.setVisible(false);
			textField_Date.setVisible(true);
			 textField_Id.setVisible(true);
			
			car_Label.setVisible(true);
			 customer_Label.setVisible(true);
			date_Label.setVisible(true);
			id_Label.setVisible(true);
			 costumerr.setVisible(true);
		   		idd.setVisible(false);

			 carr.setVisible(true);
	 		String s = "INSERT INTO orders (id, date, customer, car) VALUES(?,?,?,?)";
	 		
	   		insert.setOnAction(m -> {
	   		 textField_Car.setVisible(false);
			 textField_Customer.setVisible(false);
			textField_Date.setVisible(true);
			 textField_Id.setVisible(true);
			
			car_Label.setVisible(true);
			 customer_Label.setVisible(true);
			date_Label.setVisible(true);
			id_Label.setVisible(true);
			 costumerr.setVisible(true);
		   		idd.setVisible(false);

			 carr.setVisible(true);
	   			if(textField_Id.getText().isEmpty() ) {
		  	        JOptionPane.showMessageDialog(null, "No such ID found for insert");}
		  	        else {

	 		try {
	 			 textField_Car.setVisible(false);
				 textField_Customer.setVisible(false);
				textField_Date.setVisible(true);
				 textField_Id.setVisible(true);
				
				car_Label.setVisible(true);
				 customer_Label.setVisible(true);
				date_Label.setVisible(true);
				id_Label.setVisible(true);
				 costumerr.setVisible(true);
			   		idd.setVisible(false);

				 carr.setVisible(true);
	 		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
	 		    PreparedStatement gb = con.prepareStatement(s);

try {
	 		 	
	 		    gb.setInt(1,Integer.parseInt(textField_Id.getText()));
	 		    gb.setInt(2,Integer.parseInt(textField_Date.getText()));
	 		    gb.setInt(3,  Integer.parseInt(costumerr.getValue()));
	 		    gb.setString(4, carr.getValue());

	 		    gb.executeUpdate();
	 		   tableview.getColumns().clear();
		        buildData("SELECT * FROM orders");
		        primaryComboBox(idd,"orders","id");
	 		 }catch(Exception d) {
	 			JOptionPane.showMessageDialog(null, "Record insert unsuccessfully enter a valid type of data ");
	 		}}
catch (Exception e1) {
	 buildData("SELECT * FROM orders");
	 		}
		  	        }
		   } );  } );
	  	
	  	
	  	update.setOnAction(up -> {
			 textField_Car.setVisible(false);
			 textField_Customer.setVisible(false);
			textField_Date.setVisible(true);
			 textField_Id.setVisible(false);
			
			car_Label.setVisible(true);
			 customer_Label.setVisible(true);
			date_Label.setVisible(true);
			id_Label.setVisible(true);
			 costumerr.setVisible(true);
		   		idd.setVisible(true);
			 carr.setVisible(true);
             update.setOnAction(f->{
            	 textField_Car.setVisible(false);
    			 textField_Customer.setVisible(false);
    			textField_Date.setVisible(true);
    			 textField_Id.setVisible(false);
    			
    			car_Label.setVisible(true);
    			 customer_Label.setVisible(true);
    			date_Label.setVisible(true);
    			id_Label.setVisible(true);
    			 costumerr.setVisible(true);
    		   		idd.setVisible(true);
    			 carr.setVisible(true);
            		if(idd.getValue()==null ) {
    		  	        JOptionPane.showMessageDialog(null, "No such ID found for update");}
    		  	        else {
   	    String s = "UPDATE orders SET date=?, customer=?, car=? WHERE id=?";
   	    try {
   	     textField_Car.setVisible(false);
		 textField_Customer.setVisible(false);
		textField_Date.setVisible(true);
		 textField_Id.setVisible(false);
		
		car_Label.setVisible(true);
		 customer_Label.setVisible(true);
		date_Label.setVisible(true);
		id_Label.setVisible(true);
		 costumerr.setVisible(true);
	   		idd.setVisible(true);
		 carr.setVisible(true);
   	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
   	        PreparedStatement gb = con.prepareStatement(s);
   	        try {
 	 		  gb.setInt(1, Integer.parseInt(textField_Date.getText()));
 	 		  gb.setInt(4, Integer.parseInt(idd.getValue()));
 	 		  gb.setInt(2, Integer.parseInt(costumerr.getValue()));
 	 		  gb.setString(3, carr.getValue());
   	        }catch(Exception l) {
   	        	JOptionPane.showMessageDialog(null, "Record insert unsuccessfully enter a valid type of data ");
   	        }
   	        int rowsUpdated = gb.executeUpdate();
   	        int updateCount = gb.getUpdateCount();

   	        if (updateCount >=1) {System.out.println("update successfully");     
               JOptionPane.showMessageDialog(null, "Record updated successfully");

   	        }  
   	        else {
                         System.err.println("No such ID found for update");            	        
               JOptionPane.showMessageDialog(null, "No such ID found for update");
}
   	    
   	    }         	 	   
              catch (SQLException e1) {
   	        e1.printStackTrace(); } 
   	     tableview.getColumns().clear();
   	      buildData("SELECT * FROM orders ");}
             });
	  	 });
             
	  	delete.setOnAction(c->{
	  		
	  		 textField_Car.setVisible(false);
			 textField_Customer.setVisible(false);
			textField_Date.setVisible(false);
			 textField_Id.setVisible(false);
			
			car_Label.setVisible(false);
			 customer_Label.setVisible(false);
			date_Label.setVisible(false);
			id_Label.setVisible(true);
			 costumerr.setVisible(false);
		   		idd.setVisible(true);
			 carr.setVisible(false);

  	    String deleteQuery = "DELETE FROM " + "orders" + " WHERE " + "id" + " = ? ";

	  	delete.setOnAction(m->{
	  		 textField_Car.setVisible(false);
				 textField_Customer.setVisible(false);
				textField_Date.setVisible(false);
				 textField_Id.setVisible(false);
				
				car_Label.setVisible(false);
				 customer_Label.setVisible(false);
				date_Label.setVisible(false);
				id_Label.setVisible(true);
				 costumerr.setVisible(false);
			   		idd.setVisible(true);
				 carr.setVisible(false);
	  		if(idd.getValue()==null ) {
	  	        JOptionPane.showMessageDialog(null, "No such ID found for delete");}
	  	        else {
	  		 textField_Car.setVisible(false);
			 textField_Customer.setVisible(false);
			textField_Date.setVisible(false);
			 textField_Id.setVisible(false);
			
			car_Label.setVisible(false);
			 customer_Label.setVisible(false);
			date_Label.setVisible(false);
			id_Label.setVisible(true);
			 costumerr.setVisible(false);
		   		idd.setVisible(true);
			 carr.setVisible(false);
  	    try {
  	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
  	        PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);

  	        deleteStatement.setInt(1,Integer.parseInt(idd.getValue()) );

  	        int rowsAffected = deleteStatement.executeUpdate();

  	      tableview.getColumns().clear();
  	        buildData("SELECT * FROM orders");
  	    } catch (SQLException e1) {
  	        e1.printStackTrace();
  	    }}
		});	});
	  	
	  	
	  	
	  });
  
  
  

        
         });   
        
}
    
    private void primaryComboBox(ComboBox<String> comboBox, String tableName, String columnName) {
        comboBox.getItems().clear();

        try {
        	
            // Establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsprojecttt", "root", "");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " + columnName + " FROM " + tableName);
   
        

            // Populate ComboBox with the retrieved values
            ObservableList<String> madeList = FXCollections.observableArrayList();
         
            while (rs.next()) {
                madeList.add(rs.getString(columnName));
            }

            comboBox.setItems(madeList);

            // Close resources
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
