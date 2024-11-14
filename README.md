# API RESTful de Usuarios

Tenemos 3 endpoints:

1. Creacion de usuarios (no tiene autenticacion)

- POST: /persons
- {
  "name": "Juan",
  "email": "juan@me.com",
  "password": "secreto123"
  "phones": [
  {
  "number": "44444444",
  "citycode": "11",
  "countrycode": "54"
  }
  ]
  }

2. Login, se obtiene el JWT token (expira en una hora)

- POST: /login
- Basic Auth (usando las credenciales creadas anteriormente)

3. Listado de usuarios

- GET: /persons
- Bearer Token (usando el JWT token obtenido anteriormente)

Ver collection de Postman para probar:
Users.postman_collection.json

## Prerrequisitos

1. Crear certificados para la generacion de token JWT y firma:

```
openssl genrsa -out keypair.pem 2048
openssl rsa -in keypair.pem -pubout -out public.pem
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
```

Con estos comandos obtenemos los archivos: private.pem y public.pem que vamos a necesitar para el token JWT, vamos a colocarlos en la carpeta src/main/resources/certs/

## Pasos

Usando la collection de Postman:

1. Crear usuario con el POST /persons
2. Loguearse con el POST /login usando BasicAuth y obtener el token JWT
3. Obtener el listado de usuarios con el GET /persons usando el token JWT

## Diagrama

<image src="images/diagrama.png" alt="Operaciones">
