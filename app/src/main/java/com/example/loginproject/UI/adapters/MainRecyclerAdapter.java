package com.example.loginproject.UI.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginproject.Dtos.Section;
import com.example.loginproject.Models.Presupuesto;
import com.example.loginproject.R;
import com.example.loginproject.UI.viewHolders.MainViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainViewHolder> {
    List<Section> sectionList;
    private List<Presupuesto> sectionItems;
    private final ChildRecyclerAdapter.OnItemChildClickListener onChildItemClickListener;
    public MainRecyclerAdapter(List<Presupuesto> sectionItems, ChildRecyclerAdapter.OnItemChildClickListener
            itemClickListener) {
        this.sectionItems = sectionItems;
        this.onChildItemClickListener = itemClickListener;
        this.sectionList = new ArrayList<>();
        sectionList.add(new Section("ACTIVAS", onlyActiveBudget() ));
        sectionList.add(new Section("COMPLETADAS", onlyNonActiveBudget() )); }
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.section_row, parent, false);
        return new MainViewHolder(v);}
    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Section section = sectionList.get(position);
        String sectionName = section.getSectionName();
        List<Presupuesto> items = new ArrayList<>();
        switch (sectionName) {
            case "ACTIVAS":
                items = onlyActiveBudget();
                break;
            case "COMPLETADAS":
                items = onlyNonActiveBudget();
                break;
        }
        holder.sectionName.setText(sectionName);
        ChildRecyclerAdapter childRecyclerAdapter = new ChildRecyclerAdapter(items, onChildItemClickListener);
        holder.childRcv.setAdapter(childRecyclerAdapter);
    }
    @Override
    public int getItemCount() {
        return sectionList.size();
    }
    public void setDataList(List<Presupuesto> dataList) {
        this.sectionItems = dataList;
        notifyDataSetChanged();
    }
    private List<Presupuesto> onlyActiveBudget(){
        return sectionItems.stream()
                .filter(Presupuesto::isActivo)
                .collect(Collectors.toList());
    }
    private List<Presupuesto> onlyNonActiveBudget(){
        return sectionItems.stream()
                .filter(budget -> !budget.isActivo())
                .collect(Collectors.toList());
    }
}
