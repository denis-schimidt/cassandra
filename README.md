# cassandra

### Exemplo de teste

```bash
curl -X POST   http://localhost:8082/employee   -H 'Cache-Control: no-cache'   -H 'Content-Type: application/json'   -d '{
    "id": 3,
    "firstName": "ankit",
    "lastName": "gupasd1",
    "email": "test@gmail.com"
}'

curl -X GET http://localhost:8082/employee/async/<ID>   -H 'Cache-Control: no-cache'
```
