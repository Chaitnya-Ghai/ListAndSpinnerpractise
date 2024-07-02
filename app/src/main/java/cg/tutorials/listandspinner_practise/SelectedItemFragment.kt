package cg.tutorials.listandspinner_practise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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
    private lateinit var mainActivity: MainActivity
    lateinit var arrayAdapter: ArrayAdapter<Items>
     var cIndex:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity= activity as MainActivity
        arguments?.let {
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
        arrayAdapter= ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,mainActivity.list)
        binding.dySpinner.adapter = arrayAdapter.apply {
            mainActivity.list[cIndex].name
        }
        binding.numberPicker.minValue = 1
        binding.numberPicker.maxValue = 100
        binding.dySpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                cIndex=position
                binding.numberPicker.value=mainActivity.listAdapter.list[position].quantity
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        binding.orderBtn.setOnClickListener {
            mainActivity.list[cIndex].quantity = binding.numberPicker.value
            mainActivity.listAdapter.notifyDataSetChanged()
            Toast.makeText(requireContext(), "item quantity is updated to ${mainActivity.list[cIndex].quantity}", Toast.LENGTH_SHORT).show()
        }
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