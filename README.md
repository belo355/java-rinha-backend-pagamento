REGRAS

- [OK] Obrigatoriamente, o http status code de requisições para transações bem sucedidas deve ser 200!
- Regras Uma transação de débito -- incompleto
    - [OK] Obrigatoriamente, o http status code de requisições para transações bem sucedidas deve ser 200!
      Regras Uma transação de débito nunca pode deixar o saldo do cliente menor que seu limite disponível. Por exemplo, um cliente com limite de 1000 (R$ 10) nunca deverá ter o saldo menor que -1000 (R$ -10). Nesse caso, um saldo de -1001 ou menor significa inconsistência na Rinha de Backend!
    - [OK] Se uma requisição para débito for deixar o saldo inconsistente, a API deve retornar HTTP Status Code 422 sem completar a transação! O corpo da resposta nesse caso não será testado e você pode escolher como o representar.
    - [PENDENTE] HTTP 422 também deve ser retornado caso os campos do payload estejam fora das especificações como, por exemplo, uma string maior do que 10 caracteres para o campo descricao ou algo diferente de c ou d para o campo tipo. Se para o campo valor um número não inteiro for especificado, você poderá retornar HTTP 422 ou 400.
- Se o atributo [id] da URL for de uma identificação não existente de cliente

[DOING] -  GET /clientes/[id]/extrato

[] -  POST /clientes/[id]/transacoes
[] -  Cadastro Inicial de Clientes
[] -  
[] -  



