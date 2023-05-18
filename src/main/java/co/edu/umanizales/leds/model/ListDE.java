package co.edu.umanizales.leds.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {

    private NodeDE head;
    private NodeDE tail;
    private int size;
    private List<Led> leds = new ArrayList<>();

    public void addLed(Led led) {
        if (head != null) {
            NodeDE newNode = new NodeDE(led);
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        } else {
            head = new NodeDE(led);
        }
        size++;
    }

    public void addLedToStart(Led led)  {
        NodeDE newNode = new NodeDE(led);
        if (head != null) {
            head.setPrevious(newNode);
            newNode.setNext(head);
        }
        head = newNode;
        size++;
    }

    /*
    lo primero que debemos hacer es validar si hay datos
    si hay datos,
    como primera validacion digo que si solo hay un dato lo prendo y ya
    otra validacion es que si el tamaño de la lista es par
        si la condicion se cumple, creamos dos ayudantes y nos situamos en en el led de la mitad y en el led siguiente,
        los encendemos y en el atributo de encendidole ponemos la hora en el que sucedio y un segundo despues los
        apaguamos y le ponemos la hora en el atributo de apagado
        luego de realizar este proceso, les decimos a los dos ayudantes que se pasen a sus vecinos, al ayudante que anteriormente
        se paso al siguiente de la mitad le decimos que pase al otro siguiente y al que se situo en la mitad de la lista le decimos que
        se pase al anterior del de la mitad, una vez realizada esta funcion realizamos el mismo proceso de encender y apagar y ponerles la hora.
        Cuando el siguiente y el anterior respectivo de los led sea null decimos que solamente realice la accion de encenderlos y ponerle la hora de
        encendido y que el atributo de apagado la deje vacia o en null.

        En caso contrario de que la lista sea impar
        nos paramos uno mas que el numero entero, (es decir, si la listas tiene siete leds el ayudante este parado en el led tres y se pase uno mas)
        una vez el ayudante este en esa posicion decimos que encienda, apague y en cada una de estas acciones con una diferencia de un segundo guarde la hora
        despues de realizar la accion en el led, creamos una variable y le decimos que sea igual al siguiente del ayudante y al ayudante original le decimos
        que se pase al anterior, una vez posicionados realizamos la misma accion de encender, apagar, guardar la hora con diferencia de un segundo
        y que cuando cada uno este en los extremos solamente los encienda y guarde la hora.
     */

    // Prototipo de method
    public void turnOn(NodeDE temp){
        temp.getData().setState(true);
        temp.getData().setDateOn(LocalTime.from(LocalDateTime.now()));
    }

    public void turnOff(NodeDE after){
        after.getData().setState(false);
        after.getData().setDateOff(LocalTime.from(LocalDateTime.now()));
    }

    public void turnOn_turnOff() {
        if (head != null) {
            NodeDE temp = head;
            int position = 1;
            int half;
            if ((size % 2) == 0){
                half = size/2;
                while (temp != null){
                    if (position == half){
                        NodeDE next = temp.getNext();
                        turnOn(temp);
                        turnOn(next);
                        while (next.getNext() != null) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            turnOff(temp);
                            turnOff(next);

                            temp = temp.getPrevious();
                            next = next.getNext();

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            turnOn(temp);
                            turnOn(next);
                        }
                    }
                    position++;
                    temp= temp.getNext();
                }
            } else{
                half = (size / 2) + 1;
                while (temp != null){
                    if (position == half){
                        NodeDE next = temp;
                        turnOn(temp);
                        while (next.getNext() != null){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            turnOff(temp);
                            turnOff(next);

                            temp = temp.getPrevious();
                            next= next.getNext();

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            turnOn(temp);
                            turnOn(next);
                        }
                    }
                    position++;
                    temp= temp.getNext();
                }
            }
        }
    } //fin method turnOn_turnOff

    public List<Led> printLed() {
        leds.clear();
        if (head != null){
            NodeDE temp = head;
            while (temp != null){
                leds.add(temp.getData());
                temp = temp.getNext();
            }
        }
        return leds;
    }

    public void restart() {
        NodeDE temp = head;
        while (temp != null){
            temp.getData().setState(false);
            temp.getData().setDateOff(null);
            temp.getData().setDateOn(null);

            temp = temp.getNext();
        }
    }
}