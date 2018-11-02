<!DOCTYPE html>
<html>
<head>
	<title>Upload CSV file intoPostgreSQL using php</title>

 <!-- Javascript checks for the CSV file to get uploaded. -->

	<script type="text/javascript">
	$(document).ready(
	function() {
		$("#frmCSVImport").on(
		"submit",
		function() {

			$("#response").attr("class", "");
			$("#response").html("");
			var fileType = ".csv";
			var regex = new RegExp("([a-zA-Z0-9\s_\\.\-:])+("
					+ fileType + ")$");
			if (!regex.test($("#file").val().toLowerCase())) {
				$("#response").addClass("error");
				$("#response").addClass("display-block");
				$("#response").html(
						"Invalid File. Upload : <b>" + fileType
								+ "</b> Files.");
				return false;
			}
			return true;
		});
	});
</script>
</head>

<!-- form to upload the csv file -->
<body>
<form method='POST' enctype='multipart/form-data'>
	Upload CSV file: 
	<input type="file" name="csvusers" />
	<input type="submit" name="submit" value="UPLOAD">

</form>


<?php 



// Create database and table and users

$host = 'localhost';
$dbuser = 'username';
$dbpass = 'password';


$db = new PDO("pgsql:host=$host", $dbuser,$dbpass);
$dbcreate = "CREATE DATABASE dbcsv";

echo "Database created successfully<br>";

$dbname = 'dbcsv';


// Connecting to the created database dbcsv

$con = pg_connect("$host $dbname $dbuser $dbpass");
if(!$con) {
	echo "Error: Database could not be opened";
}
else {
	echo "Database is opened successfully";
}

// Query to create users table
$pgsql1 =<<<EOF
	CREATE TABLE users
	(ID INT PRIMARY KEY NOT NULL,
	name TEXT NOT NULL,
	surname TEXT NOT NULL,
	email TEXT NOT NULL);
EOF;
 
	$query = pg_query($con, $pgsql1);
	if(!$query){
		echo pg_last_error($con);
		}
		else
		{
			echo "Succesfully created the table";
		}

		



 if(isset($_POST["submit"])){
		
		$filename=$_FILES["file"]["tmp_name"];		
 
 
		 if($_FILES["file"]["size"] > 0)
		 {
		  	$file = fopen($filename, "r");
	        while (($getData = fgetcsv($file, 10000, ",")) !== FALSE)
	         {
	         	$emailErr = "";
	         	// convert name and surname first letters to CAPITALS
 				$getData[1]=ucfirst('$getData[1]');
 				$getData[2]=ucfirst('$getData[2]');
 				// Check for email format and convert email to lower cases
				$getData[3] = strtolower(test_input($getData[3]));
				if (!filter_var($getData[3], FILTER_VALIDATE_EMAIL)) {
 				 $emailErr = "Invalid email format"; 
				}

 				
 				

  function test_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}
 
			$pgsql2 =<<<EOF
	       		INSERT into users (id,name,surname,email) 
                   values ('".$getData[0]."','".$getData[1]."','".$getData[2]."','".$getData[3]."');
EOF;
                   $result = pg_query($con, $pgsql2);
				if(!isset($result))
				{
					echo "<script type=\"text/javascript\">
							alert(\"Invalid Format:Please Upload CSV Format.\");
							window.location = \"user_upload.php\"
						  </script>";		
				}
				else {
					  echo "<script type=\"text/javascript\">
						alert(\"CSV File has been imported successfully.\");
						window.location = \"user_upload.php\"
					</script>";
				}
	         }
			
	         fclose($file);	
		 }
	}	 


pg_close($con);



?>

</body>
</html>