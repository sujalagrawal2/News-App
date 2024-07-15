package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.model.NewsItem


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


    private val differCallBack = object : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.url == newItem.url &&
                    oldItem.title == newItem.title &&
                    oldItem.source == newItem.source &&
                    oldItem.image == newItem.image &&
                    oldItem.description == newItem.description &&
                    oldItem.content == newItem.content &&
                    oldItem.publishedAt == newItem.publishedAt
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    var news: List<NewsItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = news[position]
        holder.binding.tvTitle.text = currentNews.title
        holder.binding.tvDescription.text = currentNews.description
        Glide.with(holder.itemView.context)
            .load(currentNews.url)
            .into(holder.binding.ivImage)
    }
}