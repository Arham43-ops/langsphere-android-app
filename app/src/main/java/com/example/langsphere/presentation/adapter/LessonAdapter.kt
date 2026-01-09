package com.example.langsphere.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.langsphere.R
import com.example.langsphere.data.model.Lesson
import com.example.langsphere.databinding.ItemLessonBinding

class LessonAdapter(
    private var lessons: List<Lesson>,
    private val onItemClick: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    inner class LessonViewHolder(val binding: ItemLessonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val binding = ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LessonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        holder.binding.apply {
            tvLessonNumber.text = lesson.number.toString()
            tvLessonTitle.text = lesson.title
            tvLessonInfo.text = "${lesson.phraseCount} phrases • ${lesson.duration}"
            
            when (lesson.status) {
                "completed" -> {
                    tvLessonStatus.text = "✓ Completed"
                    tvLessonStatus.setTextColor(Color.parseColor("#4CAF50"))
                    ivActionIcon.setImageResource(android.R.drawable.ic_media_play)
                    ivActionIcon.alpha = 1.0f
                }
                "in_progress" -> {
                    tvLessonStatus.text = "In Progress"
                    tvLessonStatus.setTextColor(Color.parseColor("#FF9800"))
                    ivActionIcon.setImageResource(android.R.drawable.ic_media_play)
                    ivActionIcon.alpha = 1.0f
                }
                else -> {
                    tvLessonStatus.text = "🔒 Locked"
                    tvLessonStatus.setTextColor(Color.parseColor("#9E9E9E"))
                    ivActionIcon.setImageResource(android.R.drawable.ic_lock_lock)
                    ivActionIcon.alpha = 0.5f
                }
            }
            
            root.setOnClickListener { 
                if (lesson.status != "locked") onItemClick(lesson) 
            }
        }
    }

    override fun getItemCount() = lessons.size

    fun updateData(newLessons: List<Lesson>) {
        lessons = newLessons
        notifyDataSetChanged()
    }
}
