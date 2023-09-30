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

public class Setting extends JFrame  implements ActionListener{

	private static final long serialVersionUID = 1L;
	URL icon = getClass().getResource("settings.png");
	JButton butt[] = new JButton[3];
	JLabel label[] = new JLabel[5];
	String theme, path, folder, newFolder, buttNames[] = {"Cancel", "Done", "Select"}, lab[] = {"Settings", "Theme", "Subtites", "Thumbnail", "Download Folder:"}, down, thumb;
	JToggleButton themeButton = new JToggleButton();
	JCheckBox checkBox[] = new JCheckBox[2];  
	boolean c=false, t=false;
	JFileChooser chooser;
	
	void setTheme() {
		if(theme.equals("LIGHT")) {
			for(int i=0; i<5; i++) {
				label[i].setBackground(Color.WHITE);
				if(i<2)
					checkBox[i].setBackground(Color.WHITE);
			}
			for(int i=1; i<5; i++)
				label[i].setForeground(Color.BLACK);
			this.getContentPane().setBackground(Color.WHITE);
			themeButton.setText("LIGHT");
			themeButton.setSelected(true);
		}
		else {
			for(int i=0; i<5; i++) {
				label[i].setBackground(Color.BLACK);
				if(i<2)
					checkBox[i].setBackground(Color.BLACK);
			}
			for(int i=1; i<5; i++)
				label[i].setForeground(Color.WHITE);
			this.getContentPane().setBackground(Color.BLACK);
			themeButton.setText("DARK");
			themeButton.setSelected(false);
		}
		
		BufferedReader reader = null;
		String line, read="";
		try {
			reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null)
				read+=line+"\n";
		} catch (Exception e) {}
		
		if(read.contains("Subtitle: TRUE")) {
			checkBox[0].setSelected(true);
			c=true;
		}
		if(read.contains("Thumbnail: TRUE")) {
			checkBox[1].setSelected(true);
			t=true;
		}
		
		newFolder = folder;
		this.setVisible(true);
	}
	
	void close() {
		this.dispose();
		MyFrame.s =null;
	}
	
	Setting(String path, String folder,String theme, int x, int y, boolean W_L){
		this.theme = theme;
		this.path = path;
		this.folder = folder;
		for(int i=0; i<5; i++) {
			if(i<3) {
				butt[i] = new JButton(buttNames[i]);
				butt[i].setBackground(new Color(123,100,255));
				butt[i].setFont(new Font("Ink free", Font.PLAIN, 15));
				butt[i].setFocusable(false);
				butt[i].setVisible(true);
				butt[i].addActionListener(this);
				this.add(butt[i]);
			}
			if(i<2) {
				checkBox[i] = new JCheckBox();
				this.add(checkBox[i]);
			}
			
			label[i] = new JLabel(lab[i]);
			label[i].setOpaque(true);
			label[i].setFont(new Font("Ink free", Font.PLAIN, 16));
			label[i].setVisible(true);
			this.add(label[i]);
		}
		
		themeButton.addActionListener(this);  
		themeButton.setBackground(new Color(123,100,255));
		themeButton.setFocusable(false);
		this.add(themeButton);
		
		label[0].setForeground(Color.RED);
		label[0].setFont(new Font("Ink free", Font.BOLD, 18));
		label[0].setBounds(90, 5, 200, 40);
		label[1].setBounds(20, 50, 100, 30);
		themeButton.setBounds(150, 50, 80, 30);
		themeButton.setFont(new Font("Ink free", Font.BOLD, 12));
		
		label[4].setBounds(20, 90, 150, 30);
		butt[2].setFont(new Font("Ink free", Font.BOLD, 13));
		butt[2].setBounds(150, 90, 80, 30);
		label[2].setBounds(20, 170, 150, 30);
		checkBox[0].setBounds(180, 170, 150, 30);
		label[3].setBounds(20, 130, 100, 30);
		checkBox[1].setBounds(180, 130, 100, 30);
		butt[0].setBounds(35, 210, 80, 30);
		butt[1].setBounds(135, 210, 80, 30);
		
		//Main Frame
		this.setTitle("Settings");
		this.setIconImage(new ImageIcon(icon).getImage());
		this.setResizable(false);
		this.setLayout(null);
		WindowListener exitListener = new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		           close();
		    }
		};
		this.addWindowListener(exitListener);
		
		this.setSize(new Dimension(250,300));
		if(W_L)
			this.setSize(new Dimension(260,300));
		
		this.setLocation(x, y);
		
		setTheme();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==themeButton) {
			themeButton.setText("DARK");
			if(themeButton.isSelected())
				themeButton.setText("LIGHT");
		}
		if(e.getSource()==butt[0]) 
			close();
		if(e.getSource()==butt[1]) {
			try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
	    		String line, read="";
				while ((line = reader.readLine()) != null)
					read+=line+"\n";
				
				if(checkBox[0].isSelected())
					down = "TRUE";
				else
					down = "FALSE";
				if(checkBox[1].isSelected())
					thumb = "TRUE";
				else
					thumb = "FALSE";
				
				if(c) 
					read=read.replace("Subtitle: TRUE", "Subtitle: "+down);
				else
					read=read.replace("Subtitle: FALSE", "Subtitle: "+down);
				if(t) 
					read=read.replace("Thumbnail: TRUE", "Thumbnail: "+thumb);
				else
					read=read.replace("Thumbnail: FALSE", "Thumbnail: "+thumb);
				if(read.contains("LIGHT") && themeButton.getText().equals("DARK"))
					read = read.replace("LIGHT", "DARK");
				if(read.contains("DARK") && themeButton.getText().equals("LIGHT"))
					read = read.replace("DARK", "LIGHT");
				if(!newFolder.equals(folder)) 
					read = read.replace(folder, newFolder+"/");
				FileWriter file = new FileWriter(path);
				BufferedWriter myWriter = new BufferedWriter(file);
				myWriter.write(read);
				myWriter.close();
			} catch (IOException e1) {}
			close();
		}
		if(e.getSource()==butt[2]) {
			chooser = new JFileChooser(); 
		    chooser.setCurrentDirectory(new java.io.File(folder));
		    chooser.setDialogTitle("Choose Video Download Path");
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    chooser.setAcceptAllFileFilterUsed(false);  
		    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		    	newFolder = ""+chooser.getSelectedFile();
		}
	}
}