package com.example.langsphere.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.langsphere.data.model.Language
import com.example.langsphere.databinding.ItemLanguageBinding

class LanguageAdapter(
    private var languages: List<Language>,
    private val onItemClick: (Language) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(val binding: ItemLanguageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val language = languages[position]
        holder.binding.apply {
            tvLanguageIcon.text = language.icon
            tvLanguageName.text = language.name
            tvLessonCount.text = "${language.totalLessons} Lessons"
            tvProgress.text = "${language.progress}% Complete"
            progressBar.progress = language.progress
            
            root.setOnClickListener { onItemClick(language) }
        }
    }

    override fun getItemCount() = languages.size

    fun updateData(newLanguages: List<Language>) {
        languages = newLanguages
        notifyDataSetChanged()
    }
}
