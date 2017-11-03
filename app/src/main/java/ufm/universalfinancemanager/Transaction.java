/* Author: Sean Hansen
* ID: 108841276
* Date Started: 10/13/17
* Date Complete:
* Peer Review:
*   Date:
*   Team Members:
* Contributing Team Members:
*/

package ufm.universalfinancemanager;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Transaction implements Parcelable, ListItem {
    private String name;
    private int flow;
    private double amount;
    private Category category;
    private Account account;
    private boolean frequency;
    private Date date;
    private String notes;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd", Locale.ENGLISH);

    public Transaction(String name, int flow, double amount, Category category,
                       Account account, Date date, String notes) {

        this.name = name;
        this.flow = flow;
        this.amount = amount;
        this.category = category;
        this.account = account;
        this.date = date;
        this.notes = notes;
    }

    public Transaction(String name, int flow, double amount, Category category,
                       Account account, Date date) {
        this.name = name;
        this.flow = flow;
        this.amount = amount;
        this.category = category;
        this.account = account;
        this.date = date;
    }

    public Transaction(Parcel in) {
        name = in.readString();
        flow = in.readInt();
        amount = in.readDouble();
        category = new Category(in.readString());
        account = in.readParcelable(Account.class.getClassLoader());
        date = new Date(in.readLong());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.flow);
        dest.writeDouble(this.amount);
        dest.writeString(this.category.toString());
        dest.writeParcelable(this.account, flags);
        dest.writeLong(this.date.getTime());
    }

    public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {
        public Transaction createFromParcel(Parcel p) {
            return new Transaction(p);
        }

        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    @Override
    public int getViewType() {
        return RowType.LIST_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view;
        if(convertView == null)
            view = (View)inflater.inflate(R.layout.transaction_list_item, null);
        else
            view = convertView;

        //Instantiate all the textviews from the layout
        TextView dateText = (TextView)view.findViewById(R.id.trans_date);
        TextView nameText = (TextView)view.findViewById(R.id.trans_name);
        TextView amountText = (TextView)view.findViewById(R.id.trans_amount);
        TextView accountText = (TextView)view.findViewById(R.id.trans_account);
        TextView categoryText = (TextView)view.findViewById(R.id.trans_category);

        //Set the text of each textview based on its corresponding transaction attribute
        dateText.setText(dateFormat.format(this.date));
        nameText.setText(this.name);
        amountText.setText(Double.toString(this.amount));
        accountText.setText(this.account.toString());
        categoryText.setText(this.category.toString());

        return view;
    }

    /********Getters**********************/
    public String getName() {return name;}
    public int getFlow() {return flow;}
    public double getAmount() {return amount;}
    public Category getCategory() {return category;}
    public Account getAccount() {return account;}
    public boolean getFrequency() {return frequency;}
    public Date getDate() {return date;}
    public String getNotes() {return notes;}

    /********Setters*************************************/
    public void setName(String name) {this.name = name;}
    public void setFlow(int flow) {this.flow = flow;}
    public void setAmount(double amount) {this.amount = amount;}
    public void setCategory(Category category) {this.category = category;}
    public void setAccount(Account account) {this.account = account;}
    public void setFrequency(boolean frequency) {this.frequency = frequency;}
    public void setDate(Date date) {this.date = date;}
    public void setNotes(String notes) {this.notes = notes;}
}