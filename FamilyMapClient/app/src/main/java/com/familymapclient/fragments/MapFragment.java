package com.familymapclient.fragments;

import android.content.Intent;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.familymapclient.UserData;
import com.familymapclient.activities.FilterActivity;
import com.familymapclient.activities.MainActivity;
import com.familymapclient.R;
import com.familymapclient.activities.SearchActivity;
import com.familymapclient.activities.SettingsActivity;
import com.familymapclient.net.HttpClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

import lib.models.Event;
import lib.models.Person;
import lib.responses.PersonResult;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_BLUE;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_ORANGE;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_YELLOW;

public class MapFragment extends Fragment implements GoogleMap.OnMarkerClickListener {
    private MainActivity main_activity;
    private View view;

    private static final String TAG = "LOGIN_FRAGMENT";
    public static final String ARG_TITLE = "title";
    private GoogleMap googleMap;

    HashMap<Marker, Event> marker_list = new HashMap<>();
    ArrayList<Event> filtered_event_list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.map_fragment, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.google_map);
        assert mapFragment != null;
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                googleMap = map;

                placeMarkers();
            }
        });
        return view;
    }


    private void placeMarkers(){
        googleMap.setOnMarkerClickListener(this);

        UserData user_data = UserData.getInstance();
        filtered_event_list = user_data.getFilteredEvents();
        for (Event e : filtered_event_list){
            double lat = Double.parseDouble(e.latitude);
            double lon = Double.parseDouble(e.longitude);
            LatLng latLng = new LatLng(lat, lon);

            float color = 0;
            switch (e.eventType) {
                case "birth":
                    color = HUE_BLUE;
                    break;
                case "death":
                    color = HUE_RED;
                    break;
                case "marriage":
                    color = HUE_YELLOW;
                    break;
                default:
                    color = HUE_ORANGE;
            }

            Marker new_marker = googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(color)));
            marker_list.put(new_marker, e);
        }
    }

    private void placeLines(Event main_event){
        Person associated_person = null;
        for (Person p : UserData.getInstance().person_list){
            if (p.personID.equals(main_event.personID))
                associated_person = p;
        }
        assert associated_person != null;

        for (Event e : filtered_event_list){

        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) { //todo: consider making it final Marker marker
        Event event = marker_list.get(marker);

        placeLines(event);
        updateInfoWindow(event);
        return false;
    }

    private void updateInfoWindow(Event e){
        TextView info_text = (TextView)view.findViewById(R.id.text_info);

        Person associated_person = null;
        for (Person p : UserData.getInstance().person_list){
            if (p.personID.equals(e.personID))
                associated_person = p;
        }
        assert associated_person != null;

        info_text.setText(
                String.format("%s %s\n%s: %s, %s (%s)",
                        associated_person.firstName, associated_person.lastName,
                        e.eventType.toUpperCase(), e.city, e.country, e.year));
        ImageView gender_icon = (ImageView)view.findViewById(R.id.icon_gender);
        if (associated_person.gender.equals("m"))
            gender_icon.setImageResource(R.drawable.man_icon);
        else
            gender_icon.setImageResource(R.drawable.woman_icon);
    }




    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.maps_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        int item_id = item.getItemId();

        UserData instance = UserData.getInstance();

        if (item_id == R.id.menu_search)
            intent = new Intent(getActivity(), SearchActivity.class);
        else if (item_id == R.id.menu_filter)
            intent = new Intent(getActivity(), FilterActivity.class);
        else
            intent = new Intent(getActivity(), SettingsActivity.class);

        startActivity(intent);
        return true;
    }
}
