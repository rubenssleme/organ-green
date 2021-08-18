package com.example.organ.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organ.R;
import com.example.organ.model.Categoria;

import java.util.List;

public class ListaCategoriaAdapter extends RecyclerView.Adapter<ListaCategoriaAdapter.MyViewHolder> {

    private List<Categoria> categorias;
    private static final String TAG = "ListaRecyclerAdapter";
    private OnCategoryListener  mOnCategoryListener;

    public ListaCategoriaAdapter(List<Categoria> listaCategoria,OnCategoryListener onCategoryListener) {
        this.categorias = listaCategoria;
        this.mOnCategoryListener = onCategoryListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemListaCategoria = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_categoria, parent, false);

        return new MyViewHolder(itemListaCategoria,mOnCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Categoria categoria = categorias.get(position);
        holder.nomeCategoria.setText(categoria.getNomeCategoria());
        holder.fotoCategoria.setImageResource(categoria.getImgProdutoCategoria());

    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView fotoCategoria;
        TextView nomeCategoria;
        OnCategoryListener mOnCategoryListener;

        public MyViewHolder(View itemView, OnCategoryListener onCategoryListener ) {
            super(itemView);

            fotoCategoria = itemView.findViewById(R.id.imgProdutoCategoria);
            nomeCategoria = itemView.findViewById(R.id.txtNomeCategoria);
            mOnCategoryListener = onCategoryListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
           mOnCategoryListener.onCategoryClick(getAdapterPosition());
        }
    }

    public interface OnCategoryListener {
        void onCategoryClick(int position);
    }

}
