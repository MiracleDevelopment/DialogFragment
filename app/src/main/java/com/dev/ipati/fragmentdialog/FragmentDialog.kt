package com.dev.ipati.fragmentdialog


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class FragmentDialog : DialogFragment() {
    lateinit var tvHeader: TextView
    var tvMessage: TextView? = null
    var btCancel: Button? = null
    var btSummit: Button? = null
    var header: String? = "HelloDialogFragment"
    var Msg: String? = "I'am DialogFragment"

    var dialogClickListener: DialogOnClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            onRestoreInstanceState(arguments)
        } else {
            onRestorenonInstanceState(savedInstanceState)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.activity_fragment_dialog, container, false)
        initInstance(view)
        return view
    }

    private fun initInstance(view: View) {
        tvHeader = view.findViewById(R.id.tv_header) as TextView
        tvMessage = view.findViewById(R.id.tv_discription) as TextView
        btCancel = view.findViewById(R.id.bt_cancel) as Button
        btSummit = view.findViewById(R.id.bt_summit) as Button

        if (parentFragment != null) {
            dialogClickListener = parentFragment as DialogOnClickListener
        } else {
            dialogClickListener = activity as DialogOnClickListener
        }


        tvHeader.text = header
        tvMessage?.text = Msg



        btSummit?.setOnClickListener({ view ->
            dialogClickListener?.OnDialogPositiveClickListener(view)
            dismiss()
        })

        btCancel?.setOnClickListener({ view ->
            dialogClickListener?.OnDialogNegativeClickListener(view)
            dismiss()
        })
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("dialog_header", header)
        outState?.putString("dialog_msg", Msg)

    }

    fun onRestoreInstanceState(saveInstanceState: Bundle?) {
        header = saveInstanceState?.getString("dialog_header")
        Msg = saveInstanceState?.getString("dialog_msg")
    }

    fun onRestorenonInstanceState(bundle: Bundle?) {
        header = bundle?.getString("dialog_header")
        Msg = bundle?.getString("dialog_msg")
    }

    interface DialogOnClickListener {
        fun OnDialogPositiveClickListener(v: View?)
        fun OnDialogNegativeClickListener(v: View?)
    }

    companion object {
        fun newInstance(header: String, Msg: String): FragmentDialog {
            val fragmentDialog: FragmentDialog = FragmentDialog()
            val bundle: Bundle = Bundle()
            bundle.putString("dialog_header", header)
            bundle.putString("dialog_msg", Msg)
            fragmentDialog.arguments = bundle
            return fragmentDialog
        }
    }

    open class Builder {
        var headerDialog: String? = null
        var descriptionDialog: String? = null

        fun headingDialog(header: String): Builder {
            headerDialog = header
            return this
        }

        fun descriptionDialog(msg: String): Builder {
            descriptionDialog = msg
            return this
        }

        fun builder(): FragmentDialog {
            return FragmentDialog.newInstance(headerDialog!!, descriptionDialog!!)
        }
    }

}
