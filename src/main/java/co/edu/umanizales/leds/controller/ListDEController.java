package co.edu.umanizales.leds.controller;

import co.edu.umanizales.leds.Exception.ListDEException;
import co.edu.umanizales.leds.controller.dto.LedDTO;
import co.edu.umanizales.leds.controller.dto.ResponseDTO;
import co.edu.umanizales.leds.model.Led;
import co.edu.umanizales.leds.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/listde_leds")
public class ListDEController {

    @Autowired
    private ListDEService listDEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getLed() {
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.getLeds().printLed() , null), HttpStatus.OK);
    }

    @PostMapping(path = "/addLed/{ide}")
    public ResponseEntity<ResponseDTO> addLed(@PathVariable int ide) throws ListDEException {
        Led newLed = new Led(ide , false);
        listDEService.getLeds().addLed(newLed);
        return new ResponseEntity<>(new ResponseDTO(200, "la bombilla fue añadida", null), HttpStatus.OK);
    }

    @GetMapping(path = "/add_led_to_start")
    public ResponseEntity<ResponseDTO> addLedToStart(@RequestBody Led led){

        listDEService.getLeds().addLedToStart(led);
        return new ResponseEntity<>(new ResponseDTO(200, "la bombilla fue añadida al inicio", null), HttpStatus.OK);
    }

    @GetMapping(path = "/turn_on_turn_off")
    public ResponseEntity<ResponseDTO> turnOn_turnOff_Led(){
            listDEService.getLeds().turnOn_turnOff();
        return new ResponseEntity<>(new ResponseDTO(200, "Se realizo el encendido de las luces", null), HttpStatus.OK);
    }

    @GetMapping(path = "/restart")
    public ResponseEntity<ResponseDTO> restartLed(){
        listDEService.getLeds().restart();
        return new ResponseEntity<>(new ResponseDTO(200, "la bombillas reiniciaron su estado", null), HttpStatus.OK);
    }
}