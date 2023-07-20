package ihu.dev.travelbook.ui;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

import ihu.dev.travelbook.MainActivity;
import ihu.dev.travelbook.R;
import ihu.dev.travelbook.roomdb.TripsEntity;
import ihu.dev.travelbook.ui.admin.dashboard.DashboardRecyclerViewAdapter;
import ihu.dev.travelbook.ui.trips.TripsMap;
import ihu.dev.travelbook.ui.trips.details.Details;

public class SearchTown extends Fragment {

    TextInputLayout citySearch;

    public static SearchTown newInstance() {
        return new SearchTown();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_town, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.searchtrips);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        final DashboardRecyclerViewAdapter adapter = new DashboardRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        citySearch = view.findViewById(R.id.search_town);


        view.findViewById(R.id.search_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (citySearch.getEditText().length()==0) {

                    Toast.makeText(getContext(), "Type your City", Toast.LENGTH_SHORT).show();
                } else {
                    String city = Objects.requireNonNull(citySearch.getEditText()).getText().toString();
                   List<TripsEntity>  list =MainActivity.adatabase.tripsDao().getTripsByNameList(city);
                    if (list.isEmpty()){
                        MainActivity.adatabase.tripsDao().getTripsByName(city).observe(getViewLifecycleOwner(), adapter::setTrips);
                        Toast.makeText(getContext(), "No such City !", Toast.LENGTH_SHORT).show();
                    }else {
                        MainActivity.adatabase.tripsDao().getTripsByName(city).observe(getViewLifecycleOwner(), adapter::setTrips);
                    }
                }
            }
        });



        //MainActivity.adatabase.tripsDao().getTrips().observe(getViewLifecycleOwner(), adapter::setTrips);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


                double longitude = adapter.getTrip(viewHolder.getAbsoluteAdapterPosition()).getLongitude();
                double latitude = adapter.getTrip(viewHolder.getAbsoluteAdapterPosition()).getLatitude();

                Bundle bundle = new Bundle();
                bundle.putDouble("longitude", longitude);
                bundle.putDouble("latitude", latitude);


                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TripsMap tripsMap = new TripsMap();
                tripsMap.setArguments(bundle);
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, tripsMap);
                fragmentTransaction.commit();

            }
        }).attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListener(trips -> {

            int id = trips.getTrip_id();
            String town = trips.getTown_name();
            String country = trips.getCountry_name();
            int duration = trips.getDuration();
            String type = trips.getType();
            double price = trips.getPrice();
            double latitude = trips.getLatitude();
            double longitude = trips.getLongitude();
            boolean offer = trips.getOffer();
            String link = trips.getLink();


            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            bundle.putString("town", town);
            bundle.putString("country", country);
            bundle.putInt("duration", duration);
            bundle.putString("type", type);
            bundle.putDouble("price", price);
            bundle.putDouble("latitude", latitude);
            bundle.putDouble("longitude", longitude);
            bundle.putBoolean("offer", offer);
            bundle.putString("link", link);


            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Details details = new Details();
            details.setArguments(bundle);
            fragmentTransaction.replace(R.id.nav_host_fragment_content_main, details);
            fragmentTransaction.commit();


        });

        return view;
    }



}