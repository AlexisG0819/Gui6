package com.example.loginproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginproject.Models.GastoDia;
import com.example.loginproject.Models.Presupuesto;
import com.example.loginproject.UI.adapters.DailyExpenseRecyclerAdapter;
import com.example.loginproject.UI.viewModels.DailyExpensesVM;
import com.example.loginproject.databinding.ActivityDetailBudgetBinding;

import java.util.ArrayList;
import java.util.Objects;

public class DetailBudget extends AppCompatActivity {
    private ActivityDetailBudgetBinding binding;
    private DailyExpensesVM viewModel;
    private DailyExpenseRecyclerAdapter mainRecyclerAdapter;
    Presupuesto receivedObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_budget);
        binding = ActivityDetailBudgetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        receivedObject = (Presupuesto) intent.getSerializableExtra("mainBudget");
        setDefaultValues();
        binding.edtCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatedTotal();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        binding.edtPrecio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatedTotal();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        viewModel = new ViewModelProvider(this).get(DailyExpensesVM.class);
        mainRecyclerAdapter = new DailyExpenseRecyclerAdapter(new ArrayList<>());
        binding.rcvCompras.setAdapter(mainRecyclerAdapter);
        binding.rcvCompras.setHasFixedSize(true);
        viewModel.listenForExpensesChanges(Objects.requireNonNull(receivedObject).getId());
        viewModel.getDailyExpensesLiveData().observe(this, budgets -> {
            mainRecyclerAdapter.setDataList(budgets);
            rebuildAmountAvaliable();
        });
        binding.imvFinalizar.setOnClickListener(v -> {
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
            dialogo1.setTitle("");
            dialogo1.setMessage("¿Desea finalizar el presupuesto?");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    viewModel.finalizeBudget(
                            receivedObject.getId(),
                            documentReference -> {
                                finish();
                            },
                            e -> {
                                Toast.makeText(DetailBudget.this, "No se pudo finalizar el presupuesto", Toast.LENGTH_SHORT).show();
                            });
                }
            });
            dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                }
            });
            dialogo1.show();
        });
        binding.btnGuardarDia.setOnClickListener(v -> {
            double actualPrice = Double.parseDouble(binding.edtPrecio.getText().toString());
            int actualAmount = Integer.parseInt(binding.edtCantidad.getText().toString());
            GastoDia mObject = new GastoDia(
                    binding.edtArticulo.getText().toString(),
                    actualPrice,
                    actualAmount,
                    getSubtotal(actualPrice, actualAmount),
                    receivedObject.getId());
            viewModel.addDailyExpense(
                    mObject,
                    documentReference -> {
                        setDefaultValues();
                        rebuildAmountAvaliable();
                        Toast.makeText(this, "Su compra se guardada correctamente", Toast.LENGTH_SHORT).show();
                    },
                    e -> {
                        Toast.makeText(this, "No se pudo guardar la compra", Toast.LENGTH_SHORT).show();
                    });
        });
    }
    private void setDefaultValues(){
        binding.txvPActual.setText("$" + receivedObject.getMonto());
        binding.txvTActual.setText("$0");
        binding.edtArticulo.setText(null);
        binding.edtArticulo.clearFocus();
        binding.edtPrecio.setText(null);
        binding.edtPrecio.clearFocus();
        binding.edtCantidad.setText(null);
        binding.edtCantidad.clearFocus();
    }
    private double getSubtotal(double price, int quantity) {
        return price * quantity;
    }
    private void updatedTotal(){
        String tmp = binding.edtCantidad.getText().toString();
        String tmp1 = binding.edtPrecio.getText().toString();
        if (!tmp.isEmpty() && !tmp1.isEmpty()) {
            double actualPrice = Double.parseDouble(binding.edtPrecio.getText().toString());
            int actualAmount = Integer.parseInt(binding.edtCantidad.getText().toString());
            binding.txvTActual.setText("$"+ getSubtotal(actualPrice, actualAmount));
        } else {
            binding.txvTActual.setText("$0");
        }
    }
    private void rebuildAmountAvaliable(){
        binding.txvPActual.setText("$"+ String.format("%.2f", receivedObject.getMonto() - mainRecyclerAdapter.getActualSubTotalAll()));
    }
}
