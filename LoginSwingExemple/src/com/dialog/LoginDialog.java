package com.dialog;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.properties.Configuration;

public class LoginDialog extends JDialog {
    private boolean succeeded;
	private static final long serialVersionUID = 1L;
	private static Configuration configuration = Configuration.getInstance();
	JLabel nameLabel = new JLabel(configuration.getProperty("label.name"));
	JLabel passwordLabel = new JLabel(configuration.getProperty("label.password"));
 
	JTextField nameField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
 
	JButton okButton = new JButton(configuration.getProperty("button.ok"));
	JButton cancelButton = new JButton(configuration.getProperty("button.cancel"));
	JLabel messageLabel = new JLabel();
	private GridBagConstraints gbc_1;
	private GridBagConstraints gbc_2;
	private GridBagConstraints gbc_3;
	private GridBagConstraints gbc_4;
 
	public LoginDialog(JFrame parent, boolean modal) {
		super(parent, configuration.getProperty("title.login"), true);
		succeeded = false;
		setMinimumSize(new Dimension(300, 150));
		setupUI(); 
		setUpListeners(parent);
		pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true); 
	}
 
	public void setupUI() {

 
		JPanel topPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
 
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
 
		GridBagConstraints gbc = new GridBagConstraints();
 
		gbc.insets = new Insets(4, 4, 5, 5);
 
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		topPanel.add(nameLabel, gbc);
 
		gbc_1 = new GridBagConstraints();
		gbc_1.insets = new Insets(0, 0, 5, 0);
		gbc_1.gridx = 1;
		gbc_1.gridy = 0;
		gbc_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_1.weightx = 1;
		topPanel.add(nameField, gbc_1);
 
		gbc_2 = new GridBagConstraints();
		gbc_2.insets = new Insets(0, 0, 5, 5);
		gbc_2.gridx = 0;
		gbc_2.gridy = 1;
		gbc_2.fill = GridBagConstraints.NONE;
		gbc_2.weightx = 0;
		topPanel.add(passwordLabel, gbc_2);
 
		gbc_3 = new GridBagConstraints();
		gbc_3.insets = new Insets(0, 0, 5, 0);
		gbc_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_3.gridx = 1;
		gbc_3.gridy = 1;
		gbc_3.weightx = 1;
		topPanel.add(passwordField, gbc_3);

		gbc_4 = new GridBagConstraints();
		gbc_4.insets = new Insets(0, 0, 0, 0);
		gbc_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_4.gridx = 1;
		gbc_4.gridy = 3;
		messageLabel.setVisible(false);
		topPanel.add(messageLabel, gbc_4);
		
		getContentPane().add(topPanel);
		

 
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}
 
	private void setUpListeners(JFrame parent) {
 
		passwordField.addKeyListener(new KeyAdapter() {
 
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
 
		okButton.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
 
		cancelButton.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
	}
	
	
	private void login() {
		String login = nameField.getText();
		char[] mdp = passwordField.getPassword();
		if (login == null || login.equals("") ) {
			messageLabel.setText(configuration.getProperty("message.login"));
		} else if (mdp.length == 0) {
			messageLabel.setText(configuration.getProperty("message.mdp"));
		} else {
			succeeded = true;
			dispose();
		}
		messageLabel.setVisible(true);
	}

	public boolean isSucceeded() {
		return succeeded;
	}
	
}
