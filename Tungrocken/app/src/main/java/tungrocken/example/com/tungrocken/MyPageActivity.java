package tungrocken.example.com.tungrocken;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import tungrocken.example.com.tungrocken.domain.HamburgerMenu;
import tungrocken.example.com.tungrocken.domain.User;

/**
 * Created by Team Tungrocken
 */

public class MyPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        // Oppsett av toolbar - Må brukes av alle aktiviteter utenom hovedsiden
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.toolbarlogo);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        // Henter hvilken bruker som er pålogget, og kjører metode for å vise informasjon
        User u = SharedRespources.getInstance().getUser();
        insertUserInfo(u);

        final Button editButton = (Button) findViewById(R.id.edit_u_btn);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                Intent editData = new Intent(MyPageActivity.this, EditUserActivity.class);
                editData.putExtra("bruker", u);
                startActivity(editData);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(searchableInfo);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Håndtering av visning og klikk på hamburgermeny
        HamburgerMenu hm = new HamburgerMenu();
        Intent i = hm.getHamburgerMenu(id, this.getApplicationContext());
        if(i != null) {
            startActivity(i);
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insertUserInfo(User u) {
        TextView title = (TextView)findViewById(R.id.mypagetitle);
        String titleString = "<h2>Min side</h2>";
        title.setText(Html.fromHtml(titleString));

        TextView intro = (TextView)findViewById(R.id.mypageintro);
        String introString = "Her finner du din personlige brukerside. Denne siden gir deg informasjon om deg som bruker, og du vil ha muligheter til å gjøre endringer.";
        intro.setText(Html.fromHtml(introString));

        TextView firstname = (TextView)findViewById(R.id.mypagefirstname);
        String firstnameString = "<strong>Fornavn:</strong>";
        firstname.setText(Html.fromHtml(firstnameString));

        TextView firstname2 = (TextView)findViewById(R.id.mypagefirstname2);
        String firstname2String = u.getFirstName();
        firstname2.setText(Html.fromHtml(firstname2String));

        TextView lastname = (TextView)findViewById(R.id.mypagelastname);
        String lastnameString = "<strong>Etternavn:</strong>";
        lastname.setText(Html.fromHtml(lastnameString));

        TextView lastname2 = (TextView)findViewById(R.id.mypagelastname2);
        String lastname2String = u.getLastName();
        lastname2.setText(Html.fromHtml(lastname2String));

        TextView email = (TextView)findViewById(R.id.mypageemail);
        String emailString = "<strong>Din e-postadresse (brukernavn):</strong>";
        email.setText(Html.fromHtml(emailString));

        TextView email2 = (TextView)findViewById(R.id.mypageemail2);
        String email2String = u.getEmail();
        email2.setText(Html.fromHtml(email2String));

        TextView datecreated = (TextView)findViewById(R.id.mypagedate);
        String dateString = "<strong>Din brukerkonto ble opprettet:</strong>";
        datecreated.setText(Html.fromHtml(dateString));

        TextView datecreated2 = (TextView)findViewById(R.id.mypagedate2);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE d. MMMM YYYY");
        Date date = u.getCreated();
        String sDate= sdf.format(date);
        datecreated2.setText(Html.fromHtml(sDate));
    }

}
