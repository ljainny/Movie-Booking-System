<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <style>
      html, body {
      min-height: 100%;
      padding: 0;
      margin: 0;
      font-family: Roboto, Arial, sans-serif;
      font-size: 14px;
      color: #666;
      }
      h1 {
      margin: 0 0 20px;
      font-weight: 400;
      color: #3333cc;
      }
      p {
      margin: 0 0 5px;
      }
      .main-block {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
      background: #1c87c9;
      }
      form {
      padding: 25px;
      margin: 25px;
      box-shadow: 0 2px 5px #f5f5f5; 
      background: #f5f5f5; 
      }
      .fas {
      margin: 25px 10px 0;
      font-size: 72px;
      color: #fff;
      }
      .fa-envelope {
      transform: rotate(-20deg);
      }
      .fa-at , .fa-mail-bulk{
      transform: rotate(10deg);
      }
      input, textarea {
      width: calc(100% - 18px);
      padding: 8px;
      margin-bottom: 20px;
      border: 1px solid #1c87c9;
      outline: none;
      }
      input::placeholder {
      color: #666;
      }
      button {
      width: 100%;
      padding: 10px;
      border: none;
      background: #1c87c9; 
      font-size: 16px;
      font-weight: 400;
      color: #fff;
      }
      button:hover {
      background: #2371a0;
      }    
      @media (min-width: 568px) {
      .main-block {
      flex-direction: row;
      }
      .left-part, form {
      width: 50%;
      }
      .fa-film {
      margin-top: 0;
      margin-left: 20%;
      }
      .fa-video {
      margin-top: -10%;
      margin-left: 65%;
      }
      .fa-tape {
      margin-top: 2%;
      margin-left: 28%;
      }
      }
    </style>
<script src="js/validation.js"></script>
</head>
<body>
<div class="main-block">
      <div class="left-part">
<i class="fas fa-film"></i>
<i class="fas fa-video"></i>
<i class="fas fa-tape"></i>
      </div>
      <form method="post" action="MovieController">
        <h1>Add Movie Details</h1>
        <div class="info">
          <input class="fname" type="text" name="title" placeholder="Enter Title" required>
          <input type="text" name="director" placeholder="Enter Director Name" pattern="[a-zA-Z]*" oninvalid="setCustomValidity('Please enter alphabets only." required>
          <input type="text" name="cast" placeholder="Enter Cast Name" pattern="[a-zA-Z]*" oninvalid="setCustomValidity('Please enter alphabets only. ')" required>
          <input type="text" name="description" placeholder="Enter Description" required>
		  <input type="text" name="status" placeholder="Enter Active Status(Y/N)" required>
        </div>
        <button type="submit" value="Add" name="action">Submit</button>
      </form>
    </div>
</body>
</html>