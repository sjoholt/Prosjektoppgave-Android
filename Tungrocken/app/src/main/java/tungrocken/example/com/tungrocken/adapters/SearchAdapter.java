package tungrocken.example.com.tungrocken.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import tungrocken.example.com.tungrocken.R;
import tungrocken.example.com.tungrocken.domain.Article;

/**
 * Created by Team Tungrocken on 15.11.2016.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyGridViewHolder> {

    List<Article> articles;
    OnClickListener listener = position -> {};
    Context context;

    public interface OnClickListener {
        void onClick(int position);
    }

    public SearchAdapter(@NonNull Context context, @NonNull List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    public class MyGridViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView ingress;
        public TextView content;
        public TextView youtubeUrl;
        public ImageView photoUrl;

        public MyGridViewHolder(View view) {
            super(view);
            view.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition());
            });
            this.title = (TextView)view.findViewById(R.id.title2);
            this.ingress = (TextView)view.findViewById(R.id.ingress);
            this.content = (TextView) view.findViewById(R.id.content);
            this.youtubeUrl = (TextView) view.findViewById(R.id.youtubeUrl);
            this.photoUrl = (ImageView) view.findViewById(R.id.photoUrl);
        }
    }

    @Override
    public MyGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = viewType;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new MyGridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyGridViewHolder holder, int position) {
        Article article = articles.get(position);

        holder.title.setText(Html.fromHtml(article.getTitle()));
        holder.ingress.setText(Html.fromHtml(article.getIngress()));
        holder.content.setVisibility(View.GONE);
        holder.youtubeUrl.setVisibility(View.GONE);
        holder.photoUrl.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.homearticles;
    }

    public void setOnClickListener(@NonNull OnClickListener listener) {
        this.listener = listener;
    }
}