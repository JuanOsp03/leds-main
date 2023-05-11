package co.edu.umanizales.leds.service;

import co.edu.umanizales.leds.model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {
    private ListDE leds;
    public ListDEService(){ this.leds = new ListDE();}
}
