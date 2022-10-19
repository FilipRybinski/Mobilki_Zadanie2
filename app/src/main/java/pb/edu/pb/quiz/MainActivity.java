package pb.edu.pb.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.UnicodeSetIterator;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_EXTRA_ANSWER = "pl.edu.wi.quiz.correctAnswer";
    private static final String KEY_CURRENT_INDEX="currentIndex";
    private static final int REQUEST_CODE_PROMPT = 0;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button hintButton;
    private TextView questionTextView;
    private static boolean answerWasShown;
    @SuppressLint("MissingInflatedId")
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        questionTextView=findViewById(R.id.question_text_view);
        hintButton=findViewById(R.id.hint_button);

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
                answerWasShown=false;
                setNextQuestion();
            }
        });
        hintButton.setOnClickListener((v) -> {
            Intent intent=new Intent(MainActivity.this,PromptActivity.class);
            boolean correctAnswer=questions[currentIndex].isTrueAnswer();
            intent.putExtra(KEY_EXTRA_ANSWER,correctAnswer);
            startActivityForResult(intent,REQUEST_CODE_PROMPT);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK){return;}
        if(requestCode==REQUEST_CODE_PROMPT){
            if(data==null){return;}
            answerWasShown=data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN,false);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.d("onCreate", "Wywoałana została metoda cyklu żytcia onCreate: ");
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            currentIndex=savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("OnStart", "onStart: Uruchomiona");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("OnStop", "onStop: Zakończona");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("OnDestory", "onDestroy: Zniszczona");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("OnPause", "onPause: Zapauzowane");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("OnResume", "onResume: Wznowione");
    }

    private void checkAnswerCorrectness(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resutMessageId = 0;
        if (answerWasShown) {
            resutMessageId = R.string.answer_was_shown;
        } else {
            if(userAnswer == correctAnswer){
            resutMessageId = R.string.correct_answer;
            } else{
            resutMessageId = R.string.incorrect_answer;
        }
    }
        Toast.makeText(this, resutMessageId, Toast.LENGTH_SHORT).show();
}
    private void  setNextQuestion(){
    questionTextView.setText(questions[currentIndex].getQuestionId());
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("onSaveInstanceState", "Wywołana została metoda:onSaveInstanceState: ");
        outState.putInt(KEY_CURRENT_INDEX,currentIndex);
    }

}
