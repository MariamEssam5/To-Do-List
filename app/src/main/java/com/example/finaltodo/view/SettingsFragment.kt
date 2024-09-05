package com.example.finaltodo.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatDelegate
import com.example.finaltodo.R

class SettingsFragment : Fragment() {

    private lateinit var radioGroupTheme: RadioGroup
    private lateinit var sharedPreferences: SharedPreferences
    private val THEME_PREF_KEY = "theme_pref"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        radioGroupTheme = view.findViewById(R.id.radioGroupTheme)

        // Load saved theme preference
        when (sharedPreferences.getString(THEME_PREF_KEY, "Light")) {
            "Light" -> radioGroupTheme.check(R.id.radioLight)
            "Dark" -> radioGroupTheme.check(R.id.radioDark)
        }

        radioGroupTheme.setOnCheckedChangeListener { _, checkedId ->
            val theme = when (checkedId) {
                R.id.radioLight -> "Light"
                R.id.radioDark -> "Dark"
                else -> "Light"
            }
            saveThemePreference(theme)
            applyTheme(theme)
        }
    }

    private fun saveThemePreference(theme: String) {
        with(sharedPreferences.edit()) {
            putString(THEME_PREF_KEY, theme)
            apply()
        }
    }

    private fun applyTheme(theme: String) {
        when (theme) {
            "Dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "Light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        // Restart activity to apply the theme change
        requireActivity().recreate()
    }
}
