
package projet301;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ResolveController implements Initializable {
     @FXML
    private Label Question;
    @FXML
    private Label Title;
    @FXML
    private RadioButton Ans1;
    @FXML
    private RadioButton Ans2;
    @FXML
    private RadioButton Ans3;
    @FXML
    private Button NextQuest;
    @FXML
    private Button Submit;
    @FXML
    private Label pages;
    @FXML
    private Label errorLbl;
      @FXML
    private Label corrects;
    @FXML
     private Label answer;
    @FXML
     private Label myanswer;
    
    @FXML
    private ToggleGroup answers;
    @FXML
    private Button Quit;
    private Exam e;
    private String ans1,ans2,ans3,question,correctanswer;
    private int nbofcorrect,counter=1,linesnb;
    private File exam;
    private Student s;
    private Scanner myReader;
    private FileReader input;
     RadioButton selected;
  
    
    public void setExam(Exam e,Student s)
    {
        this.e=e;
         this.s=s;
        try {
      exam = new File(e.getName()+".txt");
      myReader = new Scanner(exam);
      input=new FileReader(exam);
      LineNumberReader lines = new LineNumberReader(input);
      linesnb=(int)lines.lines().count()+1;
      linesnb/=6;
      while(myReader.hasNext()){
        String data = myReader.nextLine();
        if(data.contains("Question"))
        {
           question=data.substring(data.indexOf(':')+1);
        }
       else if(data.contains("a)"))
        {
            ans1=data;
        }
       else if(data.contains("b)"))
        {
            ans2=data;
        }
       else if(data.contains("c)"))
        {
            ans3=data;
        }
       else if(data.contains("Answer:"))
       {
           correctanswer=data.substring(8);
       }
       else break;
      }
    } catch (FileNotFoundException f) {
      System.out.println("An error occurred.");
      
    }
        pages.setText("1/"+linesnb);
         Title.setText(e.getName());
         Question.setText(question);
        Ans1.setText(ans1);
        Ans2.setText(ans2);
        Ans3.setText(ans3);
         if(counter==linesnb)
        {
            NextQuest.setVisible(false);
        }

  }
    @FXML
    public void nextQuestion(ActionEvent a) throws IOException
    {

        try{
        selected=(RadioButton) answers.getSelectedToggle();
         if(selected.getText().substring(3).equals(correctanswer))
        {
            nbofcorrect++;
        }
        
        }catch(NullPointerException p)
        {
            errorLbl.setText("You have to choose an answer !");
            p.notify();
           
          
        }
        counter++;
        errorLbl.setText("");

        while(myReader.hasNext()){
        String data = myReader.nextLine();
        if(data.contains("Question"))
        {
           question=data.substring(data.indexOf(':')+1);
        }
       else if(data.contains("a)"))
        {
            ans1=data;
        }
       else if(data.contains("b)"))
        {
            ans2=data;
        }
       else if(data.contains("c)"))
        {
            ans3=data;
        }
       else if(data.contains("Answer:"))
       {
           correctanswer=data.substring(8);
       }
       else break;
      }
        Question.setText(question);
        Ans1.setText(ans1);
        Ans2.setText(ans2);
        Ans3.setText(ans3);
        pages.setText(counter+"/"+linesnb);
        Ans1.setSelected(false);
        Ans2.setSelected(false);
        Ans3.setSelected(false);
        if(counter==linesnb)
        {
            NextQuest.setVisible(false);
        }

     
           
    }
    @FXML
    public void SubmitQuiz(ActionEvent e)
    {
        errorLbl.setText("");
        try{
        selected=(RadioButton) answers.getSelectedToggle();
        if(selected.getText().substring(3).equals(correctanswer))
        {
            nbofcorrect++;
        }
        }catch(NullPointerException p)
        {
            errorLbl.setText("You have to choose an answer !");
            p.notify();
        }
       
            NextQuest.setVisible(false);
            Question.setText("Note: "+nbofcorrect+"/"+linesnb);
            Ans1.setVisible(false);
            Ans2.setVisible(false);
            Ans3.setVisible(false);     
            pages.setVisible(false);
            Submit.setVisible(false);
            Quit.setVisible(true);
      
    }
    @FXML
    public void Quit(ActionEvent a) throws SQLException, IOException
    {
        StudentModel M=new StudentModel();
        M.submit(s,"'"+nbofcorrect+"/"+linesnb+"'",e);
            Stage stage=new Stage();
            stage=(Stage)Quit.getScene().getWindow();
            stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
