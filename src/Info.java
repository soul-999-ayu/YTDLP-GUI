/*
* Copyright (c) 2022-2023 DeadSOUL-Studios (https://github.com/deadsoul-studios)
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public
* License as published by the Free Software Foundation; either
* version 2 of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* General Public License for more details.
*
* You should have received a copy of the GNU General Public
* License along with this program; if not, write to the
* Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
* Boston, MA 02110-1301 USA
*
* Authored by: Ayush "DeadSOUL" <ayushkumar274549@gmail.com>
*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Info extends JFrame  implements ActionListener{

	private static final long serialVersionUID = 1L;
	URL icon = getClass().getResource("information.png");
	JButton butt[] = new JButton[2];
	JLabel label[] = new JLabel[4];
	String path;
	
	void setTheme() {
		String line, read="";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null){
				read+=line;
			}
		} catch (Exception e) {}
		
		if(read.contains("LIGHT")) {
			for(int i=0; i<4; i++) 
				label[i].setBackground(Color.WHITE);
			for(int i=1; i<4; i++)
				label[i].setForeground(Color.BLACK);
			this.getContentPane().setBackground(Color.WHITE);
		}
		else {
			for(int i=0; i<4; i++) 
				label[i].setBackground(Color.BLACK);
			for(int i=1; i<4; i++)
				label[i].setForeground(Color.WHITE);
			this.getContentPane().setBackground(Color.BLACK);
		}
		
		this.setVisible(true);
	}
	
	void close() {
		this.dispose();
		MyFrame.i =null;
	}
	
	
	Info(String path, int x, int y, boolean W_L){
		this.path = path;
		String buttNames[] = {"Back", "GitHub"}, lab[] = {"About us", "", "DeadSOUL-Studios", "<html>DeadSOUL Studios is a set-up by Ayush and it provides software that are solely developed by Ayush.</html>"};
		for(int i=0; i<4; i++) {
			if(i<2) {
				butt[i] = new JButton(buttNames[i]);
				butt[i].setBackground(new Color(123,100,255));
				butt[i].setFont(new Font("Ink free", Font.PLAIN, 15));
				butt[i].setFocusable(false);
				butt[i].setVisible(true);
				butt[i].addActionListener(this);
				this.add(butt[i]);
			}
			
			label[i] = new JLabel(lab[i]);
			label[i].setOpaque(true);
			label[i].setFont(new Font("Ink free", Font.BOLD, 16));
			label[i].setVisible(true);
			this.add(label[i]);
		}
		
		label[2].setFont(new Font("Ink free", Font.BOLD, 16));
		label[3].setFont(new Font("Ink free", Font.PLAIN, 14));
		
		label[0].setForeground(Color.RED);
		label[2].setForeground(Color.RED);
		label[0].setFont(new Font("Ink free", Font.BOLD, 18));
		label[0].setBounds(95, 5, 200, 30);
		label[1].setBounds(20, 35, 60, 60);
		label[2].setBounds(90, 45, 200, 30);
		label[3].setBounds(20, 100, 210, 60);
		butt[0].setBounds(35, 175, 80, 30);
		butt[1].setBounds(135, 175, 80, 30);
		
		ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("logo.png")).getScaledInstance(label[1].getWidth(), label[1].getHeight(), Image.SCALE_SMOOTH));
		label[1].setIcon(imageIcon);
		
		//Main Frame
		this.setTitle("Information");
		this.setIconImage(new ImageIcon(icon).getImage());
		this.setResizable(false);
		this.setLayout(null);
		this.setSize(new Dimension(250,250));
		if(W_L)
			this.setSize(new Dimension(260,250));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocation(x, y);
		
		setTheme();
		
		this.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		        setTheme();
		    }
		});
		
		WindowListener exitListener = new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		           close();
		    }
		};
		this.addWindowListener(exitListener);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==butt[0]) 
			close();
		if(e.getSource()==butt[1]) {
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/deadsoul-studios"));
			} catch (IOException | URISyntaxException e1) {}
		}
	}
}
