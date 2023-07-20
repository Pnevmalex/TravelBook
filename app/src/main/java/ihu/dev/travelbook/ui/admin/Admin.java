package ihu.dev.travelbook.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import ihu.dev.travelbook.MainActivity;
import ihu.dev.travelbook.R;
import ihu.dev.travelbook.roomdb.OfficesEntity;

public class Admin extends Fragment {

    Button loginBtn, regBtn;
    TextInputLayout username, password;

    public static Admin newInstance() {
        return new Admin();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        regBtn = view.findViewById(R.id.signupBtn);
        regBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_nav_admin_to_signup));

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);

        loginBtn = view.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(v -> {
            String cusername = Objects.requireNonNull(username.getEditText()).getText().toString();
            String cpassword = Objects.requireNonNull(password.getEditText()).getText().toString();
            if (cusername.isEmpty() || cpassword.isEmpty()) {
                Toast.makeText(getContext(), "Fill All Fields", Toast.LENGTH_SHORT).show();
            } else {
                OfficesEntity office = MainActivity.adatabase.officesDao().LoginOffice(cusername, cpassword);

                if (office == null) {
                    Toast.makeText(getContext(), "Login failed. Please retry", Toast.LENGTH_SHORT).show();

                } else {
                    MainActivity.setOfid(office.getId());
                    Navigation.findNavController(v).navigate(R.id.dashboard);
                    Toast.makeText(getContext(), "Login Completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}