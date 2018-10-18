package com.dev.ipati.fragmentdialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_fragment_dialog.*

class FragmentDialog : DialogFragment() {

    var header: String? = DEFAULT_DIALOG_HEADER
    var message: String? = DEFAULT_DIALOG_MESSAGE

    var dialogClickListener: DialogOnClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            arguments?.let(this::onInitInstance)
        } else {
            onRestoreInstanceState(savedInstanceState)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    private fun initInstance() {
        dialogClickListener = if (parentFragment != null) {
            parentFragment as DialogOnClickListener
        } else {
            activity as DialogOnClickListener
        }

        tvHeader.text = header
        tvDescription.text = message

        btSubmit.setOnClickListener { view ->
            dialogClickListener?.onDialogPositiveClickListener(view)
            dismiss()
        }

        btCancel?.setOnClickListener { view ->
            dialogClickListener?.onDialogNegativeClickListener(view)
            dismiss()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ARGS_HEADER, header)
        outState.putString(ARGS_MESSAGE, message)
    }

    private fun onInitInstance(arguments: Bundle) {
        header = arguments.getString(ARGS_HEADER)
        message = arguments.getString(ARGS_MESSAGE)
    }

    private fun onRestoreInstanceState(bundle: Bundle?) {
        header = bundle?.getString(ARGS_HEADER)
        message = bundle?.getString(ARGS_MESSAGE)
    }

    interface DialogOnClickListener {
        fun onDialogPositiveClickListener(v: View)
        fun onDialogNegativeClickListener(v: View)
    }

    companion object {
        fun newInstance(header: String, Msg: String): FragmentDialog {
            val fragmentDialog = FragmentDialog()
            val bundle = Bundle().apply {
                putString(ARGS_HEADER, header)
                putString(ARGS_MESSAGE, Msg)
            }
            fragmentDialog.arguments = bundle
            return fragmentDialog
        }
    }

    class Builder {
        var header: String? = null
        var message: String? = null

        fun header(text: String): Builder {
            this.header = text
            return this
        }

        fun message(text: String): Builder {
            this.message = text
            return this
        }

        fun build(): FragmentDialog {
            val header = this.header
                ?: throw NullPointerException("You must define header")
            val description = this.message
                ?: throw NullPointerException("You must define message")

            return FragmentDialog.newInstance(header, description)
        }
    }

}

private const val ARGS_HEADER: String = "dialog_header"
private const val ARGS_MESSAGE: String = "dialog_message"
private const val DEFAULT_DIALOG_HEADER = "HelloDialogFragment"
private const val DEFAULT_DIALOG_MESSAGE = "I'm a DialogFragment"
