package appewtc.masterung.osptraining;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;


public class MyMapActivity extends FragmentActivity{

    //Explicit
    private Double douLat, douLng;
    private LatLng bangkokLatLng, changmaiLatLng, khonkenLatLng,
                    osp1LatLng, osp2LatLng, osp3LatLng, osp4LatLng,
                    changMai1LatLng, changMai2LatLng, changMai3LatLng, changMai4LatLng;
    private GoogleMap objGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_map_layout);

        //Create LatLng
        createLatLng();

        //Create Map
        createMap();

        //Create Marker
        createMarker();

        //Polyline ChangMai
        polylineChangMai();

        //Polygon OSP
        polygonOSP();

        //setOnMapClick
        setOnMapClick();


    }   // onCreate

    private void setOnMapClick() {

        objGoogleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                //get Value from Long Click
                douLat = latLng.latitude;
                douLng = latLng.longitude;

                questionDilog();

            }   // event
        });

    }   // setOnMapClick

    private void questionDilog() {

        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setIcon(R.drawable.icon_question);
        objAlert.setTitle("Are You Sure ?");
        objAlert.setMessage("You Want to Confirm Position on Map");
        objAlert.setCancelable(false);


    }   //questionDialog

    private void polygonOSP() {

        PolygonOptions objPolygonOption = new PolygonOptions();
        objPolygonOption.add(osp1LatLng);
        objPolygonOption.add(osp2LatLng);
        objPolygonOption.add(osp3LatLng);
        objPolygonOption.add(osp4LatLng);
        objPolygonOption.add(osp1LatLng);
        //objPolygonOption.strokeColor(Color.BLUE);
        objPolygonOption.strokeColor(Color.argb(100, 229, 20, 242));
        objPolygonOption.strokeWidth(10);
        objPolygonOption.fillColor(Color.argb(50, 219, 245, 77));

        objGoogleMap.addPolygon(objPolygonOption);


    }   // polugonOSP

    private void polylineChangMai() {

        PolylineOptions objPolyLineOptions = new PolylineOptions();
        objPolyLineOptions.add(changMai1LatLng);
        objPolyLineOptions.add(changMai2LatLng);
        objPolyLineOptions.add(changMai3LatLng);
        objPolyLineOptions.add(changMai4LatLng);
        objPolyLineOptions.add(changMai1LatLng);
        objPolyLineOptions.width(5);
        objPolyLineOptions.color(Color.RED);

        objGoogleMap.addPolyline(objPolyLineOptions);

    }   // polylineChangMai

    private void createMarker() {

        //Mark for Center
        objGoogleMap.addMarker(new MarkerOptions()
                .position(bangkokLatLng)
                .title("โอสถสภา").snippet("บริษัทที่ผลิด M100, M150"));
        objGoogleMap.addMarker(new MarkerOptions().position(changmaiLatLng)
                .title("เชียงใหม่")
                .snippet("เมืองทางเหนือ"));
        objGoogleMap.addMarker(new MarkerOptions().position(khonkenLatLng)
                .title("ขอนแก่น")
                .snippet("เมืองอีสาน"));

        //Mark for OSP
        objGoogleMap.addMarker(new MarkerOptions()
                .position(osp1LatLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        objGoogleMap.addMarker(new MarkerOptions()
                .position(osp2LatLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        objGoogleMap.addMarker(new MarkerOptions()
                .position(osp3LatLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        objGoogleMap.addMarker(new MarkerOptions()
                .position(osp4LatLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));


        //About ChangMai
        objGoogleMap.addMarker(new MarkerOptions()
                .position(changMai1LatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build1)));
        objGoogleMap.addMarker(new MarkerOptions()
                .position(changMai2LatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build2)));
        objGoogleMap.addMarker(new MarkerOptions().position(changMai3LatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build3)));
        objGoogleMap.addMarker(new MarkerOptions()
                .position(changMai4LatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build4)));



    }   // createMarker

    public void clickBangkok(View view) {

        objGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bangkokLatLng, 17));

    }   // clickBangkok

    public void clickChangmai(View view) {

        objGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(changmaiLatLng, 16));

    }   //  clickChangmai

    public void clickKhonken(View view) {

        objGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(khonkenLatLng, 17));

    }   // clickKhonken



    private void createMap() {

        objGoogleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        objGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bangkokLatLng, 17));


    }   // createMap

    private void createLatLng() {

        //Center Map
        bangkokLatLng = new LatLng(13.760366, 100.630224);
        changmaiLatLng = new LatLng(18.788687, 98.980572);
        khonkenLatLng = new LatLng(16.431176, 102.826174);

        //About OSP
        osp1LatLng = new LatLng(13.760811, 100.629790);
        osp2LatLng = new LatLng(13.760952, 100.631099);
        osp3LatLng = new LatLng(13.753391, 100.632016);
        osp4LatLng = new LatLng(13.754355, 100.630563);

        //About ChantMai
        changMai1LatLng = new LatLng(18.789738, 98.979188);
        changMai2LatLng = new LatLng(18.789606, 98.982364);
        changMai3LatLng = new LatLng(18.787483, 98.982278);
        changMai4LatLng = new LatLng(18.787697, 98.978319);


    }   //createLatLng

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_map, menu);
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
}   // Main Class
