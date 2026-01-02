package com.example.langsphere.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.langsphere.databinding.SettingsBinding

class Settings : Fragment() {

    private var _binding: SettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnChangePassword.setOnClickListener {
            Toast.makeText(context, "Change password clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnPrivacy.setOnClickListener {
            Toast.makeText(context, "Privacy settings clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnDailyGoal.setOnClickListener {
            Toast.makeText(context, "Daily goal clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnLanguage.setOnClickListener {
            Toast.makeText(context, "App language clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnAbout.setOnClickListener {
            Toast.makeText(context, "About LangSphere clicked", Toast.LENGTH_SHORT).show()
        }

        binding.switchSound.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(context, "Sound effects: $isChecked", Toast.LENGTH_SHORT).show()
        }

        binding.switchReminders.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(context, "Daily reminders: $isChecked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
