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

class Notas {

    function recogerParametros($id_alumno, $id_asignatura, $valorNota) {
        $notaObjeto = new \stdClass;
        if ($id_alumno != null) {
            $notaObjeto->id_alumno = $id_alumno;
        }
        if ($id_asignatura != null) {
            $notaObjeto->id_asignatura = $id_asignatura;
        }
        if ($valorNota != null) {
            $notaObjeto->nota = $valorNota;
        }
        return $notaObjeto;
    }

    function mostrarNotas() {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/notas';
        $header = array('headers' => array('apikey' => '2deee83e549c4a6e9709871d0fd58a0a'));
        $response = $client->get($uri, $header);
        $json = json_decode($response->getBody());
        return $json;
    }

    function insertarNotas($nota) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/notas';
        $header = array('headers' => array('X-Auth-Token' => '2deee83e549c4a6e9709871d0fd58a0a'));
        $response = $client->put($uri, [
            'header' => [
                'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
            ],
            'query' => [
                'nota' => json_encode($nota)
            ]
        ]);
        
    }

    function borrarNotas($nota) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/notas';
        $header = array('headers' => array('X-Auth-Token' => '2deee83e549c4a6e9709871d0fd58a0a'));
        try {
            $response = $client->delete($uri, [
                'header' => [
                'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
            ],
                'query' => [
                    'nota' => json_encode($nota)
                ]
            ]);
        } catch (ClientException $exception) {
            echo $exception->getCode();
            $alumno = json_decode($exception->getResponse()->getBody());
        }
        
    }

    function updateNotas($nota) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/notas';
        
        $response = $client->post($uri, [
            'header' => [
                'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
            ],
            'form_params' => [
                'nota' => json_encode($nota)
            ]
        ]);
        
    }

}
