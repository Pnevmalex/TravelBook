package ihu.dev.travelbook.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import ihu.dev.travelbook.MainActivity;
import ihu.dev.travelbook.R;

public class Settings extends Fragment {

    Button btn;
    public static Settings newInstance() {
        return new Settings();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        btn = view.findViewById(R.id.containedButton);
        btn.setOnClickListener(v -> deleteFirestore());

        return view;

    }





    public static void deleteFirestore() {

        CollectionReference collectionReference = MainActivity.firestoredb
                .collection("bookings");
        collectionReference
                .get().addOnSuccessListener(queryDocumentSnapshots -> {

                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        String id = documentSnapshot.getId();
                        collectionReference.document(id).delete();
                    }
                }).addOnFailureListener(e -> {
                });

    }

}