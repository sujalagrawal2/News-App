package com.example.newsapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentBussinessNewsBinding
import com.example.newsapp.databinding.FragmentGeneralNewsBinding
import com.example.newsapp.model.NewsItem
import com.example.newsapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.max


class BussinessNewsFragment : Fragment() {
    private var newsAdapter = NewsAdapter()
    private lateinit var binding: FragmentBussinessNewsBinding
    private lateinit var newsList: List<NewsItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBussinessNewsBinding.inflate(layoutInflater)



        newsList = arrayListOf()
        setUpRecyclerView()
        fetchNews()

        return binding.root
    }

    private fun fetchNews() {
        val country = arguments?.getString(COUNTRY)
        val language = arguments?.getString(LANGUAGE)
        val search = arguments?.getString(SEARCH)
        val maxLine = arguments?.getString(MAXLINE).toString().toInt()
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getNewsItems(
                        "business",
                        country!!,
                        language!!,
                        maxLine,
                        search!!
                    )
                }
                if (response.isSuccessful) {
                    val articles = response.body()?.articles

                    newsAdapter.news = articles!!
                } else {
                    Log.e("Error", "Request failed with status: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("Error", "Request failed: ${e.message}")
            }
        }
    }


    private fun setUpRecyclerView() = binding.rv.apply {
        newsAdapter = NewsAdapter()
        adapter = newsAdapter
        layoutManager = LinearLayoutManager(context)
    }

    companion object {
        const val COUNTRY = "country"
        const val SEARCH = "search"
        const val LANGUAGE = "language"
        const val MAXLINE = "10"

        @JvmStatic
        fun newInstance(myString: String, search: String, language: String, maxLine: String) =
            BussinessNewsFragment().apply {
                arguments = Bundle().apply {
                    putString(COUNTRY, myString)
                    putString(SEARCH, search)
                    putString(LANGUAGE, language)
                    putString(MAXLINE, maxLine)
                }
            }
    }
}