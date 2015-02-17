//Package à importer afin d'utiliser l'objet File
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	/*
	 * lire un fichier avec la nouvelle api nio2
	 */
	public void readFile(String fileName) throws IOException {
		Path fichier = Paths.get(fileName);
		Charset charset = StandardCharsets.UTF_8;
        BufferedReader reader = Files.newBufferedReader(fichier, charset); 
		String line = null;
		while ((line = reader.readLine()) != null) {
		  System.out.println(line);
		}	
		reader.close();
	}
	
	/*
	 * lire un fichier avec la nouvelle api nio2 (inputstream)
	 */	
	public void readFileInputStream(String fileName) throws IOException {
		  Path path = Paths.get(fileName);
		  InputStream in = Files.newInputStream(path);
		  BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		  String line = null;
		  while ((line = reader.readLine()) != null) {
		      System.out.println(line);
		  }
		  reader.close();
	}
	
	/*
	 * ecrire dans un fichier avec api nio2
	 */
	public void writeFile(String fileName) throws IOException {
		Path fichier = Paths.get(fileName);
		Charset charset = StandardCharsets.UTF_8;
		String contenu = "Contenu du fichier";
		BufferedWriter writer = Files.newBufferedWriter(fichier, charset);
		writer.write(contenu, 0, contenu.length());
		writer.close();
	}
	
	/*
	 * ecrire dans un fichier avec la nouvelle api nio2 (OutputStream)
	 */		
    public void writeFileOutputStream(String fileName) throws IOException  {
        Path rn_demo = Paths.get(fileName);
        String demo = "tutorial";
        //using NIO.2 unbuffered stream
        byte data[] = demo.getBytes();
        OutputStream outputStream = Files.newOutputStream(rn_demo);
        outputStream.write(data);
        outputStream.close();
    }}
