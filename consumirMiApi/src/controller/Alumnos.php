<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace controller;

/**
 * Description of Alumnos
 *
 * @author DAW
 */
use GuzzleHttp\Client;

class Alumnos {

    function recogerParametros($id, $nombre, $fecha_nacimiento, $mayor_edad) {
        $alumnoObjeto = new \stdClass;
        if ($id != null) {
            $alumnoObjeto->id = $id;
        }
        if ($nombre != null) {
            $alumnoObjeto->nombre = $nombre;
        }
        if ($fecha_nacimiento != null) {
            $alumnoObjeto->fecha_nacimiento = $fecha_nacimiento;
        }
        if ($mayor_edad == null) {
            $alumnoObjeto->mayor_edad = false;
        } else {
            $alumnoObjeto->mayor_edad = true;
        }
        return $alumnoObjeto;
    }

    function mostrarAlumnos() {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/alumnos';
        //$header = array('headers' => array('apikey' => '2deee83e549c4a6e9709871d0fd58a0a'));
        $response = $client->get($uri);
        $json = json_decode($response->getBody());
        return $json;
    }

    function insertarAlumnos($alumno) {
        $client = new Client();
        $uri = 'http://localhost:8080/crearApi/rest/alumnos';
        $header = array('headers' => array('X-Auth-Token' => '2deee83e549c4a6e9709871d0fd58a0a'));
        $response = $client->put($uri, [
            'header' => [
                'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
            ],
            'query' => [
                'alumno' => json_encode($alumno)
            ]
        ]);
    }

    function borrarAlumnos($alumno) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/alumnos';
        $header = array('headers' => array('X-Auth-Token' => '2deee83e549c4a6e9709871d0fd58a0a'));
        try {
            $response = $client->delete($uri, [
                'header' => [
                    'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
                ],
                'query' => [
                    'alumno' => json_encode($alumno)
                ]
            ]);
        } catch (ClientException $exception) {
            echo $exception->getCode();
            $alumno = json_decode($exception->getResponse()->getBody());
        }
    }

    function updateAlumno($alumno) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/alumnos';
        $header = array('headers' => array('X-Auth-Token' => '2deee83e549c4a6e9709871d0fd58a0a'));
        $response = $client->post($uri, [
            'header' => [
                'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
            ],
            'form_params' => [
                'alumno' => json_encode($alumno)
            ]
        ]);
    }

}
