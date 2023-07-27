package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator.databinding.FragmentButtonViewsBinding

class ButtonViewsFragment : Fragment() {
     var calcBtnClicked: CalculatorButtonsClicked? = null
    private var _binding: FragmentButtonViewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_button_views, container, false)
        _binding = FragmentButtonViewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btn0.setOnClickListener {
            calcBtnClicked?.btnClicked("0")
        }

        binding.btn1.setOnClickListener {
            calcBtnClicked?.btnClicked("1")
        }

        binding.btn2.setOnClickListener {
            calcBtnClicked?.btnClicked("2")
        }

        binding.btn3.setOnClickListener {
            calcBtnClicked?.btnClicked("3")
        }

        binding.btn4.setOnClickListener {
            calcBtnClicked?.btnClicked("4")
        }

        binding.btn5.setOnClickListener {
            calcBtnClicked?.btnClicked("5")
        }

        binding.btn6.setOnClickListener {
            calcBtnClicked?.btnClicked("6")
        }

        binding.btn7.setOnClickListener {
            calcBtnClicked?.btnClicked("7")
        }

        binding.btn8.setOnClickListener {
            calcBtnClicked?.btnClicked("8")
        }

        binding.btn9.setOnClickListener {
            calcBtnClicked?.btnClicked("9")
        }

        binding.btnDivide.setOnClickListener {
            calcBtnClicked?.btnClicked("/")
        }

        binding.btnMultiplication.setOnClickListener {
            calcBtnClicked?.btnClicked("X")
        }

        binding.btnMinus.setOnClickListener {
            calcBtnClicked?.btnClicked("-")
        }

        binding.btnPlus.setOnClickListener {
            calcBtnClicked?.btnClicked("+")
        }

        binding.btnPercent.setOnClickListener {
            calcBtnClicked?.btnClicked("%")
        }

        binding.btnEquals.setOnClickListener {
            calcBtnClicked?.btnClicked("=")
        }

        binding.btnC.setOnClickListener {
            calcBtnClicked?.btnClicked("ac")
        }

        binding.btnBackSpace.setOnClickListener {
            calcBtnClicked?.btnClicked("bs")
        }
        binding.btnHistory.setOnClickListener{
            calcBtnClicked?.btnClicked("history")
        }
    }

    companion object {
        fun newInstance() = ButtonViewsFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
       _binding = null
    }
}