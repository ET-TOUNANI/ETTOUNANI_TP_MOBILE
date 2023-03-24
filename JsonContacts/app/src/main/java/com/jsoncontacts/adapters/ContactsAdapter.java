package com.jsoncontacts.adapters;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jsoncontacts.MainActivity;
import com.jsoncontacts.R;
import com.jsoncontacts.models.Contact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactsAdapter extends BaseAdapter {
    Context context;
    ListView listView;
    ArrayList<Contact> contacts;

    public ContactsAdapter(ArrayList<Contact> elts, ListView listView, Context context) {
        this.contacts = elts;
        this.context = context;
        this.listView = listView;
    }

    public int getCount() {
        return contacts.size();
    }

    public Object getItem(int i) {
        return contacts.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Contact contact = contacts.get(i);
        View view1 = layoutInflater.inflate(R.layout.single_contact, null);
        TextView name = view1.findViewById(R.id.title);
        TextView details = view1.findViewById(R.id.description);
        Button call = view1.findViewById(R.id.call);
        call.setTag(contact);

        name.setText(contact.getFirst_name() + " " + contact.getLast_name());
        details.setText(contact.getJob() + "\n" + contact.getPhone() + "\n" + contact.getEmail());

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = (Contact) view.getTag();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.getPhone()));
                context.startActivity(intent);
            }
        });

        CircleImageView photo = view1.findViewById(R.id.photo);
        if (contact.getPhoto() != null)
            Picasso.get().load("file:///android_asset/" + contact.getPhoto()).into(photo);
        return view1;
    }

}
