package cg.tutorials.listandspinner_practise

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.findNavController
import cg.tutorials.listandspinner_practise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
     var binding: ActivityMainBinding?=  null
    private var navController: NavController?=  null
    var list = arrayListOf<Items>()
    var listAdapter = ListAdapter(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navController = findNavController(R.id.host)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.listViewFragment){
                binding?.bottomNav?.menu?.get(0)?.setChecked(true)
            }
            if(destination.id == R.id.selectedItemFragment){
                binding?.bottomNav?.menu?.get(1)?.setChecked(true)
            }
        }

        binding?.bottomNav?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.first-> navController?.navigate(R.id.listViewFragment)
                R.id.second -> navController?.navigate(R.id.selectedItemFragment)
            }
            return@setOnItemSelectedListener true
        }
    }
}
