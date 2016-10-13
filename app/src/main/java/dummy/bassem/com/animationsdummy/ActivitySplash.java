package dummy.bassem.com.animationsdummy;

import android.animation.Animator;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import dummy.bassem.com.animationsdummy.helpers.AnimationsHelper;

public class ActivitySplash extends AppCompatActivity {
    TextView helloTextView;
    RelativeLayout helloContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        helloTextView = (TextView) findViewById(R.id.tv_hello);
        helloContainer = (RelativeLayout) findViewById(R.id.rltv_hello_container);
        animateHelloToTop();

    }

    private void animateHelloToTop() {
        AnimationsHelper.moveUpToParent(helloTextView, helloContainer, 1000, true, endOfMoveUpListener);
    }

    AnimationsHelper.MyAnimationsListener endOfMoveUpListener = new AnimationsHelper.MyAnimationsListener() {
        @Override
        public void onAnimationEnd(Animator animaton) {
            Toast.makeText(ActivitySplash.this, "yo yo yo", Toast.LENGTH_SHORT).show();
        }
    };

    public void repeatButtonOnClick(View view) {
        animateHelloToTop();
    }
}
