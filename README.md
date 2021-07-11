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
add campo no documento:
db.pokemon.updateOne({ name: /^O/ }, { $set: { startsWithO: true}})

remove campo no documento:
db.pokemon.updateOne({ name: /^O/ }, { $unset: { startsWithO: true}})

Incrementa e decrementa o valor o campo do doc:
db.pokemon.updateMany({ types: "Fire"}, { $inc: {attack: 10 }  })
db.pokemon.updateMany({ types: "Fire"}, { $inc: {attack: -10 }  })

Multiplicar o valor o campo do doc:

db.pokemon.updateMany({ types: "Fire"}, { $mul: {attack: 10 }  })

Settando $min e $max:
db.pokemon.updateMany({ types: "Fire"}, { $max: {attack: 75 }  }) //Inicia pelo menos com 75

db.pokemon.updateMany({ types: "Fire"}, { $min: {attack: 150 }  }) //valor de 150 


upsert:
db.pokemon.updateOne({ name: "Charmander" }, {$set: { attack: 150} }, {upsert: true})
db.pokemon.updateOne({ name: "Burak" }, {$set: { attack: 777} }, {upsert: true})

db.pokemon.updateOne({ name: "Burak" }, {$set: { attack: 778} , $setOnInsert: {defense:400, hp: 900, speed:300}}, {upsert: true})

update Arry:
db.pokemon.updateOne({ _id: 1, types: "Poison" } , {$set: { "types.$": "Teste" } })

db.pokemon.updateOne(
{ _id: 1 },
{
$set: {
types: [
{ name: "Fire", bonus_points: 45, weakness: "Water" },
{ name: "Rock", bonus_points: 12, weakness: "Paper" },
{ name: "Bug", bonus_points: 14, weakness: "Chinelão" }
],
}
}
)

###Indice's
db.pokemon.find({ attack: { $gte: 85}}).explain()
db.pokemon.find({ _id: 240}).explain('executionStats')

db.pokemon.createIndex({neme:1})
db.pokemon.find({ attack: { $gte: 85 } }).explain('executionStats')
db.pokemon.createIndex({attack:1}, {name: "index_attack_1"})

db.pokemon.createIndex({ attack:1, name:1})
db.pokemon.dropIndex("attack_1_name_1")

###Cria funcao
function domino() {
for (let x = 0; x <= 6; x++) {
for (let y = 0; y <= x; y++) {
db.domino.insert({
piece: [x, y],
});
}
}
}

db.combats.aggregate([{ $lookup: {from: "pokemon", localField: "First_pokemon", foreignField: "_id", as: "pokemon1" }}]).pretty()
db.combats.aggregate([{ $lookup: {from: "pokemon", localField: "First_pokemon", foreignField: "_id", as: "pokemon1" }}, { $lookup: {from: "pokemon", localField: "Second_pokemon", foreignField: "_id", as: "pokemn2" }}]).pretty()

db.combats.aggregate([
{
$lookup: {
from: "pokemon",
localField: "First_pokemon",
foreignField: "_id",
as: "pokemon1_arr"
}
},
{
$lookup: {
from: "pokemon",
localField: "Second_pokemon",
foreignField: "_id",
as: "pokemon2_arr"
}
},
{
$project: {
_id: 0,
Winner: 1,
pokemon1: {
$arrayElemAt: ["$pokemon1_arr", 0]
},
pokemon2: {
$arrayElemAt: ["$pokemon2_arr", 0]
}
}
},
{
$project: {

            winner: {
                $cond: {
                    if: { $eq: ["$Winner", "$pokemon1._id"] },
                    then: "$pokemon1.name",
                    else: "$pokemon2.name"
                }
            }
			first_pokemon: "$pokemon1.name",
            second_pokemon: "$pokemon2.name",
        }
    }
]).pretty()