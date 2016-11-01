package es.unavarra.tlm.dscr_10.listapartidas;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import java.util.Locale;
import es.unavarra.tlm.dscr_10.R;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    Context context;

    public SectionsPagerAdapter(FragmentManager fm,Context context) {
        super(fm);

        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        SectionFragment fragment = new SectionFragment();
        Bundle args = new Bundle();
        args.putInt(SectionFragment.ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {

        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return context.getString(R.string.title_section_1).toUpperCase(l);
            case 1:
                return context.getString(R.string.title_section_2).toUpperCase(l);
            case 2:
                return context.getString(R.string.title_section_3).toUpperCase(l);
        }
        return null;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}