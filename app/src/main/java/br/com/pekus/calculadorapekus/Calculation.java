package br.com.pekus.calculadorapekus;

public class Calculation {
    private int id;
    private double valorA;
    private double valorB;
    private String operacao;
    private double resultado;
    private String dataCalculo;

    public Calculation() { }

    public Calculation(int id, double valorA, double valorB, String operacao, double resultado, String dataCalculo) {
        this.id = id;
        this.valorA = valorA;
        this.valorB = valorB;
        this.operacao = operacao;
        this.resultado = resultado;
        this.dataCalculo = dataCalculo;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getValorA() { return valorA; }
    public void setValorA(double valorA) { this.valorA = valorA; }

    public double getValorB() { return valorB; }
    public void setValorB(double valorB) { this.valorB = valorB; }

    public String getOperacao() { return operacao; }
    public void setOperacao(String operacao) { this.operacao = operacao; }

    public double getResultado() { return resultado; }
    public void setResultado(double resultado) { this.resultado = resultado; }

    public String getDataCalculo() { return dataCalculo; }
    public void setDataCalculo(String dataCalculo) { this.dataCalculo = dataCalculo; }
}
