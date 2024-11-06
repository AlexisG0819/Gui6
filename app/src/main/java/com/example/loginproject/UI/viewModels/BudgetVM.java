package com.example.loginproject.UI.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginproject.Models.Presupuesto;
import com.example.loginproject.Repositories.BudgetRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class BudgetVM extends ViewModel {
    private BudgetRepository repository;
    private MutableLiveData<List<Presupuesto>> budgetLiveData;
    public BudgetVM() {
        repository = new BudgetRepository();
        budgetLiveData = new MutableLiveData<>();
        listenForBudgetChanges();
    }
    public void addBudget(Presupuesto mainObject, OnSuccessListener<DocumentReference> onSuccess, OnFailureListener onFailure)
    {
        repository.addBudget(mainObject, onSuccess, onFailure);

    }
    public LiveData<List<Presupuesto>> getBudgetLiveData() {
        return budgetLiveData;
    }
    private void listenForBudgetChanges() {
        repository.listenForBudgetChanges((querySnapshot, e) -> {
            if (e != null) {
                // Handle the error
                return;
            }
            List<Presupuesto> presupuestos = new ArrayList<>();
            for (QueryDocumentSnapshot document : querySnapshot) {
                Presupuesto mainObject = document.toObject(Presupuesto.class);
                mainObject.setId(document.getId());
                presupuestos.add(mainObject);
            }
            budgetLiveData.postValue(presupuestos);
        });
    }
}
