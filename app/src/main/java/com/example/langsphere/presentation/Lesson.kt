package com.example.langsphere.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.langsphere.databinding.LessonBinding
import com.example.langsphere.presentation.adapter.PhraseAdapter
import com.example.langsphere.presentation.viewmodel.MainViewModel

class Lesson : Fragment() {

    private var _binding: LessonBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: PhraseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LessonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        binding.btnCompleteLesson.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupRecyclerView() {
        adapter = PhraseAdapter(emptyList())
        binding.rvPhrases.layoutManager = LinearLayoutManager(context)
        binding.rvPhrases.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.phrases.observe(viewLifecycleOwner) { phrases ->
            adapter.updateData(phrases)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
