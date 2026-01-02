package com.example.langsphere.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.langsphere.R
import com.example.langsphere.databinding.LessonListBinding

class LessonList : Fragment() {

    private var _binding: LessonListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LessonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Static listeners
        binding.btnLesson1.setOnClickListener {
            findNavController().navigate(R.id.action_lessonListFragment_to_lessonFragment)
        }
        binding.btnLesson2.setOnClickListener {
            findNavController().navigate(R.id.action_lessonListFragment_to_lessonFragment)
        }
        binding.btnLesson3.setOnClickListener {
            findNavController().navigate(R.id.action_lessonListFragment_to_lessonFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
