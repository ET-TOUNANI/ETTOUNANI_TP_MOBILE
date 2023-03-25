
<?php
  DEFINE('DB_USERNAME', 'root');
  DEFINE('DB_PASSWORD', 'root');
  DEFINE('DB_HOST', 'localhost');
  DEFINE('DB_DATABASE', 'inventory');

  $mysqli = new mysqli(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_DATABASE);
  if (mysqli_connect_error())
   {
    die('Connect Error ('.mysqli_connect_errno().') '.mysqli_connect_error());
    echo "Connection failed";
   }
  else
   {
    echo "Connected successfully.<br/><br/>";
    $sql="select * from tasks";
    $result=$mysqli->query($sql);
    $array=array();
      while ($raw=$result->fetch_assoc())
       {
        array_push($array,$raw);
        }
      echo json_encode($array);
    }
  $mysqli->close();
 ?>
