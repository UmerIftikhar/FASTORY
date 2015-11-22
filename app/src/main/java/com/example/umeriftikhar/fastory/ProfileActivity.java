package com.example.umeriftikhar.fastory;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.animation.Animator.AnimatorListener;


public class ProfileActivity extends Activity {

    private ImageView pallet1;
    private ImageView pallet2;
    private ImageView pallet3;

    Boolean loadPallet = false;
    Boolean palletZone3 = false;
    Boolean palletZone5 = false;
    //private Path myPath = new Path();
    private static final int BLACK = 0xff000000;
    Button test_touch;
    private String sent_userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //final String s= getIntent().getStringExtra("useremail");
        Bundle bundle = getIntent().getExtras();
        sent_userName= bundle.getString("user_email");

        pallet1 = (ImageView) findViewById( R.id.pallet1 );
        pallet2 = (ImageView) findViewById( R.id.pallet2 );
        pallet3 = (ImageView) findViewById( R.id.pallet3 );
        //final Path myPath = new Path();
        //myPath.moveTo(450f, 275f);
        //myPath.moveTo(220f, 275f);
        //final ObjectAnimator anim = ObjectAnimator.ofFloat(mCpuImageView,"x","y", myPath);
        //anim.start();

        test_touch = (Button)findViewById(R.id.button);
        final Button button = (Button) findViewById(R.id.button);
        test_touch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            if(!loadPallet) {
                Path myPath = new Path();
                myPath.moveTo(490f, 275f);
                pallet1.setAlpha(1.0f);
                pallet1.setBackgroundColor(BLACK);
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(pallet1, "x", "y", myPath);
                //ObjectAnimator anim1 = ObjectAnimator.ofInt(mCpuImageView, "backgroundColor", BLACK);
                //ObjectAnimator anim1 = ObjectAnimator.ofInt(mCpuImageView, "backgroundColor", BLACK);
                //PropertyValuesHolder pvh = PropertyValuesHolder.ofInt("backgroundColor", BLACK);
                //anim1.setValues(pvh);
                anim1.setDuration(100);
                anim1.start();
                loadPallet = true;

            }

            }
        });

        final Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              if(loadPallet && !palletZone3) {

                    Path myPath = new Path();
                    myPath.setLastPoint(490f, 275f);
                    myPath.lineTo(430, 275f);
                    ObjectAnimator anim3 = ObjectAnimator.ofFloat(pallet1, "x", "y", myPath);
                    anim3.setDuration(2000);
                    anim3.start();
///////////////////////////////////////////////////
                  anim3.addListener(new AnimatorListener() {

                      @Override
                      public void onAnimationEnd(Animator animation) {
                          pallet1.setAlpha(0f);
                          pallet2.setAlpha(1f);
                          pallet2.setBackgroundColor(BLACK);
                          Path myPath = new Path();
                          myPath.moveTo(430f, 275f);
                          ObjectAnimator anim1 = ObjectAnimator.ofFloat(pallet2, "x", "y", myPath);
                          anim1.start();
                          loadPallet = false;
                          palletZone3 = true;

                      }

                      public void  onAnimationCancel(Animator animation){


                      }
                      public void  onAnimationRepeat(Animator animation){


                      }
                      public void  onAnimationStart(Animator animation){


                      }


                  });
///////////////////////////////////////////////////


              }

            }
        });

        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(palletZone3 && !palletZone5) {

                    Path myPath = new Path();
                    myPath.setLastPoint(430, 275f);
                    myPath.lineTo(265f, 275f);
                    ObjectAnimator anim4 = ObjectAnimator.ofFloat(pallet2, "x", "y", myPath);
                    anim4.setDuration(5000);
                    anim4.start();
                    ///////////////////////////////////////////////////
                    anim4.addListener(new AnimatorListener() {

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            pallet2.setAlpha(0f);
                            pallet3.setAlpha(1f);
                            pallet3.setBackgroundColor(BLACK);
                            Path myPath = new Path();
                            myPath.moveTo(265f, 275f);
                            ObjectAnimator anim1 = ObjectAnimator.ofFloat(pallet3, "x", "y", myPath);
                            anim1.start();
                            palletZone3 = false;
                            palletZone5	= true;

                        }

                        public void onAnimationCancel(Animator animation) {


                        }

                        public void onAnimationRepeat(Animator animation) {


                        }

                        public void onAnimationStart(Animator animation) {


                        }


                    });
///////////////////////////////////////////////////

                }
            }
        });



        final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(palletZone5) {
                    pallet3.setAlpha(0f);
                    palletZone5 = false;

                 }
            }
        });


        final Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent j=new Intent(ProfileActivity.this, UserHistoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user_email", sent_userName);
                //j.putExtra("useremail",String.valueOf(sent_userName));
                j.putExtras(bundle);
                startActivity(j);

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
