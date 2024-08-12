package klivvr.test.day7.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import klivvr.test.day7.R
import klivvr.test.day7.data.local.dao.Dao
import klivvr.test.day7.data.local.database.DataBase
import klivvr.test.day7.viewModel.MainViewModel
import klivvr.test.day7.viewModel.MainViewModelFactory

const val SHARED_PREF = "Day7"
const val PHONE = "phone"
const val MESSAGE = "message"

class FragmentOne : Fragment() {
    private lateinit var dao : Dao
    val vm :MainViewModel by viewModels(
        factoryProducer = { MainViewModelFactory(dao) }
    )
    private val sharedPreferences by lazy {
        context?.getSharedPreferences(
            SHARED_PREF,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        dao = DataBase.getDatabase(requireContext()).dao()
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextPhone = view.findViewById<EditText>(R.id.editTextPhone)
        val editTextMessage = view.findViewById<EditText>(R.id.editTextMessage)
        val btnNext = view.findViewById<Button>(R.id.btn_next)
        val btnClose = view.findViewById<Button>(R.id.btn_close)

        btnNext.setOnClickListener {
            val phone = editTextPhone.text.toString()
            val message = editTextMessage.text.toString()

            if (phone.isBlank() || message.isBlank()) {
                Toast.makeText(context, "One of the fields is empty", Toast.LENGTH_SHORT).show()
            } else {
                vm.insertData(phone, message)
                saveData(phone, message)
                findNavController().navigate(
                    FragmentOneDirections.actionFragmentOneToFragmentTwo()
                )
            }
        }
        btnClose.setOnClickListener {
            activity?.finish()
        }
    }

    private fun saveData(phone: String, message: String) {
        val editor = sharedPreferences?.edit()
        editor?.putString(PHONE, phone)
        editor?.putString(MESSAGE, message)

        editor?.apply()
    }
}