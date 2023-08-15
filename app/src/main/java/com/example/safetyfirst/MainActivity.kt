package com.example.safetyfirst

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.READ_CONTACTS,
    )
    val permissioncode = 22
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askforPermission()
        val bottomBar = findViewById<BottomNavigationView>(R.id.nav_bar)
        bottomBar.setOnItemReselectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_security -> {
                    inflatefragment(GuardFragment.newInstance())
                }

                R.id.nav_home -> {
                    inflatefragment(homeFragment.newInstance())
                }

                R.id.nav_dashboard -> {
                    inflatefragment(MapsFragment())
                }

                R.id.nav_profile -> {
                    inflatefragment(ProfileFragment.newInstance())
                }
            }
            true
        }
        bottomBar.selectedItemId = R.id.nav_home
        val db = Firebase.firestore
        val currentUser = FirebaseAuth.getInstance().currentUser
        val name = currentUser?.displayName.toString()
        val mail = currentUser?.email.toString()
        val phonenumber = currentUser?.phoneNumber.toString()
        val imageurl = currentUser?.photoUrl.toString()
        val user = hashMapOf(
            "name" to name,
            "mail" to mail,
            "phoneNumber" to phonenumber,
            "imageUrl" to imageurl
        )
db.collection("users").document(mail).set(user).addOnSuccessListener {
}
    .addOnFailureListener {
    }
    db.collection("users")
        .add(user)
        .addOnSuccessListener { documentReference ->
            Log.d("FireStore", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w("Firestore89", "Error Adding Document", e)
        }
    }

    private fun askforPermission() {
        ActivityCompat.requestPermissions(this, permissions, permissioncode)
    }
     private fun inflatefragment(newInstance: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newInstance)
        transaction.commit()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissioncode) {
            if (allpermissiongranted()) {

            }
            else {

            }
        }
    }
    private fun allpermissiongranted(): Boolean {
        for (item in permissions) {
            if (ContextCompat.checkSelfPermission(this, item) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }
}