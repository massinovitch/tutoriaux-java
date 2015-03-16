package com.dialog;

import javax.swing.JFrame;
 
public class MainFrame extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainFrame(){
        this.setTitle("Dialog demo");
        this.setSize(733,495);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        new Dialog(MainFrame.this,true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args){
        new MainFrame();
    }
}