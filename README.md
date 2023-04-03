# AOCS

Documentação para o a API do projeto AOCS

## Endpoints

- Login
    - [Autenticar acesso](#autenticar-acesso)

- Novo Usuario
    - [Criar novo usuario](#criar-novo-usuario)

- Tarefas
    - [Criar](#criar-tarefa)
    - [Apagar](#deletar-tarefa)
    - [Editar](#editar-tarefa)
    - [Listar](#listar-tarefa)

- Timer
    - [ColetarTarefa](#coletar-a-tarefa)

### Autenticar acesso

`POST` /aocs/api/login

**Campos da requisição**
|   Campo  |    tipo    |   Obrigatorio | Descrição
|:-:|:-:|:-:|:-:
login|String|sim|campo reponsavel por armazenar o e-mail do usuario
senha|String|sim|Campo responsavel por armazenar a senha do usuario

**Exemplo de payload**

```JSON
{
    "login":"jow@fiap.com.br",
    "senha":"jow6969"
}

```
**Códigos de Respostas**

| código | descrição
|-|-
| 200 | usuario/senha autenticados
| 404 | usuario/senha invalidos

### Criar novo usuario

`POST` /aocs/api/register

**Campos da requisição**
|   Campo  |    tipo    |   Obrigatorio | Descrição
|:-:|:-:|:-:|:-:
email|String|sim|campo reponsavel por armazenar o e-mail do usuario
senha|String|sim|Campo responsavel por armazenar a senha do usuario
confirmarSenha|String|sim|Campo responsavel por armazenar a senha confirmada pelo usuario

**Exemplo de payload**

```JSON

{
    "email":"jow@fiap.com.br",
    "senha":"jow6969"
}

```
**Códigos de Respostas**

| código | descrição
|-|-
| 201 | usuario cadastrado com sucesso
| 400 | campos inválidos
| 500 | Erro inesperado contactar o suporte


### Criar Tarefa

`POST` /aocs/api/tarefa

**Campos da requisição**

|   Campo  |    tipo    |   Obrigatorio | Descrição
|:-:|:-:|:-:|:-:
nome|String|sim|campo reponsavel por armazenar o nome da tarefa
descricao|String|não|Campo responsavel por armazenar a descricao da tarefa
data|Data|sim|campo para armazenar o a data da tarefa  
duracao|LocalDate|sim|campo responsavel por armazenar tempo de duracao da tarefa

**Exemplo de payload**

```JSON

{
    "titulo":"Estudar Java",
    "descricao":"Estudar sobre Anatations do JPA",
    "data":"2023-03-05",
    "duracao":"00:40"
}

```
**Códigos de Respostas**

| código | descrição
|-|-
| 200 | tarefa criada  com sucesso
| 400 | campos inválidos
| 500 | Erro inesperado contactar o suporte

### Deletar Tarefa

`DELETE` /aocs/api/tarefa/{id}


**Exemplo de corpo da resposta**

```JSON

{

    "Message":"Tarefa deletada com sucesso"

}


```
**Códigos de Respostas**

| código | descrição
|-|-
| 200 | tarefa deletada
| 404 | Tarefa não encontrada
| 500 | Erro inesperado contactar o suporte


### Editar Tarefa

`PUT` /aocs/api/tarefa/{id}


**Campos da requisição**
|   Campo  |    tipo    |   Obrigatorio | Descrição
|:-:|:-:|:-:|:-:
nome|String|sim|campo reponsavel por armazenar o nome da tarefa
descricao|String|não|Campo responsavel por armazenar a descricao da tarefa
data|Data|sim|campo para armazenar o a data da tarefa  
duracao|LocalDate|sim|campo responsavel por armazenar tempo de duracao da tarefa

**Exemplo de payload**

```JSON
{
    "id":69,
    "nome":"Estudar Java",
    "descricao":"Estudar sobre JDBC",
    "data":"2023-03-13",
    "duracao":"00:40" 
}
```
**Códigos de Respostas**

| código | descrição
|-|-
| 200 | tarefa localizada
| 404 | Tarefa não encontrada
| 500 | Erro inesperado contactar o suporte


### Listar Tarefa

`GET` /aocs/api/tarefa/

**Exemplo de corpo da resposta**

```JSON
{
    "tarefa": [
        {
            "id": 69,
            "nome": "Estudar Java",
            "descricao": "Estudar sobre Anatations do JPA",
            "data": "2023-03-05",
            "duracao": "00:40"
        },
        {
            "id":6969,
            "nome": "Estudar PL/SQL",
            "descricao": "Estudar sobre PROCEDURES e DECLARE",
            "data": "2023-03-06",
            "duracao": "00:20"
        }
    ]
}
```
**Códigos de Respostas**

| código | descrição
|-|-
| 200 | tarefa coletadas
| 500 | Erro inesperado contactar o suporte

### Coletar a tarefa

`GET` /aocs/api/tarefa/{id}

**Exemplo de corpo da resposta**

```JSON
{
    "id":69,
    "nome":"Estudar Java",
    "descricao":"Estudar sobre JDBC",
    "data":"2023-03-13",
    "duracao":"00:40"
    
}
```

**Códigos de Respostas**

| código | descrição
|-|-
| 200 | tarefa localizada
| 404 | Tarefa não encontrada
| 500 | Erro inesperado contactar o suporte
