<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Users | bookstore</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            flex-direction: column;
            flex: 1;
        }
        .user-list {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
            flex: 1;
        }
        .user-card {
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            color: #333;
            font-size: 1.5em;
        }
        p {
            color: #555;
            margin: 8px 0;
        }
        p span {
            font-weight: bold;
        }
        a {
            text-decoration: none;
            color: #1a73e8;
        }
        a:hover {
            text-decoration: underline;
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
            text-align: center;
        }
        .btn-back:hover {
            background-color: #155ab6;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="user-list">
            <c:forEach items="${users}" var="user">
                <div class="user-card">
                    <h1><span>${user.login}</span></h1>
                    <p><span>ID:</span> ${user.id}</p>
                    <p><a href="controller?command=user&id=${user.id}">${user.email}</a></p>
                    <p><span>Phone Number:</span> ${user.phoneNumber}</p>
                </div>
            </c:forEach>
        </div>
        <a href="/bookstore" class="btn-back">Back to Home</a>
    </div>
</body>
</html>
