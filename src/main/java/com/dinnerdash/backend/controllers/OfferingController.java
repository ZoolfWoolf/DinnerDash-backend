package com.dinnerdash.backend.controllers;

import com.dinnerdash.backend.models.Offering;
import com.dinnerdash.backend.repositories.OfferingRepository;
import com.dinnerdash.backend.security.services.UserDetailsImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/offerings")
public class OfferingController {
    @Autowired
    OfferingRepository offeringRepository; // Will automatically attach an object which implements this interface.

    @GetMapping(value = "/getAllOfferings/{restaurantId}")
    public ResponseEntity<List<Offering>> getAllItems(@PathVariable("restaurantId") int restaurantId) {
        // Getting the ID of the logged in user.
        System.out.println("GET ALL OFFERINGS");
        // int restaurantId = ((UserDetailsImpl)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return new ResponseEntity<>(offeringRepository.findByRestaurant(restaurantId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('RESTAURANT')")
    @PostMapping(value = "/removeOffering/{offeringId}")
    public ResponseEntity<Integer> removeItem(@PathVariable("offeringId") int offeringId) {
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        System.out.println(offeringId);
        int response = 0;
        try {
            response = offeringRepository.remove(userId, offeringId);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('RESTAURANT')")
    @PostMapping(value = "/addOffering")
    public ResponseEntity<Offering> addItem(@RequestBody Offering addToCart) {
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        System.out.println(addToCart.getOfferingDescription());
        System.out.println(userId);

        Offering temp = new Offering(userId, addToCart.getOfferingName(), addToCart.getOfferingDescription(),
                addToCart.getPrice(), addToCart.getOfferingPhotoUrl());
        try {
            offeringRepository.save(temp);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('RESTAURANT')")
    @PostMapping(value = "/modifyOffering")
    public ResponseEntity<Offering> modifyItem(@RequestBody Offering addToCart) {
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        System.out.println(addToCart.getOfferingDescription());
        System.out.println(userId);

        int res = 0;
        Offering temp = new Offering(userId, addToCart.getOfferingName(), addToCart.getOfferingDescription(),
                addToCart.getPrice(), addToCart.getOfferingPhotoUrl());
        temp.setOfferingId(addToCart.getOfferingId());
        try {
            res = offeringRepository.modify(temp);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(offeringRepository.findById(temp.getOfferingId(), userId), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Offering>> getAllItems() {
        // Getting the ID of the logged in user.
        System.out.println("GET ALL OFFERINGS");
        List<Offering> list;
        try {
            int restaurantId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .getId();
            list = offeringRepository.findByRestaurant(restaurantId);
        } catch (Exception e) {
            list = offeringRepository.findAll();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Access-Control-Expose-Headers", "Content-Range");
        headers.set("Content-Range", "offerings " + list.size() + "/" + list.size());

        for (int i = 0; i < list.size(); i++) {
            list.get(i).id = list.get(i).getOfferingId();
        }

        ResponseEntity<List<Offering>> response = new ResponseEntity<>(list, headers, HttpStatus.OK);

        return response;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Offering> getOne(@PathVariable("id") int id) {
        try {
            int restaurantId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .getId();
            return getOne(restaurantId, id);
        } catch (Exception e) {
            System.out.println("GET ONE OFFERING " + id);

            Offering o = offeringRepository.findById(id);
            if (o != null) {
                return new ResponseEntity<Offering>(o, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // create
    @PreAuthorize("hasRole('RESTAURANT')")
    @PostMapping(value = "")
    public ResponseEntity<Offering> create(@RequestBody Offering offering) {
        return addItem(offering);
    }

    @PreAuthorize("hasRole('RESTAURANT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Offering> modify(@PathVariable String id, @RequestBody Offering entity) {
        return modifyItem(entity);
    }

    @PreAuthorize("hasRole('RESTAURANT')")
    @DeleteMapping(value = "/{id}")
    public int delete(@PathVariable("id") int id) {
        int restaurantId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getId();
        return offeringRepository.remove(restaurantId, id);
    }

    @GetMapping(value = "/{restaurantId}/{offeringId}")
    public ResponseEntity<Offering> getOne(@PathVariable("restaurantId") int resid,
            @PathVariable("offeringId") int id) {
        // Getting the ID of the logged in user.
        System.out.println("GET ONE OFFERING " + id);

        Offering o = offeringRepository.findById(id, resid);
        if (o != null) {
            return new ResponseEntity<Offering>(o, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
