package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mScoreButton;


    private TextView mQuestionTextView ;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Question[] mQuestionBank = new Question []
            {
                    new Question (R.string.question_australia, true),
                    new Question (R.string.question_oceans, true),
                    new Question (R.string.question_mideast, true),
                    new Question (R.string.question_africa, true),
                    new Question (R.string.question_americas, false),
                    new Question (R.string.question_asia, true),

            };
    private int mCurrentIndex = 0;

    private void updateQuestion ()
    {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void nextQuestion ()
    {
        mCurrentIndex = ( mCurrentIndex  + 1) % mQuestionBank.length;
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

    }

    private void setAnswerButtons()
    {
        if (mQuestionBank[mCurrentIndex].getUserAnswer() != 0)
        {
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }
        else
        {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);

        }
    }

    private void prevQuestion()
    {
        if (mCurrentIndex > 0)
            mCurrentIndex =  (mCurrentIndex - 1) % mQuestionBank.length;
        else
            mCurrentIndex = mQuestionBank.length - 1 ;

        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void finalScore()
    {
        int correctAnswerCount = 0;
        int inCorrectAnswerCount = 0;
        int missedAnswerCount = 0;
        int totalScore = 0;

        for (int i = 0; i < mQuestionBank.length; i++)
        {
            if (mQuestionBank[i].getUserAnswer() == 1)
                correctAnswerCount++;
            else
                if (mQuestionBank[i].getUserAnswer() == 2)
                    inCorrectAnswerCount++;
                else
                    missedAnswerCount++;
        }

        float percentage = ((float)correctAnswerCount / mQuestionBank.length ) * 100;
        String message = "Correct:" + correctAnswerCount + ", Incorrect:" + inCorrectAnswerCount + ", Missed: " + missedAnswerCount + "Total: " + mQuestionBank.length   + "P=" + percentage ;

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    private void checkAnswer(boolean userPressedTrue)
    {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            mQuestionBank[mCurrentIndex].setUserAnswer(1);
            messageResId = R.string.correct_toast;
        }
        else {
            mQuestionBank[mCurrentIndex].setUserAnswer(2);
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Log.d(TAG, "onCreate called");

        if (savedInstanceState != null)
        {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        updateQuestion();

        mScoreButton = (Button)findViewById(R.id.score_button);
        mScoreButton.setOnClickListener(new
                                               View.OnClickListener() {
                                                   @Override
                                                   public void onClick (View v)
                                                   {
                                                        finalScore();
                                                       //mTrueButton.setEnabled(false);
                                                   }

                                               }
        );

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new
                View.OnClickListener() {
            @Override
                    public void onClick (View v)
            {
                //mTrueButton.setEnabled(false);
                checkAnswer(true);
                setAnswerButtons();
            }

                }
        );

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new
        View.OnClickListener(){
            @Override
                public void onClick (View v)
            {
                //mFalseButton.setEnabled(false);
                checkAnswer(false);
                setAnswerButtons();
            }
        });

        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new
        View.OnClickListener(){
            @Override
                public void onClick (View v)
            {
                nextQuestion();
                setAnswerButtons();
            }
        });

        mPrevButton = (Button)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new
        View.OnClickListener(){
            @Override
                public void onClick(View v)
            {
                prevQuestion();
                setAnswerButtons();
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "onstart called");

    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG, "on resume");

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "on saved instance state");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);

    }
    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"on pause");
    }
    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(TAG, "on stop");
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "on resume");
    }
}
