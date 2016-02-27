package android.com.mobilecrowdlocation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by srinu on 2/24/2016.
 */
public class LoginActivity extends Activity {

    Button button_login,button_register;
    EditText userid,password;
    String uid,pwd;

    public  static SharedPreferences loginpref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        userid=(EditText)findViewById(R.id.et_userid);
        password=(EditText)findViewById(R.id.et_Password);

        button_login=(Button)findViewById(R.id.btn_login);
        button_register=(Button)findViewById(R.id.btn_register);

        loginpref = getSharedPreferences("registerPrefs", Context.MODE_PRIVATE);


        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userId=userid.getText().toString();
                String Pwd=password.getText().toString();

                uid=loginpref.getString("userid","");
                pwd=loginpref.getString("password","");

                if(userId.equals(uid)&&Pwd.equals(pwd))
                {
                    startActivity(new Intent(LoginActivity.this,UserActivity.class).putExtra("userId",uid));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Login Fails", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
