package ihu.dev.travelbook.ui.admin.signup;

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

public class Signup extends Fragment {

    Button loginBtn, regBtn;
    TextInputLayout name, address, phone, username, password;


    public static Signup newInstance() {
        return new Signup();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        loginBtn = view.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_signup_to_nav_admin));

        name = view.findViewById(R.id.reg_name);
        address = view.findViewById(R.id.reg_address);
        phone = view.findViewById(R.id.reg_phone);
        username = view.findViewById(R.id.reg_username);
        password = view.findViewById(R.id.reg_password);

        regBtn = view.findViewById(R.id.reg_btn);
        regBtn.setOnClickListener(v -> {

            String cname = Objects.requireNonNull(name.getEditText()).getText().toString();
            String caddress = Objects.requireNonNull(address.getEditText()).getText().toString();
            String cphone = Objects.requireNonNull(phone.getEditText()).getText().toString();
            String cusername = Objects.requireNonNull(username.getEditText()).getText().toString();
            String cpassword = Objects.requireNonNull(password.getEditText()).getText().toString();

            if (!checkInput(cname, caddress, cphone, cusername, cpassword)) {
                OfficesEntity office = new OfficesEntity(cname, caddress, Long.parseLong(cphone), cusername, cpassword);
                MainActivity.adatabase.officesDao().addOffice(office);
                Toast.makeText(getContext(), "Register Completed!! Please login", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.action_signup_to_nav_admin);
            }
            else {
                Toast.makeText(getContext(), "Fill all Fields", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    public boolean checkInput(String cname, String caddress, String cphone, String cusername, String cpassword) {

        return cname.isEmpty() || caddress.isEmpty() || cphone.isEmpty() || cusername.isEmpty() || cpassword.isEmpty();

    }
}