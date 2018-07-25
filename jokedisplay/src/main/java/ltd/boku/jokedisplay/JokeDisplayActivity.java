package ltd.boku.jokedisplay;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JokeDisplayActivity extends AppCompatActivity {
    public static final String JOKES="jokes";

    List<String> jokes=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        RecyclerView recyclerView=findViewById(R.id.joke_recycler);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(dividerItemDecoration);

        if (getIntent().hasExtra(JOKES)){
            jokes=(List<String>)getIntent().getSerializableExtra(JOKES);
        }
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            List<String> recyclerJokes=jokes;

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View jokeItemView=getLayoutInflater().inflate(R.layout.joke_layout,viewGroup,false);
                JokeViewHolder jokeViewHolder=new JokeViewHolder(jokeItemView);
                return jokeViewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                JokeViewHolder jokeViewHolder=(JokeViewHolder)viewHolder;
                jokeViewHolder.jokeText.setText(recyclerJokes.get(i));
            }

            @Override
            public int getItemCount() {
                if (recyclerJokes==null)return 0;
                return recyclerJokes.size();
            }

            private void setJokes(){
                recyclerJokes=jokes;
                notifyDataSetChanged();
            }

            class JokeViewHolder extends RecyclerView.ViewHolder{
                TextView jokeText;
                public JokeViewHolder(@NonNull View itemView) {
                    super(itemView);
                    jokeText=itemView.findViewById(R.id.joke_text);
                }
            }
        });
    }
}
