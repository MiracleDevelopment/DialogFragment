package com.dev.ipati.fragmentdialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

class FragmentDialogCalling : Fragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentDialog: FragmentDialog = FragmentDialog.Builder().headingDialog("HelloFragmentDialog")
                .descriptionDialog("i'm Fragmnt Dialog").builder()

        fragmentDialog.show(childFragmentManager, "")
    }

    companion object {
        fun newInstance(name: String): FragmentDialogCalling {
            val fragmentcallingDialog: FragmentDialogCalling = FragmentDialogCalling()
            val bundel: Bundle = Bundle()
            bundel.putString("DialogCalling", name)
            return fragmentcallingDialog
        }
    }
}
