package co.edu.umanizales.leds.model;

import lombok.Data;

@Data
public class NodeDE {
    private Led data;
    private NodeDE next;
    private NodeDE previous;

    public NodeDE (Led data){this.data = data;}
}
