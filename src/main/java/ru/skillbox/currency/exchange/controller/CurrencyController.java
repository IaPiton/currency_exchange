package ru.skillbox.currency.exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.currency.exchange.dto.CurrencyAllDto;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.service.CurrencyService;
import ru.skillbox.currency.exchange.util.CurrencyXmlParser;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/currency")
public class CurrencyController {
    private final CurrencyService service;
    private final CurrencyXmlParser currencyXmlParser;

    @GetMapping(value = "/")
    ResponseEntity<List<CurrencyAllDto>> getAll() {
        List<CurrencyAllDto> allCurrencyDto = service.getAll();
        return ResponseEntity.ok(allCurrencyDto);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<CurrencyDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping(value = "/convert")
    ResponseEntity<Double> convertValue(@RequestParam("value") Long value, @RequestParam("numCode") Long numCode) {
        return ResponseEntity.ok(service.convertValue(value, numCode));
    }
    @PostMapping("/create")
    ResponseEntity<CurrencyDto> create(@RequestBody CurrencyDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }
}
