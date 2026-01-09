package com.example.langsphere.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.langsphere.data.model.Phrase
import com.example.langsphere.databinding.ItemPhraseBinding

class PhraseAdapter(
    private var phrases: List<Phrase>
) : RecyclerView.Adapter<PhraseAdapter.PhraseViewHolder>() {

    inner class PhraseViewHolder(val binding: ItemPhraseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhraseViewHolder {
        val binding = ItemPhraseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhraseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhraseViewHolder, position: Int) {
        val phrase = phrases[position]
        holder.binding.apply {
            tvOriginalText.text = phrase.originalText
            tvTranslatedText.text = phrase.translatedText
            
            btnPlayAudio.setOnClickListener {
                Toast.makeText(it.context, "Playing audio for: ${phrase.translatedText}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount() = phrases.size

    fun updateData(newPhrases: List<Phrase>) {
        phrases = newPhrases
        notifyDataSetChanged()
    }
}
