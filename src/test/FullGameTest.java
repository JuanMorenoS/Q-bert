package test;

import static org.junit.Assert.*;

import java.awt.Color;

import aplicacion.*;
import org.junit.Assert;
import org.junit.Test;

public class FullGameTest {
	//Cube
		@Test
		public void deberiaDevolvermeElColorDadaLaCadena() {
			assertTrue(Color.black.equals(Cube.stringToColor("black")));
		}
		@Test
		public void noDeberiaDevolvermeElColorDadaLaCadena() {
			assertFalse(Color.black.equals(Cube.stringToColor("black1")));
		}
		@Test
		public void deberiaCambiarColorSiEsColoreable() {
			Cube a = new Cube(10,null);
			a.setColors(new String[]{"red","magenta","black","yellow"}, false);
			assertTrue(a.visited() && a.colors[0].equals(Cube.stringToColor("yellow")));
		}
		@Test
		public void noDeberiaCambiarColorSiNoEsColoreable() {
			Cube a = new Cube(10,null);
			a.setColors(new String[]{"red","magenta","black","yellow"}, true);
			assertFalse(a.visited());
		}
	//player
		@Test
		public void deberiaPerder() {
			Player a = new Player(200, 200, 5, 0, 0, "", 'r');
			a.lose('F');
			assertTrue(a.getLives()==2);
		}
		@Test
		public void NodeberiaPerder() {
			Player a = new Player(200, 200, 5, 0, 0, "", 'r');
			a.lose('p');
			assertFalse(a.getLives()==2);
		}


}
