package com.rplsukses.ezprint.ui.helper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.rplsukses.ezprint.R;

public class DrawerMenuHelper {
    private static AccountHeader header(Activity activity){
        ProfileDrawerItem operator1 = new ProfileDrawerItem().withName("D3 IT A");
        AccountHeaderBuilder mHeader = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.custom_main_background)
                .addProfiles(
                        operator1
                );
        return mHeader.build();
    }

    public static Drawer createDrawer(final Activity activity, Toolbar toolbar){
        PrimaryDrawerItem profileItem = new PrimaryDrawerItem()
                .withIdentifier(0)
                .withName(R.string.menu_profile);
        PrimaryDrawerItem pesanItem = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName(R.string.menu_pesan);
        PrimaryDrawerItem notificationItem = new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName(R.string.menu_notification);
        PrimaryDrawerItem historyItem = new PrimaryDrawerItem()
                .withIdentifier(3)
                .withName(R.string.menu_history);
        PrimaryDrawerItem helpItem = new PrimaryDrawerItem()
                .withIdentifier(4)
                .withName(R.string.menu_help);
        PrimaryDrawerItem infoItem = new PrimaryDrawerItem()
                .withIdentifier(5)
                .withName(R.string.menu_info);
        PrimaryDrawerItem settingItem = new PrimaryDrawerItem()
                .withIdentifier(6)
                .withName(R.string.menu_setting);

        DrawerBuilder mDrawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(header(activity))
                .withSelectedItem(-1)
                .addDrawerItems(
                        profileItem,
                        pesanItem,
                        notificationItem,
                        historyItem,
                        new DividerDrawerItem(),
                        helpItem,
                        infoItem,
                        settingItem
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        long i = drawerItem.getIdentifier();
                        if (i == 0){
                            //activity.startActivity(new Intent(activity, ProfileActivity.class));
                            //activity.finish();
                        }else if (i == 1){

                        }else if (i == 2){

                        }else if (i == 3){
                            //activity.startActivity(new Intent(activity, PesananActivity.class));
                            //activity.finish();
                        }else if (i == 4){

                        }else if (i == 5){

                        }else if (i == 6){

                        }
                        return false;
                    }
                });
        return mDrawer.build();
    }
}
