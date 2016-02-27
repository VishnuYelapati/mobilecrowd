package android.com.mobilecrowdlocation;

/**
 * Created by srinu on 2/25/2016.
 */
public class LocationData {

    int crowdloc_id;
    String crowdloc_name,crowdloc_address;
    double crowdloc_latitude,crowdloc_longitude;

    public LocationData()
    {

    }

    public LocationData(int id,String name,String address,double latitude,double longitude)
    {
        this.crowdloc_id=id;
        this.crowdloc_name=name;
        this.crowdloc_address=address;
        this.crowdloc_latitude=latitude;
        this.crowdloc_longitude=longitude;
    }

    public int getCrowdloc_id() {
        return crowdloc_id;
    }

    public void setCrowdloc_id(int id) {
        this.crowdloc_id = id;
    }
    public String getCrowdloc_name() {
        return crowdloc_name;
    }

    public void setCrowdloc_name(String name) {
        this.crowdloc_name = name;
    }

    public String getCrowdloc_address() {
        return crowdloc_address;
    }

    public void setCrowdloc_address(String address) {
        this.crowdloc_address = address;
    }

    public double getCrowdloc_latitude() {
        return crowdloc_latitude;
    }

    public void setCrowdloc_latitude(double latitude) {
        this.crowdloc_latitude = latitude;
    }

    public double getCrowdloc_longitude() {
        return crowdloc_longitude;
    }

    public void setCrowdloc_longitude(double longitude) {
        this.crowdloc_longitude = longitude;
    }
}
