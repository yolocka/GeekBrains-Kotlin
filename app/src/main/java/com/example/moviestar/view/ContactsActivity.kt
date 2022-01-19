package com.example.moviestar.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import com.example.moviestar.R
import com.example.moviestar.databinding.ActivityContactsBinding
import com.example.moviestar.model.ContactDTO

class ContactsActivity : AppCompatActivity() {

    private val adapter = ContactsAdapter()
    private val binding: ActivityContactsBinding by lazy {
        ActivityContactsBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.contactRecyclerView.adapter = adapter

        adapter.listener = ContactsAdapter.OnItemClick {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", it.phone, null)))
        }

        checkPermission()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermission() {
        permissionResult.launch(Manifest.permission.READ_CONTACTS)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private val permissionResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            when {
                result -> getContacts()
                !shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_CONTACTS
                ) -> AlertDialog.Builder(this).setTitle(R.string.ask_contacts_permission)
                    .setMessage(R.string.again_ask_contacts_permission)
                    .setPositiveButton(R.string.give_permission) { _, _ -> checkPermission() }
                    .setNegativeButton(R.string.do_not_give_permission) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()
                else -> Toast.makeText(this, R.string.contacts_not_available, Toast.LENGTH_LONG).show()
            }
        }

    @SuppressLint("Range")
    private fun getContacts() {
        val contentResolver: ContentResolver = contentResolver
        val contactList: MutableList<ContactDTO> = ArrayList()
        val cursor: Cursor? = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.let {
            while (cursor.moveToNext()) {
                val contactDTO = ContactDTO()
                contactDTO.name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                contactDTO.phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactList.add(contactDTO)
            }
            cursor.close()
        }
        adapter.setContacts(contactList)

    }
}