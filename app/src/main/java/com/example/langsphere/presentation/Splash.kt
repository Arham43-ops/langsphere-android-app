package com.example.langsphere.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.langsphere.R
import com.example.langsphere.databinding.SplashBinding

class Splash : Fragment() {

    private var _binding: SplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Simple scale animation
        binding.ivLogo.animate().scaleX(1.1f).scaleY(1.1f).setDuration(1500).start()
        binding.tvAppName.alpha = 0f
        binding.tvAppName.animate().alpha(1f).setDuration(1000).setStartDelay(500).start()

        // Wait 3 seconds then navigate
        Handler(Looper.getMainLooper()).postDelayed({
            // Check if added to avoid crash if user exited app
            if (isAdded) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
