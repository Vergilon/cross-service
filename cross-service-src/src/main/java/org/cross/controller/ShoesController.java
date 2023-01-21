package org.cross.controller;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.cross.api.dto.ShoesAddDto;
import org.cross.api.dto.ShoesFullDto;
import org.cross.api.dto.ShoesUpdateDto;
import org.cross.service.ShoesService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/shoes")
@RequiredArgsConstructor
public class ShoesController {

    private final ShoesService shoesService;

    @GetMapping
    public List<ShoesFullDto> getShoes() {
        return shoesService.getShoes();
    }

    @GetMapping(path = "/byshoesid/{shoesId}")
    public ShoesFullDto findById(@PathVariable("shoesId") UUID shoesID) {
        return shoesService.findById(shoesID);
    }

    @GetMapping(path = "/byclientid/{clientId}")
    public ShoesFullDto findByClientId(@PathVariable("clientId") UUID clientID) {
        return shoesService.findById(clientID);
    }

    @PostMapping
    public ShoesFullDto addNewShoes(@RequestBody ShoesAddDto shoes) {
        return shoesService.addNewShoes(shoes);
    }

    @DeleteMapping(path = "{shoesId}")
    public Boolean deleteShoes(@PathVariable("shoesId") UUID shoesId) {
        return shoesService.deleteShoes(shoesId);
    }

    @PutMapping(path = "{shoesId}")
    public ShoesFullDto updateShoes(
        @PathVariable("shoesId") UUID shoesId,
        @RequestBody ShoesUpdateDto shoesUpdateDto) {
        return shoesService.updateShoes(shoesId, shoesUpdateDto);
    }
}