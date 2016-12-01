<?php

    include_once "db.php";
	
	$table = $_GET['table'];	

    try
    {
        $bdd = new PDO("mysql:host=localhost;dbname=$DB_NAME", 'root', '');
    }
    catch (Exception $e)
    {
        die('Erreur : ' . $e->getMessage());
    }

	
    if ($table == "user") {        
    
        $query = $bdd->prepare("INSERT INTO user(username, role, isConnected, isActive, isAlive, isVictim, isGM) VALUES(:username, :role, :isConnected, :isActive, :isAlive, :isVictim, :isGM);");
        $query->bindParam(':username', $username);
        $query->bindParam(':role', $role);
        $query->bindParam(':isConnected', $isConnected);
        $query->bindParam(':isActive', $isActive);
        $query->bindParam(':isAlive', $isAlive);
        $query->bindParam(':isVictim', $isVictim);
        $query->bindParam(':isGM', $isGM);

        $username = $_GET['username'];
        $role = $_GET['role'];
        $isConnected = $_GET['isConnected'];
        $isActive = $_GET['isActive'];
        $isAlive = $_GET['isAlive'];
        $isVictim = $_GET['isVictim'];
        $isGM = $_GET['isGM'];
    }

    elseif ($table == "party") {

        $query = $bdd->prepare("INSERT INTO party(name, password, openToPublic) VALUES(:name, :password, :openToPublic);");
        $query->bindParam(':name', $name);
        $query->bindParam(':password', $password);
        $query->bindParam(':openToPublic', $openToPublic);

        $name = $_GET['name'];
        $password = $_GET['password'];
        $openToPublic = $_GET['openToPublic'];
    }

    echo json_encode($query->execute());
?>