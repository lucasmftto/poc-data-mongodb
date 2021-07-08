# Projeto para treinar conceitos de Mongo
Conecta ao bonco

# Comandos Uteis
db
show dbs
use <meu_db>
mongod --dbpath C:\Desenvolvimento\mongoData\db --logpath C:\Desenvolvimento\mongoData\logs\mongo.log
mongoimport --db query --collection pokemon --type json --file pokemon.json
mongoimport --db pokemon_center --collection pokemon --type json --file C:\Desenvolvimento\mongoData\udemy-mongodb\data\pokemon.json --jsonArray

#createCollection | com Validador
db.createCollection("cars", {
validator: {
$jsonSchema: {
bsonType: "object",
required: ["model", "year"],
properties: {
model: {
bsonType: "string",
description: "O Modelo é necessário e deve ser uma string"
},
madeBy: {
bsonType: "string",
minLength: 3
},
year: {
bsonType: "int",
minimum: 1980,
maximum: 2025
}
}
}
}
})

#Capped Collections | Funciona como uma fila!
db.createCollection("logs", {
capped: true,
size: 2048,
max: 5
})

#Query
%like%: db.pokemon.find({name: /Pik/}).pretty()
%like: db.pokemon.find({name: /^Pi/}).pretty()
like%: db.pokemon.find({name: /a$/}).pretty()

###PROJECTION:
db.pokemon.find({name: /^Pik/}, {name: true})

###IgnoreCase
db.pokemon.find({name: /^pik/i}, {name: true})

###Buscando em array
db.pokemon.find({types: "Fire"}, {name: true, types: 1})
db.pokemon.find({types: { $in:["Fire", "Rock"] } }, {name: true, types: 1})

###Sort
db.pokemon.find({types: { $in:["Fire", "Rock"] } }, {name: true, hp: 1}).sort({ hp: 1}).pretty()

###Paginacao
db.pokemon.find({types: "Fire"  }).sort({ attack: -1}).skip(5).limit(5).pretty()
db.pokemon.find({types: "Fire"  }).sort({ attack: -1}).skip(10).limit(5).pretty()

#Update
