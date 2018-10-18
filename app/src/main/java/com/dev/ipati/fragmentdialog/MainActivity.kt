package com.dev.ipati.fragmentdialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FragmentDialog.DialogOnClickListener {

    override fun onDialogPositiveClickListener(view: View) {
        Toast.makeText(view.context, "ClickPositiveButton", Toast.LENGTH_SHORT).show()
    }

    override fun onDialogNegativeClickListener(view: View) {
        Toast.makeText(view.context, "ClickNegativeButton", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btDialog.setOnClickListener {
            val fragmentDialog: FragmentDialog = FragmentDialog.Builder()
                .header("HeaderDialogFragment")
                .message("I'm Fragment Dialog Kotlin")
                .build()
            fragmentDialog.isCancelable = false
            fragmentDialog.show(supportFragmentManager, "fragmentDialog")
        }
    }
}
