package com.dev.ipati.fragmentdialog


import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), FragmentDialog.DialogOnClickListener {

    override fun OnDialogPositiveClickListener(v: View?) {
        Toast.makeText(v?.context, "ClickPositiveButton", Toast.LENGTH_SHORT).show()
    }

    override fun OnDialogNegativeClickListener(v: View?) {
        Toast.makeText(v?.context, "ClickNegativeButton", Toast.LENGTH_SHORT).show()
    }

    var btDialog: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btDialog = findViewById(R.id.bt_dialog) as Button
//        val fragmentDialog: FragmentDialog = FragmentDialog()
//        val traction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        traction.replace(R.id.frame_fragment_dialog, fragmentDialog)
//        traction.commit()
        btDialog?.setOnClickListener({ view ->
            val fragmentDialog: FragmentDialog = FragmentDialog.Builder().headingDialog("HeaderDialogFragment")
                    .descriptionDialog("I'am Fragment Dialog Kotlin").builder()
            fragmentDialog.isCancelable = false
            fragmentDialog.show(supportFragmentManager, "fragmentDialog")
        })

    }
}
