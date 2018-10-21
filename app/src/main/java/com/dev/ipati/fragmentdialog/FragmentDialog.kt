package com.dev.ipati.fragmentdialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_fragment_dialog.*

class FragmentDialog : DialogFragment() {

    var header: String? = DEFAULT_DIALOG_HEADER
    var message: String? = DEFAULT_DIALOG_MESSAGE

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
        tvHeader.text = header
        tvDescription.text = message

        btSubmit.setOnClickListener { view ->
            delegate<DialogOnClickListener>()?.onDialogPositiveClickListener(view)
            dismiss()
        }

        btCancel?.setOnClickListener { view ->
            delegate<DialogOnClickListener>()?.onDialogNegativeClickListener(view)
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

    private inline fun <reified T> Fragment.delegate(): T? = when {
        targetFragment is T -> targetFragment as T
        parentFragment is T -> parentFragment as T
        activity is T -> activity as T
        else -> null
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

        private var header: String? = null
        private var message: String? = null

        fun header(header: String) = apply { this.header = header }

        fun message(message: String) = apply { this.message = message }

        fun build() = FragmentDialog.newInstance(
                header = header ?: throw NullPointerException("Header must not be null."),
                Msg = message ?: throw NullPointerException("Message must not be null.")
        )
    }

}

private const val ARGS_HEADER: String = "dialog_header"
private const val ARGS_MESSAGE: String = "dialog_message"
private const val DEFAULT_DIALOG_HEADER = "HelloDialogFragment"
private const val DEFAULT_DIALOG_MESSAGE = "I'm a DialogFragment"
