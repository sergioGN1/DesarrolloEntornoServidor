$(document).ready(function(){
                $("#selectAsignatura").change(function() {
                
                      var nombre = "";
                      var fecha = "";
                      var mayor = false;
                      $( "#selectAsignatura option:selected" ).each(function() {
                          
                        nombre = $(this).text();
                        fecha = $(this).attr("data-fecha")
                        mayor = $(this).attr("data-mayor")
                        
                      });
                          
                      $("#nombre").val( nombre );
                      $("#fecha").val( fecha );
                      $("#mayor").attr("checked", mayor);
                          
                });
                    $("#seleccionarAsignatura").change(function() {
                
                      var nombre = "";
                      var ciclo = "";
                      var curso = "";
                      $( "#seleccionarAsignatura option:selected" ).each(function() {
                          
                        nombre = $( this ).text();
                        curso = $(this).attr("data-curso")
                        ciclo = $(this).attr("data-ciclo")
                        
                      });
                      $("#nombreAsig").val(nombre);
                      $("#curso").val(curso);
                      $("#ciclo").val(ciclo);
                          
                });
            });

