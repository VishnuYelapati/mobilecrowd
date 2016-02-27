package android.com.mobilecrowdlocation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by srinu on 2/24/2016.
 */
public class AboutActivity extends Activity {

    Button button_next2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about);

        button_next2=(Button)findViewById(R.id.btn_next2);
        button_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AboutActivity.this,LoginActivity.class));
            }
        });
    }
}
