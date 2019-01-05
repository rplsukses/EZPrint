package com.rplsukses.ezprint.ui.helper;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.ui.activity.HelpActivity;
import com.rplsukses.ezprint.ui.activity.InfoActivity;
import com.rplsukses.ezprint.ui.activity.MainActivity;
import com.rplsukses.ezprint.ui.activity.OrderActivity;
import com.rplsukses.ezprint.ui.activity.ProfileActivity;


public class DrawerMenuHelper {
    // Method ini dibuat agar tidak perlu di tulis di setiap activity
    public static NavigationView navListener(final AppCompatActivity activity, NavigationView nav, final DrawerLayout drawer){
        // Set navigation item selected listener
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navHome:
                        activity.startActivity(new Intent(activity, MainActivity.class));
                        break;
                    case R.id.navProfile:
                        activity.startActivity(new Intent(activity, ProfileActivity.class));
                        break;
                    case R.id.navOrder:
                        activity.startActivity(new Intent(activity, OrderActivity.class));
                        break;
                    case R.id.navHelp:
                        activity.startActivity(new Intent(activity, HelpActivity.class));
                        break;
                    case R.id.navAboutus:
                        activity.startActivity(new Intent(activity, InfoActivity.class));
                        break;
                }

                // Menu item clicked on, and close Drawerlayout
                item.setChecked(true);
                drawer.closeDrawers();

                return true;
            }
        });
        return nav;
    }
}
