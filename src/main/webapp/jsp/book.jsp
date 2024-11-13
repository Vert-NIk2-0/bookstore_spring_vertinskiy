<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${book.bookname} | bookstore</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            color: #333;
        }
        p {
            color: #555;
            margin: 8px 0;
        }
        p span {
            font-weight: bold;
        }
        .btn-back {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #1a73e8;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .btn-back:hover {
            background-color: #155ab6;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${book.bookname}</h1>
        <p><span>ID:</span> ${book.id}</p>
        <p><span>Author:</span> ${book.author}</p>
        <p><span>ISBN:</span> ${book.isbn}</p>
        <p><span>Number Of Pages:</span> ${book.numberOfPages}</p>
        <p><span>Year:</span> ${book.year}</p>
        <p><span>Price:</span> ${book.price}$</p>
        <p><span>Time:</span> ${date}</p>
        <a href="controller?command=books" class="btn-back">Go back</a>
    </div>
</body>
</html>
