package br.com.pekus.calculadorapekus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.button.MaterialButton;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etValorA, etValorB;
    private Spinner spinnerOperacao;
    private MaterialButton btnCalcular, btnVerLista;
    private TextView tvResultado; // Exibe o cálculo final
    private RequestQueue requestQueue;
    private static final String API_KEY = "MURL1903";
    private static final String BASE_URL = "https://intranet.pekus.com.br/calcapi/api/calculadora";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValorA = findViewById(R.id.etValorA);
        etValorB = findViewById(R.id.etValorB);
        spinnerOperacao = findViewById(R.id.spinnerOperacao);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnVerLista = findViewById(R.id.btnVerLista);
        tvResultado = findViewById(R.id.tvResultado);

        String[] operacoes = { "+", "-", "*", "/" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operacoes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperacao.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);

        btnCalcular.setOnClickListener(v -> calcularEArmazenar());
        btnVerLista.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListActivity.class)));
    }

    private void calcularEArmazenar() {
        String strValorA = etValorA.getText() != null ? etValorA.getText().toString().trim() : "";
        String strValorB = etValorB.getText() != null ? etValorB.getText().toString().trim() : "";

        if (TextUtils.isEmpty(strValorA)) {
            etValorA.setError("Informe o Valor A");
            return;
        }
        if (TextUtils.isEmpty(strValorB)) {
            etValorB.setError("Informe o Valor B");
            return;
        }

        double valorA, valorB;
        try {
            valorA = Double.parseDouble(strValorA);
        } catch (NumberFormatException e) {
            etValorA.setError("Valor inválido");
            return;
        }
        try {
            valorB = Double.parseDouble(strValorB);
        } catch (NumberFormatException e) {
            etValorB.setError("Valor inválido");
            return;
        }

        String operacao = spinnerOperacao.getSelectedItem() != null ?
                spinnerOperacao.getSelectedItem().toString() : "";
        double resultado;
        switch (operacao) {
            case "+":
                resultado = valorA + valorB;
                break;
            case "-":
                resultado = valorA - valorB;
                break;
            case "*":
                resultado = valorA * valorB;
                break;
            case "/":
                if (valorB == 0) {
                    etValorB.setError("Divisão por zero não permitida");
                    return;
                }
                resultado = valorA / valorB;
                break;
            default:
                Toast.makeText(this, "Operação inválida", Toast.LENGTH_SHORT).show();
                return;
        }

        tvResultado.setText(String.format(Locale.getDefault(), "Cálculo Final: %.2f", resultado));

        String dataCalculo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                .format(new Date());

        final JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("valorA", valorA);
            jsonBody.put("valorB", valorB);
            jsonBody.put("operacao", operacao);
            jsonBody.put("resultado", resultado);
            jsonBody.put("dataCalculo", dataCalculo);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao criar dados", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = BASE_URL + "?apikey=" + API_KEY;

        StringRequest postRequest = new StringRequest(
                Request.Method.POST,
                url,
                response -> {
                    try {
                        int id = Integer.parseInt(response.trim());
                        Toast.makeText(MainActivity.this, "Dados armazenados com sucesso, ID: " + id, Toast.LENGTH_LONG).show();
                        etValorA.setText("");
                        etValorB.setText("");
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Erro ao interpretar resposta: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(MainActivity.this, "Erro ao enviar dados: " + error.getMessage(), Toast.LENGTH_SHORT).show()
        ) {
            @Override
            public byte[] getBody() {
                return jsonBody.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        requestQueue.add(postRequest);
    }
}
