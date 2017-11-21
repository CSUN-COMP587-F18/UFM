/* Author: Sean Hansen
* ID: 108841276
* Date Started: 9/27/17
* Date Complete:
* Peer Review:
*   Date:
*   Team Members:
* Contributing Team Members:
*/
package ufm.universalfinancemanager;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.app.Fragment;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ufm.universalfinancemanager.User;

public class Main_Activity extends AppCompatActivity{
    private String[] drawer_items;
    private DrawerLayout drawer_layout;
    private ListView list_view;
    private ActionBarDrawerToggle drawer_toggle;
    private User sessionUser;

    public static final String EXTRA_USER = "ufm.universalfinancemanager.USER";

    //For Test dates...remove later
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        drawer_layout = (DrawerLayout)findViewById(R.id.drawer_layout);
        list_view = (ListView)findViewById(R.id.drawer);

        //set the drawer
        list_view.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                selectItem(pos);
            }
        }
        );

        try {
            FileInputStream fis = getApplicationContext().openFileInput("testUser");
            ObjectInputStream is = new ObjectInputStream(fis);
            sessionUser = (User)is.readObject();
            is.close();
            fis.close();
        }catch(FileNotFoundException e) {
            sessionUser = new User("Test");
            sessionUser.addCategory(new Category("Gas"));
            sessionUser.addAccount(new Account("Checking", AccountType.CHECKING, 0, new Date()));

            try {
                sessionUser.addTransaction(new Transaction("Gas", Flow.OUTCOME, 30.24, new Category("Transportation"),
                        sessionUser.getAccount("Checking"), dateFormat.parse("10/28/2017")));
                sessionUser.addTransaction(new Transaction("Ralphs", Flow.OUTCOME, 86.13, new Category("Food"),
                        sessionUser.getAccount("Checking"), dateFormat.parse("10/28/2017")));
                sessionUser.addTransaction(new Transaction("AMC", Flow.OUTCOME, 8.50, new Category("Fun"),
                        sessionUser.getAccount("Checking"), dateFormat.parse("10/29/2017")));
                sessionUser.addTransaction(new Transaction("CSUN", Flow.OUTCOME, 57.00, new Category("Education"),
                        sessionUser.getAccount("Checking"), dateFormat.parse("10/30/2017")));
                sessionUser.addTransaction(new Transaction("Amazon", Flow.OUTCOME, 24.15, new Category("Household"),
                        sessionUser.getAccount("Checking"), dateFormat.parse("10/30/2017")));
                sessionUser.addTransaction(new Transaction("Autozone", Flow.OUTCOME, 11.15, new Category("Vehicle Maintenance"),
                        sessionUser.getAccount("Checking"), dateFormat.parse("10/30/2017")));
                sessionUser.addTransaction(new Transaction("Gas", Flow.OUTCOME, 29.13, new Category("Transportation"),
                        sessionUser.getAccount("Checking"), dateFormat.parse("10/30/2017")));
            }catch(ParseException f) {
                //shouldn't happen...
            }
        }catch(ClassNotFoundException | IOException e) {
            Log.d("Main_Activity", e.getMessage());
        }



        /**************TEST DATA*************/
        // Test data for net worth -Aaron

        sessionUser.addAccount(new Account("American Express", AccountType.CREDIT_CARD, 2353.95, new Date()));
        sessionUser.addAccount(new Account("Bank of America Visa", AccountType.CREDIT_CARD, 631.31, new Date()));
        sessionUser.addAccount(new Account("Capital One Visa", AccountType.CREDIT_CARD, 103.31, new Date()));
        sessionUser.addAccount(new Account("Chase Visa", AccountType.CREDIT_CARD, 381.99, new Date()));
        sessionUser.addAccount(new Account("CitiBank Mastercard", AccountType.CREDIT_CARD, 741.48, new Date()));
        sessionUser.addAccount(new Account("Etrade Brokerage", AccountType.SAVINGS, 15534.33, new Date()));
        sessionUser.addAccount(new Account("Federal Student Loan", AccountType.CREDIT_CARD, 3455.22, new Date()));
        sessionUser.addAccount(new Account("Fidelity 401k", AccountType.SAVINGS, 11607.24, new Date()));
        sessionUser.addAccount(new Account("Matadors Credit Union Checking", AccountType.CHECKING, 2481.93, new Date()));
        sessionUser.addAccount(new Account("Matadors Credit Union Savings", AccountType.SAVINGS, 4950.07, new Date()));
        sessionUser.addAccount(new Account("Vanguard 401k", AccountType.SAVINGS, 21657.95, new Date()));
        sessionUser.addAccount(new Account("Wells Fargo", AccountType.CREDIT_CARD, 360.36, new Date()));

        /**************TEST DATA*************/

        drawer_items = getResources().getStringArray(R.array.drawer_items);

        drawer_toggle = new ActionBarDrawerToggle(this,drawer_layout,R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
                invalidateOptionsMenu();
            }

        };

        drawer_layout.addDrawerListener(drawer_toggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Populate navigation drawer with page names
        list_view.setAdapter(new ArrayAdapter<>(this, R.layout.nav_drawer_item, drawer_items));

    }

    private void selectItem(int position) {
        switch(position) {
            case(0):    //HOME
                break;
            case(1):    //BUDGET
                break;
            case(2):    //TRANSACTIONS

                //Create a new Transaction_Activity to place in main view container
                Fragment fragment = new TransactionFragment();

                //Put the users transactions in a bundle, pass to fragment via setArguments()
                Bundle b = new Bundle();
                b.putParcelableArrayList("TRANSACTIONS", sessionUser.getTransactions());
                fragment.setArguments(b);

                FragmentManager fragmentManager = getFragmentManager();

                //Replace the current container with the fragment and commit changes
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

                //Set the action bar title to "Transaction History"
                try {
                    getSupportActionBar().setTitle(R.string.transaction_title);
                }catch(java.lang.NullPointerException e) {

                }

                //Highlight touched item in the nav drawer and then close the nav drawer
                list_view.setItemChecked(position, true);
                drawer_layout.closeDrawer(list_view);
                break;
            case(3):    //INCOME/OUTCOME
                break;
            case(4):    //NET WORTH

                // Make new Net_Worth to place in main view container
                fragment = new NetWorthFragment();

                //Put the users accounts and totals in a bundle, pass to fragment via setArguments()
                b = new Bundle();
                b.putParcelableArrayList("ACCOUNT", sessionUser.getAccounts());
                fragment.setArguments(b);

                // Get fragment manager
                fragmentManager = getFragmentManager();

                //Replace the current container with the fragment and commit changes
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

                //Set the action bar title to "Transaction History"
                try {
                    getSupportActionBar().setTitle(R.string.networth_title);
                }catch(java.lang.NullPointerException e) {

                }

                //Highlight touched item in the nav drawer and then close the nav drawer
                list_view.setItemChecked(position, true);
                drawer_layout.closeDrawer(list_view);

                break;
            case(5):    //Reminders
                break;
            case(6):    //Settings
                break;
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawer_toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawer_toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event

        if (drawer_toggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        switch(id) {
           case R.id.action_add_transaction:
               Intent intent_trans = new Intent(this, Transaction_Add.class);
               intent_trans.putExtra(EXTRA_USER, (Parcelable)sessionUser);
               startActivityForResult(intent_trans, 1);
               return true;
            case R.id.action_add_account:
                Intent intent_account = new Intent(this, Account_Add.class);
                intent_account.putExtra(EXTRA_USER, (Parcelable)sessionUser);
                startActivityForResult(intent_account, 2);
                return true;
            case R.id.action_add_category:
                startActivity(new Intent(this, Category_Add.class));
                return true;
            case R.id.action_add_reminder:
                startActivity(new Intent(this, Reminder_Add.class));
                return true;
            case R.id.action_add_budget:
                startActivity(new Intent(this, Budget_Add.class));
                return true;
               default:
                   return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                Transaction newTransaction = data.getParcelableExtra("result");
                sessionUser.addTransaction(newTransaction);

            }
        }else if(requestCode == 2) {
            if(resultCode == Activity.RESULT_OK) {
                Account newAccount = data.getParcelableExtra("result");
                sessionUser.addAccount(newAccount);
            }
        }
    }

    @Override
    protected void onPause() {
        try {
            FileOutputStream fos = getApplicationContext().openFileOutput("testUser", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(sessionUser);
            os.close();
            fos.close();
        }catch(FileNotFoundException e) {

        }catch(IOException e) {

        }

        super.onPause();
    }
}
