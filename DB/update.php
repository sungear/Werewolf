<?php

    include_once "db.php";    

    $table = $_GET['table'];
    $column = $_GET['column'];
    $value = $_GET['value'];
    $refColumn = $_GET['refColumn'];
    $refValue = $_GET['refValue'];
    $refColumn2 = $_GET['refColumn2'];
    $refValue2 = $_GET['refValue2'];

    try
    {
        $bdd = new PDO("mysql:host=localhost;dbname=$DB_NAME", 'root', '');
        // $bdd->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        // $bdd->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
    }
    catch (Exception $e)
    {
        die('Erreur : ' . $e->getMessage());
    }

    $query = $bdd->prepare("UPDATE $table SET $column='$value' WHERE $refColumn='$refValue' AND $refColumn2='$refValue2';");
    $query->bindParam('table', $table);
    $query->bindParam('column', $column);
    $query->bindParam('value', $value);
    $query->bindParam('refColumn', $refColumn);
    $query->bindParam('refValue', $refValue);
    $query->bindParam('refColumn2', $refColumn2);
    $query->bindParam('refValue2', $refValue2);
    

    echo json_encode($query->execute());
?>