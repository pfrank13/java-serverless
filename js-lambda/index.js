const mysql = require("mysql")
const { v4: uuidv4 } = require('uuid');

exports.handler = async (event) => {
  const promise = new Promise(function(resolve, reject) {
    const con = mysql.createConnection({
                                         host: "lambdatest.c4qoxufww9zr.us-west-2.rds.amazonaws.com",
                                         database: "booksdb",
                                         user: "pfrank",
                                         password: "Lv8adJvRMPkFCKuVANky"
                                       });

    con.connect(function(err) {
      if (err) return reject(err);
      console.log("Connected!");
    });

    con.query(`INSERT INTO book(isbn, name) VALUES('${uuidv4()}','${event.name}')`, function (error, results, fields) {
      if (error)
        return reject(error);

      /*
      con.query("SELECT * FROM book", function(error, results, fields){
        if(error)
          return reject(error);

        results.forEach(result => {
          console.log(result);
        });
        resolve({
                  "statusCode": 200,
                  "body": JSON.stringify(results)
                });
      })*/
    });

  })
  return promise
};
