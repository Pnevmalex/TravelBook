package ihu.dev.travelbook.ui.admin.dashboard.addtrips;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import ihu.dev.travelbook.MainActivity;
import ihu.dev.travelbook.R;
import ihu.dev.travelbook.roomdb.TripsEntity;

public class AddTrips extends Fragment {

    TextInputLayout town, country, type, price, duration, offer, longitude, latidute, imagLink;
    Button btn_add;

    public static AddTrips newInstance() {
        return new AddTrips();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_trips, container, false);

        btn_add = view.findViewById(R.id.add_trips_btn);
        town = view.findViewById(R.id.add_town);
        country = view.findViewById(R.id.add_country);
        type = view.findViewById(R.id.add_type);
        price = view.findViewById(R.id.add_price);
        duration = view.findViewById(R.id.add_duration);
        offer = view.findViewById(R.id.add_offer);
        longitude = view.findViewById(R.id.add_longitude);
        latidute = view.findViewById(R.id.add_latitude);
        imagLink = view.findViewById(R.id.add_link);

        btn_add.setOnClickListener(v -> {

            String trip_town = Objects.requireNonNull(town.getEditText()).getText().toString();
            String trip_country = Objects.requireNonNull(country.getEditText()).getText().toString();
            String trip_type = Objects.requireNonNull(type.getEditText()).getText().toString();
            String trip_price = Objects.requireNonNull(price.getEditText()).getText().toString();
            String trip_duration = Objects.requireNonNull(duration.getEditText()).getText().toString();
            String trip_offer = Objects.requireNonNull(offer.getEditText()).getText().toString();
            String trip_longitude = Objects.requireNonNull(longitude.getEditText()).getText().toString();
            String trip_latidute = Objects.requireNonNull(latidute.getEditText()).getText().toString();
            String trip_imageLink = Objects.requireNonNull(imagLink.getEditText()).getText().toString();

            if (!inputCheck(trip_town, trip_country, trip_type, trip_price, trip_duration, trip_offer, trip_longitude, trip_latidute, trip_imageLink)) {

                TripsEntity tripsEntity = new TripsEntity();

                tripsEntity.setCountry_name(trip_country);
                tripsEntity.setDuration(Integer.parseInt(trip_duration));
                tripsEntity.setLatitude(Double.parseDouble(trip_latidute));
                tripsEntity.setLink(trip_imageLink);
                tripsEntity.setOffer(Boolean.parseBoolean(trip_offer));
                tripsEntity.setType(trip_type);
                tripsEntity.setLongitude(Double.parseDouble(trip_longitude));
                tripsEntity.setPrice(Double.parseDouble(trip_price));
                tripsEntity.setTown_name(trip_town);
                tripsEntity.setOfficeId(MainActivity.getOfid());
                MainActivity.adatabase.tripsDao().addTrip(tripsEntity);
                succesfullAddNotification();
                Navigation.findNavController(v).navigate(R.id.action_addTrips_to_dashboard);
                Toast.makeText(getContext(), "Add Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    public boolean inputCheck(String trip_town, String trip_country, String trip_type, String trip_price, String trip_duration, String trip_offer, String trip_longitude, String trip_latidute, String trip_imageLink) {
        return trip_country.isEmpty() || trip_duration.isEmpty() || trip_type.isEmpty() || trip_price.isEmpty() || trip_longitude.isEmpty() || trip_latidute.isEmpty() || trip_imageLink.isEmpty() || trip_offer.isEmpty() || trip_town.isEmpty();
    }

    public void succesfullAddNotification() {

        NotificationChannel channel = new NotificationChannel("notificationId", "notification", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = requireActivity().getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireActivity(), "notificationId")
                .setContentText("TravelBook")
                .setSmallIcon(R.drawable.logo_travelbook)
                .setAutoCancel(true)
                .setContentText("New trip added. Check now!!!");

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getActivity());
        managerCompat.notify(999, builder.build());
    }
}