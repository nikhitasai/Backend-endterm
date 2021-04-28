package com.example.upGradET.controller;

import com.example.upGradET.model.Details;
import com.example.upGradET.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DetailsController
{
    @Autowired
   private DetailsService detailsService;

    @RequestMapping("/displayDetails")
    public String getDetails(Model model)
    {
        List<Details> details=detailsService.getAllDetails();
        model.addAttribute("details",details);
        return "display";

    }

    @RequestMapping(method = RequestMethod.GET,value = "/details/add")
    public String addDetails1(Model model)
    {
        Details details=new Details();
        model.addAttribute("details",details);
        return "details";
    }


    @RequestMapping(method = RequestMethod.POST,value = "/details/add")
    public String addDetails(Details details)
    {
        detailsService.addCredentials(details);
        return "redirect:/displayDetails";
    }
    @RequestMapping(method=RequestMethod.GET,value="/editDetail")
    public String editDetails(@RequestParam(value = "detailId") Integer detailId, Model model)
    {
        Details detail=detailsService.getDetail(detailId);
        model.addAttribute("detail",detail);
        return "edit";
    }

    @RequestMapping(method = RequestMethod.PUT,value="/editDetail")
    public String updateDetail(@RequestParam(value = "detailId") Integer detailId, Details updatedDetail)
    {
    updatedDetail.setId(detailId);
    detailsService.updateDetails(updatedDetail);
    return "redirect:/displayDetails";
    }

    @RequestMapping(method=RequestMethod.DELETE,value="/deleteDetail")
    public String deleteDetail(@RequestParam(value = "detailId") Integer detailId)
    {
        detailsService.deleteDetail(detailId);
        return "redirect:/displayDetails";
    }

}
