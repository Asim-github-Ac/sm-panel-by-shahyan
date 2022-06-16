package com.mytech.smmpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.mytech.smmpanel.Admins.SplashScreen;
import com.mytech.smmpanel.SharedPrefrence.PrefManager;
import com.mytech.smmpanel.fragments.ServiceCmpaigns;
import com.mytech.smmpanel.fragments.login.LoginFragment;
import com.mytech.smmpanel.fragments.main.MainFragment;
import com.mytech.smmpanel.fragments.signup.SignUpFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        type= intent.getStringExtra("key");
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homes:
                navigationView.setCheckedItem(R.id.homes);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new MainFragment()).commit();
                break;
            case R.id.nav_Services:
                navigationView.setCheckedItem(R.id.nav_Services);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ServiceCmpaigns()).commit();
                break;
            case R.id.nav_FAQs:
                navigationView.setCheckedItem(R.id.nav_FAQs);
                break;
            case R.id.nav_Terms:
                navigationView.setCheckedItem(R.id.nav_Terms);
                break;
            case R.id.nav_Contact:
                navigationView.setCheckedItem(R.id.nav_Contact);
                break;
            case R.id.nav_API:
                navigationView.setCheckedItem(R.id.nav_API);
                break;
            case R.id.logout:
                startActivity(new Intent(getApplicationContext(), SplashScreen.class));
                PrefManager prefManager=new PrefManager(getApplicationContext());
                prefManager.setToken_Email("");
                navigationView.setCheckedItem(R.id.logout);

                break;

            case R.id.nav_AboutUs:
                navigationView.setCheckedItem(R.id.nav_AboutUs);
                break;
            default:
                Toast.makeText(this, "Not Yet Implemented", Toast.LENGTH_SHORT).show();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}