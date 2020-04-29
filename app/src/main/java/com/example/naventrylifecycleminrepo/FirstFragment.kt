package com.example.naventrylifecycleminrepo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        observeResult()
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        view.findViewById<Button>(R.id.button_dialog).setOnClickListener {
            findNavController().navigate(R.id.toDialog)
        }
    }

    private fun observeResult() {
        val navBackStackEntry = findNavController().getBackStackEntry(R.id.FirstFragment)
        navBackStackEntry.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            Log.d("FirstFragment", "$event")
            if (event == Lifecycle.Event.ON_RESUME && navBackStackEntry.savedStateHandle.contains("TEST")) {
                val result = navBackStackEntry.savedStateHandle.get<String>("TEST")
                result?.let { onResult(it) }
            }
        })
    }

    private fun onResult(result: String) {
        Toast.makeText(requireContext(), result, Toast.LENGTH_LONG).show()
    }
}