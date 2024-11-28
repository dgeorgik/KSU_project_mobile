package com.example.ksu_project_mobile.fragments

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.ksu_project_mobile.databinding.FragmentSettingsBinding
import java.io.File

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext()
            .getSharedPreferences("app_settings", Context.MODE_PRIVATE)


        binding.lightThemeButton.setOnClickListener {
            applyTheme(isDarkMode = false)
            sharedPreferences.edit().putBoolean("dark_mode", false).apply()
        }

        binding.darkThemeButton.setOnClickListener {
            applyTheme(isDarkMode = true)
            sharedPreferences.edit().putBoolean("dark_mode", true).apply()
        }
         updateFileStatus()

        binding.deleteFileButton.setOnClickListener {
            if (isCalendarFilePresent(requireContext())) {
                if (backupCalendarFile(requireContext())) {
                    deleteCalendarFile(requireContext())
                    updateFileStatus()
                } else {
                    Log.e("SettingsFragment", "Резервная копия не создана, файл не будет удалён")
                }
            } else {
                Log.d("SettingsFragment", "Файл для удаления отсутствует")
            }
        }



        binding.restoreFileButton.setOnClickListener {
            if (restoreCalendarFile(requireContext())) {
                updateFileStatus()
            }
        }
    }

    private fun applyTheme(isDarkMode: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    private fun isCalendarFilePresent(context: Context): Boolean {
        val fileDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) ?: return false
        val file = File(fileDir, "calendar_days.txt")
        return file.exists()
    }

    private fun deleteCalendarFile(context: Context): Boolean {
        val fileDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) ?: return false
        val file = File(fileDir, "calendar_days.txt")
        return if (file.exists()) {
            file.delete()
            Log.d("SettingsFragment", "Файл удалён: ${file.absolutePath}")
            true
        } else {
            false
        }
    }

    private fun backupCalendarFile(context: Context): Boolean {
        val externalFileDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) ?: return false
        val externalFile = File(externalFileDir, "calendar_days.txt")

        if (!externalFile.exists()) {
            Log.d("SettingsFragment", "Файл для резервного копирования отсутствует: ${externalFile.absolutePath}")
            return false
        }

        val internalFileDir = context.filesDir
        val internalFile = File(internalFileDir, "calendar_backup.txt")

        Log.d("SettingsFragment", "Путь для резервной копии: ${internalFile.absolutePath}")

        return try {
            externalFile.copyTo(internalFile, overwrite = true)
            Log.d("SettingsFragment", "Резервная копия создана: ${internalFile.absolutePath}")
            true
        } catch (e: Exception) {
            Log.e("SettingsFragment", "Ошибка резервного копирования: ${e.message}", e)
            false
        }
    }


        private fun restoreCalendarFile(context: Context): Boolean {
            val internalFileDir = context.filesDir
            val internalFile = File(internalFileDir, "calendar_backup.txt")

            if (!internalFile.exists()) {
                Log.d("SettingsFragment", "Резервная копия отсутствует")
                return false
            }

            val externalFileDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) ?: return false
            val externalFile = File(externalFileDir, "calendar_days.txt")

            return try {
                internalFile.copyTo(externalFile, overwrite = true)
                Log.d("SettingsFragment", "Файл восстановлен: ${externalFile.absolutePath}")
                true
            } catch (e: Exception) {
                Log.e("SettingsFragment", "Ошибка восстановления файла", e)
                false
            }
        }


    private fun updateFileStatus() {
        val isFilePresent = isCalendarFilePresent(requireContext())
        binding.fileStatusTextView.text =
            if (isFilePresent) "Файл присутствует в директории" else "Файл отсутствует"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
