package tech.shreshth.oneword.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import tech.shreshth.oneword.R;
import tech.shreshth.oneword.customview.TypeWriter;

public class SplashScreenActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private DisplayMetrics displayMetrics;
    private ImageView earthLogoImageView;
    private EditText editText;
    private Button searchTopicButton;
    private LinearProgressIndicator progressIndicator;
    private long NEXT_SCREEN_TIMEOUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        displayMetrics= new DisplayMetrics();
        relativeLayout=findViewById(R.id.splash_screen_root_view);
        earthLogoImageView=findViewById(R.id.earth_logo_image_view);
        editText=findViewById(R.id.input_keyword_edit_text);
        searchTopicButton=findViewById(R.id.search_topic_btn);
        progressIndicator=findViewById(R.id.linear_progress_bar);
        progressIndicator.getProgressDrawable().setColorFilter(getResources().getColor(R.color.teal_200), PorterDuff.Mode.SRC_IN);
        rotateEarth();
        for(int x=0;x<5;x++)
            addCircles((5+x)*1000,770+x*80);
        Animation bottom_up=AnimationUtils.loadAnimation(this,R.anim.bottom_up);
        TypeWriter writer = new TypeWriter(this);
        relativeLayout.addView(writer);
//        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        layoutParams.setMargins(10,10,10,10);
        writer.setPadding(120,100,100,100);
        writer.setTextColor(ContextCompat.getColor(this,R.color.teal_200));
        writer.setTextSize(TypedValue.COMPLEX_UNIT_SP,48);
//        writer.setPadding(10,10,10,10);
        writer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        writer.setCharacterDelay(200);
        writer.setTypeface(writer.getTypeface(), Typeface.BOLD);
        writer.animateText("One\nWord a\nDay");
        editText.startAnimation(bottom_up);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()<=2) {
                    if(searchTopicButton.getVisibility()==View.VISIBLE)
                        searchTopicButton.setVisibility(View.GONE);

                }
                else {
                    if (searchTopicButton.getVisibility() != View.VISIBLE) {
                        searchTopicButton.setVisibility(View.VISIBLE);
                        Animation pulse=AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.pulse);
                        searchTopicButton.startAnimation(pulse);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTopicButton.setVisibility(View.GONE);
                progressIndicator.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent listActivityIntent=new Intent(SplashScreenActivity.this, MainActivity.class);
                        listActivityIntent.putExtra("query",editText.getText().toString());
                        startActivity(listActivityIntent);
                    }
                },NEXT_SCREEN_TIMEOUT);
            }
        });
    }

    public void rotateEarth()
    {
        earthLogoImageView.setX(-128);
        earthLogoImageView.setY(-128);
    }

    public void addCircles(long duration,int radius)
    {
            View view=new View(this);
            view.setLayoutParams(new ViewGroup.LayoutParams(20,20));
            view.setBackgroundColor(Color.RED);
            relativeLayout.addView(view);
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            Path path=new Path();
            path.addCircle(0,0,radius,Path.Direction.CCW);
            ObjectAnimator animator=ObjectAnimator.ofFloat(view,View.X,View.Y,path);
            animator.setRepeatMode(ValueAnimator.REVERSE);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setDuration(duration);
            animator.start();
        }


        public void resetScreen()
        {
            editText.setText("");
            searchTopicButton.setVisibility(View.GONE);
            progressIndicator.setVisibility(View.GONE);
        }

    @Override
    protected void onResume() {
        super.onResume();
        resetScreen();

    }
}