package com.mytech.smmpanel.Admins;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.mytech.smmpanel.Admins.payment.PaymentFragment;
import com.mytech.smmpanel.Admins.services.ServicesFragment;
import com.mytech.smmpanel.Admins.user.UserFragment;
import com.mytech.smmpanel.R;
import com.mytech.smmpanel.SharedPrefrence.PrefManager;
import com.mytech.smmpanel.fragments.ServiceCmpaigns;

public class AdminMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new UserFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_Users1);
            toolbar.setTitle("Users");
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_Users1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new UserFragment()).commit();
                toolbar.setTitle("Users");
                break;
            case R.id.nav_Orders:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new UserTotalOrders()).commit();
                toolbar.setTitle("Orders");
                break;
            case R.id.nav_Services1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ServicesFragment()).commit();
                toolbar.setTitle("Services");
                break;
            case R.id.nav_Payments1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new PaymentFragment()).commit();
                toolbar.setTitle("Payments");
                break;
            case R.id.logouts:
                PrefManager prefManager=new PrefManager(getApplicationContext());
                prefManager.setToken_Email("");
                startActivity(new Intent(getApplicationContext(),SplashScreen.class));
                break;

            default:
                Toast.makeText(this, "Not Yet Implemented", Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}