package com.example.langsphere.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.langsphere.R
import com.example.langsphere.databinding.LessonListBinding
import com.example.langsphere.presentation.adapter.LessonAdapter
import com.example.langsphere.presentation.viewmodel.MainViewModel

class LessonList : Fragment() {

    private var _binding: LessonListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: LessonAdapter

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
        
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = LessonAdapter(emptyList()) { lesson ->
            viewModel.loadPhrasesForLesson(lesson.id)
            findNavController().navigate(R.id.action_lessonListFragment_to_lessonFragment)
        }
        binding.rvLessons.layoutManager = LinearLayoutManager(context)
        binding.rvLessons.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.lessons.observe(viewLifecycleOwner) { lessons ->
            adapter.updateData(lessons)
            val completedCount = lessons.count { it.status == "completed" }
            binding.tvCourseProgressValue.text = "$completedCount of ${lessons.size} lessons completed"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
