package br.com.pekus.calculadorapekus;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalculationAdapter extends RecyclerView.Adapter<CalculationAdapter.CalculationViewHolder> {

    private final List<Calculation> calculationList;
    private final Context context;

    public CalculationAdapter(final List<Calculation> calculationList, final Context context) {
        this.calculationList = calculationList;
        this.context = context;
    }

    @NonNull
    @Override
    public CalculationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_calculation, parent, false);
        return new CalculationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CalculationViewHolder holder, int position) {
        Calculation calc = calculationList.get(position);
        DecimalFormat df = new DecimalFormat("0.00");

        holder.tvId.setText(context.getString(R.string.id_format, calc.getId()));
        holder.tvValores.setText(context.getString(R.string.valores_format, calc.getValorA(), calc.getValorB()));
        holder.tvOperacaoResultado.setText(context.getString(R.string.operacao_resultado_format, calc.getOperacao(), calc.getResultado()));
        holder.tvData.setText(context.getString(R.string.data_format, formatarData(calc.getDataCalculo())));

        holder.btnExcluir.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION && context instanceof ListActivity) {
                ((ListActivity) context).excluirCalculation(calc.getId(), adapterPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return calculationList.size();
    }

    public static class CalculationViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvValores, tvOperacaoResultado, tvData;
        Button btnExcluir;

        public CalculationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvValores = itemView.findViewById(R.id.tvValores);
            tvOperacaoResultado = itemView.findViewById(R.id.tvOperacaoResultado);
            tvData = itemView.findViewById(R.id.tvData);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }

    private String formatarData(String dataIso) {
        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            Date date = isoFormat.parse(dataIso);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
            return sdf.format(date);
        } catch (ParseException e) {
            Log.e("CalculationAdapter", "Erro ao formatar data: " + dataIso, e);
            return dataIso;
        }
    }
}
