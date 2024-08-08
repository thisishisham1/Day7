package klivvr.test.day7

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class FragmentTwo : Fragment() {
    private val sharedPreferences by lazy {
        context?.getSharedPreferences(
            SHARED_PREF, Context.MODE_PRIVATE
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextPhone = view.findViewById<EditText>(R.id.editTextPhoneFragmentTwo)
        val editTextMessage = view.findViewById<EditText>(R.id.editTextMessageFragmentTwo)
        val btnClose = view.findViewById<Button>(R.id.btn_close_fragment_two)


        fun updateEditTexts() {
            val phone = sharedPreferences?.getString(PHONE, "unknown")
            val message = sharedPreferences?.getString(MESSAGE, "unknown")
            editTextPhone.setText(phone)
            editTextMessage.setText(message)
        }

        view.findViewById<Button>(R.id.btn_clear_pref_fragment_two).setOnClickListener {
            sharedPreferences?.edit()?.clear()?.apply()
            updateEditTexts()
        }

        view.findViewById<Button>(R.id.btn_read_pref_fragment_two).setOnClickListener {
            updateEditTexts()
        }

        btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}