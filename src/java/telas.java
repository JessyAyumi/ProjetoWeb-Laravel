
import java.io.File;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Regina Sobral
 */
public class telas {
    
     public static void main(String[] args) throws IOException {
         java.awt.Desktop.getDesktop().open( new File( "index.html" ) );
          Runtime.getRuntime().exec("start  C:/Users/Regina Sobral/Documents/GitHub/ProjetoWeb-Laravel/index.html");     
    }
   
}
