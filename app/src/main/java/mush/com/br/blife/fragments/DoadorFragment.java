package mush.com.br.blife.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gordonwong.materialsheetfab.MaterialSheetFab;


import mush.com.br.blife.AdicionarDoadorActivity;
import mush.com.br.blife.R;
import mush.com.br.blife.custom.Fab;


public class DoadorFragment extends Fragment {

    MaterialSheetFab mMaterialSheetFab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public MaterialSheetFab getMaterialSheet(){
        return mMaterialSheetFab;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Fab fab = (Fab) getActivity().findViewById(R.id.fab_doador);
        View sheetView = getActivity().findViewById(R.id.fab_sheet);
        View overlay = getActivity().findViewById(R.id.dim_overlay);
        mMaterialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay, Color.WHITE, Color.WHITE);
        mMaterialSheetFab.showFab(-10,-10);

        View myTv = (TextView) getActivity().findViewById(R.id.tvNovo);
        myTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMaterialSheetFab.hideSheet();
                Intent intent = new Intent(getActivity(), AdicionarDoadorActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return  inflater.inflate(R.layout.fragment_doador, container, false);

    }



}
