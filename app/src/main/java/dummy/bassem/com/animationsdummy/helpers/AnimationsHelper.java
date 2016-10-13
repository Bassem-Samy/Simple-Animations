package dummy.bassem.com.animationsdummy.helpers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

/**
 * Created by Bassem.Wissa on 10/12/2016.
 */

public class AnimationsHelper {
    static LinearInterpolator linearInterpolator = new LinearInterpolator();

    // move vertically by providing dimensions
    public static void moveVertically(final View view, float basePoint, float toPoint, long duration, final MyAnimationsListener endListener) {
        ValueAnimator animator = new ValueAnimator().ofFloat(basePoint, toPoint);
        completeVerticalAnimaion(animator, view, duration, endListener);

    }

    // move vertically to the top of parent by providing parent only no dimensions and save calculations on the UI side
    public static void moveUpToParent(final View childView, final View parentView, final long duration, final boolean fromOutsideOfParent, final MyAnimationsListener endListener) {
        if (parentView.getHeight() == 0) {
            parentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    parentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    // get heights and so;
                    continueMoveUpToParent(childView, parentView, duration, fromOutsideOfParent, endListener);

                }

            });
        } else
            continueMoveUpToParent(childView, parentView, duration, fromOutsideOfParent, endListener);
    }

    // continue moveUpToParent
    private static void continueMoveUpToParent(final View childView, final View parentView, final long duration, final boolean fromOutsideOfParent, final MyAnimationsListener endListener) {
        float childHeight = childView.getHeight();
        float parentHeight = parentView.getHeight();
        ValueAnimator animator;
        if (fromOutsideOfParent == true)
            animator = new ValueAnimator().ofFloat(childHeight, -(parentHeight - childHeight));
        else
            animator = new ValueAnimator().ofFloat(0, -(parentHeight - childHeight));
        completeVerticalAnimaion(animator, childView, duration, endListener);
    }

    // apply translate y animation
    private static void completeVerticalAnimaion(ValueAnimator animator, final View toAnimateView, final long duration, final MyAnimationsListener endListener) {
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                toAnimateView.setTranslationY(value);

            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (endListener != null)
                    endListener.onAnimationEnd(animation);

            }
        });

        animator.setInterpolator(linearInterpolator);
        animator.setDuration(duration);
        animator.start();
    }

    // Listener that takes an animator as a parameter
    public interface MyAnimationsListener {
        public void onAnimationEnd(Animator animaton);
    }
}
