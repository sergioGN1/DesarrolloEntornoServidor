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

class Asignaturas {

    function recogerParametros($id, $nombre, $curso, $ciclo) {
        $asignaturaObjeto = new \stdClass;
        if ($id != null) {
            $asignaturaObjeto->id = $id;
        }
        if ($nombre != null) {
            $asignaturaObjeto->nombre = $nombre;
        }
        if ($curso != null) {
            $asignaturaObjeto->curso = $curso;
        }
        if ($ciclo != null) {
            $asignaturaObjeto->ciclo = $ciclo;
        }
        return $asignaturaObjeto;
    }

    function mostrarAsignaturas() {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/asignaturas';
        $response = $client->get($uri);
        $json = json_decode($response->getBody());
        return $json;
    }

    function insertarAsignaturas($asignatura) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/asignaturas';
        $response = $client->put($uri, [
            'query' => [
                'asignatura' => json_encode($asignatura)
            ]
        ]);
    }

    function borrarAsignaturas($asignatura) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/asignaturas';
        try {
            $response = $client->delete($uri, [
                'query' => [
                    'asignatura' => json_encode($asignatura)
                ]
            ]);
        } catch (ClientException $exception) {
            echo $exception->getCode();
            $alumno = json_decode($exception->getResponse()->getBody());
        }
        
    }

    function updateAsignaturas($asignatura) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/asignaturas';
        $response = $client->post($uri, [
            'form_params' => [
                'asignatura' => json_encode($asignatura)
            ]
        ]);
    }

}
