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

    function mostrarNotas($nota) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/notas';
        try{
            $response = $client->get($uri, [
                'headers' => [
                    'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
                ],
                'query' => [
                    'nota' => json_encode($nota)
                ]
            ]);
        } catch (Exception $exception) {
            return $exception->getCode();
        }
        $mostrarNota = json_decode($response->getBody());
        return $mostrarNota;
    }

    function insertarNotas($nota) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/notas';
        try{
            $response = $client->put($uri, [
                'headers' => [
                    'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
                ],
                'query' => [
                    'nota' => json_encode($nota)
                ]
            ]);
        } catch (Exception $exception) {
            return $exception->getCode();
         }   
        $notaI = json_decode($response->getBody());
        return $notaI;
    }

    function borrarNotas($nota) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/notas';
        
        try {
            $response = $client->delete($uri, [
            'headers' => [
                'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
            ],
                'query' => [
                    'nota' => json_encode($nota)
                ]
            ]);
            
        } catch (Exception $exception) {
            return $exception->getCode();
            
        }
        $notaB = json_decode($response->getBody());
        return $notaB;
    }

    function updateNotas($nota) {
        $client = new Client();

        $uri = 'http://localhost:8080/crearApi/rest/notas';
        try {
            $response = $client->post($uri, [
                'header' => [
                    'apikey' => "2deee83e549c4a6e9709871d0fd58a0a",
                ],
                'form_params' => [
                    'nota' => json_encode($nota)
                ]
            ]);
        } catch (Exception $exception) {
            return $exception->getCode();
            
        }

        $notaA = json_decode($response->getBody());
        return $notaA;
    }

}
