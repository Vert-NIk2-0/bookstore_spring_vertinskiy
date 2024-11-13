<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - Invalid Request</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .error-container {
            text-align: center;
            background-color: #fff;
            padding: 30px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .error-container h1 {
            color: #e74c3c;
            font-size: 2em;
        }
        .error-container p {
            color: #555;
            margin: 10px 0;
        }
        .error-container .btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #1a73e8;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .error-container .btn:hover {
            background-color: #155ab6;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Oops! Invalid Request</h1>
        <p>It seems like the action you requested is not valid.</p>
        <p>Please check the request and try again.</p>
        <a href="/" class="btn">Go Back to Home</a>
    </div>
</body>
</html>