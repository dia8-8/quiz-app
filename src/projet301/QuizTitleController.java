
package projet301;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class QuizTitleController implements Initializable {

    @FXML
    private TextArea Question;
    @FXML
    private TextField Title;
    @FXML
    private RadioButton R1;
    @FXML
    private RadioButton R2;
    @FXML
    private RadioButton R3;
    @FXML
    private Button AddQuest;
    @FXML
    private TextField Ans1;
    @FXML
    private TextField Ans3;
    @FXML
    private TextField Ans2;
    @FXML
    private Button EndQuiz;
    @FXML
    private Label pages;
       @FXML
    private Label errorLbl;
       @FXML
    private ToggleGroup answers;
    int c=1;
    private Teacher t;
   
     public void setTeacher(Teacher t)
     {
         this.t=t;
     }
    @FXML
    public void nextQuestion (ActionEvent e) throws IOException
    {   
        String s="",correct="";
        RadioButton answer=(RadioButton)answers.getSelectedToggle();
        try{
        s=answer.getText();
        }catch(NullPointerException p)
        {
            errorLbl.setText("All fields are required ! ");
        }
       if(Title.getText().equals("")||Question.getText().equals("")||Ans1.getText().equals("")||Ans2.getText().equals("")||Ans3.getText().equals("")||s.equals(""))
       {
           errorLbl.setText("All fields are required ! ");
       }
       else{
            
            if(s.equals("1"))
            {
               
                correct=Ans1.getText();
            }
           else if(s.equals("2"))
            {
              
                correct=Ans2.getText();
            }
           else if(s.equals("3"))
            {
                correct=Ans3.getText();
            }
           File exam =new File(Title.getText()+".txt");
           if(exam.createNewFile())
           {
               System.out.println("File Created");
               FileWriter myWriter = new FileWriter(Title.getText()+".txt");
               myWriter.write("Question:"+Question.getText()+"\n");
               myWriter.write("a) "+Ans1.getText()+"\n");
               myWriter.write("b) "+Ans2.getText()+"\n");
               myWriter.write("c) "+Ans3.getText()+"\n");
               myWriter.write("Answer: "+correct+"\n");
               myWriter.write("////\n");    
               myWriter.close();
      System.out.println("Successfully wrote to the file.");
           }
           else
           { 
               System.out.println("File already exists");
               FileWriter myWriter = new FileWriter(Title.getText()+".txt",true);
               myWriter.write("Question:"+Question.getText()+"\n");
               myWriter.write("a) "+Ans1.getText()+"\n");
               myWriter.write("b) "+Ans2.getText()+"\n");
               myWriter.write("c) "+Ans3.getText()+"\n");
               myWriter.write("Answer: "+correct+"\n");
               myWriter.write("////\n");    
               myWriter.close();
           }
            c++;
             FXMLLoader loader=new FXMLLoader(getClass().getResource("quizTitle.fxml"));
           Stage  stage=new Stage();
            stage.setScene(new Scene(loader.load()));
            QuizTitleController Q=loader.getController();
            Q.setTeacher(t);
            Q.c=c;
            Q.Title.setText(this.Title.getText());
            Q.Title.setEditable(false);
            Q.pages.setText(c+"/"+c);
            stage.setTitle("Next Question");
            stage.show(); 
            
         stage=(Stage) AddQuest.getScene().getWindow();
            stage.close();
           
       }
       
    }
    
    @FXML
    public void EndQuiz(ActionEvent e) throws IOException, SQLException
    {
            String s="",correct="";
        RadioButton answer=(RadioButton)answers.getSelectedToggle();
        try{
        s=answer.getText();
        }catch(NullPointerException p)
        {
            errorLbl.setText("All fields are required ! ");
        }
       if(Title.getText().equals("")||Question.getText().equals("")||Ans1.getText().equals("")||Ans2.getText().equals("")||Ans3.getText().equals("")||s.equals(""))
       {
           errorLbl.setText("All fields are required ! ");
       }
       else{
            
            if(s.equals("1"))
            {
               
                correct=Ans1.getText();
            }
           else if(s.equals("2"))
            {
              
                correct=Ans2.getText();
            }
           else if(s.equals("3"))
            {
                correct=Ans3.getText();
            }
           File exam =new File(Title.getText()+".txt");
           if(exam.createNewFile())
           {
               System.out.println("File Created");
               FileWriter myWriter = new FileWriter(Title.getText()+".txt");
               myWriter.write("Question:"+Question.getText()+"\n");
               myWriter.write("a) "+Ans1.getText()+"\n");
               myWriter.write("b) "+Ans2.getText()+"\n");
               myWriter.write("c) "+Ans3.getText()+"\n");
               myWriter.write("Answer: "+correct+"\n");
               myWriter.write("////\n");    
               myWriter.close();
      System.out.println("Successfully wrote to the file.");
           }
           else
           { 
               System.out.println("File already exists");
               FileWriter myWriter = new FileWriter(Title.getText()+".txt",true);
               myWriter.write("Question:"+Question.getText()+"\n");
               myWriter.write("a) "+Ans1.getText()+"\n");
               myWriter.write("b) "+Ans2.getText()+"\n");
               myWriter.write("c) "+Ans3.getText()+"\n");
               myWriter.write("Answer: "+correct+"\n");
               myWriter.write("////\n");               
               myWriter.close();
           }
          
            String name=Title.getText();
            TeacherModel M=new TeacherModel();
            M.createExam(t, name);
            Stage stage=new Stage();
         stage=(Stage) AddQuest.getScene().getWindow();
            stage.close();
    
        
    }
   }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pages.setText(c+"/"+c);
    }    
    
}
