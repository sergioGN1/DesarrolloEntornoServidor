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

    function recogerParametros($id,$nombre,$fecha_nacimiento,$mayor_edad) {
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
        $response = $client->get($uri);
        $json = json_decode($response->getBody());
        return $json;
    }

    function insertarAlumnos($alumno) {
        $client = new Client();
        $uri = 'http://localhost:8080/crearApi/rest/alumnos';
        $response = $client->put($uri, [
            'query' => [
                'alumno' => json_encode($alumno)
            ]
        ]);
    }

    function borrarAlumnos($alumno) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/alumnos';
        try {
            $response = $client->delete($uri, [
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
        $response = $client->post($uri, [
            'form_params' => [
                'alumno' => json_encode($alumno)
            ]
        ]);
    }

}
