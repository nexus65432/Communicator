package com.nexus.communicator.Utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;

import com.nexus.communicator.Datamodel.ContactObj;

import java.util.ArrayList;
import java.util.List;


public class ContactFetcher {

    private final Context context;

    public ContactFetcher(Context ctx) {
        this.context = ctx;
    }

    public List<ContactObj> fetchAllContacts() {

        String[] projectionFields = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,
        };

        CursorLoader cursorLoader = new CursorLoader(context,
                ContactsContract.Contacts.CONTENT_URI,
                projectionFields, // the columns to retrieve
                null, // the selection criteria (none)
                null, // the selection args (none)
                ContactsContract.Contacts.SORT_KEY_PRIMARY + " ASC" // the sort order (default)
        );

        Cursor contactsCursor = cursorLoader.loadInBackground();

        List<ContactObj> contactObjList = new ArrayList<>(contactsCursor.getCount());

        if (contactsCursor.moveToFirst()) {

            int idIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts._ID);
            int nameIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            int thumbnailPhotoUri = contactsCursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI);

            do {
                String contactId = contactsCursor.getString(idIndex);
                String contactDisplayName = contactsCursor.getString(nameIndex);
                String contactPhotoUri = contactsCursor.getString(thumbnailPhotoUri);

                ContactObj contact = new ContactObj(contactId, contactDisplayName, contactPhotoUri);
                contactObjList.add(contact);
            } while (contactsCursor.moveToNext());
        }

        contactsCursor.close();

        return contactObjList;
    }
}
