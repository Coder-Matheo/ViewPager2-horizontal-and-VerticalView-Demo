package rob.viewpager2horizontal_verticalviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //Initialize variable
    ViewPager2 vpVertical, vpHorizontal;
    int[] images = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};

    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        vpVertical = findViewById(R.id.vp_vertical);
        vpHorizontal = findViewById(R.id.vp_horizontal);

        adapter = new MainAdapter(images);

        //Set Adapter
        vpVertical.setAdapter(adapter);

        //Set clip padding
        vpHorizontal.setClipToPadding(false);
        //Set clip children
        vpHorizontal.setClipChildren(false);
        //Set page Limit
        vpHorizontal.setOffscreenPageLimit(3);
        //Set default start position
        vpHorizontal.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        //Set adapter on horizontal viewpager
        vpHorizontal.setAdapter(adapter);

        //Init composite page transformer
        CompositePageTransformer transformer = new CompositePageTransformer();
        //Add margin between page
        transformer.addTransformer(new MarginPageTransformer(8));
        //Increase selected page size
        transformer.addTransformer(new ViewPager2.PageTransformer(){

            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                //Set scale y
                page.setScaleY(0.8f + v * 0.2f);
            }
        });
        //set page transform
        vpHorizontal.setPageTransformer(transformer);



    }
}