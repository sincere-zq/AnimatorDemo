package sincere.com.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int[] res = {R.id.img_a, R.id.img_b, R.id.img_c, R.id.img_d,
            R.id.img_e, R.id.img_f, R.id.img_g, R.id.img_h};
    private List<ImageView> imageViewList = new ArrayList<>();
    private boolean flag = true;
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < res.length; i++) {
            ImageView imageView = (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            imageViewList.add(imageView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_a:
                if (flag) {
                    findViewById(R.id.img_a).setEnabled(false);
                    startExpand();
                    flag = false;

                } else {
                    findViewById(R.id.img_a).setEnabled(false);
                    startClose();
                    flag = true;
                }
                break;
            default:
                break;
        }
    }

    /**
     * 收缩
     */
    private void startClose() {
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(
                    imageViewList.get(i), "translationY", i * 100, 0F);
            animator.setDuration(500);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setStartDelay(i * 200);
            animator.start();
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    num++;
                    if (num == res.length - 2) {
                        num = 0;
                        findViewById(R.id.img_a).setEnabled(true);
                    }
                }
            });
        }
    }

    /**
     * 展开
     */
    private void startExpand() {
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(
                    imageViewList.get(i), "translationY", 0F, i * 100);
            animator.setDuration(500);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setStartDelay(i * 200);
            animator.start();
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    num++;
                    if (num == res.length - 2) {
                        num = 0;
                        findViewById(R.id.img_a).setEnabled(true);
                    }
                }
            });
        }
    }

}
