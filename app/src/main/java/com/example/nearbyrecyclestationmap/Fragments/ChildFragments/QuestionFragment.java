package com.example.nearbyrecyclestationmap.Fragments.ChildFragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nearbyrecyclestationmap.Activity.LoginActivity;
import com.example.nearbyrecyclestationmap.Activity.QuizQuestionModel;
import com.example.nearbyrecyclestationmap.R;
import com.example.nearbyrecyclestationmap.databinding.FragmentQuestionBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class QuestionFragment extends Fragment {
    public static final String EXTRA_SCORE = "extraScore";

    private FragmentQuestionBinding binding;
    private QuestionFragmentArgs questionFragmentArgs;
    private List<QuizQuestionModel> questionsList;
    private QuizQuestionModel currentQuestion;

    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftMillis;

    int totalQuestions;
    int qCounter = 0;
    int score;

    ColorStateList dfRbColor;
    boolean answered;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuestionBinding.inflate(inflater, container, false);
        questionFragmentArgs = QuestionFragmentArgs.fromBundle(requireArguments());
        questionsList = new ArrayList<>();

        dfRbColor = binding.rb1.getTextColors();
        textColorDefaultCd = binding.textTimer.getTextColors();

        addQuestions();
        totalQuestions = questionsList.size();
        showNextQuestion();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answered == false){
                    if (binding.rb1.isChecked() || binding.rb2.isChecked() || binding.rb3.isChecked() || binding.rb4.isChecked()){
                        checkAnswer();
                    }else {
                        Toast.makeText(requireContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    showNextQuestion();
                }
            }
        });

        return binding.getRoot();
    }

    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        int rbSelected = binding.radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) getView().findViewById(rbSelected);
        int answerNo = binding.radioGroup.indexOfChild(radioButton) + 1;

        if (answerNo == currentQuestion.getCorrectAnsNo()){
            score++;
            binding.textScore.setText("Score: " + score);
            Toast.makeText(requireContext(), "Yes, you're right!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Opps, wrong answer!", Toast.LENGTH_SHORT).show();
        }

        binding.rb1.setTextColor(Color.RED);
        binding.rb2.setTextColor(Color.RED);
        binding.rb3.setTextColor(Color.RED);
        binding.rb4.setTextColor(Color.RED);
        switch (currentQuestion.getCorrectAnsNo()){
            case 1:
                binding.rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                binding.rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                binding.rb3.setTextColor(Color.GREEN);
                break;
            case 4:
                binding.rb4.setTextColor(Color.GREEN);
                break;
        }
        if (qCounter < totalQuestions){
            binding.btnNext.setText("Next");
        }else {
            binding.btnNext.setText("Finish");
        }
    }

    private void showNextQuestion() {

        binding.radioGroup.clearCheck();
        binding.rb1.setTextColor(dfRbColor);
        binding.rb2.setTextColor(dfRbColor);
        binding.rb3.setTextColor(dfRbColor);
        binding.rb4.setTextColor(dfRbColor);
        binding.radioGroup.clearCheck();

        if (qCounter < totalQuestions)
        {
            currentQuestion = questionsList.get(qCounter);

            binding.textQuestion.setText(currentQuestion.getQuestion());
            binding.rb1.setText(currentQuestion.getOption1());
            binding.rb2.setText(currentQuestion.getOption2());
            binding.rb3.setText(currentQuestion.getOption3());
            binding.rb4.setText(currentQuestion.getOption4());

            switch (qCounter) {
                case 1:
                    // setting up image for each question
                    binding.image.setImageResource(R.drawable.f2);
                    break;
                case 2:
                    binding.image.setImageResource(R.drawable.f3);
                    break;
                case 3:
                    binding.image.setImageResource(R.drawable.f4);
                    break;
                case 4:
                    binding.image.setImageResource(R.drawable.f5);
                    break;
                case 5:
                    binding.image.setImageResource(R.drawable.f6);
                    break;
                case 6:
                    binding.image.setImageResource(R.drawable.f7);
                    break;
                case 7:
                    binding.image.setImageResource(R.drawable.f8);
                    break;
                case 8:
                    binding.image.setImageResource(R.drawable.f9);
                    break;
                case 9:
                    binding.image.setImageResource(R.drawable.f10);
                    break;
                case 10:
                    binding.image.setImageResource(R.drawable.f11);
                    break;
                case 11:
                    binding.image.setImageResource(R.drawable.f12);
                    break;
                case 12:
                    binding.image.setImageResource(R.drawable.f13);
                    break;
                case 13:
                    binding.image.setImageResource(R.drawable.f14);
                    break;
                case 14:
                    binding.image.setImageResource(R.drawable.f15);
                    break;
                case 15:
                    binding.image.setImageResource(R.drawable.f16);
                    break;
                case 16:
                    binding.image.setImageResource(R.drawable.f17);
                    break;
            }

            qCounter++;
            binding.btnNext.setText("Submit");
            binding.textQuestionNo.setText("Question: " + qCounter + "/" + totalQuestions);
            answered = false;

            timeLeftMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }else {
            // End quiz
            QuestionFragmentDirections.ActionQuestionFragmentToBtnQuiz directions =
                    QuestionFragmentDirections.actionQuestionFragmentToBtnQuiz();

            Navigation.findNavController(getView()).navigate(directions);
            Toast.makeText(requireContext(), "Total score: " + score, Toast.LENGTH_SHORT).show();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftMillis / 1000) / 60;
        int seconds = (int) (timeLeftMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        binding.textTimer.setText(timeFormatted);

        if (timeLeftMillis < 10000) {
            binding.textTimer.setTextColor(Color.RED);
        } else {
            binding.textTimer.setTextColor(textColorDefaultCd);
        }
    }

    private void addQuestions() {
        questionsList.add(new QuizQuestionModel("What is recycling?", "Burying rubbish", "Reusing rubbish", "Burning rubbish", "Ignoring rubbish", 2));
        questionsList.add(new QuizQuestionModel("What does recycling help us to protect?", "Oceans", "Ice caps", "Rain forests", "The whole world", 4));
        questionsList.add(new QuizQuestionModel("Recycling helps us to produce less:", "Pollution", "Chocolate", "Water", "Traffic", 1));
        questionsList.add(new QuizQuestionModel("How much plastic goes into the sea each year?", "8 tons", "800 tons", "8,000 tons", "8,000,000 tons", 4));
        questionsList.add(new QuizQuestionModel("How much of the world's plastic has been recycled?", "Less than 10%", "Around 20%", "Around 40%", "More than 50%", 1));
        questionsList.add(new QuizQuestionModel("How long does a plastic bottle take to decompose (break down)?", "Around 60 years", "Around 280 years", "Around 450 years", "Around 720 years", 3));
        questionsList.add(new QuizQuestionModel("What can recycled plastic bottles be turned into?", "Clothes", "Jelly beans", "Trees", "Cans", 1));
        questionsList.add(new QuizQuestionModel("Which of the following symbols means recycling?", "A round wheel", "Three arrows", "A smiley planet", "Yin Yang", 2));
        questionsList.add(new QuizQuestionModel("Which of these is recycled most around the world?", "Plastic bottles", "Juice cartons", "Aluminium cans", "Glass bottles", 3));
        questionsList.add(new QuizQuestionModel("Which country recycles the most?", "Germany", "Australia", "China", "Mexico", 1));
        questionsList.add(new QuizQuestionModel("What are the 3R's of recycling?", "Redirect, Rude, Round", "Reduce, Reuse, Recycle", "Respectful, Responsible, Right", "Rewrite, rewind, respond", 2));
        questionsList.add(new QuizQuestionModel("Are the plastic bottle tops recyclable?", "No", "Only in some states", "Yes", "Yes, but only if still attached to their bottle", 1));
        questionsList.add(new QuizQuestionModel("How many times can glass be recycled?", "10 times", "Indefinitely", "80 times", "20 times", 2));
        questionsList.add(new QuizQuestionModel("How long does it take to recycle an aluminum can?", "7 days", "1 month", "2 months", "6 months", 3));
        questionsList.add(new QuizQuestionModel("Which of these numbers is not recycle?", "1", "3", "5", "7", 4));
        questionsList.add(new QuizQuestionModel("Which of these items shouldn’t go in the compost heap?", "Coffee grounds", "Vacuum cleaner waste", "White paper bags", "Meat scraps", 4));
        questionsList.add(new QuizQuestionModel("Which of the following help stop plastic pollution?", "Reuse Shopping Bags", "Use steel straws", "Pick up plastic litter", "All of thems", 4));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}