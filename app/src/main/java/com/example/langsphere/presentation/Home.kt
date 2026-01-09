package com.example.langsphere.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.langsphere.R
import com.example.langsphere.databinding.HomeBinding
import com.example.langsphere.presentation.adapter.LanguageAdapter
import com.example.langsphere.presentation.viewmodel.MainViewModel

class Home : Fragment() {

    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: LanguageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.btnAddCourse.setOnClickListener {
            Toast.makeText(context, "Add course coming soon!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        adapter = LanguageAdapter(emptyList()) { language ->
            viewModel.loadLessonsForLanguage(language.id)
            findNavController().navigate(R.id.action_homeFragment_to_lessonListFragment)
        }
        binding.rvCourses.layoutManager = LinearLayoutManager(context)
        binding.rvCourses.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.languages.observe(viewLifecycleOwner) { languages ->
            adapter.updateData(languages)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
