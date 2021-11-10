/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet301;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class ClassController implements Initializable {
@FXML
private TableView<Student> classtable; 
@FXML 
private TableColumn <Student,Integer> id;
@FXML 
private TableColumn <Student,String> firstname;
@FXML 
private TableColumn <Student,String> lastname;
@FXML 
private TableColumn <Student,String> username;
@FXML 
private TextField searchmyClass;
@FXML
private Button SearchBtn;
@FXML
private Button RemoveStudent;
        
private Teacher t;

ObservableList<Student> mystudents=FXCollections.observableArrayList();

    public void setTeacher(Teacher t) throws SQLException
    {
        this.t=t;
        TeacherModel M=new TeacherModel();
        mystudents=M.DisplayMyStudents(t);
        classtable.setItems(mystudents);
        
    }
    @FXML
    public void Search(ActionEvent e) throws SQLException
    {
        String s=searchmyClass.getText();
        TeacherModel T=new TeacherModel();
        mystudents.clear();
        mystudents.addAll(T.SearchMyStudent(s, t));
        classtable.setItems(mystudents);
    }
    @FXML
    public void removeStudents(ActionEvent e) throws SQLException
    {
        int i=classtable.getSelectionModel().getFocusedIndex();
        Student s=mystudents.get(i);
        TeacherModel M=new TeacherModel();
        M.RemoveStudent(s);
        mystudents.remove(i);
        classtable.setItems(mystudents);
    }
      
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     classtable.setItems(mystudents);
     id.setCellValueFactory(new PropertyValueFactory<Student,Integer>("ID"));
     firstname.setCellValueFactory(new PropertyValueFactory<Student,String>("firstname"));
     lastname.setCellValueFactory(new PropertyValueFactory<Student,String>("lastname"));
     username.setCellValueFactory(new PropertyValueFactory<Student,String>("username"));
        
      
    }    
    
}
