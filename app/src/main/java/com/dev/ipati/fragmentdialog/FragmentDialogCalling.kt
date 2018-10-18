package com.dev.ipati.fragmentdialog

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

class FragmentDialogCalling : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentDialog: FragmentDialog = FragmentDialog.Builder()
            .header("HelloFragmentDialog")
            .message("I'm Fragment Dialog")
            .build()

        fragmentDialog.show(childFragmentManager, "")
    }

    companion object {
        fun newInstance(name: String): FragmentDialogCalling {
            return FragmentDialogCalling().apply {
                arguments = Bundle().apply { putString("DialogCalling", name) }
            }
        }
    }
}
