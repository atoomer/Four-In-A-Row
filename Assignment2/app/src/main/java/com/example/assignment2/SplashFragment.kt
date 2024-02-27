package com.example.assignment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class SplashFragment: Fragment (){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {

        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        val startButton = view.findViewById<Button>(R.id.start)
        val nameView = view.findViewById<EditText>(R.id.nameInput)

        startButton.setOnClickListener {
            val name = nameView.text.toString()
            val action = SplashFragmentDirections.actionSplashFragmentToGameFragment(name)
            view.findNavController().navigate(action)
        }

        return view
    }


}
