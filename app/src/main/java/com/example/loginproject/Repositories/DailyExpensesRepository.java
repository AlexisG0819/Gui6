package com.example.loginproject.Repositories;

import com.example.loginproject.Models.GastoDia;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class DailyExpensesRepository {
    String mainNameDocument = "gastosDia";
    private FirebaseFirestore databaseReference;
    public DailyExpensesRepository() {
        databaseReference = FirebaseFirestore.getInstance();
    }
    public void addDailyExpense(GastoDia dailyExpense, OnSuccessListener<DocumentReference> onSuccess, OnFailureListener onFailure) {
        databaseReference.collection(mainNameDocument)
                .add(dailyExpense)
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }
    public void endBudget(String budgetId, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        databaseReference.collection("presupuesto")
                .document(budgetId)
                .update("activo", false)
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }
    public void listenForExpensesChanges(String budgetId, EventListener<QuerySnapshot> listener) {
        databaseReference.collection(mainNameDocument)
                .whereEqualTo("idPresupuesto", budgetId)
                .addSnapshotListener(listener);
    }
}
