package cg.tutorials.listandspinner_practise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import cg.tutorials.listandspinner_practise.databinding.FragmentSelectedItemBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectedItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectedItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentSelectedItemBinding
    private var name:String ?=null
    private var quantity:String?=null
    private var array = mutableListOf<String>()
    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString("name")?:""
            quantity =it.getString("quantity")?:""
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentSelectedItemBinding.inflate(layoutInflater)
//        return inflater.inflate(R.layout.fragment_selected_item, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        array.add(name.toString())
        arrayAdapter=ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,array)
        binding.dySpinner.adapter = arrayAdapter

        binding.numberPicker.minValue = 1
        binding.numberPicker.maxValue = 100
        binding.decreaseBtn.setOnClickListener {
            if(binding.numberPicker.value>binding.numberPicker.minValue){
                binding.numberPicker.value-=1
            }
            else{
                Toast.makeText(requireContext(), "Min Limit", Toast.LENGTH_SHORT).show()
            }
        }
        binding.increaseBtn.setOnClickListener {
            if(binding.numberPicker.value<binding.numberPicker.maxValue){
                binding.numberPicker.value+=1
            }else{
                Toast.makeText(requireContext(), "Max Limit", Toast.LENGTH_SHORT).show()
            }
        }
        binding.numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            println("Selected number: $newVal")
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SelectedItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectedItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}