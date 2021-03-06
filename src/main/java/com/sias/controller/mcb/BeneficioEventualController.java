/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sias.controller.mcb;

import com.sias.model.entity.mcb.BeneficioEventual;
import com.sias.model.service.mcb.interfaces.BeneficioEventualService;
import com.sias.util.Constants;
import com.sias.util.GSONConverter;
import com.sias.util.ValidateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jaderhenrique
 */
@Controller
@RequestMapping("/cadastrosBasicos")
public class BeneficioEventualController {

    @Autowired
    private BeneficioEventualService beneficioEventualService;

    @RequestMapping(value = "/beneficioEventual", method = RequestMethod.GET)
    public ModelAndView beneficioEventual() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("beneficioEventualList");

        try {
            mv.addObject("beneficioEventualList", beneficioEventualService.readAll());
        } catch (Exception ex) {
            Logger.getLogger(BeneficioEventualController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mv;
    }

    @RequestMapping(value = "/beneficioEventual/novo", method = RequestMethod.GET)
    public ModelAndView novo() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("beneficioEventualForm");
        try {
        } catch (Exception ex) {
            Logger.getLogger(BeneficioEventualController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/beneficioEventual/save", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> save(String json) {

        Map<String, Object> response = new HashMap<String, Object>();

        try {
            BeneficioEventual beneficioEventual = (BeneficioEventual) GSONConverter.convert(json, BeneficioEventual.class);
            if (beneficioEventual.getId() == null) {
                beneficioEventualService.create(beneficioEventual);
            } else{
                beneficioEventualService.update(beneficioEventual);
            }
            response.put("success", true);
            response.put("msg", "Salvo com sucesso!");
        } catch (ValidateException ex) {
            Logger.getLogger(BeneficioEventualController.class.getName()).log(Level.SEVERE, null, ex);
            response.put("success", false);
            response.put("msg", ex.getMessage());
        } catch (DuplicateKeyException ex) {
            Logger.getLogger(BeneficioEventualController.class.getName()).log(Level.SEVERE, null, ex);
            response.put("success", false);
            response.put("msg", Constants.MENSAGEM_DUPLICATE_KEY_EXCEPTION);
        } catch (Exception ex) {
            Logger.getLogger(BeneficioEventualController.class.getName()).log(Level.SEVERE, null, ex);
            response.put("success", false);
            response.put("msg", Constants.MENSAGEM_ERRO_INESPERADO);
        }

        return response;
    }

    @RequestMapping(value = "/beneficioEventual/{id}/editar", method = RequestMethod.GET)
    public ModelAndView editar(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("beneficioEventualForm");

        try {
            modelAndView.addObject("beneficioEventual", beneficioEventualService.readById(id));
        } catch (Exception ex) {
            Logger.getLogger(BeneficioEventualController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/beneficioEventual/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView excluir(@PathVariable Long id) {

        try {
            beneficioEventualService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(BeneficioEventualController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ModelAndView("redirect:/cadastrosBasicos/beneficioEventual");
    }

    @RequestMapping(value = "/beneficioEventual/readAll", method = RequestMethod.POST)
    public @ResponseBody
    List<BeneficioEventual> readAll() {

        try {
            return beneficioEventualService.readAll();
        } catch (Exception ex) {
            Logger.getLogger(BeneficioEventualController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
