<?php

    include_once "db.php";

    $table = $_GET['table'];
    $column = $_GET['column'];
    $refColumn = $_GET['refColumn'];
    $refValue = $_GET['refValue'];
    $refColumn2 = $_GET['refColumn2'];
    $refValue2 = $_GET['refValue2'];

    try
    {
        $bdd = new PDO("mysql:host=localhost;dbname=$DB_NAME", 'root', '');
    }
    catch (Exception $e)
    {
        die('Erreur : ' . $e->getMessage());
    }

    $query = $bdd->prepare("SELECT $column FROM $table WHERE $refColumn='$refValue' AND $refColumn2='$refValue2';");
    $query->bindParam('table', $table);
    $query->bindParam('column', $column);
    $query->bindParam('refColumn', $refColumn);
    $query->bindParam('refValue', $refValue);
    $query->bindParam('refColumn2', $refColumn2);
    $query->bindParam('refValue2', $refValue2);

    $query->execute();
    $tab = array();
    while(($result = $query->fetch(PDO::FETCH_ASSOC)) != null){
        $tab[] = $result;
    }
    echo json_encode($tab);

?>