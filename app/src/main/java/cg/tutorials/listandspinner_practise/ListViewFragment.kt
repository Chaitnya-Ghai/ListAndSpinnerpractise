package cg.tutorials.listandspinner_practise

import android.app.ActionBar
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cg.tutorials.listandspinner_practise.databinding.CustomDialogBinding
import cg.tutorials.listandspinner_practise.databinding.FragmentListViewBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentListViewBinding

    private lateinit var mainActivity: MainActivity
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
//        return inflater.inflate(R.layout.fragment_list_view, container, false)
        binding = FragmentListViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listview.adapter = mainActivity.listAdapter
        binding.fab.setOnClickListener {
            val dialogViewBinding = CustomDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(requireContext()).apply {
                setContentView(dialogViewBinding.root)
                setCancelable(false)
                window?.setLayout(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
                show()
            }
            dialogViewBinding.addBtn.setOnClickListener {
                if (dialogViewBinding.etName.text.trim().toString().isNullOrBlank()) {
                    dialogViewBinding.etName.error = "Enter your Name"
                } else if (dialogViewBinding.etQuantity.text.trim().toString().isNullOrBlank()) {
                    dialogViewBinding.etQuantity.error = "Enter Quantity"
                } else {
                    mainActivity.list.add(
                        Items(
                            dialogViewBinding.etName.text.toString(),
                            dialogViewBinding.etQuantity.text.toString().toInt()
                        )
                    )
                    mainActivity.listAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialogViewBinding.cancelButton.setOnClickListener {
                dialog.dismiss()
            }
            mainActivity.binding?.bottomNav?.setOnClickListener {
                findNavController().navigate(R.id.selectedItemFragment)
            }
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}