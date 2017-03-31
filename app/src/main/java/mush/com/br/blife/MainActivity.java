package mush.com.br.blife;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import mush.com.br.blife.fragments.CampanhasFragment;
import mush.com.br.blife.fragments.DoadorFragment;
import mush.com.br.blife.fragments.DoacaoFragment;
import mush.com.br.blife.fragments.HomeFragment;
import mush.com.br.blife.fragments.RankingFragment;
import mush.com.br.blife.fragments.SaidasFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ResideMenu mResideMenu;
    private ResideMenuItem mHomeItem;
    private ResideMenuItem mDoadorItem;
    private ResideMenuItem mDonation;
    private ResideMenuItem mSaidas;
    private ResideMenuItem mCampanhas;
    private ResideMenuItem mRankings;
    private ResideMenuItem mConfigs;
    private Fragment mSelectedFrament;

    //TODO  RESULVER BUG DOS FRAGMENTS EMPILHAREM

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(savedInstanceState == null){
            mSelectedFrament = new HomeFragment();
           displayHomeFragment(mSelectedFrament);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();


        //getSupportActionBar can return null
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(new IconicsDrawable(this, FontAwesome.Icon.faw_bars).color(Color.RED).sizeDp(20));
        }


        mResideMenu = new ResideMenu(this);
        mResideMenu.attachToActivity(this);
        mResideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);
        mResideMenu.setBackground(R.color.menuColor);

        mHomeItem = new ResideMenuItem(this, R.drawable.ic_home_black_48dp, R.string.home_menu_name);
        mDoadorItem = new ResideMenuItem(this,R.drawable.ic_tag_faces_black_48dp, R.string.donator_menu_name);
        mDonation = new ResideMenuItem(this, R.drawable.ic_favorite_black_48dp, R.string.donations_menu_name);
        mSaidas = new ResideMenuItem(this, R.drawable.ic_assignment_turned_in_black_48dp, R.string.saidas_menu_name);
        mCampanhas = new ResideMenuItem(this, R.drawable.ic_trending_up_black_48dp, R.string.campanhas_menu_name);
        mRankings = new ResideMenuItem(this, R.drawable.ic_assessment_black_48dp, R.string.rankings_menu_name);
        mConfigs = new ResideMenuItem(this, R.drawable.ic_assessment_black_48dp, R.string.config_menu_name);
        //Rankings ainda não tem Fragment nem Atividade


        mHomeItem.setOnClickListener(this);
        mDoadorItem.setOnClickListener(this);
        mDonation.setOnClickListener(this);
        mSaidas.setOnClickListener(this);
        mCampanhas.setOnClickListener(this);
        mRankings.setOnClickListener(this);
        mConfigs.setOnClickListener(this);


        mResideMenu.addMenuItem(mHomeItem, ResideMenu.DIRECTION_RIGHT);
        mResideMenu.addMenuItem(mDoadorItem, ResideMenu.DIRECTION_RIGHT);
        mResideMenu.addMenuItem(mDonation, ResideMenu.DIRECTION_RIGHT);
        mResideMenu.addMenuItem(mSaidas, ResideMenu.DIRECTION_RIGHT);
        mResideMenu.addMenuItem(mCampanhas, ResideMenu.DIRECTION_RIGHT);
        mResideMenu.addMenuItem(mRankings, ResideMenu.DIRECTION_RIGHT);
        mResideMenu.addMenuItem(mConfigs, ResideMenu.DIRECTION_RIGHT);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mResideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mResideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View view) {
        if (view == mHomeItem){

            mSelectedFrament = new HomeFragment();
            changeFragment(mSelectedFrament);


        }else if(view == mDoadorItem) {

            mSelectedFrament = new DoadorFragment();
            changeFragment(mSelectedFrament);


        }else if(view == mDonation){

            changeFragment(new DoacaoFragment());

        }else if(view == mSaidas){

            changeFragment(new SaidasFragment());


        }else if(view == mCampanhas){

            changeFragment(new CampanhasFragment());


        }else if(view == mRankings){

            changeFragment(new RankingFragment());

        }else if(view == mConfigs){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Configurações")
                    .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            // TODO
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setView(R.layout.configs_menu)
                    .create()
                    .show();

        }
        mResideMenu.closeMenu();
    }

    void changeFragment(Fragment targetFragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_place,targetFragment, "fragment")
                .addToBackStack(null)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    //This duplication is because addToBackStack in first fragment
    //causes a ugly appearence when back button is pressed.
    void displayHomeFragment(Fragment targetFragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_place,targetFragment, "home_fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }


    public Fragment getmSelectedFrament() {
        return mSelectedFrament;
    }
}
