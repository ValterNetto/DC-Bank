# DC-Bank
Pequeno projeto de banco com interfaces em Java Puro

Proposta: 
Fazer um sistema bancário que armazene dados e aceite fazer transações com taxa de Juros e com tipos de conta na seguinte condição:
- Conta-Salário: Tarifa = 5%, Máximo Negativo = 0;
- Conta-Corrente: Tarifa = 1% Máximo Negativo = 100;
- Conta-Poupança: Tarifa = 0% Máximo Negativo = 0;
Apenas a Conta-Corrente pode ter um saldo negativo de 100.
Para o armazenamento dos dados de forma que eles não se percam após o fechamento do programa, usamos o PostgreSQL.
A conexão com o banco de dados foi feita à partir do JDBC.
