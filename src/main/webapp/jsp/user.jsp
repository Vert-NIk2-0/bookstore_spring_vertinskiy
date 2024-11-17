<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${user.login} | bookstore</title>
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
        <h1>${user.login}</h1>
        <p><span>${user.lastName} ${user.firstName}</span></p>
        <p><span>${user.email}</span></p>
        <p><span>Phone number:</span> ${user.phoneNumber}</p>
        <p><span>Date of birth:</span> ${user.dateOfBirth}</p>
        <p><span>Gender:</span> ${user.gender}</p>
        <p><span>Role:</span> ${user.role}</p>
        <a href="controller?command=users" class="btn-back">Go back</a>
    </div>
</body>
</html>