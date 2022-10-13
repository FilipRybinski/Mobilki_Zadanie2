package pb.edu.pb.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.icu.text.UnicodeSetIterator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        questionTextView=findViewById(R.id.question_text_view);
        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswerCorrectness(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswerCorrectness(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                currentIndex=(currentIndex+1)%questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion();
    };
    private  Question[] questions=new Question[]{
            new Question(R.string.ask1, true),
            new Question(R.string.ask2, false),
            new Question(R.string.ask3,true),
            new Question(R.string.ask4,false),
            new Question(R.string.ask5,true)

    };
    private int currentIndex=0;
    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer=questions[currentIndex].isTrueAnswer();
        int resutMessageId=0;
        if(userAnswer==correctAnswer){
            resutMessageId=R.string.correct_answer;
        }else{
            resutMessageId=R.string.incorrect_answer;
        }
        Toast.makeText(this,resutMessageId,Toast.LENGTH_SHORT).show();
    }
    private void  setNextQuestion(){
    questionTextView.setText(questions[currentIndex].getQuestionId());
    }
}
