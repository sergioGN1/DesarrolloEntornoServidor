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

class SacarDinero {

    function formarMovimiento($numeroCuenta, $descripcion, $hor, $importe, $fecha) {

        $movimiento = new \stdClass;
        if ($numeroCuenta != null && $descripcion != null && $hor != null && $importe != null) {

            $movimiento->mo_ncu = $numeroCuenta;
            $movimiento->mo_des = $descripcion;
            $movimiento->mo_imp = $importe;
            $movimiento->mo_hor = $hor;
            $movimiento->mo_fec = $fecha;
        }
        return $movimiento;
    }

    function formarCliente($dni) {
        $cliente = new \stdClass;
        if ($dni != null) {
            $cliente->cl_dni = $dni;
        }
        return $cliente;
    }

    function sacarDinero($cliente, $movimientos) {
        $client = new Client();

        $uri = 'http://localhost:8080/aplicacionBancaria/apirest';
        try {
            $response = $client->post($uri, [
                'headers' => [
                    'apikey' => "a86tkwxxrfpcn0pf9krfu30kj7bd57uy",
                ],
                'form_params' => [
                    'movimiento' => json_encode($movimientos),
                    'cliente' => json_encode($cliente),
                ]
            ]);
        } catch (Exception $exception) {
            return $exception->getCode();
        }
        $mensaje = json_decode($response->getBody());
        return $mensaje;
    }

}
