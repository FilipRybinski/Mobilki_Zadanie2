package pb.edu.pb.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PromptActivity extends AppCompatActivity {
    private boolean correctAnswer;
    private Button showCorrectAnswerButton;
    private TextView answerTextView;
    public static final String KEY_EXTRA_ANSWER_SHOWN="pb.edu.pl.wi.quiz.answerShown";


    private void setAnswerShowResult(boolean answerWasShown){
        Intent resultIntent=new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN,answerWasShown);
        setResult(RESULT_OK,resultIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);
        showCorrectAnswerButton=findViewById(R.id.showCorrectAnswerButton);
        answerTextView=findViewById(R.id.answerTextView);
        correctAnswer=getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER,true);
      showCorrectAnswerButton.setOnClickListener(new View.OnClickListener(){
      @Override
           public void onClick(View v){
           int answer=correctAnswer ? R.string.button_true : R.string.button_false;
           answerTextView.setText(answer);
           setAnswerShowResult(true);
      }
   });

    };


}