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
public class RegisterActivity extends Activity {

    EditText uname,uid,pwd,phone;
    Button button_register2;

    public   static SharedPreferences registerPrefrences;
    public  SharedPreferences.Editor registerPrefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        uname=(EditText)findViewById(R.id.et_username);
        uid=(EditText)findViewById(R.id.et_userId);
        pwd=(EditText)findViewById(R.id.et_password);
        phone=(EditText)findViewById(R.id.et_phonenum);

        registerPrefrences=getSharedPreferences("registerPrefs", Context.MODE_PRIVATE);
        registerPrefEditor=registerPrefrences.edit();

        button_register2=(Button)findViewById(R.id.btn_register2);
        button_register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName=uname.getText().toString();
                String userId=uid.getText().toString();
                String password=pwd.getText().toString();
                String phoneNum=phone.getText().toString();

                registerPrefEditor.putString("username",userName);
                registerPrefEditor.putString("userid",userId);
                registerPrefEditor.putString("password",password);
                registerPrefEditor.putString("phonenum",phoneNum);
                registerPrefEditor.commit();

                Toast.makeText(getApplicationContext(), "Register sucess..", Toast.LENGTH_LONG).show();

                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
}
