<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
namespace controller;
/**
 * Description of ListarMovimientos
 *
 * @author Sergio
 */
use GuzzleHttp\Client;
class ListarMovimientos {
    function montarObjeto($dni,$numeroCuenta,$fecha1,$fecha2){
        $datos = new \stdClass;
        if($dni != "" && $numeroCuenta != "" && $fecha1 != "" && $fecha2 != ""){
            $datos->dni = $dni;
            $datos->numeroCuenta = $numeroCuenta;
            $datos->fecha1 = $fecha1;
            $datos->fecha2 = $fecha2;
        }
        return $datos;
    }
    function listarMovimientos($datos) {
        $client = new Client();

        $uri = 'http://localhost:8080/aplicacionBancaria/apirest';
        
        try{
            $response = $client->get($uri, [
                'headers' => [
                    'apikey' => "a86tkwxxrfpcn0pf9krfu30kj7bd57uy",
                ],
                'query' => [
                    'datos' => json_encode($datos)
                ]
            ]);
        } catch (Exception $exception) {
            return $exception->getCode();
        }
        $listadoMovimientos = json_decode($response->getBody());
        return $listadoMovimientos;
    }
}
