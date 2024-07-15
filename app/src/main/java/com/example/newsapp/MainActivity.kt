package com.example.newsapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.fragment.BussinessNewsFragment
import com.example.newsapp.fragment.EntermentNewsFragment
import com.example.newsapp.fragment.GeneralNewsFragment
import com.example.newsapp.fragment.HealthNewsFragment
import com.example.newsapp.fragment.NationNewsFragment
import com.example.newsapp.fragment.ScienceNewsFragment
import com.example.newsapp.fragment.SportsNewsFragment
import com.example.newsapp.fragment.TechnologyNewsFragment
import com.example.newsapp.fragment.WorldNewsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var topic: String = "general"
    private lateinit var country: String
    private lateinit var language: String
    private lateinit var search: String
    private var maxLine: String = "10"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        changeTopic()
        filter()
        setCountryDropDown()
        setLanguageDropDown()
        doChanges()


    }

    private fun changeTopic() {
        binding.ivChangeTopic.setOnClickListener() {
            binding.clChangeTopic.isVisible = !binding.clChangeTopic.isVisible
        }
        binding.btnWorld.setOnClickListener() {
            topic = "world"
            doChanges()
        }
        binding.btnHealth.setOnClickListener() {
            topic = "health"
            doChanges()
        }
        binding.btnNation.setOnClickListener() {
            topic = "nation"
            doChanges()
        }
        binding.btnSports.setOnClickListener() {
            topic = "sports"
            doChanges()
        }
        binding.btnBusiness.setOnClickListener() {
            topic = "business"
            doChanges()
        }
        binding.btnEntertainment.setOnClickListener() {
            topic = "entertainment"
            doChanges()
        }
        binding.btnGeneral.setOnClickListener() {
            topic = "general"
            doChanges()
        }
        binding.btnScience.setOnClickListener() {
            topic = "science"
            doChanges()
        }
        binding.btnTechnology.setOnClickListener() {
            topic = "technology"
            doChanges()
        }
        binding.ivClose2.setOnClickListener() {
            binding.clChangeTopic.isVisible = false
        }


    }

    private fun filter() {
        binding.ivFilter.setOnClickListener() {

            binding.clFilter.isVisible = !binding.clFilter.isVisible
        }
        binding.ivClose.setOnClickListener() {
            binding.clFilter.isVisible = false
        }

        binding.btnFilter.setOnClickListener() {
            doChanges()
        }
    }

    private fun doChanges() {

        val maximumLine: Int = binding.etMaxLine.text.toString().toInt()
        maxLine = if (maximumLine > 100) "100"
        else maximumLine.toString()

        val inputSearch: String = binding.etSearch.text.toString()
        search = if (inputSearch == "") "None"
        else inputSearch
        val countryDD: String = binding.acCountry.text.toString()
        country = when (countryDD) {
            "India" -> "in"
            "China" -> "cn"
            "Germany" -> "de"
            "France" -> "fr"
            "Japan" -> "jp"
            "Taiwan" -> "tw"
            "United Kingdom" -> "gb"
            "United States" -> "us"
            "Any" -> "Any"
            else -> "Any"
        }

        val languageDD: String = binding.acLanguage.text.toString()
        language = when (languageDD) {
            "Hindi" -> "hi"
            "Chinese" -> "zh"
            "French" -> "fr"
            "English" -> "en"
            "Russian" -> "ru"
            "Tamil" -> "ta"
            "Telugu" -> "te"
            "Ukrainian" -> "uk"
            "Any" -> "Any"
            else -> "Any"
        }

        if (topic == "general") {
            goToGeneralFragment()
        }
        if (topic == "business") {
            goToBusinessFragment()
        }
        if (topic == "entertainment") {
            goToEntertainmentFragment()
        }
        if (topic == "health") {
            goToHealthFragment()
        }
        if (topic == "nation") {
            goToNationFragment()
        }
        if (topic == "science") {
            goToScienceFragment()
        }
        if (topic == "sports") {
            goToSportsFragment()
        }
        if (topic == "technology") {
            goToTechnologyFragment()
        }
        if (topic == "world") {
            goToWorldFragment()
        }

    }

    private fun setLanguageDropDown() {
        val items =
            listOf(
                "Hindi",
                "Chinese",
                "French",
                "English",
                "Russian",
                "Tamil",
                "Telugu",
                "Ukrainian",
                "Any"
            )

        val adapter = ArrayAdapter(this, R.layout.drop_down_item, items)

        binding.acLanguage.setAdapter(adapter)
        binding.acLanguage.setText("Any", false)
    }

    private fun setCountryDropDown() {
        val items =
            listOf(
                "India",
                "China",
                "Germany",
                "France",
                "Japan",
                "Taiwan",
                "United Kingdom",
                "United States",
                "Any"
            )

        val adapter = ArrayAdapter(this, R.layout.drop_down_item, items)

        binding.acCountry.setAdapter(adapter)
        binding.acCountry.setText("Any", false)

    }

    private fun goToGeneralFragment() {
        binding.tvTopNews.setText("General News")
        val fragment: Fragment = GeneralNewsFragment.newInstance(country, search, language, maxLine)
        val changeFragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        changeFragment.replace(R.id.frame, fragment).commit()
    }

    private fun goToBusinessFragment() {
        binding.tvTopNews.setText("Business News")
        val fragment: Fragment =
            BussinessNewsFragment.newInstance(country, search, language, maxLine)
        val changeFragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        changeFragment.replace(R.id.frame, fragment).commit()
    }

    private fun goToEntertainmentFragment() {
        binding.tvTopNews.setText("Entertainment News")
        val fragment: Fragment =
            EntermentNewsFragment.newInstance(country, search, language, maxLine)
        val changeFragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        changeFragment.replace(R.id.frame, fragment).commit()
    }

    private fun goToHealthFragment() {
        binding.tvTopNews.setText("Health News")
        val fragment: Fragment = HealthNewsFragment.newInstance(country, search, language, maxLine)
        val changeFragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        changeFragment.replace(R.id.frame, fragment).commit()
    }

    private fun goToNationFragment() {
        binding.tvTopNews.setText("Nation News")
        val fragment: Fragment = NationNewsFragment.newInstance(country, search, language, maxLine)
        val changeFragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        changeFragment.replace(R.id.frame, fragment).commit()
    }

    private fun goToScienceFragment() {
        binding.tvTopNews.setText("Science News")
        val fragment: Fragment = ScienceNewsFragment.newInstance(country, search, language, maxLine)
        val changeFragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        changeFragment.replace(R.id.frame, fragment).commit()
    }

    private fun goToSportsFragment() {
        binding.tvTopNews.setText("Sports News")
        val fragment: Fragment = SportsNewsFragment.newInstance(country, search, language, maxLine)
        val changeFragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        changeFragment.replace(R.id.frame, fragment).commit()
    }

    private fun goToTechnologyFragment() {
        binding.tvTopNews.setText("Technology News")
        val fragment: Fragment =
            TechnologyNewsFragment.newInstance(country, search, language, maxLine)
        val changeFragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        changeFragment.replace(R.id.frame, fragment).commit()
    }

    private fun goToWorldFragment() {
        binding.tvTopNews.setText("World News")
        val fragment: Fragment = WorldNewsFragment.newInstance(country, search, language, maxLine)
        val changeFragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        changeFragment.replace(R.id.frame, fragment).commit()
    }

}