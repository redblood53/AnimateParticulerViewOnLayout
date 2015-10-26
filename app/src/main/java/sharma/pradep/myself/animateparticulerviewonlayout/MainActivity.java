package sharma.pradep.myself.animateparticulerviewonlayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;
    ImageView imageView;
    // here we save initial postion of button so we can come again
    // after moving
    int buttonxIntial,buttonYinitial;

    String key="breckPoint";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(key,"activity created");
        imageView= (ImageView) findViewById(R.id.screenView);
        imageView.setVisibility(View.INVISIBLE);
        imageButton= (ImageButton) findViewById(R.id.imageButton);


    }

    private void show(View view) {
        Log.e(key, "Show called");
        int x=view.getWidth()/2;
        int y=view.getHeight()/2;
        int rad=Math.max(view.getWidth(),view.getHeight());
         Animator anim= ViewAnimationUtils.createCircularReveal(view,x,y,0,rad);
        anim.setDuration(1000);
        view.setVisibility(View.VISIBLE);
        anim.start();
        Log.e(key, "Show end");
    }


    //here i am override back buuton so when i back my button can go
    //to its orginal position
    @Override
    public void onBackPressed() {
        Log.e(key, "back called");
        hide(imageView,imageButton);
        Log.e(key, "back end");
    }

    private void hide(final ImageView view, final ImageButton button) {
        Log.e(key, "hide called");
        int x=view.getWidth()/2;
        int y=view.getHeight()/2;
        int rad=Math.max(view.getWidth(),view.getHeight());
        Animator anim= ViewAnimationUtils.createCircularReveal(view,x,y,rad,0);
        anim.setDuration(1000);
        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e(key, "hide animation end");
                //super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
                Log.e(key, "image button gone");
             ViewPropertyAnimator propertyAnimator=   button.animate().x(buttonxIntial).y(buttonYinitial).setDuration(1000);
                propertyAnimator.setListener(new AnimatorListenerAdapter() {
                    /**
                     * {@inheritDoc}
                     *
                     * @param animation
                     */
                    @Override
                    public void onAnimationEnd(Animator animation) {
                      //  super.onAnimationEnd(animation);
                    }
                });

            }
        });
        anim.start();
        Log.e(key, "hide called");
    }
    public void clickEvent(View v)
    {
        Log.e(key,"button is clicked");

        buttonxIntial= (int) v.getPivotX();
        buttonYinitial= (int) v.getPivotY();
        final ViewPropertyAnimator anim1= v.animate().x((imageView.getWidth()/2)-20).y((imageView.getHeight()/2)-15).setDuration(1000);
            anim1.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    show(imageView);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    //  anim1.cancel();
                }
            });
            //show(imageView);
    }
}
