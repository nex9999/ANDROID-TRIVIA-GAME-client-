package es.unavarra.tlm.dscr_10.listapartidas;

import android.app.ActionBar;
import android.app.Fragment;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.List;

import es.unavarra.tlm.dscr_10.R;

public class ListaPartidasTabs extends FragmentActivity implements ActionBar.TabListener {

    SectionsPagerAdapter mSectionsPagerAdapter;
    SharedPreferences.Editor editor;
    SharedPreferences settings;
    ViewPager mViewPager;
    Context context;
    FragmentManager fmanager;
    CreadorPartidas gameMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listas_partidas);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        context = this.findViewById(android.R.id.content).getContext();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),this);
        settings = getSharedPreferences("Config", 0);
        editor = settings.edit();
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        fmanager = getSupportFragmentManager();

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                actionBar.setSelectedNavigationItem(position);
                /*List<android.support.v4.app.Fragment> allFragments = fmanager.getFragments();
                SectionFragment fragment = (SectionFragment)allFragments.get(position);
                fragment.update();*/
                ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
                mViewPager.getAdapter().notifyDataSetChanged();
            }
        });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {

            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }


        gameMaker = new CreadorPartidas(this);

    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_logout:
                finish();
                editor.putString("token","");
                editor.commit();
                Intent mainActivity = new Intent(context, es.unavarra.tlm.dscr_10.MyActivity.class);
                startActivity(mainActivity);

                return true;
            case R.id.action_settings:

                return true;
            case R.id.action_create_game:
                gameMaker.creaNuevaPartida();
                List<android.support.v4.app.Fragment> allFragments = fmanager.getFragments();
                SectionFragment fragment = (SectionFragment)allFragments.get(2);
                Toast.makeText(this.context, "Creada nueva partida", Toast.LENGTH_LONG).show();
                fragment.update();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
