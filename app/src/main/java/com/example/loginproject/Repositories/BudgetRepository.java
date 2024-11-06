package com.example.loginproject.Repositories;

import com.example.loginproject.Dtos.PresupuestoDto;
import com.example.loginproject.Models.Presupuesto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class BudgetRepository {
    String mainNameDocument = "presupuesto";
    private FirebaseFirestore databaseReference;
    public BudgetRepository() {
        databaseReference = FirebaseFirestore.getInstance();
    }
    public void addBudget(Presupuesto budget, OnSuccessListener<DocumentReference> onSuccess, OnFailureListener onFailure) {
        PresupuestoDto mainInserObject = new PresupuestoDto(budget.getNombre(), budget.getMonto(), true);
        databaseReference.collection(mainNameDocument)
                .add(mainInserObject)
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }
    public void listenForBudgetChanges(EventListener<QuerySnapshot> listener) {
        databaseReference.collection(mainNameDocument)
                .addSnapshotListener(listener);
    }
}
