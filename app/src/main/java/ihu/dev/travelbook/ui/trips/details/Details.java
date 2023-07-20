package ihu.dev.travelbook.ui.trips.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
import ihu.dev.travelbook.firestoredb.Customers;
import ihu.dev.travelbook.ui.trips.Trips;

public class Details extends Fragment {


    Button book_btn;


    public static Details newInstance() {
        return new Details();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);


        Bundle bundle = getArguments();
        assert bundle != null;
        int id = bundle.getInt("id");
        String town = bundle.getString("town");
        String country = bundle.getString("country");
        int duration = bundle.getInt("duration");
        String type = bundle.getString("type");
        double price = bundle.getDouble("price");

        TextView did = view.findViewById(R.id.details_id);
        TextView dtown = view.findViewById(R.id.details_city);
        TextView dcountry = view.findViewById(R.id.details_country);
        TextView dduration = view.findViewById(R.id.details_duration);
        TextView dtype = view.findViewById(R.id.details_type);
        TextView dprice = view.findViewById(R.id.details_price);


        did.setText(String.valueOf(id));
        dtown.setText(String.valueOf(town));
        dcountry.setText(String.valueOf(country));
        dduration.setText(String.valueOf(duration));
        dtype.setText(String.valueOf(type));
        dprice.setText(String.valueOf(price));

        TextInputLayout firstname = view.findViewById(R.id.book_first_name);
        TextInputLayout lastname = view.findViewById(R.id.book_last_name);
        TextInputLayout email = view.findViewById(R.id.book_email);
        TextInputLayout phone = view.findViewById(R.id.book_phone);


        book_btn = view.findViewById(R.id.book_trip_btn);
        book_btn.setOnClickListener(v -> {

            String bname = Objects.requireNonNull(firstname.getEditText()).getText().toString();
            String bsurname = Objects.requireNonNull(lastname.getEditText()).getText().toString();
            String bemail = Objects.requireNonNull(email.getEditText()).getText().toString();
            String bphone = Objects.requireNonNull(phone.getEditText()).getText().toString();

            Customers customer = new Customers();
            customer.setTrip_id(id);
            customer.setEmail(bemail);
            customer.setPhone(Long.parseLong(bphone));
            customer.setName(bname);
            customer.setLastname(bsurname);


            MainActivity.firestoredb.collection("bookings")
                    .document()
                    .set(customer);

            Toast.makeText(getContext(), "Thank you for the Booking!", Toast.LENGTH_SHORT).show();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Trips trips = new Trips();
            fragmentTransaction.replace(R.id.nav_host_fragment_content_main, trips);
            fragmentTransaction.commit();


        });


        return view;
    }


}