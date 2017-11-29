package com.bignerdranch.android.geoquiz;

/**
 * Created by matrika on 11/28/2017.
 */

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private int mUserAnswer = 0;

    public int getUserAnswer() {
        return mUserAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        mUserAnswer = userAnswer;
    }

    public Question (int textResId, boolean answerTrue)
    {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
