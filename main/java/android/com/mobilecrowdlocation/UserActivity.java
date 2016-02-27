package android.com.mobilecrowdlocation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by srinu on 2/24/2016.
 */
public class UserActivity extends Activity {

    TextView tv_userName;
    AutoCompleteTextView autoCompleteTextView;
    Button btn_search;
    String[] Names={"Ameerpet","Maithrivanam","SR Nagar","kukatpally","Lingampally"};
    String autoComplteText;
    DataBaseHandler dbhandler;
    GPSTracker gps;
    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user);

        Intent intent=getIntent();

        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        tv_userName=(TextView)findViewById(R.id.tv_userId);
        btn_search=(Button)findViewById(R.id.btn_search);
        tv_userName.setText("Hi...\n"+intent.getStringExtra("userId"));

        dbhandler=new DataBaseHandler(this);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,Names);

        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                autoComplteText = (String) parent.getItemAtPosition(position);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                gps = new GPSTracker(UserActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                     latitude = gps.getLatitude();
                    longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }


                if (dbhandler.getLocationDataCount() == 0) {
                    dbhandler.addLocationInfo(new LocationData(1, "kukatpally", "kukatpally,hyderabad", 17.494793, 78.399644));
                    dbhandler.addLocationInfo(new LocationData(2, "Ameerpet", "Ameerpet,hyderabad", 17.437461, 78.448288));
                    dbhandler.addLocationInfo(new LocationData(3, "Maithrivanam", "Maithrivanam,hyderabad", 17.348127, 78.485251));
                    dbhandler.addLocationInfo(new LocationData(4, "SR Nagar", "SR Nagar,hyderabad", 17.443650, 78.445826));
                    dbhandler.addLocationInfo(new LocationData(5, "Lingampally", "Lingampally,hyderabad", 17.483698, 78.315834));
                }


                // dbhandler.updatelocationDetails(new LocationData(1, "Kukatpally", "Kukatpally,Hyderabad", 17.494793,78.399644),autoComplteText);
                // LocationData data=dbhandler.getLocationData(autoComplteText);
                //String log = "Id: "+data.getCrowdloc_id()+" ,Name: " + data.getCrowdloc_name() + " ,address: " + data.getCrowdloc_address()+" ,latitude"+data.getCrowdloc_latitude()+" ,longitude"+data.getCrowdloc_longitude();
                // Writing Contacts to log
                // Log.d("Name: ", log);

               /* List<LocationData> data = dbhandler.getAllDetails();

                for (LocationData cn : data) {
                    String log = "Id: "+cn.getCrowdloc_id()+" ,Name: " + cn.getCrowdloc_name() + " ,address: " + cn.getCrowdloc_address()+" ,latitude"+cn.getCrowdloc_latitude()+" ,longitude"+cn.getCrowdloc_longitude();
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                }*/

                startActivity(new Intent(UserActivity.this, MapsActivity.class).putExtra("place", autoComplteText));
            }
        });



        Toast.makeText(getApplicationContext(),"Welcome...."+intent.getStringExtra("userId"),Toast.LENGTH_LONG).show();
    }
}
