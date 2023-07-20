package ihu.dev.travelbook.ui.bookings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import ihu.dev.travelbook.MainActivity;
import ihu.dev.travelbook.R;
import ihu.dev.travelbook.firestoredb.Customers;

public class Bookings extends Fragment {
    static int counter = 1;

    public static Bookings newInstance() {
        return new Bookings();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookings, container, false);
        CollectionReference collectionReference = MainActivity.firestoredb
                .collection("bookings");
        collectionReference
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
                    String result = "";
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Customers customers = documentSnapshot.toObject(Customers.class);
                        String costumer_name = String.valueOf(customers.getName());
                        String costumer_lastname = String.valueOf(customers.getLastname());
                        String costumer_tid = String.valueOf(customers.getTrip_id());
                        String seperator = "----------------------------------";
                        result = result + seperator + "\nFirst Name: " + costumer_name + "\n Last Name: " + costumer_lastname + "\nTrip Id: " + costumer_tid + "\n";
                    }
                    TextView textView = view.findViewById(R.id.test);
                    textView.setText(result);
                }).addOnFailureListener(e -> Toast.makeText(getActivity(), "Firestore read failed", Toast.LENGTH_SHORT).show());
        FloatingActionButton btn = view.findViewById(R.id.ordeby_bookings);
        btn.setOnClickListener(view1 -> {
            if (counter % 2 != 0) {
                counter++;
                CollectionReference collectionReference1 = MainActivity.firestoredb
                        .collection("bookings");
                collectionReference1.orderBy("name")
                        .get().addOnSuccessListener(queryDocumentSnapshots -> {
                            String result1 = "";
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                Customers customers = documentSnapshot.toObject(Customers.class);
                                String costumer_name = String.valueOf(customers.getName());
                                String costumer_lastname = String.valueOf(customers.getLastname());
                                String costumer_tid = String.valueOf(customers.getTrip_id());
                                String seperator = "----------------------------------";
                                result1 = result1 + seperator + "\nFirst Name: " + costumer_name + "\n Last Name: " + costumer_lastname +"\nTrip Id: " + costumer_tid + "\n";
                            }
                            TextView textView = view1.getRootView().findViewById(R.id.test);
                            textView.setText(result1);
                            Toast.makeText(getActivity(), "Order by Trip", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(e -> Toast.makeText(getActivity(), "Firestore read failed", Toast.LENGTH_SHORT).show());
            } else {
                counter++;
                CollectionReference collectionReference1 = MainActivity.firestoredb
                        .collection("bookings");
                collectionReference1
                        .get().addOnSuccessListener(queryDocumentSnapshots -> {
                            String result2 = "";
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                Customers customers = documentSnapshot.toObject(Customers.class);
                                String costumer_name = String.valueOf(customers.getName());
                                String costumer_lastname = String.valueOf(customers.getLastname());
                                String costumer_tid = String.valueOf(customers.getTrip_id());
                                String seperator = "----------------------------------";
                                result2 = result2 + seperator + "\nFirst Name: " + costumer_name + "\n Last Name: " + costumer_lastname +"\nTrip Id: " + costumer_tid + "\n";
                            }
                            TextView textView = view1.getRootView().findViewById(R.id.test);
                            textView.setText(result2);
                            Toast.makeText(getActivity(), "Order by Name", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(e -> Toast.makeText(getActivity(), "Firestore read failed", Toast.LENGTH_SHORT).show());
            }
        });
        return view;
    }
}