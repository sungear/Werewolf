<?php

    include_once "db.php";

    $table = $_GET['table'];
    $column = $_GET['column'];
    $value = $_GET['value'];

    try
    {
        $bdd = new PDO("mysql:host=localhost;dbname=$DB_NAME", 'root', '');
    }
    catch (Exception $e)
    {
        die('Erreur : ' . $e->getMessage());
    }

    $query = $bdd->prepare("DELETE FROM $table WHERE $column='$value';");
    $query->bindParam('table', $table);
    $query->bindParam('column', $column);
    $query->bindParam('value', $value);

    
    echo json_encode($query->execute());
?>