/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import frames.FrmLogon;

/**
 *
 * @author Edgar
 */
public class main {
     public static void main(String[] args) {
        new ScreenSplash().animar();

        FrmLogon a = new FrmLogon();
        a.setVisible(true);
        a.toFront();
    }
}
