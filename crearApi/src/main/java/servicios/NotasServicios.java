/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.NotasDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Nota;

/**
 *
 * @author DAW
 */
public class NotasServicios {
    
    public List<Nota> getAllNotas()
    {
        NotasDAO dao = new NotasDAO();
        
        return dao.getAllNotasDBUtils();
    }
    
    public int getAllNotasSelect(Nota selectNota)
    {
        NotasDAO dao = new NotasDAO();
        
        return dao.getAllNotasSelectDBUtils(selectNota);
    }
    
   /* public Nota getNotaById(int id){
        NotasDAO dao = new NotasDAO();
        
        return dao.getUserById(id);
        
    }*/
    public boolean addNota(Nota insertNota)
    {
        NotasDAO dao = new NotasDAO();
        if(dao.insertNotaDBUtils(insertNota) == 1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean updateNota(Nota notaAct)
    {
        NotasDAO dao = new NotasDAO();
        if(dao.updateNotaDBUtils(notaAct) == 1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean deleteNota(Nota notaDel)
    {
        NotasDAO dao = new NotasDAO();
        if(dao.deleteNotaDBUtils(notaDel) == 1){
            return true;
        }else{
            return false;
        }
    }
}
