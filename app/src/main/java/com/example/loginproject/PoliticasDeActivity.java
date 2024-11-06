package com.example.loginproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PoliticasDeActivity extends AppCompatActivity {
    private TextView txtPoliticas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicas_de);
        txtPoliticas = findViewById(R.id.txtPoliticas);
        String politicasTexto = "Términos y Condiciones:\n\n"
                + "1. **Privacidad:** Todos los datos personales proporcionados serán utilizados exclusivamente para mejorar el servicio.\n\n"
                + "2. **Uso Responsable:** Los usuarios se comprometen a utilizar la aplicación de manera ética y conforme a la ley.\n\n"
                + "3. **Modificaciones:** La empresa se reserva el derecho de modificar los términos de uso y políticas de privacidad en cualquier momento.\n\n"
                + "4. **Responsabilidad:** La empresa no se hace responsable por el mal uso de la aplicación por parte de los usuarios.\n\n"
                + "5. **Seguridad:** Se implementarán medidas de seguridad para proteger la información del usuario, aunque no se garantiza la protección absoluta.\n\n"
                + "6. **Consentimiento:** Al utilizar esta aplicación, el usuario acepta estos términos y condiciones.";
        // Asignar el texto al TextView
        txtPoliticas.setText(politicasTexto);
    }
}
