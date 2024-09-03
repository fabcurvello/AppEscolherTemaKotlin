package br.com.fabriciocurvello.appescolhertemakotlin

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvTema: TextView
    private lateinit var btLight: Button
    private lateinit var btDark: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTema = findViewById(R.id.tv_tema)
        btLight = findViewById(R.id.bt_light)
        btDark = findViewById(R.id.bt_dark)

        // Recuperar tema salvo nas preferências
        val sharedPref = getPreferences(MODE_PRIVATE)
        val isDarkTheme = sharedPref.getBoolean("DARK_THEME", false)


        // Aplicar o tema
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            tvTema.text = "Tema atual: ESCURO"
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            tvTema.text = "Tema atual: CLARO"
        }


        // Configurar os botões para salvar a preferência do usuário
        btLight.setOnClickListener { saveThemePreference(false) }
        btDark.setOnClickListener { saveThemePreference(true) }

    } // fim do onCreate()

    private fun saveThemePreference(isDarkTheme: Boolean) {
        // Salvar preferência no armazenamento interno usando SharedPreferences
        val sharedPref = getPreferences(MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("DARK_THEME", isDarkTheme)
        editor.apply()

        // Aplicar o tema escolhido
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            tvTema.text = "Tema atual: ESCURO"
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            tvTema.text = "Tema atual: CLARO"
        }
    }
}