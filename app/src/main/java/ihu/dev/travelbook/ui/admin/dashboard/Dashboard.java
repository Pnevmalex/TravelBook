package ihu.dev.travelbook.ui.admin.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ihu.dev.travelbook.MainActivity;
import ihu.dev.travelbook.R;
import ihu.dev.travelbook.roomdb.OfficesEntity;
import ihu.dev.travelbook.ui.admin.Admin;
import ihu.dev.travelbook.ui.admin.dashboard.updatetrips.UpdateTrips;

public class Dashboard extends Fragment {

    public static Dashboard newInstance() {
        return new Dashboard();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.trip_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        final DashboardRecyclerViewAdapter adapter = new DashboardRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        MainActivity.adatabase.tripsDao().getTripByOfficeId(MainActivity.getOfid()).observe(getViewLifecycleOwner(), adapter::setTrips);

        view.findViewById((R.id.delete_office)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              OfficesEntity deleteOffice = MainActivity.adatabase.officesDao().getOfficeById(MainActivity.getOfid());
              MainActivity.adatabase.officesDao().deleteOffice(deleteOffice);


                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Admin login = new Admin();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, login);
                fragmentTransaction.commit();

                Toast.makeText(getContext(), "Your Office Deleted", Toast.LENGTH_SHORT).show();

            }
        });





        view.findViewById(R.id.addTrips).setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.addTrips));


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
            UpdateTrips updateTrips = new UpdateTrips();
            updateTrips.setArguments(bundle);
            fragmentTransaction.replace(R.id.nav_host_fragment_content_main, updateTrips);
            fragmentTransaction.commit();
        });
        return view;
    }
}