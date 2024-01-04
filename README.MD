# Commerce
_API REST para gerenciamento de pedidos de compra._

## Como iniciar a aplicação

### Docker
É necessário executar o `docker-compose.yml` que se encontra na pasta raiz do projeto.

Você pode fazer isso via linha de comando por meio do `docker-compose up -d`.

### Banco de dados
É possível acessar o banco de dados através de um DBMS, como Dbeaver ou DataGrip, utilizando a conexão abaixo:

<b>Credenciais de acesso</b></br>
Host: localhost</br>
Port: 5432 </br>
Database: commerce </br>
Username: admin </br>
Password: admin </br>

### Mensageria
É possível acessar o console do RabbitMQ através do endereço: http://localhost:15672

<b>Credenciais de acesso</b></br>
Username: admin </br>
Password: admin </br>

#### Publicando uma mensagem na fila "order.save" para consumo da aplicação
![Alt text](https://i.imgur.com/0OsYx2b.png "Publicando mensagem na fila.")
![Alt text](https://i.imgur.com/inbOajb.png "Publicando mensagem na fila.")
![Alt text](https://i.imgur.com/ATgScuv.png "Publicando mensagem na fila.")

```json
{
  "id": 1,
  "partyId": 123,
  "state": 1,
  "items": [
    {
      "name": "Camiseta",
      "amount": 2,
      "price": 20.5
    },
    {
      "name": "Tênis",
      "amount": 1,
      "price": 15.8
    }
  ]
}

```
