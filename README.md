<h1 align=center> Produtos Frescos - Projeto Integrador </h1>
<p align=center> TRANSFORMELI Grupo 2 </p>
<br>

## ✅ Testes
* Unitários [`src/test/java/br/com/dh/meli/projeto_integrador`](src/test/java/br/com/dh/meli/projeto_integrador)

## 🚩 Postman collection
* Variveis do Postman [`src/main/resources/collection/workspace.postman_globals.json`](src/main/resources/collection/workspace.postman_globals.json)
* Exemplos de Uso [`src/main/resources/collection/Projeto_Integrador.postman_collection.json`](src/main/resources/collection/Projeto_Integrador.postman_collection.json)

## 📝 Proposta
Criar uma API REST que faz o gerenciamento de estoque e venda de produtos frescos.

## 💣 Requisito 6
* Gestão de qualidade - batchstocks
* Collection Postman [`collection/Requisito06.postman_collection.json`](collection/Requisito06.postman_collection.json)
* User History [`userHistory/Requisito6-BatchStock.pdf`](userHistory/Requisito6-BatchStock.pdf)
* Teste unitário: [`src/test/java/br/com/dh/meli/projeto_integrador/service/BatchStockServiceTest.java`](src/test/java/br/com/dh/meli/projeto_integrador/service/BatchStockServiceTest.java)
* Endpoints:

. GET
```
/api/v1/fresh-products/batch-stock/due-date/state/list?state={state}
```


. POST
```
/api/v1/fresh-products/inboundorder
```

. PUT
```
/api/v1/fresh-products/batch-stock/due-date
```

. DELETE
```
/api/v1/fresh-products/batch-stock/due-date
```


* API DE PRODUTOS FRESCOS

## 🚀 Como clonar e iniciar a aplicação

- Abra seu terminal e digite o seguinte comando:

```
git clone git@github.com:rebecccruz/projeto_integrador.git
```

- Ainda em seu terminal digite o comando abaixo para entrar na pasta do projeto:

```
cd projeto_integrador
```

- Instale as dependências do maven:

```
mvn install
```

ou
<br>

```
maven install
```

- Rode localmente:

```
mvn spring-boot:run
```

## 👥 Membros do grupo

- <a href="https://github.com/aborgssouzameli">Alexandre Borges Souza</a>
- <a href="https://github.com/evycoliveira">Evelyn Cristini Oliveira</a>
- <a href="https://github.com/isaiasfmeli">Isaias Finger</a>
- <a href="https://github.com/laridevmeli">Larissa Navarro</a>
- <a href="https://github.com/lucaspinheirorocha">Lucas Pinheiro Rocha</a>
- <a href="https://github.com/rebecccruz">Rebecca da Cunha Cruz</a>
