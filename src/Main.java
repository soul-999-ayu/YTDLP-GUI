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

public class Main {
	public static void main(String[] args){
		//Checking the Operating System
        if (System.getProperty("os.name").toLowerCase().contains("win")) 
        	new MyFrame(true);
        else if (System.getProperty("os.name").toLowerCase().contains("nix") || System.getProperty("os.name").toLowerCase().contains("nux") || System.getProperty("os.name").toLowerCase().contains("aix"))
        	new MyFrame(false);
	}
}