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
        $header = array('headers' => array('apikey' => '2deee83e549c4a6e9709871d0fd58a0a'));
        $response = $client->get($uri, $header);
        $json = json_decode($response->getBody());
        return $json;
    }

    function insertarAsignaturas($asignatura) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/asignaturas';
        $header = array('headers' => array('X-Auth-Token' => '2deee83e549c4a6e9709871d0fd58a0a'));
        $response = $client->put($uri, [
            'header' => [
                'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
            ],
            'query' => [
                'asignatura' => json_encode($asignatura)
            ]
        ]);
    }

    function borrarAsignaturas($asignatura) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/asignaturas';
        $header = array('headers' => array('X-Auth-Token' => '2deee83e549c4a6e9709871d0fd58a0a'));
        try {
            $response = $client->delete($uri, [
                'header' => [
                    'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
                ],
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
        $header = array('headers' => array('X-Auth-Token' => '2deee83e549c4a6e9709871d0fd58a0a'));
        $response = $client->post($uri, [
            'header' => [
                'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
            ],
            'form_params' => [
                'asignatura' => json_encode($asignatura)
            ]
        ]);
    }

}
