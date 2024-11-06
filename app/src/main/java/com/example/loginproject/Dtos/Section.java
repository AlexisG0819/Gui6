package com.example.loginproject.Dtos;

import com.example.loginproject.Models.Presupuesto;

import java.util.List;

public class Section {
    private String sectionName;
    private List<Presupuesto> sectionItems;
    public Section(String sectionName, List<Presupuesto> sectionItems) {
        this.sectionName = sectionName;
        this.sectionItems = sectionItems;
    }
    public String getSectionName() {
        return sectionName;
    }
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    public List<Presupuesto> getSectionItems() {
        return sectionItems;
    }
    public void setSectionItems(List<Presupuesto> sectionItems) {
        this.sectionItems = sectionItems;
    }
    @Override
    public String toString() {
        return "Section{" +
                "sectionName='" + sectionName + '\'' +
                ", sectionItems=" + sectionItems +
                '}';
    }
}
