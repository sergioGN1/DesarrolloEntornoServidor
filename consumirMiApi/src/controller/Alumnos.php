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
        $header = array('headers' => array('apikey' => 'a86tkwxxrfpcn0pf9krfu30kj7bd57uy'));
        try{
            $response = $client->get($uri,$header);
        } catch (Exception $exception) {
            return $exception->getCode();
        }
        $mostrarAlumno = json_decode($response->getBody());
        return $mostrarAlumno;
    }

    function insertarAlumnos($alumno) {
        $client = new Client();
        $uri = 'http://localhost:8080/crearApi/rest/alumnos';
        try{
            $response = $client->put($uri, [
                'headers' => [
                    'apikey' => "a86tkwxxrfpcn0pf9krfu30kj7bd57uy",
                ],
                'query' => [
                    'alumno' => json_encode($alumno)
                ]
            ]);
        } catch (Exception $exception) {
            return $exception->getCode();
        }
        $insertarAlumno = json_decode($response->getBody());
        return $insertarAlumno;
    }

    function borrarAlumnos($alumno) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/alumnos';
        
        try {
            $response = $client->delete($uri, [
                'headers' => [
                    'apikey' => "a86tkwxxrfpcn0pf9krfu30kj7bd57uy",
                ],
                'query' => [
                    'alumno' => json_encode($alumno)
                ]
            ]);
        } catch (ClientException $exception) {
            return $exception->getCode();
        }
        $borrarAlumno = json_decode($response->getBody());
        return $borrarAlumno;
    }

    function updateAlumno($alumno) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/alumnos';
        try{
            $response = $client->post($uri, [
                'headers' => [
                    'apikey' => "a86tkwxxrfpcn0pf9krfu30kj7bd57uy",
                ],
                'form_params' => [
                    'alumno' => json_encode($alumno)
                ]
            ]);
        } catch (ClientException $exception) {
            return $exception->getCode();
        }
        $updateAlumno = json_decode($response->getBody());
        return $updateAlumno;
    }

}
