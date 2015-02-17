package com.main;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.parameter.Parameter;
public class MainClass {
	
  public static void main(String[] a) throws IOException, InterruptedException {
	  
	  MainClass mainClass = new MainClass();
	  Parameter parameter = new Parameter();
	  parameter.setDriveName(System.getProperty("driveName"));
	  parameter.setFolder(System.getProperty("folderGlobal"));
	  parameter.setLogFile(System.getProperty("logFile"));
	  parameter.setUser(System.getProperty("user"));
	  parameter.setMdp(System.getProperty("mdp"));
	  mainClass.connexionLecteur(parameter);
	  String folderKdo = System.getProperty("folderKdo");
	  File dossier = new File(folderKdo);
	  File[] tousLesFichiers = dossier.listFiles();
	  for(File f : tousLesFichiers){
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		  
		  System.out.println("After Format : " + sdf.format(f.lastModified()));		  
	  }	  
  }
  
  
  //créer une classe qui contient tout les paramètres et la passer comme variable
  public void connexionLecteur (Parameter parameter) throws IOException, InterruptedException {
	  Runtime runtime = Runtime.getRuntime();
	  String commande = "NET USE " + parameter.getDriveName() + " " + parameter.getFolder() + " " + parameter.getMdp() + " /USER:" + parameter.getUser() + " > " + parameter.getLogFile() + " 2<&1";
	  String[] args = { "cmd.exe", "/C", commande };	  
	  runtime.exec(args);
	  Thread.sleep(5000);
  }
  
  public void deconnextionLecteur(String driveName) throws IOException {
	  Runtime runtime = Runtime.getRuntime();
	  String[] args = { "cmd.exe", "/C", "NET USE " + driveName + " /Delete" };
	  runtime.exec(args);	  
  }
		  
  
  
}
