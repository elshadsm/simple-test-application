package com.example.simple_test_application

import android.annotation.SuppressLint
import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_plus_one.*

class PlusOneFragment(
    var index: Int = 0,
    var title: String = "No Title",
    var badge: Int = 1,
    var clickInterface: ClickInterface
) : Fragment() {

    var visibility: Int = View.VISIBLE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_plus_one, container, false)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plusOneButton.text = "${index+1}. Button"
        plusOneButton.setOnClickListener {
            clickInterface.buttonClicked(index)
        }
    }

}

interface ClickInterface {
    fun buttonClicked(index: Int)
}
