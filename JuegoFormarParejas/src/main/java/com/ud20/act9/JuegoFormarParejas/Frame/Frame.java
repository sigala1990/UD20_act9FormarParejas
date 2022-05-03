package com.ud20.act9.JuegoFormarParejas.Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;

public class Frame extends JFrame {

	private JPanel contentPane;

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
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		tglbtnNewToggleButton.setBounds(83, 25, 100, 100);
		contentPane.add(tglbtnNewToggleButton);
		
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1.setBounds(184, 25, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1);
		
		JToggleButton tglbtnNewToggleButton_1_1 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_1.setBounds(286, 25, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_1);
		
		JToggleButton tglbtnNewToggleButton_1_2 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_2.setBounds(388, 25, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_2);
		
		JToggleButton tglbtnNewToggleButton_1_3 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_3.setBounds(83, 127, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_3);
		
		JToggleButton tglbtnNewToggleButton_1_4 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_4.setBounds(184, 127, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_4);
		
		JToggleButton tglbtnNewToggleButton_1_5 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_5.setBounds(286, 127, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_5);
		
		JToggleButton tglbtnNewToggleButton_1_6 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_6.setBounds(388, 127, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_6);
		
		JToggleButton tglbtnNewToggleButton_1_6_1 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_6_1.setBounds(83, 229, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_6_1);
		
		JToggleButton tglbtnNewToggleButton_1_6_2 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_6_2.setBounds(184, 229, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_6_2);
		
		JToggleButton tglbtnNewToggleButton_1_6_3 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_6_3.setBounds(286, 229, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_6_3);
		
		JToggleButton tglbtnNewToggleButton_1_6_4 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_6_4.setBounds(388, 229, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_6_4);
		
		JToggleButton tglbtnNewToggleButton_1_6_5 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_6_5.setBounds(83, 331, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_6_5);
		
		JToggleButton tglbtnNewToggleButton_1_6_6 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_6_6.setBounds(184, 331, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_6_6);
		
		JToggleButton tglbtnNewToggleButton_1_6_7 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_6_7.setBounds(286, 331, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_6_7);
		
		JToggleButton tglbtnNewToggleButton_1_6_8 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1_6_8.setBounds(388, 331, 100, 100);
		contentPane.add(tglbtnNewToggleButton_1_6_8);
	}
}
