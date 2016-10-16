package dummy.bassem.com.animationsdummy;

import android.animation.Animator;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ogaclejapan.arclayout.ArcLayout;

import dummy.bassem.com.animationsdummy.helpers.AnimationsHelper;

public class ActivitySplash extends AppCompatActivity {
    ProgressBar progressBar;
    FrameLayout progressContainer;
    private final long mSplashTime = 150;
    final long progressDuration = 250;
    final long bounceFirstDuration = 350;
    final long bounceSecondDuration = 200;
    LinearLayout bounceLinearLayout;
    LinearLayout splashContainerLinearLayout;
    ImageView logoImageView;
    Handler mHandler = new Handler();

    // test git
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressContainer = (FrameLayout) findViewById(R.id.frm_progress_container);
        progressContainer.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        bounceLinearLayout = (LinearLayout) findViewById(R.id.lnr_to_bounce);
        splashContainerLinearLayout = (LinearLayout) findViewById(R.id.lnr_splash_container);
        logoImageView = (ImageView) findViewById(R.id.img_logo);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animateHelloToTop();
            }
        }, mSplashTime);


    }


    private void animateHelloToTop() {
        progressBar.setVisibility(View.VISIBLE);
        AnimationsHelper.moveUpToParent(progressBar, progressContainer, progressDuration, true, endOfMoveUpListener);
    }

    AnimationsHelper.MyAnimationsListener endOfMoveUpListener = new AnimationsHelper.MyAnimationsListener() {
        @Override
        public void onAnimationEnd(Animator animaton) {
            animateBounceLayout();
        }


    };

    private void animateBounceLayout() {

        AnimationsHelper.animateHeightToFillParent(bounceLinearLayout, splashContainerLinearLayout, bounceFirstDuration, firstBounceEndListener);
    }

    ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            progressContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);

        }
    };
    private AnimationsHelper.MyAnimationsListener firstBounceEndListener = new AnimationsHelper.MyAnimationsListener() {
        @Override
        public void onAnimationEnd(Animator animaton) {


            AnimationsHelper.animateHeight(bounceLinearLayout, bounceSecondDuration, (splashContainerLinearLayout.getHeight() - (splashContainerLinearLayout.getHeight() / 10)), secondBounceEndListener);

        }
    };
    AnimationsHelper.MyAnimationsListener secondBounceEndListener = new AnimationsHelper.MyAnimationsListener() {
        @Override
        public void onAnimationEnd(Animator animaton) {
            //Toast.makeText(ActivitySplash.this, "yo yo yo", Toast.LENGTH_SHORT).show();
        }
    };

    public void repeatButtonOnClick(View view) {
        animateHelloToTop();
    }
}
