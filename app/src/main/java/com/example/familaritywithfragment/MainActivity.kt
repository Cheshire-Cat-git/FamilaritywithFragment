package com.example.familaritywithfragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.familaritywithfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.btn_fragment_container, FragmentBtn())
                .commit()
        }

        viewModel.isSwapped.observe(this) { isSwapped ->
            updateFragmentPlacement(isSwapped)
        }
    }

    private fun updateFragmentPlacement(isSwapped: Boolean) {
        val fm = supportFragmentManager

        val frag2 = fm.findFragmentByTag("FRAG2") ?: FragmentColor()
        val frag3 = fm.findFragmentByTag("FRAG3") ?: FragmentColor2()

        val removeTransaction = fm.beginTransaction()
        frag2.let { removeTransaction.remove(it) }
        frag3.let { removeTransaction.remove(it) }

        if (!fm.isStateSaved) {
            removeTransaction.commitNow()
        }
        val transaction = fm.beginTransaction()
        transaction.setReorderingAllowed(true)

        if (isSwapped) {
            transaction.replace(R.id.color_fragment_container_1, frag3, "FRAG3")
            transaction.replace(R.id.color_fragment_container_2, frag2, "FRAG2")
        } else {
            transaction.replace(R.id.color_fragment_container_1, frag2, "FRAG2")
            transaction.replace(R.id.color_fragment_container_2, frag3, "FRAG3")
        }

        if (!fm.isStateSaved) {
            transaction.commit()
        }
    }
}
