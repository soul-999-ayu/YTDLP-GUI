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
import java.util.*;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	JTextField urlField;
	JButton butt[] = new JButton[7];
	JLabel loadInfo[] = new JLabel[8];
	JPanel menuPanel = new JPanel(null);
	int initial=0;
	String path = "/home/"+System.getProperty("user.name")+"/.script.sh" ,Data="", result, folder, theme, Quality[] = {"144p", "240p", "360p", "480p", "720p", "1080p", "1440p", "2160p"},  buttNames[] = {"S", "Download", "Refresh", "", "", "Cancel", "Done"},
			sh = "if [ \"$2\" = \"Quality\" ];\n"
					+ "then\n"
					+ "	yt-dlp -F $1 --force-ipv4\n"
					+ "elif [ \"$2\" = \"Download\" ];\n"
					+ "then\n"
					+ " yt-dlp -f 'bv*[height='$3']+ba' $1 -o $4\"%(title)s\"\"-$3\" -S ext:mp4:m4a --force-ipv4\n"
					+ "elif [ \"$2\" = \"Thumbnail\" ];\n"
					+ "then\n"
					+ "	yt-dlp -f 'bv*[height='$3']+ba' $1 -o $4\"%(title)s\"\"-$3\" --write-thumbnail -S ext:mp4:m4a --force-ipv4\n"
					+ "elif [ \"$2\" = \"Subtitle\" ];\n"
					+ "then\n"
					+ "	yt-dlp -f 'bv*[height='$3']+ba' $1 -S ext:mp4:m4a -o $4\"%(title)s\"\"-$3\" --write-subs --force-ipv4\n"
					+ "elif [ \"$2\" = \"SubWThumb\" ];\n"
					+ "then\n"
					+ "	yt-dlp -f 'bv*[height='$3']+ba' $1 -o $4\"%(title)s\"\"-$3\" --write-thumbnail --write-subs -S ext:mp4:m4a --force-ipv4\n"
					+ "elif [ \"$2\" = \"Title\" ];\n"
					+ "then\n"
					+ "	yt-dlp --print \"%(title)s\" $1 --force-ipv4\n"
					+ "fi";
	JComboBox<Object> qualitySelector;
	URL imageURL, icon = getClass().getResource("icon.jpg"), setting;
	boolean internet = false, W_L, sub, thumb;
	File f;
	static Setting s = null;
	static Info i = null;
	
	void script() {
		if(!W_L) {
			f = new File(path);
			if(f.exists() && !f.isDirectory())
				System.out.println(".sh file exists.");
			else {
				try {
					FileWriter file = new FileWriter(path);
					BufferedWriter myWriter = new BufferedWriter(file);
					myWriter.write(sh);
					myWriter.close();
					Runtime.getRuntime().exec("chmod u+x "+path);
				} catch (IOException e) {}
			}
			f = new File("/home/"+System.getProperty("user.name")+"/.settings.txt");
		}
		else
			f = new File("C:\\Users\\"+System.getProperty("user.name")+"\\.settings.txt");
		if(f.exists() && !f.isDirectory()) 
			System.out.println("config file exists.");
		else {
			try {
				FileWriter file = new FileWriter(f.getAbsolutePath());
				BufferedWriter myWriter = new BufferedWriter(file);
				myWriter.write("//settings for YTDLP-GUI:\n"
						+ "Folder: "+System.getProperty("user.home")+"/Downloads/YouTube/"+"\n"
						+ "Subtitle: FALSE\n"
						+ "Thumbnail: FALSE\n"
						+ "Theme: LIGHT\n"
						+ "Location: CENTER");
				myWriter.close();
			} catch (IOException e) {}
		}
	}
	
	void dependencies() {
		boolean NA = false;
		String notInstalled = "";
			try {
				Runtime.getRuntime().exec("yt-dlp");
			} catch (IOException e1) {
				NA = true;
				notInstalled += " ytdlp";
			}
			try {
				Runtime.getRuntime().exec("ffmpeg");
			} catch (IOException e1) {
				NA = true;
				notInstalled += " ffmpeg";
			}
		
		if(NA) {
			for(int i=0; i<2; i++) {
				butt[i].setVisible(false);
				loadInfo[i].setVisible(false);
			}
			loadInfo[2].setVisible(true);
			urlField.setVisible(false);
			loadInfo[2].setBounds(20, 55, 280, 50);
			butt[2].setText("Restart");
			butt[2].setVisible(true);
			if(notInstalled.contains("ytdlp") && !notInstalled.contains("ffmpeg"))
				loadInfo[2].setText("YT_DLP is not properly installed.");
			else if(!notInstalled.contains("ytdlp") && notInstalled.contains("ffmpeg"))
				loadInfo[2].setText("FFMPEG is not properly installed.");
			else 
				loadInfo[2].setText("No dependencies are installed.");
		}
		else 
			loadInfo[2].setText("");
	}
	
	void internetCheck() {
		if(loadInfo[2].getText().equals("YT_DLP is not properly installed.") || loadInfo[2].getText().equals("FFMPEG is not properly installed.") || loadInfo[2].getText().equals("No dependencies are installed.")) 
			dependencies();
		else {
			for(int i=0; i<2; i++) {
				try {
					URL url = new URL("https://www.google.com");
					URLConnection connection = url.openConnection();
					connection.connect();
					butt[2].setVisible(false);
					urlField.setVisible(true);
					butt[0].setVisible(true);				
					loadInfo[2].setText("Select Quality:");
					loadInfo[2].setBounds(20, 335, 130, 35);
					internet = true;
				}	
				catch (Exception e) {
				loadInfo[2].setVisible(true);
				urlField.setVisible(false);
				butt[i].setVisible(false);
				loadInfo[i].setVisible(false);
				loadInfo[2].setBounds(20, 55, 280, 50);
				loadInfo[2].setText("Oops!, It looks like you're offline...");
				butt[2].setVisible(true);
				qualitySelector.setVisible(false);
				loadInfo[3].setVisible(false);
				internet = false;
				}
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void search() {
		result="";
		loadInfo[3].setText("");
		if((urlField.getText().contains("youtube.com") || urlField.getText().contains("youtu.be")) && !urlField.getText().contains("playlist")) {
			loadInfo[0].setIcon(null);
			loadInfo[0].setText("             Looking for the resource.");
			loadInfo[0].setBounds(20, 90, 280, 190);
			loadInfo[0].setVisible(true);
			loadInfo[1].setBounds(20, 290, 280, 35);
			loadInfo[1].setVisible(true);
			loadInfo[1].setText("Searching...");
			butt[1].setEnabled(false);
			loadInfo[2].setVisible(true);
			qualitySelector.setVisible(true);
			super.paintComponents(getGraphics());
			
			ProcessBuilder pb = null;
			if(!W_L)
				pb = new ProcessBuilder(new File(path).getAbsolutePath(), urlField.getText(), "Title");
			else
				pb = new ProcessBuilder("cmd.exe", "/c", "yt-dlp", "--print", "'%(title)s'", urlField.getText(), " --force-ipv4");
				
			Process p = null;
			try {
				p = pb.start();
			} catch (IOException e1) {}
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			try {
				while((line = reader.readLine()) != null)
					result += line;
			} catch (IOException e) {
			}
			result=result.replace("null", "");
			System.out.println(result);
			if(!result.equals("")) {
				loadInfo[1].setText(result);
				this.setTitle(result);
				butt[1].setEnabled(true);
				try {
					if(urlField.getText().contains("youtu.be") && !urlField.getText().contains("shorts"))
						imageURL = new URL("https://img.youtube.com/vi/"+urlField.getText().replace("https://youtu.be/", "")+"/hqdefault.jpg");
					else if(urlField.getText().contains("youtube") && !urlField.getText().contains("shorts"))
						imageURL = new URL("https://img.youtube.com/vi/"+urlField.getText().replace("https://www.youtube.com/watch?v=", "").substring(0 , 11)+"/hqdefault.jpg");
					else if(urlField.getText().contains("shorts"))
						imageURL = new URL("https://img.youtube.com/vi/"+urlField.getText().replace("https://www.youtube.com/shorts/", "").substring(0 , 11)+"/hqdefault.jpg");
					ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(imageURL).getScaledInstance(loadInfo[0].getWidth(), loadInfo[0].getHeight(), Image.SCALE_SMOOTH));
					loadInfo[0].setText("");
					loadInfo[0].setIcon(imageIcon);
				} catch (MalformedURLException e1) {}
			}
			else {
				loadInfo[0].setIcon(null);
				loadInfo[0].setText("             Failed to load thumbnail.");
				loadInfo[1].setText("The URL seems to be incorrect.");
				butt[1].setEnabled(false);
				this.setTitle("SOUL-Tube");
			}
			loadInfo[2].setVisible(true);
		    loadInfo[2].setBounds(20, 335, 130, 35);
			loadInfo[2].setText("Select Quality:");
		    butt[1].setVisible(true);
		    Data = "";
		    ProcessBuilder process = null;
			if(!W_L)
		    	process = new ProcessBuilder(new File(path).getAbsolutePath(), urlField.getText(), "Quality");
			else
				process = new ProcessBuilder("cmd.exe", "/c", "yt-dlp", "-F", urlField.getText(), " --force-ipv4");
		    p = null;
		    try {
		    	p = process.start();
		    } catch (IOException e2) {}
		    reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    line = null;
		    try {
		    	while ((line = reader.readLine()) != null) {
		    		Data+=line;
		    		Data.trim();
		    	}
		    } catch (IOException e3) {}
		    
		    if(!Data.isEmpty()) {
		    	String[] availableQuality = new String[8];
		    	for(int i1=0; i1<8; i1++) {
		    		if(Data.contains(Quality[i1]))
		    			availableQuality[i1] = Quality[i1];
		    	}
		    	qualitySelector.setModel(new javax.swing.DefaultComboBoxModel(Arrays.stream(availableQuality).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])));
		    }
		}
		else {
			loadInfo[0].setBounds(20, 90, 280, 50);
			loadInfo[0].setIcon(null);
			loadInfo[0].setVisible(true);
			loadInfo[1].setVisible(false);
			loadInfo[2].setVisible(false);
			qualitySelector.setVisible(false);
			butt[1].setVisible(false);
			if(urlField.getText().contains("playlist"))
				loadInfo[0].setText("Playlists are not Supported yet...");
			else
				loadInfo[0].setText("This URL is not Supported yet...");	
		}
	}
	
	void setTheme() {
		if(initial<1 || s==null) {
			try{
				ImageIcon imageIcon=null, imageIcon2=null;
				BufferedReader reader = new BufferedReader(new FileReader(f.getAbsolutePath()));
				String line, read="";
				while ((line = reader.readLine()) != null)
					read+=line;
				if(read.contains("Theme: LIGHT")) {
					theme = "LIGHT";
					this.getContentPane().setBackground(Color.WHITE);
					menuPanel.setBackground(Color.WHITE);
					imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("settings.png")).getScaledInstance(butt[4].getWidth(), butt[4].getHeight(), Image.SCALE_SMOOTH));
					imageIcon2 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("information.png")).getScaledInstance(butt[3].getWidth(), butt[3].getHeight(), Image.SCALE_SMOOTH));
					loadInfo[3].setBackground(Color.WHITE);
					loadInfo[4].setForeground(Color.BLACK);
					loadInfo[4].setBackground(Color.WHITE);
					loadInfo[5].setBackground(Color.WHITE);
					butt[3].setBackground(Color.WHITE);
				}
				else {
					theme = "DARK";
					this.getContentPane().setBackground(Color.BLACK);
					menuPanel.setBackground(Color.BLACK);
					imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("settings-dark.jpg")).getScaledInstance(butt[4].getWidth(), butt[4].getHeight(), Image.SCALE_SMOOTH));
					imageIcon2 = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("information-dark.png")).getScaledInstance(butt[3].getWidth(), butt[3].getHeight(), Image.SCALE_SMOOTH));
					loadInfo[3].setBackground(Color.BLACK);
					loadInfo[4].setForeground(Color.WHITE);
					loadInfo[4].setBackground(Color.BLACK);
					loadInfo[5].setBackground(Color.BLACK);
					butt[3].setBackground(Color.BLACK);
				}
				if(read.contains("Location: CENTER")) 
					this.setLocationRelativeTo(null);
				else {
					String x = read.substring(read.indexOf("X-")+2,read.indexOf("Y-")-1), y = read.substring(read.indexOf("Y-")+2);
					this.setLocation(Integer.parseInt(x), Integer.parseInt(y));
				}
				folder = read.substring(read.indexOf("Folder: ")+8,read.indexOf("Subtitle"));
				if(read.contains("Subtitle: TRUE"))
					sub=true;
				else
					sub=false;
				if(read.contains("Thumbnail: TRUE"))
					thumb=true;
				else
					thumb=false;
				
				butt[4].setIcon(imageIcon);
				butt[3].setIcon(imageIcon2);
				reader.close();
				this.setVisible(true);
			}
			catch (Exception e1){}
			initial++;
		}
	}
	
	void saveLocation() {
		try {
    		BufferedReader reader = new BufferedReader(new FileReader(f.getAbsolutePath()));
			String line, read="", replace;
			while ((line = reader.readLine()) != null)
				read+=line+"\n";
			if(read.contains("DARK"))
				replace = read.substring(read.indexOf("DARK")+4);
			else
				replace = read.substring(read.indexOf("LIGHT")+5);
			read=read.replace(replace, "\nLocation: X-"+this.getX()+" Y-"+this.getY());
			FileWriter file = new FileWriter(f.getAbsolutePath());
			BufferedWriter myWriter = new BufferedWriter(file);
			myWriter.write(read);
			myWriter.close();
		} catch (IOException e) {}
	}
	
	MyFrame(boolean W_L){
		this.W_L=W_L;
        script();
        
		urlField = new JTextField("Enter Url Here");
		urlField.addKeyListener(this);
		urlField.setFont(new Font("Ink free", Font.PLAIN, 15));
		urlField.setBounds(20, 40, 240, 35);
		

		for(int i=0; i<8; i++) {
			loadInfo[i] = new JLabel();
			loadInfo[i].setOpaque(true);
			loadInfo[i].setBackground(Color.LIGHT_GRAY);
			loadInfo[i].setForeground(Color.RED);
			loadInfo[i].setFont(new Font("Ink free", Font.PLAIN, 15));
			loadInfo[i].setVisible(false);
			if(i<5)
				this.add(loadInfo[i]);
			if(i<7) {
				butt[i] = new JButton(buttNames[i]);
				butt[i].setBackground(new Color(123,100,255));
				butt[i].setFont(new Font("Ink free", Font.PLAIN, 15));
				butt[i].setFocusable(false);
				butt[i].setVisible(true);
				butt[i].addActionListener(this);
				if(i<3)
					this.add(butt[i]);
			}
		}
		
		loadInfo[5].setText("SOUL-Tube");
		loadInfo[5].setFont(new Font("Ink free", Font.BOLD, 22));
		loadInfo[5].setBounds(57, 0, 200, 25);
		loadInfo[5].setVisible(true);
		butt[4].setBackground(Color.WHITE);
		butt[4].setBounds(255, 0, 20, 20);
		butt[4].setBorder(null);
		butt[3].setBounds(225, 0, 19, 19);
		butt[3].setBorder(null);
		menuPanel.setBounds(20, 10, 285, 25);
		menuPanel.add(butt[4]);
		menuPanel.add(butt[3]);
		menuPanel.add(loadInfo[5]);
		this.add(menuPanel);
		
		loadInfo[3].setBounds(20,420, 380, 40);
		loadInfo[3].setForeground(Color.RED);
		loadInfo[3].setVisible(true);
		loadInfo[4].setBounds(20,449, 380, 40);
		loadInfo[4].setFont(new Font("Ink free", Font.PLAIN, 13));
		loadInfo[4].setText("        Copyright © 2023 DeadSOUL-Studios");
		loadInfo[4].setVisible(true);
		butt[1].setVisible(false);
		butt[2].setVisible(false);
		qualitySelector = new JComboBox<Object>(Quality);
		qualitySelector.setSelectedItem(Quality[0]);
		qualitySelector.addActionListener(this);
		qualitySelector.setVisible(false);
		qualitySelector.setFont(new Font("Ink free", Font.PLAIN, 15));

		butt[0].setBounds(260, 40, 45, 35);
		butt[1].setBounds(20, 380, 280, 35);
		butt[2].setBounds(90, 125, 140, 35);
		qualitySelector.setBounds(170, 335, 130, 35);
		
		butt[0].setToolTipText("Search Button.");
		butt[1].setToolTipText("Download video from given link.");
		butt[2].setToolTipText("Recheck if internet connection is available or not.");
		qualitySelector.setToolTipText("Select video quality.");
		
		//Main Frame
		this.setTitle("SOUL-Tube");
		this.setIconImage(new ImageIcon(icon).getImage());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setSize(new Dimension(335, 520));
		if(!W_L)
			this.setSize(new Dimension(325, 520));
		this.add(urlField);
		this.add(qualitySelector);
		this.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		    	saveLocation();
		        setTheme();
		    }
		});
		
		WindowListener exitListener = new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0)
		           System.exit(0);
		    }
		};
		this.addWindowListener(exitListener);
		
		setTheme();
		dependencies();
		
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
		    @Override
		    public void run()
		    {
		    	saveLocation();
		    }
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		internetCheck();
			//Search Button
			if(e.getSource()==butt[0] && internet)
				search();
			//Download Button
			if(e.getSource()==butt[1] && internet) {
				loadInfo[3].setVisible(true);
				char what = 'v';
				butt[1].setEnabled(false);
				ProcessBuilder pb = null;
				if(!W_L) {
					if(!thumb && !sub)
						pb = new ProcessBuilder(new File(path).getAbsolutePath(), urlField.getText(), "Download", String.valueOf(qualitySelector.getSelectedItem()).replaceFirst("p",""), folder);
					else if(thumb && !sub)
						pb = new ProcessBuilder(new File(path).getAbsolutePath(), urlField.getText(), "Thumbnail", String.valueOf(qualitySelector.getSelectedItem()).replaceFirst("p",""), folder);
					else if(!thumb && sub)
						pb = new ProcessBuilder(new File(path).getAbsolutePath(), urlField.getText(), "Subtitle", String.valueOf(qualitySelector.getSelectedItem()).replaceFirst("p",""), folder);
					else
						pb = new ProcessBuilder(new File(path).getAbsolutePath(), urlField.getText(), "SubWThumb", String.valueOf(qualitySelector.getSelectedItem()).replaceFirst("p",""), folder);
				}
				else if(W_L) {
					if(!thumb && !sub)
						pb = new ProcessBuilder("cmd.exe", "/c", "yt-dlp", "-f", "bv*[height="+String.valueOf(qualitySelector.getSelectedItem()).replaceFirst("p","")+"]+ba", urlField.getText(), "-o", folder+"%(title)s-"+String.valueOf(qualitySelector.getSelectedItem()).replaceFirst("p",""), "-S ext:mp4:m4a", "--force-ipv4");
					else if(thumb && !sub)
						pb = new ProcessBuilder("cmd.exe", "/c", "yt-dlp", "-f", "bv*[height="+String.valueOf(qualitySelector.getSelectedItem()).replaceFirst("p","")+"]+ba", urlField.getText(), "-o", folder+"%(title)s-"+String.valueOf(qualitySelector.getSelectedItem()).replaceFirst("p",""), "--write-thumbnail","-S ext:mp4:m4a","--force-ipv4");
					else if(!thumb && sub)
						pb = new ProcessBuilder("cmd.exe", "/c", "yt-dlp", "-f", "bv*[height="+String.valueOf(qualitySelector.getSelectedItem()).replaceFirst("p","")+"]+ba", urlField.getText(), "-o", folder+"%(title)s-"+String.valueOf(qualitySelector.getSelectedItem()).replaceFirst("p",""), "--write-subs","-S ext:mp4:m4a","--force-ipv4");
					else if(thumb && sub)
						pb = new ProcessBuilder("cmd.exe", "/c", "yt-dlp", "-f", "bv*[height="+String.valueOf(qualitySelector.getSelectedItem()).replaceFirst("p","")+"]+ba", urlField.getText(), "-o", folder+"%(title)s-"+String.valueOf(qualitySelector.getSelectedItem()).replaceFirst("p",""), "--write-thumbnail","--write-subs","-S ext:mp4:m4a","--force-ipv4");
				}
					
				Process p = null;
				try {
					p = pb.start();
				} catch (IOException e1) {}
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = null;
				int show = 0;
				try {
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
						if(line.contains("[download] Destination") && !line.contains(".f140.m4a")) 
							what='v';
						if(line.contains("[download] Destination") && line.contains(".f140.m4a")) 
							what='c';
						if(what=='v') {
							if(line.contains("[download]") && line.contains("of")) 
								loadInfo[3].setText(line.substring(line.indexOf("[download]")+11, line.indexOf("of")-2)+"% video downloaded.");
						}
						else {
							if(line.contains("[download]") && line.contains("of   ")) 
								loadInfo[3].setText(line.substring(line.indexOf("[download]")+11, line.indexOf("of")-2)+"% audio downloaded.");
						}
						if(show%10==0) 
							super.paintComponents(getGraphics());
						if(line.contains("[Merger]")) {
							loadInfo[3].setText("Merging audio and video files.");
							super.paintComponents(getGraphics());
						}
						if(line.contains("Deleting original file")) {
							loadInfo[3].setText("Video available at "+folder.replace(System.getProperty("user.home")+"/", "")+".");
							super.paintComponents(getGraphics());
						}
						if(line.contains("has already been downloaded")) {
							loadInfo[3].setText("Video has already been downloaded.");
							super.paintComponents(getGraphics());
						}
						show++;
					}
				} catch (IOException e1) {}
				butt[1].setEnabled(true);
			}
			
			if(e.getSource()==butt[2]) {
				if(butt[2].getText().equals("Restart")) {
					this.dispose();
					new MyFrame(W_L);
				}
				else {
					loadInfo[2].setVisible(false);
					internetCheck();
				}
			}
			
			if(e.getSource()==butt[4]) {
				if(s==null)
					s = new Setting(f.getAbsolutePath(), folder,theme, this.getX()+35,this.getY()+120, W_L);
			}
			if(e.getSource()==butt[3]) {
				if(i==null)
					i = new Info(f.getAbsolutePath(), this.getX()+35,this.getY()+120, W_L);
			}
	}

	@Override
	public void keyTyped(KeyEvent k) {}
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_ENTER && !urlField.getText().equals("")) {
			internetCheck();
			if(internet)
				search();
		}
	}
	@Override
	public void keyReleased(KeyEvent k) {}
}