package klivvr.test.day7.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import klivvr.test.day7.R
import klivvr.test.day7.data.local.dao.Dao
import klivvr.test.day7.data.local.database.DataBase
import klivvr.test.day7.viewModel.MainViewModel
import klivvr.test.day7.viewModel.MainViewModelFactory


class FragmentTwo : Fragment() {
    private lateinit var dao : Dao
    val vm : MainViewModel by viewModels(
        factoryProducer = { MainViewModelFactory(dao) }
    )
    private val sharedPreferences by lazy {
        context?.getSharedPreferences(
            SHARED_PREF, Context.MODE_PRIVATE
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        dao = DataBase.getDatabase(requireContext()).dao()
        super.onCreate(savedInstanceState)
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

    fun updateEditTexts(phone: String?, message: String?) {
        editTextPhone.setText(phone ?: "unknown")
        editTextMessage.setText(message ?: "unknown")
    }

    vm.data.observe(viewLifecycleOwner) { dataEntity ->
        updateEditTexts(dataEntity?.phone, dataEntity?.message)
    }

    view.findViewById<Button>(R.id.btn_clear_pref_fragment_two).setOnClickListener {
        vm.deleteAllData()
        updateEditTexts(null, null)
    }

    view.findViewById<Button>(R.id.btn_read_pref_fragment_two).setOnClickListener {
        vm.getData()
    }

    btnClose.setOnClickListener {
        findNavController().popBackStack()
    }

    vm.getData()
}
}