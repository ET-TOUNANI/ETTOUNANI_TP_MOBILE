<?php
  DEFINE('DB_USERNAME', 'root');
  DEFINE('DB_PASSWORD', 'root');
  DEFINE('DB_HOST', 'localhost');
  DEFINE('DB_DATABASE', 'calendar');

  $mysqli = new mysqli(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_DATABASE);
  if (mysqli_connect_error())
  {
die('Connect Error ('.mysqli_connect_errno().') '.mysqli_connect_error());
    echo "Connection failed";
  }

    $task=$_POST['task'];
    $sql="INSERT INTO tasks(task) values('$task')";
    $result=$mysqli->query($sql);

      $resultJson["result"]=$result;
      echo json_encode($resultJson);
  $mysqli->close();
 ?>
