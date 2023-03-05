#AOCS

Documentação para o a API do projeto AOCS

## Endpoints

-Login
    -Autenticar acesso
    -Criar novo login
    -Esqueci a senha

-Calendario
    -Selecionar dia da semana

-Tarefas
    -Criar
    -Inserir
    -Apagar
    -Listar
    -Consultar
-Timer
    -ColetarTarefa

### Autenticar acesso

´POST´ /aocs/api/login

*Campos da requisição*
|   Campo  |    tipo    |   Obrigatorio | Descrição
|:-:|:-:|:-:|:-:
email|String|sim|campo reponsavel por armazenar o e-mail do usuario
senha|String|sim|Campo responsavel por armazenar a senha do usuario

*Exemplo de requisição*

```js
{
    "email":"jow@fiap.com.br",
    "senha":"jow6969"
}

```
