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
        try{
            $uri = 'http://localhost:8080/crearApi/rest/asignaturas';
            $header = array('headers' => array('apikey' => 'a86tkwxxrfpcn0pf9krfu30kj7bd57uy'));
            $response = $client->get($uri, $header);
        } catch (Exception $exception) {
            return $exception->getCode();
        }
        $mostrarAsignatura = json_decode($response->getBody());
        return $mostrarAsignatura;
    }

    function insertarAsignaturas($asignatura) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/asignaturas';
        try{
            $response = $client->put($uri, [
                'headers' => [
                    'apikey' => "a86tkwxxrfpcn0pf9krfu30kj7bd57uy",
                ],
                'query' => [
                    'asignatura' => json_encode($asignatura)
                ]
            ]);
        } catch (Exception $exception) {
            return $exception->getCode();
        }
        $insertaAsignatura = json_decode($response->getBody());
        return $insertaAsignatura;
    }

    function borrarAsignaturas($asignatura) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/asignaturas';
        
        try {
            $response = $client->delete($uri, [
                'headers' => [
                    'apikey' => "a86tkwxxrfpcn0pf9krfu30kj7bd57uy",
                ],
                'query' => [
                    'asignatura' => json_encode($asignatura)
                ]
            ]);
        } catch (Exception $exception) {
            return $exception->getCode();
        }
        $borrarAsignatura = json_decode($response->getBody());
        return $borrarAsignatura;
    }

    function updateAsignaturas($asignatura) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/asignaturas';
        try{
            $response = $client->post($uri, [
                'headers' => [
                    'apikey' => "a86tkwxxrfpcn0pf9krfu30kj7bd57uy",
                ],
                'form_params' => [
                    'asignatura' => json_encode($asignatura)
                ]
            ]);
        } catch (Exception $exception) {
            return $exception->getCode();
        }
        $borrarAsignatura = json_decode($response->getBody());
        return $borrarAsignatura;
    }

}
