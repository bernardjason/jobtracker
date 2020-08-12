package com.example.jobtracker.web;


import com.example.jobtracker.entity.Job;
import com.example.jobtracker.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class JobTrackerController {
    private JobService jobService;

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @Value("${oauth.enabled:false}" )
    private boolean oauth;

    @Value("${oauth.logout.url:set oauth.logout.url}" )
    private String auth_logout;

    @Value("${spring.security.oauth2.client.registration.custom-client.client-id:not set}")
    private  String clientId;
    @Value("${spring.security.oauth2.client.registration.custom-client.client-secret:not set}")
    private  String clientSecret;
    @Value("${spring.security.oauth2.client.provider.custom-provider.authorization-uri:no set}")
    private String authorizationUri ;
    @Value("${spring.security.oauth2.client.registration.custom-client.redirect-uri:not set}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.provider.custom-provider.token-uri:not set}")
    private  String tokenUri;

    @Value("${oauth.getcode.url:not set}")
    private String getCode;


    @GetMapping("/")
    public String singlePage(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("jobs", jobService.listJobs());
        model.addAttribute("job", new Job());

        return "index";
    }
    @PostMapping("/")
    public String postJob(@ModelAttribute Job job,Model model) {
        jobService.addJob(job);
        model.addAttribute("jobs", jobService.listJobs());
        model.addAttribute("job", new Job());
        return "index";
    }
    @GetMapping("jobs")
    public String jobs(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("jobs", jobService.listJobs());
        return "jobs";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/do_logout")
    public String logout(HttpServletRequest request, HttpServletResponse response,RedirectAttributes attributes,Model model){

        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        if ( oauth ) {
            return "redirect:"+auth_logout;
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/code")
    public String code(Model model,@RequestParam String code) {
        model.addAttribute("code",code.trim());
        model.addAttribute("clientId",clientId);
        model.addAttribute("clientSecret",clientSecret);
        model.addAttribute("redirectUri",redirectUri);
        model.addAttribute("tokenUri",tokenUri);
        model.addAttribute("getCode",getCode);
        return "code";
    }

    @GetMapping("/getcode")
    public RedirectView redirectWithUsingRedirectView(
            RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");

        //String url = "http://127.0.0.1:8081/auth/oauth/authorize?response_type=code&client_id=R2dpxQ3vPrtfgF72&redirect_uri=http://127.0.0.1:8080/code/";
        String url = authorizationUri+"?response_type=code&client_id="+
                clientId+"&redirect_uri="+getCode;
        return new RedirectView(url);
    }

}
