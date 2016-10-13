package dummy.bassem.com.animationsdummy;

import android.animation.Animator;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ogaclejapan.arclayout.ArcLayout;

import dummy.bassem.com.animationsdummy.helpers.AnimationsHelper;

public class ActivitySplash extends AppCompatActivity {
    ProgressBar progressBar;
    FrameLayout progressContainer;
    final long progressDuration = 250;
    View arcView;
    ArcLayout arcLayout;

    // test git
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressContainer = (FrameLayout) findViewById(R.id.frm_progress_container);
        // arcView = (View) findViewById(R.id.arc_view);
        arcLayout = (ArcLayout) findViewById(R.id.arc_layout);
        progressContainer.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        animateHelloToTop();
       // initializeArc();

    }

    private void initializeArc() {
        long width = progressContainer.getWidth();
        arcLayout.setRadius((int) (1 * width));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
       // params.setMargins(0, (int) (-1 *width)+150, 0, 0);
        arcLayout.setLayoutParams(params);
    }

    private void animateHelloToTop() {
        AnimationsHelper.moveUpToParent(progressBar, progressContainer, progressDuration, true, endOfMoveUpListener);
    }

    AnimationsHelper.MyAnimationsListener endOfMoveUpListener = new AnimationsHelper.MyAnimationsListener() {
        @Override
        public void onAnimationEnd(Animator animaton) {
            Toast.makeText(ActivitySplash.this, "yo yo yo", Toast.LENGTH_SHORT).show();
        }
    };
    ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            progressContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
initializeArc();
        }
    };

    public void repeatButtonOnClick(View view) {
        animateHelloToTop();
    }
}
