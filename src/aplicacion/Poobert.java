package aplicacion;

import java.io.*;

public class Poobert{
	private  static BufferedReader in;
	public static void main(String[] args){
		try{
		in = new BufferedReader(new FileReader("<resources/Levels/1.level>"));}
		catch (Exception e) {
		}
		
	}
}
