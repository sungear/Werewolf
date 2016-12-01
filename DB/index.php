<?php
$objet = array("key"=>"value","key1"=>"value1");

$array = array();
$array[] = $objet;
$array[] = $objet;
echo json_encode($array);
?>