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

public class Frame extends JFrame {

	private JPanel contentPane;
	private List<JToggleButton> list;
	private List<Color> listColors = new ArrayList<Color>();
	private List<Integer> listPosition = new ArrayList<Integer>();
	private Hashtable htable;
	private int position1, position2;
	private int contadorTgBtnSeleccionado;
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
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
			JToggleButton tgbtn = new JToggleButton("");
		
			boolean colorNoRepetido = true;
			
			while(colorNoRepetido) {
				color = randomColor();
				if(controlColorRepetido(color)) {
					colorNoRepetido = false;
				}
			}
			tgbtn.setSelected(true);
			tgbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {		
				
					mostrar2unicosToggleButton();
				}
			});
			listColors.add(color);
			tgbtn.setBackground(color);
			list.add(tgbtn);
		}
		return list;
	}

	public void mostrarToggleButton() {
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

	public Color randomColor() {                                                                                                                                                                                                                                                                                                                                                                                                                                     
		
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
	
	private boolean controlColorRepetido(Color randomColor) {
		int contadorColorRepetido = 0;
		for (int i = 0; i < listColors.size(); i++) {
			if(listColors.get(i) == (randomColor)) {
				contadorColorRepetido++;
			}
		}
		if(contadorColorRepetido >=2) {// color repetido mas de 2 veces, vamos a seguir buscando
			return false;
		}return true;
	}
	
	
	public static int numRandom(int min, int max) { // metodo devuelve num random
		return (int) (Math.random() * (max - min) + min);
	}
	
	public void mostrar2unicosToggleButton() { //FUNCIONALIDAD MOSTRAR 2 Cartas A la vez
		contadorTgBtnSeleccionado = 0;
		position1 = 999;
		position2= 0;
		for (int i = 0; i < list.size(); i++) {
			
			if(!list.get(i).isSelected()) {//comprueba la lista i mira cuantos han sido pulsados
				contadorTgBtnSeleccionado++;//contador de botones pulsados
			}
			if(contadorTgBtnSeleccionado == 2) {
				verificar2posiciones();			
				listPosition.add(position1);
				listPosition.add(position2);
			}
			if(contadorTgBtnSeleccionado == 3) {
				//verificar2posiciones();
				
				unSelectLastPosition();
				mostrarAllSelects();
				verificarSiSonIguales();
				//intento(); //voltea todas las cartas de la lista
				contadorTgBtnSeleccionado=0;//reinicio de contador
			}
		}
	}

	
	public void verificarSiSonIguales() {
	
		if(list.get(listPosition.get(0)).getBackground().toString().equals(list.get(listPosition.get(1)).getBackground().toString())){
		
			list.get(listPosition.get(0)).setVisible(false);
			list.get(listPosition.get(1)).setVisible(false);
			list.remove(listPosition.get(0));
			list.remove(listPosition.get(1)-1);//borramos 1 poscion ya que localizadoTgBtn1 sera la posicion borrada enfrente de localizadoTgBtn2
		}else {
			intento(listPosition.get(0), listPosition.get(1));
		}
		listPosition.clear();
	}
	public void verificar2posiciones() {//modifica el valor de position1 position2 
		for (int i = 0; i < list.size(); i++) {
			if(!list.get(i).isSelected() && position1 == 999) {
				position1 = i;
			}else if(!list.get(i).isSelected() && position1 != 999 && position2 ==0) {
				position2 = i;
			}
		}
	}
	public void unSelectLastPosition() {
		for (int i = 0; i < list.size(); i++) {
			if(!list.get(i).isSelected()) {//si es false
				if(i != (listPosition.get(0)) && i != listPosition.get(1)) {
					list.get(i).setSelected(true);
				}
			}
		}
	}
	
	public void intento(int num1, int num2) {		
	
		list.get(num1).setSelected(true);
		list.get(num2).setSelected(true);
	}
	public void mostrarAllSelects() {
		////mostrar Select
		int count = 0;
		System.out.println("\n");
		for (int j = 0; j < list.size(); j++) {
			
			if(count == 4) {
				System.out.println("\n");
				count = 0;
			}
			if(list.get(j).isSelected()) {
				System.out.print("\tO");
			}else {
				System.out.print("\tX");
			}
			//System.out.print("\t" + j + list.get(j).isSelected());
			count++;
		
		}	
		System.out.println("\n");
		///mostrar select
	}
}
