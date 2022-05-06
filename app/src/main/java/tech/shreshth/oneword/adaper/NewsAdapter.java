package tech.shreshth.oneword.adaper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import tech.shreshth.oneword.R;
import tech.shreshth.oneword.model.Articles;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Articles> articlesList;

    public NewsAdapter(List<Articles> articlesList) {
        this.articlesList = articlesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(articlesList.get(position).getTitle());
        holder.description.setText(articlesList.get(position).getDescription());
        Picasso.get().load(articlesList.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(articlesList==null)
            return 0;
        return articlesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title_text_view);
            description=itemView.findViewById(R.id.content_text_view);
            imageView=itemView.findViewById(R.id.news_item_image_view);
        }
    }
}
