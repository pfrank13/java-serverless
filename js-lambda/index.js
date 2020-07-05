const { v4: uuidv4 } = require('uuid');
const serverless = require('serverless-http');
const express = require('express');
const app = express();
app.use(express.json())
const { Sequelize, Model, DataTypes } = require('sequelize');
const sequelize = new Sequelize("mysql://pfrank:NotAPassword@lambdatest.c4qoxufww9zr.us-west-2.rds.amazonaws.com:3306/booksdb", {
  define: {
    timestamps: false
  }
});

class Book extends Model{}

Book.init({
  isbn: {
    primaryKey: true,
    type: DataTypes.STRING
  },
  name: {type: DataTypes.STRING}
}, { sequelize, modelName: 'book' });

(async() => sequelize.sync({

}))()

app.post('/books', async(req, res, next) => {
  try {
    const book = {
      isbn: uuidv4(),
      name: req.body.name
    };
    await Book.create(book);
    res.json(book);
  }catch(e){
    next(e);
  }
})

app.get("/book/:isbn", async(req, res, next) => {
  try {
    res.json(await Book.findOne({
      where: {
        isbn: req.params.isbn
      }
    }));
  }catch(e){
    next(e);
  }
})

module.exports.handler = serverless(app);