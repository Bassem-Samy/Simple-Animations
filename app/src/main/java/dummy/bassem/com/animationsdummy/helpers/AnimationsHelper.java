package dummy.bassem.com.animationsdummy.helpers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

/**
 * Created by Bassem.Wissa on 10/12/2016.
 */

public class AnimationsHelper {
    static LinearInterpolator linearInterpolator = new LinearInterpolator();


    // move vertically by providing dimensions

    /**
     * Moves  a view vertically by providing base and to points
     *
     * @param view
     * @param basePoint
     * @param toPoint
     * @param duration
     * @param endListener
     */
    public static void moveVertically(final View view, float basePoint, float toPoint, long duration, final MyAnimationsListener endListener) {
        ValueAnimator animator = new ValueAnimator().ofFloat(basePoint, toPoint);
        completeVerticalAnimaion(animator, view, duration, endListener);

    }

    /**
     * animates height property for a view
     *
     * @param view
     * @param duration
     * @param desiredHeight
     * @param endListener
     */
    public static void animateHeight(final View view, final long duration, final float desiredHeight, final MyAnimationsListener endListener) {
        final ViewGroup.LayoutParams params = view.getLayoutParams();

        ValueAnimator animator = new ValueAnimator().ofFloat(view.getHeight(), desiredHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                params.height = (int) value;
                view.setLayoutParams(params);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (endListener != null)
                    endListener.onAnimationEnd(animation);
            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(duration);
        animator.start();
    }

    /**
     * animates a child view to fill it's parent
     *
     * @param childView
     * @param parentView
     * @param duration
     * @param endListener
     */
    public static void animateHeightToFillParent(final View childView, final View parentView, final long duration, final MyAnimationsListener endListener) {
        if (parentView.getHeight() == 0) {
            parentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    parentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    continueAnimateHeightToFillParent(childView, parentView, duration, endListener);
                }
            });
        } else
            continueAnimateHeightToFillParent(childView, parentView, duration, endListener);

    }

    /**
     * moves an element vertically to the top of parent by providing the child and parent views
     *
     * @param childView
     * @param parentView
     * @param duration
     * @param fromOutsideOfParent
     * @param endListener
     */
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

    /**
     * private method to continue animating the child to fill it's parent by height
     *
     * @param childView
     * @param parentView
     * @param duration
     * @param endListener
     */
    private static void continueAnimateHeightToFillParent(final View childView, View parentView, long duration, final MyAnimationsListener endListener) {
        final float childHeight = childView.getHeight();
        final float parentHeight = parentView.getHeight();
        float parentPadding = parentView.getPaddingTop() + parentView.getPaddingBottom();
        final ViewGroup.LayoutParams params = childView.getLayoutParams();
        ValueAnimator animator = new ValueAnimator().ofFloat(childHeight, parentHeight - parentPadding);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                params.height = (int) value;


                childView.setLayoutParams(params);


            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (endListener != null)
                    endListener.onAnimationEnd(animation);


            }
        });

        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(duration);
        animator.start();

    }


    /**
     * private method to continue moving an element up to it's parent
     *
     * @param childView
     * @param parentView
     * @param duration
     * @param fromOutsideOfParent
     * @param endListener
     */
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

    /**
     * private method to complete vertical animation on a view
     *
     * @param animator
     * @param toAnimateView
     * @param duration
     * @param endListener
     */
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

    /**
     * Moves a view horizontally
     *
     * @param toMoveView
     * @param startValue
     * @param endValue
     * @param duration
     * @param endListener
     */
    private static void moveHorizontally(final View toMoveView, float startValue, float endValue, long duration, final MyAnimationsListener endListener) {
        ValueAnimator animator = new ValueAnimator().ofFloat(startValue, endValue);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                toMoveView.setTranslationX(value);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (endListener != null)
                    endListener.onAnimationEnd(animation);
            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(duration);

        animator.start();
    }

    public static void enterHorizontally(final View toMoveView, final View parentView, final long duration, final MyAnimationsListener endListener) {
        if (parentView.getWidth() == 0) {
            parentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    parentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    continueEnterHorizontally(toMoveView, parentView, duration, endListener);
                }


            });
        } else
            continueEnterHorizontally(toMoveView, parentView, duration, endListener);
    }

    /**
     * continues entry of an element horizontally
     *
     * @param toMoveView
     * @param parentView
     * @param duration
     * @param endListener
     */
    private static void continueEnterHorizontally(final View toMoveView, View parentView, final long duration, final MyAnimationsListener endListener) {
        float startingPoint = (-1) * toMoveView.getWidth();
        float endPoint = (parentView.getWidth() / 2) - (toMoveView.getWidth() / 2);
        moveHorizontally(toMoveView, startingPoint, endPoint, duration, new MyAnimationsListener() {
            @Override
            public void onAnimationEnd(Animator animaton) {
                //doBreaksAnimation(toMoveView,);
            }
        });


    }

    public static void doBreaksAnimation(final View toMoveView, float amountToMove, long duration, MyAnimationsListener endListener) {

        final ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) toMoveView.getLayoutParams();
        ValueAnimator animator = new ValueAnimator().ofFloat(params.getMarginStart(), params.getMarginStart() + amountToMove);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                params.setMarginStart((int) value);
                Log.e("value", Float.toString(value));
                toMoveView.setLayoutParams(params);
            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        animator.start();
    }

    // Listener that takes an animator as a parameter
    public interface MyAnimationsListener {
        public void onAnimationEnd(Animator animaton);
    }
}
