
# Calculadora Pekus

Calculadora Pekus é um aplicativo Android desenvolvido em Java que realiza cálculos básicos (soma, subtração, multiplicação e divisão) e armazena os resultados através do consumo de uma API. O app possui duas telas principais:  
- **Tela de Cálculo:** Permite inserir os valores, escolher a operação, exibir o resultado e enviar os dados para a API.  
- **Tela de Listagem:** Exibe uma tabela com todos os cálculos armazenados e possibilita a exclusão de registros.

## Funcionalidades

- **Cálculo e Validação:**  
  - Entrada dos valores (Valor A e Valor B) com validação para números válidos e não vazios.
  - Seleção da operação matemática (representada por um único caractere: `+`, `-`, `*` ou `/`).
  - Exibição do resultado final na mesma tela.
  
- **Integração com API:**  
  - Envio dos dados (Valor A, Valor B, operação, resultado e data/hora do cálculo) via POST.
  - Recebimento do ID gerado pela API, que é exibido ao usuário.
  - Listagem de todos os cálculos armazenados utilizando GET.
  - Exclusão de registros utilizando DELETE com o ID do cálculo.


## Requisitos

- **Android Studio 2025** (ou versão superior)
- **Android SDK API Level 21+**
- **Java 8+**

## Instalação e Configuração

1. **Clone o Repositório:**

   ```bash
   git clone https://github.com/seu-usuario/CalculadoraPekus.git
   ```

2. **Abra no Android Studio:**
   - Selecione "Open an existing project" e navegue até a pasta clonada.

3. **Sincronize o Gradle:**
   - Certifique-se de que as dependências estão instaladas (Volley, Material Components, RecyclerView, Gson).

4. **Execute o Projeto:**
   - Compile e execute o app em um emulador ou dispositivo físico.

## Estrutura do Projeto

```
CalculadoraPekus/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/br/com/pekus/calculadorapekus/
│   │   │   │   ├── Calculation.java
│   │   │   │   ├── CalculationAdapter.java
│   │   │   │   ├── MainActivity.java
│   │   │   │   └── ListActivity.java
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── activity_list.xml
│   │   │   │   │   └── list_item_calculation.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   └── themes.xml
│   │   └── AndroidManifest.xml
└── build.gradle
```

## Uso

### Tela de Cálculo (MainActivity)
1. **Insira os valores:**  
   - Digite o Valor A e o Valor B.
2. **Selecione a Operação:**  
   - Utilize o Spinner para escolher entre `+`, `-`, `*` ou `/`.
3. **Calcule e Armazene:**  
   - Clique no botão **"Calcular e Armazenar"**.
   - O app exibirá o resultado final na tela e enviará os dados para a API.
   - Uma mensagem informará o ID retornado pela API, e os campos serão limpos.

   
### Tela de Listagem (ListActivity)
- **Visualize os Cálculos Armazenados:**  
  - Navegue para a tela de listagem e veja os cálculos em formato de tabela.
  - Cada registro mostra: Valor A, Valor B (ambos com 2 casas decimais), operação, resultado (2 casas decimais) e data/hora formatada como "dd/MM/yyyy HH:mm:ss".
- **Exclusão de Registros:**  
  - Utilize o botão "Excluir" em cada item para remover o registro (a exclusão é feita via API).

## Agradecimentos

- A equipe da **Pekus** pelo desafio e pela oportunidade.

---

Esse README cobre os pontos principais e está pronto para ser adicionado ao seu repositório GitHub. Se precisar de ajustes ou de mais informações, estou à disposição para ajudar!
