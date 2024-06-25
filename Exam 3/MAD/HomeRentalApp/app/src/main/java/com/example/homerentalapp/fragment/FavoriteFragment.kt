package com.example.homerentalapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.homerentalapp.R
import com.example.homerentalapp.screens.DetailsActivity
import com.example.homerentalapp.screens.Home2Activity
import com.example.homerentalapp.screens.LoginActivity

class FavoriteFragment : Fragment() {

    private lateinit var  rent1: Button
    private lateinit var  rent2: Button
    private lateinit var  rent3: Button
    private lateinit var  rent4: Button
    private lateinit var  rent5: Button
    private lateinit var  back: Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        rent1 = view.findViewById(R.id.button5)
        rent1.setOnClickListener {
            val intent = Intent(requireContext(), DetailsActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        rent2 = view.findViewById(R.id.button6)
        rent2.setOnClickListener {
            val intent = Intent(requireContext(), DetailsActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        rent3 = view.findViewById(R.id.button7)
        rent3.setOnClickListener {
            val intent = Intent(requireContext(), DetailsActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        rent4 = view.findViewById(R.id.button8)
        rent4.setOnClickListener {
            val intent = Intent(requireContext(), DetailsActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        rent5 = view.findViewById(R.id.button9)
        rent5.setOnClickListener {
            val intent = Intent(requireContext(), DetailsActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        back = view.findViewById(R.id.button18)
        back.setOnClickListener {
            val intent = Intent(requireContext(), Home2Activity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        return view
    }


}