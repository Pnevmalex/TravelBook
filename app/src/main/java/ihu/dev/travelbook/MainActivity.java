package ihu.dev.travelbook;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import ihu.dev.travelbook.databinding.ActivityMainBinding;
import ihu.dev.travelbook.roomdb.Adatabase;

public class MainActivity extends AppCompatActivity {
    public static int ofid = 0;
    public static Adatabase adatabase;
    @SuppressLint("StaticFieldLeak")
    public static FirebaseFirestore firestoredb;
    private AppBarConfiguration mAppBarConfiguration;

    public static int getOfid() {
        return ofid;
    }

    public static void setOfid(int id) {
        ofid = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firestoredb = FirebaseFirestore.getInstance();
        adatabase = Room.databaseBuilder(getApplicationContext(), Adatabase.class, "database").allowMainThreadQueries().build();

        ihu.dev.travelbook.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_trips, R.id.nav_offers,R.id.dashboard, R.id.nav_bookings, R.id.nav_admin, R.id.nav_settings, R.id.nav_about, R.id.searchTown)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}