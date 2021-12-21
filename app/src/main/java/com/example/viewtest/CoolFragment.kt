package com.example.viewtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


private var viewsCreated = 0
private var viewsDestroyed = 0
private var globalId = 0

private var log = ""

class CoolFragment : Fragment(R.layout.cool_layout) {

    private val localId = globalId++

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewsCreated++
        view.findViewById<TextView>(R.id.coolText).text =
            "Created: $viewsCreated Destroyed: $viewsDestroyed"

        click(view, R.id.refresh) {
            findNavController().navigate(R.id.action_global_coolFragment)
        }

        refreshLog(view)
        click(view, R.id.refreshLog) {
            refreshLog(view)
        }

        log += "created $localId\n"
    }

    private fun refreshLog(view: View) {
        view.findViewById<TextView>(R.id.log).text = log
    }

    private fun click(
        view: View, refresh: Int, function: (View) -> Unit
    ) = view.findViewById<Button>(refresh).setOnClickListener(function)

    override fun onDestroyView() {
        log += "destroyed $localId\n"
        viewsDestroyed++
        super.onDestroyView()
    }
}