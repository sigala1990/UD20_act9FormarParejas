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

public class Frame extends JFrame {

	private JPanel contentPane;
	private List<JToggleButton> list;
	private List<Color> listColors = new ArrayList<Color>();
	private Hashtable htable;
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
}
