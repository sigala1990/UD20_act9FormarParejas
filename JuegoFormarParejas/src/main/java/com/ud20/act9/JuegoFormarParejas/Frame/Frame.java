package com.ud20.act9.JuegoFormarParejas.Frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet.ColorAttribute;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Frame extends JFrame {

	private JPanel contentPane;
	private List<JToggleButton> list;
	private List<Color> listColors = new ArrayList<Color>();
	private List<Integer> listPosition = new ArrayList<Integer>();
	private Hashtable htable;
	private int position1, position2;
	private int contadorBotonesPulsados;
	private int controlPrimerIntento = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Frame() {
		setTitle("Juego de formar parejas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = CrearListaTB(); // crea 16 tooglebuttons
		mostrarToggleButton();

	}

	public List<JToggleButton> CrearListaTB() {
		List<JToggleButton> list = new ArrayList<JToggleButton>();
		Color color = null;
		for (int i = 0; i < 16; i++) {
			JToggleButton tgbtn = new JToggleButton();
			tgbtn.setName("" + i);
			boolean colorNoRepetido = true;
			while (colorNoRepetido) { //while que repetira randomColor hasta que coja un color que no este repetido mas de 2 veces
				color = randomColor();
				if (controlColorRepetido(color)) {
					colorNoRepetido = false;
				}
			}
			tgbtn.setSelected(true); //gira cada carta(togglebuttons)creada del reves
			tgbtn.addActionListener(new ActionListener() {//accion que se realiza al pulsar el boton
				public void actionPerformed(ActionEvent arg0) {
					if (controlPrimerIntento == 2) {//solo se debe ejecutar cuando tengamos 2 togglebuttons seleccionados
						verificarSiSonIguales();
						controlPrimerIntento = 0;
					}
					pulsando2ToggleButon();//metodo que pasa todos los togglebuttons al ser pulsado, donde se verificara cuantos botones estan pulsados y se comprueba su color
				}
			});
			listColors.add(color); // se añade el color a una lista llamada "listColors" para tener informacion de cuales estan siendo usados y cuantos.
			tgbtn.setBackground(color); //añadir el colorRandom del while anterior
			list.add(tgbtn);// creacion del boton realizada dentro de una posicion de la lista llamada "list"
		}
		return list;
	}

	private boolean controlColorRepetido(Color randomColor) {//metodo que devuelve boolean dependiendo si se ha usado el colorRandom 
		int contadorColorRepetido = 0;
		for (int i = 0; i < listColors.size(); i++) {
			if (listColors.get(i) == (randomColor)) {
				contadorColorRepetido++;
			}
		}
		if (contadorColorRepetido >= 2) {// color repetido mas de 2 veces, vamos a seguir buscando
			return false;
		}
		return true;
	}

	public static int numRandom(int min, int max) { // metodo devuelve num random
		return (int) (Math.random() * (max - min) + min);
	}

	public void pulsando2ToggleButon() { // FUNCIONALIDAD MOSTRAR 2 Cartas A la vez
		contadorBotonesPulsados = 0;
		position1 = 999;
		position2 = 0;
		for (int i = 0; i < list.size(); i++) {

			if (!list.get(i).isSelected()) {// comprueba la lista i mira cuantos han sido pulsados
				contadorBotonesPulsados++;// contador de botones pulsados
			}
			if (contadorBotonesPulsados == 2) {// cuando han sido pulsado2 botones procede a verificarlos y guardar cuales han sido los pulsados
				verificar2posiciones(); //guarda posiciones pulsadas
				listPosition.add(position1);
				listPosition.add(position2);
				controlPrimerIntento = 2; //control que se verifica al action.SetListener del boton, para poder ejecutar el metodo "verificarSiSonIguales"
				contadorBotonesPulsados = 0;// reinicio de contador
			}
		}
	}

	public void verificarSiSonIguales() {//checkea si son el mismo color 

		if (list.get(listPosition.get(0)).getBackground().toString()
				.equals(list.get(listPosition.get(1)).getBackground().toString())) {
			//ponemos los botones invisibles
			list.get(listPosition.get(0)).setVisible(false);
			list.get(listPosition.get(1)).setVisible(false);
			
			//eliminamos la posicion en la lista de los botones, ya que no nos es necesario
			int pos1 = listPosition.get(0);
			int pos2 = listPosition.get(1);
			list.remove(pos1);
			list.remove(pos2-1);// borramos 1 poscion ya que pos1 sera la posicion borrada enfrente de pos2
		}
		intento();
		listPosition.clear();
	}

	public void verificar2posiciones() {// modifica el valor de position1 position2
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).isSelected() && position1 == 999) {//saca el valor del primer boton seleccionado
				position1 = i;
			} else if (!list.get(i).isSelected() && position1 != 999 && position2 == 0) {//saca el valor del segundo boton
				position2 = i;
			}
		}
	}

	public void intento() {//al terminar cada intento de pulsar 2 botones, recorremos toda la lista para ponerlos sin color de nuevo

		for (int i = 0; i < list.size(); i++) {
			list.get(i).setSelected(true);
		}
	}

	public void mostrarToggleButton() {//da valor a las posiciones de los toglebuton para que tengan forma de cuadrado final
		int contador = 0;
		int y = 25;
		int x = 80;
		for (int i = 0; i < list.size(); i++) {
			if (contador > 3) {
				contador = 0;
				y = y + 101;
				x = 80;
			}
			list.get(i).setBounds(x, y, 100, 100);
			contador++;
			x = x + 101;
			contentPane.add(list.get(i));
		}

	}

	public Color randomColor() { //escogera un color aleatorio

		switch (numRandom(0, 8)) {
		case 1:
			return Color.black;
		case 2:
			return Color.white;
		case 3:
			return Color.yellow;
		case 4:
			return Color.blue;
		case 5:
			return Color.red;
		case 6:
			return Color.green;
		case 7:
			return Color.pink;
		case 8:
			return Color.cyan;
		default:
			return null;
		}
	}
}
