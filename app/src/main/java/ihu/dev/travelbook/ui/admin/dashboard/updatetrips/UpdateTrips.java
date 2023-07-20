package ihu.dev.travelbook.ui.admin.dashboard.updatetrips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import ihu.dev.travelbook.MainActivity;
import ihu.dev.travelbook.R;
import ihu.dev.travelbook.roomdb.TripsEntity;
import ihu.dev.travelbook.ui.admin.dashboard.Dashboard;

public class UpdateTrips extends Fragment {

    Button update_btn;
    Button delete_btn;

    public static UpdateTrips newInstance() {
        return new UpdateTrips();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_update_trips, container, false);


        delete_btn = view.findViewById(R.id.delete_trips_btn);


        Bundle bundle = getArguments();
        assert bundle != null;
        int id = bundle.getInt("id");
        String town = bundle.getString("town");
        String country = bundle.getString("country");
        int duration = bundle.getInt("duration");
        String type = bundle.getString("type");
        double price = bundle.getDouble("price");
        double latitude = bundle.getDouble("latitude");
        double longitude = bundle.getDouble("longitude");
        boolean offer = bundle.getBoolean("offer");
        String link = bundle.getString("link");
        TextInputLayout uid = view.findViewById(R.id.update_id);
        TextInputLayout utown = view.findViewById(R.id.update_town);
        TextInputLayout ucountry = view.findViewById(R.id.update_country);
        TextInputLayout uduration = view.findViewById(R.id.update_duration);
        TextInputLayout utype = view.findViewById(R.id.update_type);
        TextInputLayout uprice = view.findViewById(R.id.update_price);
        TextInputLayout ulatitude = view.findViewById(R.id.update_latitude);
        TextInputLayout ulongitude = view.findViewById(R.id.update_longitude);
        TextInputLayout uoffer = view.findViewById(R.id.update_offer);
        TextInputLayout ulink = view.findViewById(R.id.update_link);

        Objects.requireNonNull(uid.getEditText()).setText(String.valueOf(id));
        Objects.requireNonNull(utown.getEditText()).setText(String.valueOf(town));
        Objects.requireNonNull(ucountry.getEditText()).setText(String.valueOf(country));
        Objects.requireNonNull(uduration.getEditText()).setText(String.valueOf(duration));
        Objects.requireNonNull(utype.getEditText()).setText(String.valueOf(type));
        Objects.requireNonNull(uprice.getEditText()).setText(String.valueOf(price));
        Objects.requireNonNull(ulatitude.getEditText()).setText(String.valueOf(latitude));
        Objects.requireNonNull(ulongitude.getEditText()).setText(String.valueOf(longitude));
        Objects.requireNonNull(uoffer.getEditText()).setText(String.valueOf(offer));
        Objects.requireNonNull(ulink.getEditText()).setText(String.valueOf(link));


        update_btn = view.findViewById(R.id.update_trips_btn);
        update_btn.setOnClickListener(v -> {

            String trip_id = uid.getEditText().getText().toString();
            String trip_town = utown.getEditText().getText().toString();
            String trip_country = ucountry.getEditText().getText().toString();
            String trip_type = utype.getEditText().getText().toString();
            String trip_price = uprice.getEditText().getText().toString();
            String trip_duration = uduration.getEditText().getText().toString();
            String trip_offer = uoffer.getEditText().getText().toString();
            String trip_longitude = ulongitude.getEditText().getText().toString();
            String trip_latitude = ulatitude.getEditText().getText().toString();
            String trip_imageLink = ulink.getEditText().getText().toString();


            if (!inputCheck(trip_town, trip_country, trip_type, trip_price, trip_duration, trip_offer, trip_longitude, trip_latitude, trip_imageLink)) {

                TripsEntity tripsEntity = new TripsEntity();
                tripsEntity.setTrip_id(id);
                tripsEntity.setCountry_name(trip_country);
                tripsEntity.setDuration(Integer.parseInt(trip_duration));
                tripsEntity.setLatitude(Double.parseDouble(trip_latitude));
                tripsEntity.setLongitude(Double.parseDouble(trip_longitude));
                tripsEntity.setLink(trip_imageLink);
                tripsEntity.setOffer(Boolean.parseBoolean(trip_offer));
                tripsEntity.setType(trip_type);

                tripsEntity.setPrice(Double.parseDouble(trip_price));
                tripsEntity.setTown_name(trip_town);
                tripsEntity.setOfficeId(MainActivity.getOfid());
                MainActivity.adatabase.tripsDao().updateTrip(tripsEntity);


                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Dashboard dashboard = new Dashboard();
                dashboard.setArguments(bundle);
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, dashboard);
                fragmentTransaction.commit();


                Toast.makeText(getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();

            }


        });


        delete_btn.setOnClickListener(v -> {
            TextInputLayout uid1 = view.findViewById(R.id.update_id);
            int Id = Integer.parseInt(Objects.requireNonNull(uid1.getEditText()).getText().toString());
            TripsEntity tripsEntity = MainActivity.adatabase.tripsDao().getTripById(id);
            MainActivity.adatabase.tripsDao().deleteTrip(tripsEntity);

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Dashboard dashboard = new Dashboard();
            dashboard.setArguments(bundle);
            fragmentTransaction.replace(R.id.nav_host_fragment_content_main, dashboard);
            fragmentTransaction.commit();

        });


        return view;
    }


    public boolean inputCheck(String trip_town, String trip_country, String trip_type, String trip_price, String trip_duration, String trip_offer, String trip_longitude, String trip_latitude, String trip_imageLink) {

        return trip_country.isEmpty() || trip_duration.isEmpty() || trip_type.isEmpty() || trip_price.isEmpty() || trip_longitude.isEmpty() || trip_latitude.isEmpty() || trip_imageLink.isEmpty() || trip_offer.isEmpty() || trip_town.isEmpty();
    }


}