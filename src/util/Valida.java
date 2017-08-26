package util;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author PabloAntonio
 */
public class Valida {
    public void SoloLetras(JTextField jt){
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(
                   ((int)c==32) || //32 es para la barra espaciadora      
                    (Character.isLetter(c))                  
                  )                                    
                {}
                else 
                {
                    e.consume();
                }
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }               
    }
    
    public void LetrasSinEspacios(final JTextField jt){ //Para las siglas en Universidad
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(                    
                    (c>='A' && c<='Z') ||
                    (c=='Ñ')           ||
                    (c>='a' && c<='z') ||
                    (c=='ñ') ||
                    (c=='-')
                   )
                {
                      e.setKeyChar(Character.toUpperCase(c));
                } 
                else 
                {
                        e.consume();
                }                
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }               
    }
    
    public void LetrasNumeros(final JTextField jt){
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();          
                if (
                    ((int)c=='-')|| 
                    (Character.isDigit(c)) || //Valores de 0-9
                    (c>='A' && c<='Z') ||
                    (c=='Ñ')           ||
                    (c>='a' && c<='z') ||
                    (c=='ñ')
                   ){                                                   
                    e.setKeyChar(Character.toUpperCase(c));
                }
                else {
                    e.consume();
                }
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }               
    }
    
    public void LetrasNumerosUsuario(final JTextField jt){
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();          
                if (
                    ((int)c=='-')|| 
                    (Character.isDigit(c)) || //Valores de 0-9
                    (c>='A' && c<='Z') ||
                    (c=='Ñ')           ||
                    (c>='a' && c<='z') ||
                    (c=='ñ')
                   ){                                                                       
                }
                else {
                    e.consume();
                }
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }               
    }
    
    public void LetrasNumerosEspacio(final JTextField jt){
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();          
                if ((int)c==32 ||
                    ((int)c=='-')||
                    ((int)c=='/')||
                    ((int)c=='_')||
                    (Character.isDigit(c)) || //Valores de 0-9
                    (c>='A' && c<='Z') ||
                    (c=='Ñ')           ||
                    (c>='a' && c<='z') ||
                    (c=='ñ')
                   ){                                                                       
                }
                else {
                    e.consume();
                }
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }               
    }
    
    public void SoloNumerosNota(final JTextField jt){
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if (
                     (Character.isDigit(c))||
                     c=='.'
                    )
                {
                    if (c=='.' && jt.getText().indexOf('.')!=-1)
                    {
                        e.consume();
                    }
                    else {}                    
                } 
                else
                {
                    e.consume();
                }
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }
    }
    
    
    
public void SoloNumerosCelular(final JTextField jt){
    try{
        jt.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e){
            char c = e.getKeyChar();
            if (Character.isDigit(c))
            {} 
            else
            {
                e.consume();
            }
        }
    });
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error en la validación " + e);
    }
}

public void SeleccionarTodo(final JTextField jt){  
    try{
        jt.addFocusListener(new FocusAdapter() {                        
        @Override
        public void focusGained(FocusEvent e){
            jt.selectAll();
        }                
    });
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error en la validación " + e);
    }               
}
    
//    public void email(JTextField jt){
//        try{
//            jt.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e){
//                char c = e.getKeyChar();
//                if(Character.isSpaceChar(c)){                    
//                    e.consume();
//                }
//            }
//        });
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
//        }               
//    }
    
    //"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z]{2,}){1}$)"
//    public void esEmail(String jt) {
//   
//        String input = jt;
//        // comprueba que no empieze por punto o @
//        Pattern p = Pattern.compile("^.|^@");
//        Matcher m = p.matcher(input);
//        try{            
//            if (m.find()){                
//                JOptionPane.showMessageDialog(null,"Las direcciones email no empiezan por punto o @");
//            }
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
//        }
//
//        // comprueba que no empieze por www.
//        p = Pattern.compile("^www.");
//        m = p.matcher(input);
//        try {
//            if (m.find())
//                JOptionPane.showMessageDialog(null,"Los emails no empiezan por www");
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
//        }        
//
//        // comprueba que contenga @
//        p = Pattern.compile("@");
//        m = p.matcher(input);
//        try {
//            if (!m.find())
//                JOptionPane.showMessageDialog(null,"La cadena no tiene arroba");         
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
//        }
//        
//        // comprueba que no contenga caracteres prohibidos	
//        p = Pattern.compile("[^A-Za-z0-9.@_-~#]+");
//        m = p.matcher(input);
//        try {
//            StringBuffer sb = new StringBuffer();
//            boolean resultado = m.find();
//            boolean caracteresIlegales = false;
//
//            while(resultado) {
//                caracteresIlegales = true;
//                m.appendReplacement(sb, "");
//                resultado = m.find();
//            }
//            
//            m.appendTail(sb);
//
//            //input = sb.toString();
//
//            if (caracteresIlegales) {
//                JOptionPane.showMessageDialog(null, "La cadena contiene caracteres ilegales");
//        }
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
//        }
//        
//        // Añade el ultimo segmento de la entrada a la cadena
//        
//   }
}
