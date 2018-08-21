package com.example.boby3.vote;



   import android.content.Context;
           import android.content.Intent;
           import android.support.v7.app.AppCompatActivity;
           import android.os.Bundle;
           import android.support.v7.widget.LinearLayoutManager;
           import android.support.v7.widget.RecyclerView;
           import android.view.Menu;
           import android.view.MenuInflater;
           import android.view.MenuItem;
           import android.view.View;
           import android.widget.Button;
           import android.widget.ImageView;
           import android.widget.TextView;

           import com.firebase.ui.database.FirebaseRecyclerAdapter;
           import com.google.firebase.auth.FirebaseAuth;
           import com.google.firebase.auth.FirebaseUser;
           import com.google.firebase.database.DatabaseReference;
           import com.google.firebase.database.FirebaseDatabase;
           import com.squareup.picasso.Picasso;

public class recycle extends AppCompatActivity  {

    //firebase auth object
    private FirebaseAuth firebaseAuth;
    private RecyclerView mbloglist;
    private DatabaseReference mDatabase;

    //view objects

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    private FirebaseUser mCurrent;



    //defining firebaseauth object
    //private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Blog1");

        mbloglist=(RecyclerView) findViewById(R.id.blog_view);
        mbloglist.setHasFixedSize(true);
        mbloglist.setLayoutManager(new LinearLayoutManager(this));
        // firebaseAuth = FirebaseAuth.getInstance();
        //mAuth=FirebaseAuth.getInstance();



        //if getCurrentUser does not returns null
      /*  if(firebaseAuth.getCurrentUser() == null){
            //that means user is already logged in
            //so close this profileactivity
            finish();

            //and open login  activity
            startActivity(new Intent(getApplicationContext(), Main3Activity.class));

        }
*/

        database=FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog1");



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog, BlogViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(

                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase

        ) {

            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImageUrl(getApplicationContext(),model.getImageUrl());



            }
        };
        mbloglist.setAdapter(firebaseRecyclerAdapter);

    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setTitle(String title){
            TextView post_title=(TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);

        }
        public void setDescription(String description){
            TextView post_des=(TextView) mView.findViewById(R.id.post_des);
            post_des.setText(description);

        }
        public void  setImageUrl(Context ctx ,String imageUrl){
            ImageView post_image=(ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(imageUrl).into(post_image);
        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        if (id == R.id.action_add) {



            startActivity(new Intent(recycle. this, upload.class ));


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(recycle.this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}


