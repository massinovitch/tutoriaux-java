package com.dialog;

import javax.swing.JFrame;

import com.properties.Configuration;

 
public class MainFrame extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Configuration configuration = Configuration.getInstance();
	
	public MainFrame(){
        this.setTitle(configuration.getProperty("title.principal"));
        this.setSize(733,495);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        LoginDialog loginDlg =  new LoginDialog(MainFrame.this,true);
        if ( !loginDlg.isSucceeded() ) {
        	this.dispose();
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args){
        new MainFrame();
    }
}