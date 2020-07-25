package com.assignment.articleassignment.data.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.assignment.articleassignment.R
import com.assignment.articleassignment.data.models.ArticleModel
import com.assignment.articleassignment.utils.Util
import com.assignment.articleassignment.view.fragment.ArticleListFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_article_item.view.*

class ArticleAdapter(
    var context: ArticleListFragment,
    private var arrayListArticleData: ArrayList<ArticleModel?>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_article_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return arrayListArticleData.size
    }

    fun setUserData(arrArticle: ArrayList<ArticleModel?>) {
        arrayListArticleData = arrArticle
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as ItemViewHolder
        itemViewHolder.itemView.textUsername_ArticleListItem.text =
            arrayListArticleData.get(position)?.user?.get(0)?.name + " " + arrayListArticleData.get(
                position
            )?.user?.get(0)?.lastname
        itemViewHolder.itemView.textDesignation_ArticleListItem.text =
            arrayListArticleData.get(position)?.user?.get(0)?.designation
        Picasso.get().load(arrayListArticleData.get(position)?.user?.get(0)?.avatar)
            .placeholder(R.drawable.ic_user_default)
            .into(itemViewHolder.itemView.imageUser_ArticleListItem)

        itemViewHolder.itemView.textArticleContent_ArticleListItem.text =
            arrayListArticleData.get(position)?.content

        if (arrayListArticleData.get(position)?.media?.size!! > 0) {
            itemViewHolder.itemView.textArticleTitle_ArticleListItem.text =
                arrayListArticleData.get(position)?.media?.get(0)?.title
            itemViewHolder.itemView.textArticleLink_ArticleListItem.text =
                arrayListArticleData.get(position)?.media?.get(0)?.url

            Picasso.get().load(arrayListArticleData.get(position)?.media?.get(0)?.image)
                .placeholder(R.drawable.ic_article_default)
                .into(itemViewHolder.itemView.imageArticle_ArticleListItem)
        } else {
            itemViewHolder.itemView.textArticleTitle_ArticleListItem.visibility = View.GONE
            itemViewHolder.itemView.textArticleLink_ArticleListItem.visibility = View.GONE
            itemViewHolder.itemView.imageArticle_ArticleListItem.visibility = View.GONE

        }

        itemViewHolder.itemView.textLikesCount_ArticleListItem.text =
            arrayListArticleData.get(position)?.likes?.let { Util.convertValueInK(it) } + " " + context.getString(
                R.string.str_likes
            )
        itemViewHolder.itemView.textCommentsCount_ArticleListItem.text =
            arrayListArticleData.get(position)?.comments?.let { Util.convertValueInK(it) } + " " + context.getString(
                R.string.str_comments
            )

        itemViewHolder.itemView.textTime_ArticleListItem.setText(arrayListArticleData.get(position)?.createdAt?.let {
            Util.getTimePeriodBetweenDate(
                it
            )
        })

        //arrayListArticleData.get(position)?.createdAt?.let { Util.getTimePeriodBetweenDate(it) };

    }
}