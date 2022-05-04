package com.ud20.act9.JuegoFormarParejas.Frame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	private JPanel contentPane;
	private List<JToggleButton> list_toggle;
	// private List<Color> listColors = new ArrayList<Color>();
	private List<Integer> listPosition = new ArrayList<Integer>();
	private int position1, position2;
	private int contadorBotonesPulsados;
	private int controlPrimerIntento = 0;

	private List<ImageIcon> list_image_put = new ArrayList<ImageIcon>();
	Hashtable<ImageIcon, Integer> hash_imagenes_puestas = hashImages();
	private List<Integer> list_image_inserted = new ArrayList<Integer>();

	private static ImageIcon arduino;
	private static ImageIcon c;
	private static ImageIcon c_plus;
	private static ImageIcon java;
	private static ImageIcon js;
	private static ImageIcon tys;
	private static ImageIcon ktlin;
	private static ImageIcon pyth;

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

		list_toggle = CrearListaTB(); // crea 16 tooglebuttons
		mostrarToggleButton();

	}

	public static Hashtable<ImageIcon, Integer> hashImages() {
		Hashtable<ImageIcon, Integer> hash_imagenes = new Hashtable<ImageIcon, Integer>();

		// Creamos los iconos
		arduino = new ImageIcon(Frame.class.getResource("../images/arduino.png"), "arduino");
		c = new ImageIcon(Frame.class.getResource("../images/c.png"), "c");
		c_plus = new ImageIcon(Frame.class.getResource("../images/c++.png"), "c_plus");
		java = new ImageIcon(Frame.class.getResource("../images/java.png"), "java");
		js = new ImageIcon(Frame.class.getResource("../images/javascript.png"), "js");
		ktlin = new ImageIcon(Frame.class.getResource("../images/kotlin.png"), "ktlin");
		pyth = new ImageIcon(Frame.class.getResource("../images/python.png"), "pyth");
		tys = new ImageIcon(Frame.class.getResource("../images/tpescript.png"), "tys");

		// Añadimos los productos a la Hashtable
		hash_imagenes.put(arduino, 1);
		hash_imagenes.put(c, 2);
		hash_imagenes.put(c_plus, 3);
		hash_imagenes.put(java, 4);
		hash_imagenes.put(js, 5);
		hash_imagenes.put(ktlin, 6);
		hash_imagenes.put(pyth, 7);
		hash_imagenes.put(tys, 8);

		return hash_imagenes;
	}

	public List<JToggleButton> CrearListaTB() {
		List<JToggleButton> list_toggle_buttons = new ArrayList<JToggleButton>();
		ImageIcon imagen = null;
		for (int i = 0; i < 16; i++) {
			JToggleButton tgbtn = new JToggleButton();
			tgbtn.setName("" + i);
			tgbtn.setSelectedIcon(new ImageIcon(Frame.class.getResource("../images/esplai.png")));
			boolean imagenNoRepetido = true;
			while (imagenNoRepetido) { // while que repetira randomColor hasta que coja un color que no este repetido
										// // mas de 2 veces
				imagen = randomImage();
				if (controlImagenRepetida(imagen)) { // Devuelve: true -> color no repetido; false -> repetido
					colorPuesto(imagen);
					imagenNoRepetido = false;
				}
			}
			tgbtn.setSelected(true); // gira cada carta(togglebuttons)creada del reves
			tgbtn.addActionListener(new ActionListener() {// accion que se realiza al pulsar el boton
				public void actionPerformed(ActionEvent arg0) {
					if (controlPrimerIntento == 2) {// solo se debe ejecutar cuando tengamos 2 togglebuttons
													// seleccionados
						verificarSiSonIguales();
						controlPrimerIntento = 0;
					}
					pulsando2ToggleButon();// metodo que pasa todos los togglebuttons al ser pulsado, donde se
											// verificara cuantos botones estan pulsados y se comprueba su color
				}
			});
			list_image_put.add(imagen);
			tgbtn.setIcon(imagen);
			list_toggle_buttons.add(tgbtn);// creacion del boton realizada dentro de una posicion de la lista llamada
											// "list"
		}
		return list_toggle_buttons;
	}

	private void colorPuesto(ImageIcon image) {

		if (image.getDescription().equals("arduino")) {
			list_image_inserted.add(1);
		} else if (image.getDescription().equals("c")) {
			list_image_inserted.add(2);
		} else if (image.getDescription().equals("c_plus")) {
			list_image_inserted.add(3);
		} else if (image.getDescription().equals("java")) {
			list_image_inserted.add(4);
		} else if (image.getDescription().equals("js")) {
			list_image_inserted.add(5);
		} else if (image.getDescription().equals("ktlin")) {
			list_image_inserted.add(6);
		} else if (image.getDescription().equals("pyth")) {
			list_image_inserted.add(7);
		} else if (image.getDescription().equals("tys")) {
			list_image_inserted.add(8);
		}

	}

	private boolean controlImagenRepetida(ImageIcon icon) {// metodo que devuelve boolean dependiendo si se ha usado la
															// imagenRandom

		int contadorImageRepetida = 0;

		for (int i = 0; i < list_image_put.size(); i++) {
			if (hash_imagenes_puestas.get(icon) == list_image_inserted.get(i)) {
				contadorImageRepetida++;
			}
		}

		if (contadorImageRepetida >= 2) {// imagen repetido mas de 2 veces, vamos a seguir buscando
			return false;
		}
		return true;
	}

	public static int numRandom(int min, int max) { // metodo devuelve num random
		return (int) (Math.random() * (max - min) + min);
	}

	public void pulsando2ToggleButon() { // FUNCIONALIDAD MOSTRAR 2 Cartas A la vez. Verificamos si se ha pulsado 1 o 2
											// toggleButtons.
		contadorBotonesPulsados = 0;
		position1 = 999;
		position2 = 0;
		for (int i = 0; i < list_toggle.size(); i++) {

			if (!list_toggle.get(i).isSelected()) {// comprueba la lista i mira cuantos han sido pulsados
				contadorBotonesPulsados++;// contador de botones pulsados
			}
			if (contadorBotonesPulsados == 2) {// cuando han sido pulsado2 botones procede a verificarlos y guardar
												// cuales han sido los pulsados
				verificar2posiciones(); // guarda posiciones pulsadas
				listPosition.add(position1);// Añadimos la posicion de la lista de botones en la que se encuentra el
											// primer boton pulsado
				listPosition.add(position2);// Añadimos la posicion de la lista de botones en la que se encuentra el
											// segundo boton pulsado
				controlPrimerIntento = 2; // control que se verifica al action.SetListener del boton, para poder
											// ejecutar el metodo "verificarSiSonIguales"
				contadorBotonesPulsados = 0;// reinicio de contador
			}
		}
	}

	public void verificarSiSonIguales() {// checkea si son el mismo color

		if (list_image_inserted.get(position1) == list_image_inserted.get(position2)) {
			list_toggle.get(listPosition.get(0)).setVisible(false);
			list_toggle.get(listPosition.get(1)).setVisible(false);

			// eliminamos la posicion en la lista de los botones, ya que no nos es necesario
			int pos1 = listPosition.get(0);
			int pos2 = listPosition.get(1);
			list_toggle.remove(pos1);
			list_image_inserted.remove(pos1);
			list_toggle.remove(pos2 - 1);// borramos 1 poscion ya que pos1 sera la posicion borrada enfrente de pos2
			list_image_inserted.remove(pos2 - 1);
		}

		intento();
		listPosition.clear();
	}

	public void verificar2posiciones() {// modifica el valor de position1 position2
		for (int i = 0; i < list_toggle.size(); i++) {
			if (!list_toggle.get(i).isSelected() && position1 == 999) {// saca el valor del primer boton seleccionado
				position1 = i;
			} else if (!list_toggle.get(i).isSelected() && position1 != 999 && position2 == 0) {// saca el valor del
																								// segundo boton
				position2 = i;
			}
		}
	}

	public void intento() {// al terminar cada intento de pulsar 2 botones, recorremos toda la lista para
							// ponerlos sin color de nuevo

		for (int i = 0; i < list_toggle.size(); i++) {
			list_toggle.get(i).setSelected(true); // Ponemos todos los botones que quedan girados (color por defecot el
													// azul)
		}
	}

	public void mostrarToggleButton() {// da valor a las posiciones de los toglebuton para que tengan forma de cuadrado
										// final
		int contador = 0;
		int y = 25;
		int x = 80;
		for (int i = 0; i < list_toggle.size(); i++) {
			if (contador > 3) {
				contador = 0;
				y = y + 101; // Añadimos los toggle en la siguiente fila
				x = 80;
			}
			list_toggle.get(i).setBounds(x, y, 100, 100);
			contador++;
			x = x + 101; // Añadimos el siguiente toggle al lado del otro en horizontal separao 1 unidad
			contentPane.add(list_toggle.get(i));
		}

	}

	public ImageIcon randomImage() { // escogera una imagen aleatoria

		int valor = numRandom(1, 9);

		switch (valor) {
		case 1:
			return arduino;
		case 2:
			return c;
		case 3:
			return c_plus;
		case 4:
			return java;
		case 5:
			return js;
		case 6:
			return ktlin;
		case 7:
			return pyth;
		case 8:
			return tys;
		default:
			return null;
		}
	}

}
