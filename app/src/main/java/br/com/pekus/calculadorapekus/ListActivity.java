package br.com.pekus.calculadorapekus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CalculationAdapter adapter;
    private List<Calculation> calculationList;
    private RequestQueue requestQueue;
    private static final String API_KEY = "MURL1903";
    private static final String BASE_URL = "https://intranet.pekus.com.br/calcapi/api/calculadora";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerView);
        calculationList = new ArrayList<>();
        adapter = new CalculationAdapter(calculationList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);
        carregarCalculations();
    }

    private void carregarCalculations() {
        String url = BASE_URL + "?apikey=" + API_KEY;
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Calculation>>() {}.getType();
                    List<Calculation> list = gson.fromJson(response.toString(), listType);
                    calculationList.clear();
                    calculationList.addAll(list);
                    adapter.notifyDataSetChanged();
                },
                error -> Toast.makeText(ListActivity.this, "Erro ao carregar cálculos: " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );
        requestQueue.add(arrayRequest);
    }

    public void excluirCalculation(final int id, final int position) {
        String url = BASE_URL + "?apikey=" + API_KEY + "&id=" + id;
        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, url,
                response -> {
                    Toast.makeText(ListActivity.this, "Registro excluído", Toast.LENGTH_SHORT).show();
                    calculationList.remove(position);
                    adapter.notifyItemRemoved(position);
                },
                error -> Toast.makeText(ListActivity.this, "Erro ao excluir: " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );
        requestQueue.add(deleteRequest);
    }
}
