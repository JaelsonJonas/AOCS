#AOCS

Documentação para o a API do projeto AOCS

## Endpoints

- Login
    - [Autenticar acesso](#autenticar-acesso)

- Novo Usuario
    - [Criar novo login](#criar-novo-login)

- Tarefas
    - [Criar](#criar-tarefa)
    - [Apagar](#deletar-tarefa)
    - Listar
    - Consultar
- Timer
    - ColetarTarefa

### Autenticar acesso

´POST´ /aocs/api/login

**Campos da requisição**
|   Campo  |    tipo    |   Obrigatorio | Descrição
|:-:|:-:|:-:|:-:
email|String|sim|campo reponsavel por armazenar o e-mail do usuario
senha|String|sim|Campo responsavel por armazenar a senha do usuario

**Exemplo de requisição**

```js
{
    "email":"jow@fiap.com.br",
    "senha":"jow6969"
}

```
**Códigos de Respostas**

| código | descrição
|-|-
| 200 | usuario cadastrada com sucesso
| 400 | campos inválidos



### Criar novo login

´POST´ /aocs/api/register

**Campos da requisição**
|   Campo  |    tipo    |   Obrigatorio | Descrição
|:-:|:-:|:-:|:-:
email|String|sim|campo reponsavel por armazenar o e-mail do usuario
senha|String|sim|Campo responsavel por armazenar a senha do usuario
confirmarSenha|String|sim|Campo responsavel por armazenar a senha confirmada pelo usuario

**Exemplo de requisição**

```js
{
    "email":"jow@fiap.com.br",
    "senha":"jow6969",
    "confirmarSenha":"jow6969"
}

```
**Códigos de Respostas**

| código | descrição
|-|-
| 201 | despesa cadastrada com sucesso
| 400 | campos inválidos



### Criar Tarefa

´POST´ /aocs/api/tarefa

**Campos da requisição**

|   Campo  |    tipo    |   Obrigatorio | Descrição
|:-:|:-:|:-:|:-:
nome|String|sim|campo reponsavel por armazenar o nome da tarefa
descricao|String|não|Campo responsavel por armazenar a descricao da tarefa
data|Data|sim|campo para armazenar o a data da tarefa  
duracao|Datetime|sim|campo responsavel por armazenar tempo de duracao da tarefa

**Exemplo de requisição**

```js
{
    "nome":"Estudar Java",
    "descricao":"Estudar sobre Anatations do JPA",
    "data":"2023-03-05"
    "duracao":"40min"
}
```

### Deletar Tarefa

´DELETE´ /aocs/api/tarefa/{id}


**Exemplo de corpo da resposta**

```js
{

    "Message":"Tarefa deletada com sucesso"

}
```
