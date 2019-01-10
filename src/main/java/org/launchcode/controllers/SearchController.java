package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")
    // TODO #1 - Create handler to process search request and display results
    public String searchJobs(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        model.addAttribute("columns", ListController.columnChoices);
        ArrayList<HashMap<String, String>> jobs;
        if (searchTerm == null || searchTerm.equals("")) {
            String error = "Please enter a search parameter.";
            model.addAttribute("error", error);
        }
        else if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
            model.addAttribute("searchTerm", searchTerm);
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", ListController.columnChoices);
            if (jobs == null || jobs.size() == 0) {
                String error = "No search results found.";
                model.addAttribute("error", error); }

        }else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("searchTerm", searchTerm);
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", ListController.columnChoices);
            if (jobs == null || jobs.size() == 0) {
                String error = "No search results found.";
                model.addAttribute("error", error);
            }
        }

     return "search";
    }

}