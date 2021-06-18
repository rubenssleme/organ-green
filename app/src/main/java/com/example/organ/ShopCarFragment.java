package com.example.organ;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopCarFragment extends Fragment {


    public ShopCarFragment() {
        // Required empty public constructor
    }


    private Button btnFinalizar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_car, container, false);

        btnFinalizar = view.findViewById(R.id.btnFinalizarCompra);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "É necessário logar primeiro", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
